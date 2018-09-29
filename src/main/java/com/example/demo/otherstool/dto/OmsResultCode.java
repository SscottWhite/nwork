package com.example.demo.otherstool.dto;


public class OmsResultCode extends EcomResultCode {

	public  static String OMS_ERROR = "0002";
	public  static String OMS_SYM_DICTIONARY_NO_REPEAT = "0003";
	public  static String OMS_SYM_DICTIONARY_NAME_REPEAT = "0004";
	public  static String OMS_SYM_BDSKU_SKUNO_REPEAT = "0005";
	public  static String OMS_SYM_BDSKU_PRODUCTNO_REPEAT = "0006";
	public  static String OMS_SYM_BDSKU_PRODUCTNAME_REPEAT = "0007";
	public  static String OMS_SYM_DICTIONARY_DATA_REPEAT_DEL = "0008";
	public  static String OMS_SYM_BDCOMBO_INSERT_ERROR = "0009";
	public  static String OMS_SYM_BDSKU_INSERT_ERROR = "0010";
	public  static String OMS_DATE_ISEMPTY = "0011";
	public  static String OMS_BASEM_STORAGE_DATE_EMTPT = "0012";
	public  static String OMS_BASEM_TOCK_NOTEMTPT = "0013";
	public  static String OMS_BASEM_SHOPNO_REPEAT = "0014";
	public  static String OMS_BASEM_SITE_NAME_OR_SHORT_NAME_REPEAT = "0015";
	public  static String OMS_PRODM_PICTURE_BE_DELETE = "0016";
	public  static String OMS_PRODM_PICTURE_SABE_FAIL = "0017";
	public  static String OMS_IMPORT_FAIL = "0018";
	public  static String OMS_PRODM_CENERALSKU_NOT_EXIST = "0019";

	public  static String OMS_PRODM_CENERALSKU_SAVE_REPEAT = "0020";
	public  static String OMS_PRODM_CENERALSKU_SAVE_FAILED = "0021";
	public  static String OMS_PRODM_CENERALSKU_SELECT_FAILED = "0022";
	public  static String OMS_PRODM_CENERALSKU_SELECT_LOCATIONNO = "0023";
	public  static String OMS_PRODM_CENERALSKU_SELECT_LOCATIONNO_SKUNO = "0024";
	public  static String OMS_PRODM_CENERALSKU_SELECT_LOCATIONNO_DELETE = "0025";
	public  static String OMS_PRODM_CENERALSKU_SELECT_LOCATIONNO_DELETEBDSTORAGELOCATION = "0026";
	
	
	/**
	 *  * @Fields OMS_BD_BRAND_NAME_REPEAT : TODO 品牌名称重复！
	 */
	public static String OMS_BD_BRAND_NAME_REPEAT = "0027";
	
	
	public  static String OMS_SYM_USER_SAVE_PICTURE_FAILED = "0028";//图片保存失败
	public  static String OMS_SYM_USER_DEL_PICTURE_FAILED = "0029";//删除原图片失败
	public  static String OMS_SYM_USER_DEL_PICTURE = "0030";//图片已被删除
	public  static String OMS_SYM_USER_CENTRE_EDIT_PICTURE = "0031";//服务中心用户表更新失败
	
	
	public  static String OMS_PRODM_CENERALSKU_REPEAT = "0050";
	public  static String OMS_DM_DATA_IS_EMPTY = "0051";
	public  static String OMS_DM_CORP_IS_EMPTY = "0052";
	
	/**
	 *  * @Fields OMS_LOCK_NAME_ERROR : 请输入锁定名称!
	 */
	public static String OMS_LOCK_NAME_ERROR = "0053";
	
	/**
	 *  * @Fields OMS_EXPIRE_UNLOCK_ERROR : 请指定过期时间!
	 */
	public static String OMS_EXPIRE_UNLOCK_ERROR = "0054";
	
	/**
	 *  * @Fields OMS_SKU_NO_ERROR : 请选择需要锁定的商品!
	 */
	public static String OMS_SKU_NO_ERROR = "0055";
	
	/**
	 *  * @Fields OMS_SHOP_SKU_NO_ERROR : 同一家店铺，相同sku一次只能有一种模式的锁定库存!
	 */
	public static String OMS_SHOP_SKU_NO_ERROR = "0056";

	/**
	 *  * @Fields OMS_SKU_NO_NUM_ERROR : 商品锁定量不能超出实时库存数!
	 */
	public static String OMS_SKU_NO_NUM_ERROR = "0057";
	
	public static String OMS_DM_STRATEGY_DELETE_ERROR = "0058";
	
	public static String OMS_DM_DISTRIBUTOR_REPEAT_NO = "0059";
	
