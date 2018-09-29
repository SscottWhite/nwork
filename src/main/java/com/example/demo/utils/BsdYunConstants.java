package com.example.demo.utils;


public class BsdYunConstants {

	public static String tokenTimeOut;

	public static String openApiAddress = "http://172.16.17.159:8080";


	//TOKEN秘钥Key
	public static final String REDIS_TOKEN_KEY = "authorization:open:token:secretkey";
	// redis 存储第三方appid token
	public static final String REDIS_AUTH_APPID_TOKEN = "authorization:open:appid:";
	//redis存储每分钟允许最大访问次数
	public static final String REDIS_AUTH_MAX_ALLOW_TIMES = "authorization:open:maxallowtimes:";

	
	public static String getTokenTimeOut() {
		return tokenTimeOut;
	}


}
