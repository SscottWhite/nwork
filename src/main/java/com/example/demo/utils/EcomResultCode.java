package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BaseResultCode
 * @Description: TODO 基础返回码
 * @author Chen Xinjie chenxinjie@bosideng.com
 * @date 2016年3月11日 上午10:14:38
 * 
 */
public abstract class EcomResultCode {

	public static Map<String, String> map = new HashMap<String, String>();

	public final static String TRUE = "0000";
	public final static String COMMON_FAIL = "0001";
/*	public final static String OMS_SYM_DICTIONARY_DATA_REPEAT_DEL = "0002";
	public final static String OMS_SYM_DICTIONARY_NO_REPEAT = "0003";
	public final static String OMS_SYM_DICTIONARY_NAME_REPEAT = "0004";*/
	public final static String IMAGE_SIZE_ERR = "0005";
	public final static String FILE_FORMAT_NULL = "0006";
	public final static String PICTURE_DISK_DIRECTORY_NOT_EXIST = "0007";
	
	public final static String TOKEN_IS_NULL = "0008";
	
	public final static String TOKEN_TIME_OUT = "0009";
	
	public final static String TOKEN_CREATE_FAIL = "0010";
	
	public final static String TOKEN_CHECK_FAIL = "0011";
	
	public final static String TOKEN_PARSE_FAIL = "0012";
	
	public final static String ADMIN_URL_FAILD = "0013";
	
	public final static String USER_IS_INVALID = "0014";
	
	public final static String COMPANY_IS_DISABLE = "0015";
	
	public final static String DISTRIBUTOR_IS_DISABLE = "0016";
	
	public final static String DISTRIBUTOR_FREEZE = "0017";
	
	public final static String LOGIN_PARAM_IS_NULL = "0099";

	public static final String URL_PERMISSION_FAILD = "0024";

	public static final String OVER_MAX_REQUEST = "0021";

	public final static String CLINET_ADDRESS_UNAUTHORIZE = "0022";
	
	static {
		map.put(TRUE, "操作成功！");
		map.put(COMMON_FAIL, "操作失败，请重试操作或联系客服人员！");
/*		map.put(OMS_SYM_DICTIONARY_DATA_REPEAT_DEL, "数据已被删除，请勿重复删除！");
		map.put(OMS_SYM_DICTIONARY_NO_REPEAT, "自主编号重复！");
		map.put(OMS_SYM_DICTIONARY_NAME_REPEAT, "自主名称重复！");*/
		map.put(IMAGE_SIZE_ERR, "上传图片不允许超过5M！");
		map.put(FILE_FORMAT_NULL, "文件格式错误！");
		map.put(PICTURE_DISK_DIRECTORY_NOT_EXIST, "保存图片的磁盘目录不存在！");
		map.put(TOKEN_IS_NULL, "Token已失效,请重新登录！");
		map.put(TOKEN_TIME_OUT, "Token is timeout,请重新登录！");
		map.put(TOKEN_CREATE_FAIL, "创建Token失败！");
		map.put(TOKEN_CHECK_FAIL, "Token校验失败！");
		map.put(TOKEN_PARSE_FAIL, "Token解析失败！");
		map.put(ADMIN_URL_FAILD, "系统管理员无法访问！");
		map.put(USER_IS_INVALID, "当前用户已作废 ！");
		map.put(COMPANY_IS_DISABLE, "当前登录公司已被禁用 ,请联系管理员！");
		map.put(DISTRIBUTOR_IS_DISABLE, "当前用户所对应分销商已被禁用 ,请联系管理员！");
		map.put(DISTRIBUTOR_FREEZE, "分销商已被冻结 ,请联系管理员！");
		map.put(LOGIN_PARAM_IS_NULL, "用户名密码不能为空！");
		map.put(URL_PERMISSION_FAILD,"请求地址无权限");
		map.put(CLINET_ADDRESS_UNAUTHORIZE,"客户端IP未被授权访问");
	}

	public static String getValueWithKey(String key) {
		return map.get(key);
	}

}
