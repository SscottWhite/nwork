package com.example.demo.otherstool.dto;

/**
 * @ClassName: BsdYunConstants
 * @Description: TODO 波司登云常量类
 * @author Chen Xinjie chenxinjie@bosideng.com
 * @date 2018年1月6日 下午3:42:30
 * 
 */
public class BsdYunConstants {

	public static String tokenTimeOut; // 令牌超时时间
	public static String drdsAotuNo;
	public static String openApiAddress; //波司登开放平台地址
	public static String openApiToken;	//波司登开放平台token
	public static String ecomOpenApiAddress; //雪冰开放平台地址
	
	public static String ecomOpenApiToken;	//雪冰开放平台token
	

	public static String omsRedirectUrl; //平台回调地址
	public static String omsReactDomain; //oms的前端域名注意最后要带上/(斜线)
	public static String serverIp; //modified note: 御城河定时任务记录订单日志的时候需要用服务器端的IP wangzh 2018-06-06 10:12
	public static String vipServerUrl; //vip服务器地址




	//modified note: 新增api错误忽略配置 wangzh 2018-05-11 14:23
	public static String apiErrorsTaobaoIgnore;
	public static String apiErrorsJdIgnore;
	public static String apiErrorsVipIgnore;
	public static String apiErrorsCnIgnore;
	public static String apiErrorsFengQiaoIgnore;
	
	public static String topicOmOrderCheck;
	public static String topicDeliverySync;
	//modified note: 存储wm处理日志的主题 wangzh 2018-06-04 09:49
	public static String topicSaveWmOperateLogs;
	public static String topicToExecute;

	public static String taobaoSafeCode;
	public static String taobaoSafeShopNo;



	


	public static String getTaobaoSafeShopNo() {
		return taobaoSafeShopNo;
	}

	public static void setTaobaoSafeShopNo(String taobaoSafeShopNo) {
		BsdYunConstants.taobaoSafeShopNo = taobaoSafeShopNo;
	}


	public static final Byte STATUS_ENABILE = new Byte("1"); //启用
	public static final Byte STATUS_DISABLE = new Byte("0"); //禁用
	
	public static final Long DEFAULT_IMAGE_GROUP_ID = new Long("1020"); //电商图片默认分组

	public static final String AUTHORIZATION = "Authorization"; //header请求的token Key
	//TOKEN秘钥Key
	public static final String REDIS_TOKEN_KEY = "authorization:open:token:secretkey";
	// redis 存储第三方appid token
	public static final String REDIS_AUTH_APPID_TOKEN = "authorization:open:appid:";
	//redis存储每分钟允许最大访问次数
	public static final String REDIS_AUTH_MAX_ALLOW_TIMES = "authorization:open:maxallowtimes:";
	//redis存储每分钟允许最大访问次数
	public static final String REDIS_OPEN_PERMISSION = "authorization:open:permission:";
	
	
	//存放Google验证码redis地址
	public static final String GOOGLE_LOGIN_CAPTCHA = "authorization:bsd:captcha:";
	
	//存放黑名单列表
	public static final String LOGIN_BLACKLIST = "authorization:bsd:blacklist:";
	
	//企业配置参数编号
	public static final String JWT_TOKEN = "JWT_TOKEN";
	public static final String FX_LEVEL_MAX = "FX_LEVEL_MAX";//分销商最大层级
	public static final String FX_ACCOUNT_ROLE_NO = "FX_ACCOUNT_ROLE_NO";//分销商账户默认角色
	public static final String OMS_ORDER_CHECK_CAL_EXPRESS = "OMS_ORDER_CHECK_CAL_EXPRESS";//订单审核时自动计算快递
	public static final String OMS_ORDER_DOWN_STATUS = "OMS_ORDER_DOWN_STATUS";//下载哪些状态的订单
	public static final String WMS_DELICACY_MAN = "WMS_DELICACY_MAN";//是否开启精细化管理
	public static final String OMS_STOCK_SYNC_TYPE = "OMS_STOCK_SYNC_TYPE";//往线上同步库存选项
	public static final String OMS_DF_ORDER_REPEAT_CHARGE = "OMS_DF_ORDER_REPEAT_CHARGE";//货到付款订单，判断重复货到付款订单
	public static final String SYS_ANDROID_EWM = "SYS_ANDROID_EWM";//安卓PDA的下载二维码
	public static final String DEFAULT_SUPPLIER_NO = "DEFAULT_SUPPLIER_NO";//供应商编号必须存在并有效
	public static final String WMS_SOWING_MARK = "WMS_SOWING_MARK";//播种通道设置
	public static final String VIP_STORAGE_AREA_NO = "VIP_STORAGE_AREA_NO"; //唯品会专属区域号
	//quartz时间参数
	public static final String QUARTZ_RUN_PRODUCT_TIME = "QUARTZ_RUN_PRODUCT_TIME";//同步商品开始时间
	public static final String QUARTZ_RUN_STORE_TIME = "QUARTZ_RUN_STORE_TIME";//同步店铺开始时间
	public static final String QUARTZ_RUN_BARCODE_TIME = "QUARTZ_RUN_BARCODE_TIME";//同步条码开始时间
	public static final String QUARTZ_RUN_CASECODE_TIME = "QUARTZ_RUN_CASECODE_TIME";//同步箱码开始时间
	
