package com.example.demo.otherstool;

import com.alibaba.fastjson.JSON;
import com.example.demo.otherstool.dto.ApiAuthorizeDTO;
import com.example.demo.otherstool.dto.JwtDto;
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

@Slf4j
@Component
public class OpenJwtUtil {

	/**
	 * @Description: 由字符串生成加密key
	 * @param: @return      
	 * @return: SecretKey
	 * @author: james  
	 * @throws
	 */
	public static SecretKey generalKey(){
		//秘钥
		String secretKey = JedisUtil.getString(BsdYunConstants.REDIS_TOKEN_KEY);
		//数据库后台
		byte[] encodedKey = Base64Utils.encode(secretKey.getBytes());
	    SecretKey key = new SecretKeySpec(encodedKey,0,encodedKey.length, "AES");
	    return key;
	}
	
	/**
	 * 
	 * @Description: 创建JWT Token,以下为JWT参数
	 * @param: @param expire  token超时时间
	 * @param: @param subject
	 * @param: @param host
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Map<String,Object>    
	 * @author: james  
	 * @throws
	 */
	public static Map<String, Object> createToken(Integer expire, String subject,  String host) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		SecretKey key = generalKey();
		Date issuedAt = new Date();
		//过期时间设定--在checktoken的时候,如果jwt过期了,是解析通不过的
		Integer tmpExpire = expire;
		if(expire==0) {
			tmpExpire =  365 * 24 * 60 * 60;
		}else {
			tmpExpire = expire * 24 * 60 * 60;
		}
		Date expiration = plusSecond(tmpExpire);
		
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
	 * @return: ResultDO<Object>    
	 * @author: james  
	 * @throws
	 */
	public static EcomResultDO<Object> checkToken(String token, String host, String url) throws Authorize401Exception {
		Claims claims = null;
		try {
			//解析token 对象
			claims = parseJWT(token);
			String jsonUser = claims.getSubject();
			//System.out.println(jsonUser);
			ApiAuthorizeDTO authDTO = FastJsonUtil.toObject(jsonUser, ApiAuthorizeDTO.class);
			
			//region 未启用url访问权限限制
			//boolean urlValidFlag = false;
			//校验URL是否被
			/*
			String permissions = authDTO.getPermissionList();
			if (StringUtils.isEmpty(permissions)){
				return new EcomResultDO<>(EcomResultCode.URL_PERMISSION_FAILD, "URL["+host+"]访问未经许可无法访问！", false);
			}else{
				String[] permissionArray = StringUtil.splitByComma(permissions);
				for(int i = 0; i < permissionArray.length; i++){
					String permissionNo = permissionArray[i];
					String perUrl = (String)JedisUtil.get(BsdYunConstants.REDIS_OPEN_PERMISSION+permissionNo);
					String strPase = url.substring(0, 2);
					if (strPase.equals("//")){
						url = url.substring(1, url.length());
					}
					String urlSplit = url.replaceFirst("/bsdyun-open-api", "");
					
					if (urlSplit.equals(perUrl)){
						urlValidFlag = true;
						break;
					}
				}
			}
			
			if (!urlValidFlag){
				return new EcomResultDO<>(EcomResultCode.URL_PERMISSION_FAILD, "URL["+host+"]访问未经许可无法访问！", false);
			}
			
			*/
			//endregion
			
			//校验用户请求是否每分钟超过MaxAllowedTimes次请求
			String appId = authDTO.getAppNo();
			boolean allowFlag = JedisUtil.ifAllowed(BsdYunConstants.REDIS_AUTH_MAX_ALLOW_TIMES+appId, 1000000); //authDTO.getMaxAllowedTimes()
			if (allowFlag != true){
				return new EcomResultDO<Object>(EcomResultCode.OVER_MAX_REQUEST, "["+host+"]请求异常,您存在恶意攻击行为！", false);
			}
			
			//校验是否开启外部系统IP系统认证
			//todo:等启用的时候在放开这段代码
			/*
			if (authDTO.getEnableAuthAddress().equals("Y")){
				//校验IP地址或域名是否为允许访问地址
				if (!host.equals(authDTO.getAuthorizeAddress())){
					return new EcomResultDO<Object>(EcomResultCode.CLINET_ADDRESS_UNAUTHORIZE, false);
				}
			}
			*/
			
			//解析用户登录redis后的jwt对象(JWTDTO对象存在redis)
			String redisValue = JedisUtil.getString(BsdYunConstants.REDIS_AUTH_APPID_TOKEN + authDTO.getAppNo());
			if (CheckUtil.isEmpty(redisValue)){
				return new EcomResultDO<Object>(EcomResultCode.TOKEN_IS_NULL, false);
			}
			JwtDto jwt = JSON.parseObject(redisValue, JwtDto.class);
			boolean flag = validateJwt(claims, jwt);
			if (flag){
				EcomResultDO<Object> result = new EcomResultDO<>(EcomResultCode.TRUE, true);
				
				result.setData(authDTO);
				return result;
				
			}else{
				return new EcomResultDO<>(EcomResultCode.TOKEN_CHECK_FAIL, false);
			}
		} catch (Exception e) {
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
	private static boolean validateJwt(Claims claims, JwtDto jwt) throws Exception{
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