	public static String OMS_DM_DISTRIBUTOR_REPEAT_YES = "0060";
	
	/**
	 *  * @Fields OMS_WM_EDIT_LOCK_NUM_ERR : 本次修改数量不可超出锁定数+可用库存!
	 */
	public static String OMS_WM_EDIT_LOCK_NUM_ERR = "0061";
	
	/**
	 *  * @Fields OMS_DISTRIBUTOR_NO_ERR : 请选择分销商!
	 */
	public static String OMS_DISTRIBUTOR_NO_ERR = "0062";

	/**
	 *  * @Fields OMS_CREDIT_AMOUNT_ERR : 授信金额，必须满足数字要求，且必须大于0!
	 */
	public static String OMS_CREDIT_AMOUNT_ERR = "0063";
	
	/**
	 *  * @Fields OMS_CREDIT_TIME_ERR : 日期格式不满足 (yyyy-MM-dd) 或不满足过期日期 > 授信日期!
	 */
	public static String OMS_CREDIT_TIME_ERR = "0064";
	
	/**
	 *  * @Fields OMS_CREDIT_AUTO_NO_ERR : 该授信单不存在!
	 */
	public static String OMS_CREDIT_AUTO_NO_ERR = "0065";
	
	/**
	 *  * @Fields OMS_CREDIT_STATUS_ERR : 授信单类型错误!
	 */
	public static String OMS_CREDIT_STATUS_ERR = "0066";
	/**
	 *  * @Fields OMS_CREDIT_CHANGE_STATUS_ERR : 授信单,无法执行当前操作!
	 */
	public static String OMS_CREDIT_CHANGE_STATUS_ERR = "0067";
	
	public  static String OMS_SYM_SKU_PRODUCTNAME_REPEAT = "0070";
	
	
	public  static String OMS_SYM_PRODUCTLIMIT_NO_MATCH = "0080";
	
	
	/**
	 *  * @Fields WM_BARCODE_IS_RETURN : 商品SKU_NO已退回！
	 */
	public static String WM_BARCODE_IS_RETURN = "0093";
	
	/**
	 *  * @Fields WM_BARCODE_IS_NO_SALE : 商品SKU_NO未售！
	 */
	public static String WM_BARCODE_IS_NO_SALE = "0094";
	
	/**
	 *  * @Fields WM_BARCODE_NONE : 商品条码异常，请在PC端操作！
	 */
	public static String WM_BARCODE_NONE = "0095";
	
	
	/**
	 *  * @Fields OMS_AS_SCAN_CHECK_BARCORD_ERROR : 该件码未出库！
	 */
	public  static String OMS_AS_SCAN_CHECK_BARCORD_ERROR = "0090";
	
	
	/**
	 *  * @Fields OMS_AS_SCAN_CHECK_BARCORD_NONE : 传入的件码或SKUNO不存在！
	 */
	public  static String OMS_AS_SCAN_CHECK_BARCORD_NONE = "0091";
	
	/**
	 *  * @Fields OMS_AS_SCAN_CHECK_NUM_ERROR : 没有任何退货/换货记录!
	 */
	public static String OMS_AS_SCAN_CHECK_NUM_ERROR = "0092";