	//品牌商管理员角色
	public static final Integer SYSTEM_ADMIN_ROLE_NO = 41;
	
	//菜单类型常量(0: 电脑; 1: PDA)
	public static final Byte MENU_TYPE_PC = new Byte("0");
	public static final Byte MENU_TYPE_PDA = new Byte("1");
	
	//电商平台单据号生成规则类型
	public static final String BILL_NO_ECOMS = "ECOMS"; //线上订单号
	public static final String BILL_NO_ECOMX = "ECOMX"; //支付订单号
	
	//开放平台接口URL
	public static final String OPEN_API_URL_APPLY_TOKEN = "/sym/applyAccessToken";	//注册用户URL
	public static final String OPEN_API_URL_REGISTER_USER = "/center/user/registerUser";	//注册用户URL
	public static final String OPEN_API_URL_MODIFY_USER = "/center/user/modifyUser";	//修改用户URL
	public static final String OPEN_API_URL_M_PWD_USER = "/center/user/modifyPassword";	//修改密码用户URL
	public static final String OPEN_API_URL_GET_USER = "/center/user/getUser";	//获取用户URL
	public static final String OPEN_API_URL_FORCE_PWD_USER = "/center/user/forcePassword";	//用户密码初始化
	public static final String OPEN_API_URL_ENABILE_USER = "/center/user/enableUser";	//用户激活
	public static final String OPEN_API_URL_DISABLE_USER = "/center/user/disableUser";	//用户失效
	public static final String OPEN_API_SEND_DING_MSG = "/crm/messages/sendDDMessages"; 	//钉钉推送消息de


	public static final String REST_PASSWORD = "bsd123456";
	
	//内部接口url
	public static final String PRODUCT_SYNC_URL = "/center/product/getProduct";
	public static final String SELF_STORE_SYNC_URL = "/center/store/getSelfStore";
	public static final String FC_STORE_SYNC_URL = "/center/store/getFcStore";
	public static final String BARCODE_SYNC_URL = "/center/product/barcode";//条码请求地址
	public static final String BARCODE_DIRECT_URL = "/center/product/getBarcode";//获取指定条码请求地址
	public static final String CASECODE_DIRECT_URL =  "/center/product/getCasecode"; //获取指定箱码

	public static final String CASECODE_SYNC_URL = "/center/product/casecode";//箱码请求地址
	public static final String UPLOADTO_AFSANDSCM_URL = "/scm/purchase/addToAfsAndScm";//上传采购单据到AFS和SCM

	public static final String O2O_REDUCE_LOCK_URL = "/center/stock/store/reduceO2OLock";//pos接单解锁
	public static final String SYNC_STORE_STOCKS_URL = "/center/stock/store/getStoreStocksBySku";//同步pos库存
	public static final String SYNC_STORE_SAFE_STOCKS_URL = "/center/stock/store/getBdScmSafetyStock";//同步pos安全库存
	public static final String SYNC_STORE_ADD_LOCK ="/center/stock/store/addO2OLock"; //pos 增加O2O线上订单锁定库存
	public static final String SYNC_ORDER_CHECK_RESULT = "/center/order/updateSaleOrderStatus";  //pos 订单的审核结果通知
	public static final String SYNC_ORDER_DELIVERY_RESULT = "/center/order/addSaleOrderExt";  //pos 订单的发货后通知

	
	public static final String EXCEPTION = "EXCE";
	
	public static String getTokenTimeOut() {
		return tokenTimeOut;
	}

	public static void setTokenTimeOut(String tokenTimeOut) {
		BsdYunConstants.tokenTimeOut = tokenTimeOut;
	}
	
	public static String getOpenApiAddress() {
		return openApiAddress;
	}

	public static void setOpenApiAddress(String openApiAddress) {
		BsdYunConstants.openApiAddress = openApiAddress;
	}

	public static String getOpenApiToken() {
		return openApiToken;
	}

	public static void setOpenApiToken(String openApiToken) {
		BsdYunConstants.openApiToken = openApiToken;
	}

