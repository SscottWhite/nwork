package com.example.demo.otherstool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.BsdYunConstants;
import com.example.demo.utils.EcomResultCode;
import com.example.demo.utils.EcomResultDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

@Slf4j
public class HttpUtil {
	
	/**
	 * @Description: 创建token 
	 * @param: @param appId
	 * @param: @param appKey
	 * @param: @return      
	 * @return: EcomResultDO<Object>    
	 * @author: james  
	 * @throws
	 */
	public static EcomResultDO<Object> createToken(String appId, String appKey){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("appId", "james");
		jsonObj.put("appKey", "123456");
		
		String url = BsdYunConstants.openApiAddress+"/applyAccessToken";
		
		EcomResultDO<Object> result = null;
	    HttpPost post = null;
	    HttpClient httpClient = HttpClients.createDefault();
	    try {
	        post = new HttpPost(url);
	        // 构造消息头
	        post.setHeader("Content-type", "application/json; charset=utf-8");
	                    
	        // 构建消息实体
	        StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
	        entity.setContentEncoding("UTF-8");
	        // 发送Json格式的数据请求
 	        entity.setContentType("application/json");
	        post.setEntity(entity);
	            
	        HttpResponse response = httpClient.execute(post);
	        
	            
	        // 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        if(statusCode == HttpStatus.SC_OK){
	        	HttpEntity he = response.getEntity();
	        	String respContent = EntityUtils.toString(he,"UTF-8");
	        	JSONObject json = JSONObject.parseObject(respContent);
	        	result = JSON.toJavaObject(json, EcomResultDO.class);
	        	return result;
	        }else{
	        	result = new EcomResultDO<Object>(EcomResultCode.COMMON_FAIL, "HTTP POST 请求异常错误码["+statusCode+"]", false);
	        	return result;
	        }
	    } catch (Exception e) {
	        log.error(e.getMessage());
	        result = new EcomResultDO<Object>(EcomResultCode.COMMON_FAIL, e.getMessage(), false);
        	return result;
	    }
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param jsonObj
	 * @param: @param url
	 * @param: @param token
	 * @param: @return      
	 * @return: EcomResultDO<Object>    
	 * @author: james  
	 * @throws
	 */
	public static EcomResultDO<Object> httpPost(String jsonStr,String url,String token){
	    EcomResultDO<Object> result = null;
	    HttpPost post = null;
		log.info("----------开始访问------------");
	    HttpClient httpClient = HttpClients.createDefault();
	    String remoteUrl = BsdYunConstants.openApiAddress+url;
		log.info(remoteUrl);
	    try {
	        post = new HttpPost(remoteUrl);
	        // 构造消息头
	        post.setHeader("Content-type", "application/json; charset=utf-8");
	        post.setHeader("Authorization", "Basic "+token);
	                    
	        // 构建消息实体
	        StringEntity entity = new StringEntity(jsonStr, Charset.forName("UTF-8"));
	        entity.setContentEncoding("UTF-8");
	        // 发送Json格式的数据请求
 	        entity.setContentType("application/json");
	        post.setEntity(entity);
	            
	        HttpResponse response = httpClient.execute(post);

			log.info(FastJsonUtil.toJsonString(response));
	            
	        // 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        if(statusCode == HttpStatus.SC_OK){
	        	HttpEntity he = response.getEntity();
	        	String respContent = EntityUtils.toString(he,"UTF-8");
	        	JSONObject json = JSONObject.parseObject(respContent);
	        	result = JSON.toJavaObject(json, EcomResultDO.class);
	        	return result;
	        }else{
	        	result = new EcomResultDO<Object>(EcomResultCode.COMMON_FAIL, "HTTP POST 请求异常错误码["+statusCode+"]", false);
	        	return result;
	        }
	    } catch (Exception e) {
	        log.error(e.getMessage());
	        result = new EcomResultDO<Object>(EcomResultCode.COMMON_FAIL, e.getMessage(), false);
        	return result;
	    }
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param jsonObj
	 * @param: @param url
	 * @param: @param token
	 * @param: @return      
	 * @return: EcomResultDO<Object>    
	 * @author: james  
	 * @throws
	 */
	public static EcomResultDO<Object> httpPost(String jsonStr,String apiAddress, String url,String token){
	    EcomResultDO<Object> result = null;
	    HttpPost post = null;
	    HttpClient httpClient = HttpClients.createDefault();
	    String remoteUrl = apiAddress+url;
	    try {
	        post = new HttpPost(remoteUrl);
	        // 构造消息头
	        post.setHeader("Content-type", "application/json; charset=utf-8");
	        post.setHeader("Authorization", "Basic "+token);
	                    
	        // 构建消息实体
	        StringEntity entity = new StringEntity(jsonStr, Charset.forName("UTF-8"));
	        entity.setContentEncoding("UTF-8");
	        // 发送Json格式的数据请求
 	        entity.setContentType("application/json");
	        post.setEntity(entity);
	            
	        HttpResponse response = httpClient.execute(post);
	        
	         System.out.println("==========httpUtil=remoteUrl:"+remoteUrl+"=================");
	         System.out.println("==========httpUtil=url:"+url+"=================");
	         System.out.println("==========httpUtil=token:"+token+"=================");
	         System.out.println("==========json: "+jsonStr+" =================");
	        // 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        if(statusCode == HttpStatus.SC_OK){
	        	HttpEntity he = response.getEntity();
	        	String respContent = EntityUtils.toString(he,"UTF-8");
	        	JSONObject json = JSONObject.parseObject(respContent);
	        	result = JSON.toJavaObject(json, EcomResultDO.class);
	        	return result;
	        }else{
	        	result = new EcomResultDO<Object>(EcomResultCode.COMMON_FAIL, "HTTP POST 请求异常错误码["+statusCode+"]", false);
	        	return result;
	        }
	    } catch (Exception e) {
	        log.error(e.getMessage());
	        result = new EcomResultDO<Object>(EcomResultCode.COMMON_FAIL, e.getMessage(), false);
        	return result;
	    }
	}
	
	public static void main(String[] args) {
//		JSONObject jsonObj1 = new JSONObject();
//		jsonObj1.put("appId", "james");
//		jsonObj1.put("appKey", "123456");
//		String url1 = "http://localhost:8099/sym/applyAccessToken";
//		EcomResultDO<Object> result1 = createToken("","", url1);
//		JSONObject tokenJson =  (JSONObject)result1.getData();
//		String token  = tokenJson.getString("token");
		
		
		String json = "{\"createDate\":1522328067822,\"gender\":\"SECRET\",\"mobileNo\":\"13992813191\",\"nickName\":\"james1111\",\"password\":\"tzq123456\",\"userName\":\"田志强\",\"userNo\":\"james1111\",\"userType\":1,\"validFlag\":\"Y\"}";
		String url = "//center/user/registerUser";
		EcomResultDO<Object> result2 = httpPost(json, url, "eyJhbGciOiJIUzI1NiIsInR5cGUiOiJKV1QifQ.eyJqdGkiOiIxNTIyMzcyNTIxMzU4NDc4YmRhNDlhNmEyNGNkMGI2MzQ3MDg0NWMyYzFhNDYiLCJzdWIiOiJ7XCJhcHBJZFwiOlwiZWNvbVwiLFwiYXV0aG9yaXplQWRkcmVzc1wiOlwiMTI3LjAuMC4xXCIsXCJhdXRob3JpemVTdGF0dXNcIjp0cnVlLFwiYXV0aG9yaXplVHlwZVwiOlwiMFwiLFwiYXV0b05vXCI6MSxcImNyZWF0ZVRpbWVcIjoxNTIxNjIwNDM5MDAwLFwiY3JlYXRlVXNlclwiOlwiamFtZXNcIixcImV4cGlyZVwiOjUyNTYwMDAwMCxcIm1heEFsbG93ZWRUaW1lc1wiOjYwLFwibWVyY2hhbnRDb2RlXCI6XCJFQ09NXCIsXCJwZXJtaXNzaW9uTGlzdFwiOlwiMyw0LDEsMiw1XCIsXCJwbGF0Zm9ybU5hbWVcIjpcIumbquWGsOeUteWVhuW5s-WPsFwiLFwicGxhdGZvcm1TaG9ydE5hbWVcIjpcIumbquWGsOeUteWVhlwiLFwidHNcIjoxNTIxNjIwNTUxMDAwLFwidXBkYXRlVGltZVwiOjE1MjE2MjA0NDUwMDAsXCJ1cGRhdGVVc2VyXCI6XCJqYW1lc1wifSIsImlzcyI6IjEyNy4wLjAuMSIsImlhdCI6MTUyMjM3MjUyMSwiZXhwIjoyMDQ3OTcyNTIxfQ.GW3eMANkODHG2LlgwKA0FJWT_4aeyQsmeLSul-e2nGA");
		System.out.println(result2);

	}

}