	static {
		map.put(OMS_ERROR, "OMS 自定义错误类型");
		map.put(OMS_SYM_DICTIONARY_DATA_REPEAT_DEL, "数据已被删除，请勿重复删除！");
		map.put(OMS_SYM_DICTIONARY_NO_REPEAT, "自主编号重复！");
		map.put(OMS_SYM_DICTIONARY_NAME_REPEAT, "名称重复！");
		map.put(OMS_SYM_BDSKU_SKUNO_REPEAT, "商品编码重复！");
		map.put(OMS_SYM_BDSKU_PRODUCTNO_REPEAT, "款式编码重复！");
		map.put(OMS_SYM_BDSKU_PRODUCTNAME_REPEAT, "款式名称重复！");
		map.put(OMS_SYM_BDSKU_INSERT_ERROR, "商品资料写入失败！");
		map.put(OMS_SYM_BDCOMBO_INSERT_ERROR, "组合商品配置写入失败！");
		map.put(OMS_PRODM_CENERALSKU_SAVE_REPEAT, "该商品编码已经存在，请确认后再添加！");
		map.put(OMS_PRODM_CENERALSKU_SAVE_FAILED, "必要信息不完整，请补充后添加！");
		map.put(OMS_PRODM_CENERALSKU_SELECT_FAILED, "未找到符合该条件的商品！");
		map.put(OMS_PRODM_CENERALSKU_SELECT_LOCATIONNO, "指定仓位不存在或者没有该仓位权限!");
		map.put(OMS_PRODM_CENERALSKU_SELECT_LOCATIONNO_SKUNO, "商品编码无效！");
		map.put(OMS_PRODM_CENERALSKU_SELECT_LOCATIONNO_DELETE, "请选择需要删除仓位的商品资！");
		map.put(OMS_PRODM_CENERALSKU_SELECT_LOCATIONNO_DELETEBDSTORAGELOCATION, "有商品为启用状态请禁用后在操作！");
		map.put(OMS_DATE_ISEMPTY, "保存数据为空！");
		map.put(OMS_BASEM_STORAGE_DATE_EMTPT, "仓位不存在！");
		map.put(OMS_BASEM_TOCK_NOTEMTPT, "该仓位下商品库存不是！");
		map.put(OMS_BD_BRAND_NAME_REPEAT, "品牌名称重复！");
		map.put(OMS_BASEM_SHOPNO_REPEAT, "店铺名称不允许重复！");
		map.put(OMS_BASEM_SITE_NAME_OR_SHORT_NAME_REPEAT, "站点名称或简称已存在！");
		map.put(OMS_PRODM_PICTURE_BE_DELETE, "图片已被删除！");
		map.put(OMS_PRODM_PICTURE_SABE_FAIL, "图片关系绑定失败！");
		map.put(OMS_IMPORT_FAIL, "导入的没有有效值！");
		map.put(OMS_SYM_USER_SAVE_PICTURE_FAILED, "图片保存失败！");
		map.put(OMS_SYM_USER_DEL_PICTURE_FAILED, "删除原图片失败！");
		map.put(OMS_SYM_USER_DEL_PICTURE, "图片已被删除！");
		map.put(OMS_SYM_USER_CENTRE_EDIT_PICTURE, "服务中心用户表更新失败！");
		map.put(OMS_PRODM_CENERALSKU_NOT_EXIST, "该商品不存在或者该商品不是有效商品！");
		map.put(OMS_PRODM_CENERALSKU_REPEAT, "该仓位已绑定其他商品！");
		map.put(OMS_DM_DATA_IS_EMPTY, "保存的数据已被删除,请刷新后重试！");
		map.put(OMS_DM_CORP_IS_EMPTY, "未找到公司默认供销折扣率！");
		map.put(OMS_LOCK_NAME_ERROR, "请输入锁定名称!");
		map.put(OMS_EXPIRE_UNLOCK_ERROR, "请指定过期时间!");
		map.put(OMS_SKU_NO_ERROR, "请选择需要锁定的商品!");
		map.put(OMS_SHOP_SKU_NO_ERROR, "同一家店铺，相同sku一次只能有一种模式的锁定库存!");
		map.put(OMS_SKU_NO_NUM_ERROR, "商品锁定量不能超出实时库存数!");
		map.put(OMS_DM_STRATEGY_DELETE_ERROR, "数据删除失败,该策略不存在或者公司权限未匹配成功!");
		map.put(OMS_DM_DISTRIBUTOR_REPEAT_YES, "该分销商已存在允许销售限定款");
		map.put(OMS_DM_DISTRIBUTOR_REPEAT_NO, "该分销商已存在禁止销售限定款");
		map.put(OMS_WM_EDIT_LOCK_NUM_ERR, "本次修改数量不可超出锁定数+可用库存!");
		map.put(OMS_DISTRIBUTOR_NO_ERR, "请选择分销商!");
		map.put(OMS_CREDIT_AMOUNT_ERR, "授信金额，必须满足数字要求，且必须大于0!");
		map.put(OMS_CREDIT_TIME_ERR, "日期格式不满足 (yyyy-MM-dd) 或不满足过期日期 > 授信日期!");
		map.put(OMS_CREDIT_AUTO_NO_ERR, "该授信单不存在!");
		map.put(OMS_CREDIT_STATUS_ERR, "授信类型错误!");
		map.put(OMS_CREDIT_CHANGE_STATUS_ERR, "授信单,无法执行当前操作!");
		map.put(OMS_SYM_SKU_PRODUCTNAME_REPEAT, "商品名称重复!");
		map.put(OMS_SYM_PRODUCTLIMIT_NO_MATCH, "未匹配到该货号!");
		
		map.put(OMS_AS_SCAN_CHECK_BARCORD_ERROR, "该件码未出库！");
		map.put(OMS_AS_SCAN_CHECK_BARCORD_NONE, "传入的件码或SKUNO不存在！");
		map.put(OMS_AS_SCAN_CHECK_NUM_ERROR, "没有任何退货/换货记录!");

		map.put(WM_BARCODE_IS_RETURN, "商品SKU_NO已退回！");
		map.put(WM_BARCODE_IS_NO_SALE, "商品SKU_NO未售！");
		map.put(WM_BARCODE_NONE, "商品条码异常，请在PC端操作！");
	}
	
	
}