	public static String getOmsRedirectUrl() {
		return omsRedirectUrl;
	}

	public static void setOmsRedirectUrl(String omsRedirectUrl) {
		BsdYunConstants.omsRedirectUrl = omsRedirectUrl;
	}
	
	//modified note: xxx wangzh 2018-05-11 14:26
	public static String getApiErrorsTaobaoIgnore() {
		return apiErrorsTaobaoIgnore;
	}

	public static void setApiErrorsTaobaoIgnore(String apiErrorsTaobaoIgnore) {
		BsdYunConstants.apiErrorsTaobaoIgnore = apiErrorsTaobaoIgnore;
	}

	public static String getApiErrorsJdIgnore() {
		return apiErrorsJdIgnore;
	}

	public static void setApiErrorsJdIgnore(String apiErrorsJdIgnore) {
		BsdYunConstants.apiErrorsJdIgnore = apiErrorsJdIgnore;
	}

	public static String getApiErrorsVipIgnore() {
		return apiErrorsVipIgnore;
	}

	public static void setApiErrorsVipIgnore(String apiErrorsVipIgnore) {
		BsdYunConstants.apiErrorsVipIgnore = apiErrorsVipIgnore;
	}

	public static String getApiErrorsCnIgnore() {
		return apiErrorsCnIgnore;
	}

	public static void setApiErrorsCnIgnore(String apiErrorsCnIgnore) {
		BsdYunConstants.apiErrorsCnIgnore = apiErrorsCnIgnore;
	}

	public static String getDrdsAotuNo() {
		return drdsAotuNo;
	}

	public static void setDrdsAotuNo(String drdsAotuNo) {
		BsdYunConstants.drdsAotuNo = drdsAotuNo;
	}

	public static String getApiErrorsFengQiaoIgnore() {
		return apiErrorsFengQiaoIgnore;
	}

	public static void setApiErrorsFengQiaoIgnore(String apiErrorsFengQiaoIgnore) {
		BsdYunConstants.apiErrorsFengQiaoIgnore = apiErrorsFengQiaoIgnore;
	}
	
	public static String getTopicOmOrderCheck() {
		return topicOmOrderCheck;
	}

	public static void setTopicOmOrderCheck(String topicOmOrderCheck) {
		BsdYunConstants.topicOmOrderCheck = topicOmOrderCheck;
	}

	public static String getTopicDeliverySync() {
		return topicDeliverySync;
	}

	public static void setTopicDeliverySync(String topicDeliverySync) {
		BsdYunConstants.topicDeliverySync = topicDeliverySync;
	}
	

	public static String getOmsReactDomain() {
		return omsReactDomain;
	}

	public static void setOmsReactDomain(String omsReactDomain) {
		BsdYunConstants.omsReactDomain = omsReactDomain;
	}
	
	public static String getTopicSaveWmOperateLogs() {
		return topicSaveWmOperateLogs;
	}

	public static void setTopicSaveWmOperateLogs(String topicSaveWmOperateLogs) {
		BsdYunConstants.topicSaveWmOperateLogs = topicSaveWmOperateLogs;
	}
	public static String getEcomOpenApiAddress() {
		return ecomOpenApiAddress;
	}

	public static void setEcomOpenApiAddress(String ecomOpenApiAddress) {
		BsdYunConstants.ecomOpenApiAddress = ecomOpenApiAddress;
	}
	public static String getEcomOpenApiToken() {
		return ecomOpenApiToken;
	}

	public static void setEcomOpenApiToken(String ecomOpenApiToken) {
		BsdYunConstants.ecomOpenApiToken = ecomOpenApiToken;
	}


	public static String systemType; //当前系统类型

	public static String getSystemType() {
		return systemType;
	}

	public static void setSystemType(String systemType) {
		BsdYunConstants.systemType = systemType;
	}

	public static String getServerIp() {
		return serverIp;
	}

	public static void setServerIp(String serverIp) {
		BsdYunConstants.serverIp = serverIp;
	}
	
	public static String getTopicToExecute() {
		return topicToExecute;
	}

	public static void setTopicToExecute(String topicToExecute) {
		BsdYunConstants.topicToExecute = topicToExecute;
	}
	

	public static String getVipServerUrl() {
		return vipServerUrl;
	}

	public static void setVipServerUrl(String vipServerUrl) {
		BsdYunConstants.vipServerUrl = vipServerUrl;
	}



	public static String getTaobaoSafeCode() {
		return taobaoSafeCode;
	}

	public static void setTaobaoSafeCode(String taobaoSafeCode) {
		BsdYunConstants.taobaoSafeCode = taobaoSafeCode;
	}
}
