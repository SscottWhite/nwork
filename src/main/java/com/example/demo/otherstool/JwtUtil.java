package com.example.demo.otherstool;

import com.alibaba.fastjson.JSON;

import com.example.demo.otherstool.dto.JwtDto;
import com.example.demo.otherstool.dto.JwtUserDTO;
import com.example.demo.otherstool.exception.Authorize401Exception;
import com.example.demo.utils.BsdYunConstants;
import com.example.demo.utils.EcomResultCode;
import com.example.demo.utils.EcomResultDO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.sun.javafx.tools.packager.Log;

/**
 * @ClassName: JwtUtil
 * @Description: TODO JWT令牌工具类
 * @author james	tianzhiqiang@bosideng.com
 * @date 2018年1月3日 下午1:18:55
 *
 */
@Slf4j
@Component
public class JwtUtil {
	
	//TOKEN秘钥Key
	public static final String REDIS_TOKEN_KEY = "authorization:bsd:token:secretkey";
	// redis 存储用户token
	public final static String AUTH_USER_TOKEN = "authorization:bsd:user:";
	
	public static final String USER_TOKEN = "user:token";
	
	/**
	 * @Description: 由字符串生成加密key
	 * @param: @return      
	 * @return: SecretKey    
	 * @author: james  
	 * @throws
	 */
	public static SecretKey generalKey(){
		//秘钥
		//log.info("login-info: 获取token密匙");
		String secretKey = JedisUtil.getString(REDIS_TOKEN_KEY);
//		log.info("login-info: token密匙" + secretKey);
		//数据库后台
		byte[] encodedKey = Base64Utils.encode(secretKey.getBytes());
	    SecretKey key = new SecretKeySpec(encodedKey,0,encodedKey.length, "AES");
	    return key;
	}
	
	/**
	 * 创建JWT Token,以下为JWT参数
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> createToken(String subject,  String host) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		SecretKey key = generalKey();
		Date issuedAt = new Date();
		Date expiration = plusSecond(Integer.valueOf(BsdYunConstants.getTokenTimeOut()));
		//生成jid
		String uuid = UUID.randomUUID().toString().replace("-", "");
		uuid = issuedAt.getTime() +uuid;
		Map<String,Object> header = new HashMap<String,Object>();
		header.put("type", "JWT");
		header.put("alg", "HS256");
		JwtBuilder builder = Jwts.builder()
			.setHeader(header)
			.setId(uuid) //用户唯一标识
			.setSubject(subject)	//保存当前登录用户+公司（如果公司用户有调整则自动失效）
			.setIssuer(host)	//签发着		
			.setIssuedAt(issuedAt)	//签发时间
			.setExpiration(expiration)	//失效时间
		    .signWith(signatureAlgorithm, key);
//		JwtDto jwt = new JwtDto(header, uuid, subject, host, issuedAt,expiration);
		JwtDto jwt = new JwtDto();
		jwt.setExpiration(expiration);
		jwt.setHeader(header);
		jwt.setIssuedAt(issuedAt);
		jwt.setIssuser(host);
		jwt.setSubject(subject);
		jwt.setUuid(uuid);
		String token = builder.compact();
		map.put("token", token);
		map.put("jwt", jwt);
		return map;
	}
	
	/**
	 * @Description: Token校验
	 * @param: @param token
	 * @param: @param userNo
	 * @param: @param remoteAddress
	 * @param: @return      
	 * @return: EcomResultDO<Object>    
	 * @author: james  
	 * @throws
	 */
	public static EcomResultDO<Object> checkToken(String token, String host) throws Authorize401Exception {
		Claims claims = null;
		try {
			//解析token 对象
			claims = parseJWT(token);
			String jsonUser = claims.getSubject();
			JwtUserDTO autoUser = JSON.parseObject(jsonUser, JwtUserDTO.class);
			
			//解析用户登录redis后的jwt对象
//			Object json = JedisUtil.get(AUTH_USER_TOKEN + autoUser.getUserNo());
			String json = JedisUtil.getString(AUTH_USER_TOKEN + autoUser.getUserNo());

//			if (json == null || !CheckUtil.isEmpty(json.toString())){
			if(CheckUtil.isEmpty(json)){
				return new EcomResultDO<>(EcomResultCode.TOKEN_IS_NULL, false);
			}
			JwtDto jwt = (JwtDto) JSON.parseObject(json, JwtDto.class);
			
			boolean flag = validateJwt(claims, jwt, host);
//			log.info("login-info: 验证通过!");
			if (flag){
				EcomResultDO<Object> result = new EcomResultDO<>(EcomResultCode.TRUE, true);
				
				result.setData(autoUser);
				return result;
				
			}else{
				return new EcomResultDO<>(EcomResultCode.TOKEN_CHECK_FAIL, false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Token["+token+"] 解析失败!\n"+e.getMessage());
			return new EcomResultDO<>(EcomResultCode.TOKEN_PARSE_FAIL, false);
		}
	}
	
	/**
	 * @Description: token解密
	 * @param: @param token
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Claims    
	 * @author: james  
	 * @throws
	 */
	public static Claims parseJWT(String token) throws Exception{
		SecretKey key = generalKey();
		Claims claims = Jwts.parser()         
		   .setSigningKey(key)
		   .parseClaimsJws(token).getBody();
		return claims;
	}
	
	/**
	 * 
	 * @Description: 校验token解析后信息    
	 * @param: @param claims
	 * @param: @param jwt
	 * @param: @param host 用户登录的IP地址
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: boolean    
	 * @author: james  
	 * @throws
	 */
	private static boolean validateJwt(Claims claims, JwtDto jwt, String host) throws Exception{
		String cId = claims.getId();
		String cIssuser = claims.getIssuer();
		String cSubject = claims.getSubject();
		Date cIssueAt = claims.getIssuedAt();
		String id = jwt.getUuid();
		String issuser = jwt.getIssuser();
		String subject = jwt.getSubject();
		Date issueAt = jwt.getIssuedAt();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cIssueAtDate = df.format(cIssueAt);
		String issueAtDate = df.format(issueAt);
		
		//如果用户登录host和issuser（之前登录）IP地址不一致说明有风险，需要重新登录
		if (!host.equals(issuser)){
			log.error("用户登录的IP地址["+host+"]与登录的IP不一致,请重新登录认证！");
			return false;
		}
		if (cId.equals(id) && cIssuser.equals(issuser) && cSubject.equals(subject) && cIssueAtDate.equals(issueAtDate)){
			return true;
		}
		return false;
		
	}
	
	
	/**
     * 当前日期时间加上多少天
     * @param day 为增加的天数
     * @return
     */
    public static Date plusDay(int day){
        Date d = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.add(Calendar.DATE, day);// num为增加的分数，可以改变的
        d = ca.getTime();
        return d;
    }
	
	/**
     * 当前日期时间加上多少分钟
     * @param minute 为增加的时间分
     * @return
     */
    public static Date plusMinute(int minute){
        Date d = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.add(Calendar.MINUTE, minute);// num为增加的分数，可以改变的
        d = ca.getTime();
        return d;
    }
    
	/**
     * 当前日期时间加上多少秒
     * @param second 为增加的时间秒
     * @return
     */
    public static Date plusSecond(int second){
        Date d = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.add(Calendar.SECOND, second);// second为增加的秒数，可以改变的
        d = ca.getTime();
        return d;
    }
    
}
