package com.example.demo.otherstool.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: OutSiteConstants.java
 * @Description: 外部平台用到的常量
 * @Created By:  wangzh 2018/3/13 09:28
 * @Updated By:  修改人  日期  时间  修改说明
 **/
public class OutSiteConstants {


    /*
    淘宝相关的常量字符串
     */

    //region 淘宝相关的常量字符串
    //订单接口用的查询字段
    public static final String TB_GET_SOLD_ORDER_FIELDS = "";
    public static final String TB_SELLER_RECEIVE_REFUND_FIELDS="refund_id, tid, title,buyer_nick, seller_nick, total_fee, status, created, refund_fee,refund_phase";
    public static final String TB_REFUND_GET_FIELDS="refund_id, alipay_no, tid, oid, buyer_nick, seller_nick, total_fee, status, created, refund_fee, good_status, has_good_return, payment, reason, desc, num_iid, title, price, num, good_return_time, company_name, sid, address, shipping_type, refund_remind_timeout, refund_phase, refund_version, operation_contraint,attribute, outer_id, sku";

    //下载订单时所判定的订单状态信息,多个可以逗号分割
    /*
        备选值:
        WAIT_BUYER_PAY：等待买家付款 -- >0
        WAIT_SELLER_SEND_GOODS：等待卖家发货 -->0,1
        SELLER_CONSIGNED_PART：卖家部分发货 --> 0,1
        WAIT_BUYER_CONFIRM_GOODS：等待买家确认收货 --> 0,1
        TRADE_BUYER_SIGNED：买家已签收（货到付款专用） -->
        TRADE_FINISHED：交易成功 0
        TRADE_CLOSED：交易关闭 0
        TRADE_CLOSED_BY_TAOBAO：交易被淘宝关闭 0
        TRADE_NO_CREATE_PAY：没有创建外部交易（支付宝交易）
        WAIT_PRE_AUTH_CONFIRM：余额宝0元购合约中
        PAY_PENDING：外卡支付付款确认中
        ALL_WAIT_PAY：所有买家未付款的交易（包含：WAIT_BUYER_PAY、TRADE_NO_CREATE_PAY）
        ALL_CLOSED：所有关闭的交易（包含：TRADE_CLOSED、TRADE_CLOSED_BY_TAOBAO）
        PAID_FORBID_CONSIGN，该状态代表订单已付款但是处于禁止发货状态。
     */
    //0全部
    public static final String TB_DOWN_ORDER_STATUS_ALL = "";
    //1已付款,等待买家确认收货WAIT_BUYER_CONFIRM_GOODS
	//除了默认值外每次只能查询一种状态,循环请求。
	//           ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public static final String TB_DOWN_ORDER_STATUS="WAIT_SELLER_SEND_GOODS,WAIT_BUYER_CONFIRM_GOODS";

	//淘宝下载退款的状态

    /*
    退款状态，默认查询所有退款状态的数据，除了默认值外每次只能查询一种状态。
                                                ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    WAIT_SELLER_AGREE(买家已经申请退款，等待卖家同意)
    WAIT_BUYER_RETURN_GOODS(卖家已经同意退款，等待买家退货)
    WAIT_SELLER_CONFIRM_GOODS(买家已经退货，等待卖家确认收货)
    SELLER_REFUSE_BUYER(卖家拒绝退款)
    CLOSED(退款关闭)
    SUCCESS(退款成功)
     */

    public static final String TB_DOWN_REFUND_STATUS="WAIT_SELLER_AGREE,WAIT_BUYER_RETURN_GOODS";
    //endregion



    //region 京东相关的常量字符串

    //下载的订单状态(JD)
    /*
    备选值:多订单状态可以用英文逗号隔开
    1）WAIT_SELLER_STOCK_OUT 等待出库
    2）WAIT_GOODS_RECEIVE_CONFIRM 等待确认收货
    3）WAIT_SELLER_DELIVERY等待发货（只适用于海外购商家，含义为'等待境内发货'标签下的订单,非海外购商家无需使用）
    
    
    4) PAUSE 暂停（loc订单可通过此状态获取）
    5）FINISHED_L 完成
    6）TRADE_CANCELED 取消
    7）LOCKED 已锁定
     */
    public static final String JD_DOWN_ORDER_STATUS = "WAIT_SELLER_STOCK_OUT,WAIT_SELLER_DELIVERY,WAIT_GOODS_RECEIVE_CONFIRM";

    //下载的退款单状态(JD)
    /*
    退款申请单状态,不传是查询全部状态
    0、未审核
    1、审核通过
    2、审核不通过
    3、京东财务审核通过
    4、京东财务审核不通过
    5、人工审核通过
    6、拦截并退款
    7、青龙拦截成功
    8、青龙拦截失败
    9、强制关单并退款
    10、物流待跟进。
     */
    public static final String JD_DOWN_REFUND_STATUS="1";

	/**
	 * 订单状态,
	 * 1-待付款
	 * 2-等待发货
	 * 3-已发货(待确认收货)
	 * 5-交易成功
	 * 7-交易关闭
	 * 0-全部
	 */
	public static final String ZHE_DOWN_ORDER_STATUS =  "2";

	public static final String ZHE_DOWN_ORDER_STATUS_ALL = "0";
    //endregion

	/**
	 * 发货状态，
	 * 1-待发货，
	 * 2-已发货待签收，
	 * 3-已签收，
	 * 5-全部
	 * */

	public static final String PDD_DOWN_ORDER_STATUS =  "1";

	public static final String PDD_DOWN_ORDER_STATUS_ALL = "5";

   //订单状态

   public static final String WAIT_SELLER_AGREE = "WAIT_SELLER_AGREE";
   public static final String SELLER_NOT_RECEIVED = "SELLER_NOT_RECEIVED";
   //货物状态
   public static final String BUYER_NOT_RECEIVED = "BUYER_NOT_RECEIVED";

   private static HashMap<String,Integer> JdExpressMap ;
   
   public static HashMap<String,Integer> getJdExpressMap(){
	   if(JdExpressMap!=null) {
		   return JdExpressMap;
	   }
	   JdExpressMap = new HashMap<String, Integer>();
	   JdExpressMap.put("SF",467);//顺丰
	   JdExpressMap.put("STO",470);//申通
	   JdExpressMap.put("YTO",463);//圆通
	   JdExpressMap.put("YUNDA",1327);//韵达
	   JdExpressMap.put("ZJS",1409);//宅急送1549
	   JdExpressMap.put("GTO",2465);//国通
	   JdExpressMap.put("DBKD",3046);//德邦快递
	   JdExpressMap.put("EMS",465);//EMS
	   JdExpressMap.put("QFKD",2016);//全峰快递
	   JdExpressMap.put("UC",1747);//优速
	   JdExpressMap.put("POSTB",2170);//邮政快递包裹
	   JdExpressMap.put("SURE",2105);//速尔
	   JdExpressMap.put("ZTO",1499);//中通 680414
	   JdExpressMap.put("FAST",2094);//快捷快递
	   JdExpressMap.put("KJKD",2094);//快捷快递
	   JdExpressMap.put("QY",2100);//全一快递
	   JdExpressMap.put("SE",2105);//快捷快递
	   JdExpressMap.put("EMSBZ",3688);//邮政EMS标准快递
	   JdExpressMap.put("ZGYZZHDD",2170);//中国邮政小包
	   JdExpressMap.put("KYE",599866);//跨越速运
	   JdExpressMap.put("ANXB",596494);//安能快递速运
	   JdExpressMap.put("JD",2087);//京东快递
	   JdExpressMap.put("5000000007756",3668);//邮政标准快递
	   JdExpressMap.put("2608021499_235",596494);//安能
	   JdExpressMap.put("CN7000001003751",599866);//跨越
	   return JdExpressMap;
   }

	private static HashMap<String,String> ZheExpressMap ;

	public static HashMap<String,String> getZheExpressMap(){
		if(ZheExpressMap!=null) {
			return ZheExpressMap;
		}
		ZheExpressMap = new HashMap<String, String>();
		ZheExpressMap.put("SF","顺丰速运");//顺丰
		ZheExpressMap.put("STO","申通快递");//申通
		ZheExpressMap.put("YTO","圆通速递");//圆通
		ZheExpressMap.put("YUNDA","韵达快运");//韵达
		ZheExpressMap.put("ZJS","宅急送");//宅急送1549
		ZheExpressMap.put("GTO","国通快递");//国通
		ZheExpressMap.put("DBKD","德邦快递");//德邦快递
		ZheExpressMap.put("EMS","邮政EMS速递");//EMS
		ZheExpressMap.put("QFKD","全峰快递");//全峰快递
		ZheExpressMap.put("UC","");//优速
		ZheExpressMap.put("POSTB","");//邮政快递包裹
		ZheExpressMap.put("SURE","速尔快递");//速尔
		ZheExpressMap.put("ZTO","中通快递");//中通 680414
		ZheExpressMap.put("FAST","快捷快递");//快捷快递
		ZheExpressMap.put("KJKD","");//快捷快递
		ZheExpressMap.put("QY","");//全一快递
		ZheExpressMap.put("SE","");//快捷快递
		ZheExpressMap.put("EMSBZ","邮政平邮");//邮政EMS标准快递
		ZheExpressMap.put("ZGYZZHDD","邮政快递(国内小包)");//中国邮政小包
		ZheExpressMap.put("KYE","");//跨越速运
		ZheExpressMap.put("ANXB","安能物流");//安能快递速运
		ZheExpressMap.put("JD","");//京东快递
		ZheExpressMap.put("5000000007756","");//邮政标准快递
		ZheExpressMap.put("2608021499_235","");//安能
		ZheExpressMap.put("CN7000001003751","");//跨越
		return ZheExpressMap;
	}


	private static Map<String,Integer> tbExchangeOasStatusMap;

	/**
	 * 淘宝线上换货状态,与 本地的售后(换货类型)的状态对应值
	 * @return
	 */
	public static Map<String,Integer> getTbExchangeOasStatusMap(){
		if(tbExchangeOasStatusMap !=null){
			return tbExchangeOasStatusMap;
		}
		tbExchangeOasStatusMap = new HashMap<>();
		tbExchangeOasStatusMap.put("换货待处理",9);  //不显示给前台
		tbExchangeOasStatusMap.put("待买家修改",9);  //不显示给前台
		tbExchangeOasStatusMap.put("待买家退货",0);   //卖家已经同意了
		tbExchangeOasStatusMap.put("买家已退货，待收货", 0);//卖家已经同意了
		tbExchangeOasStatusMap.put("换货关闭",2);     //说明换货取消了
		tbExchangeOasStatusMap.put("请退款",2);       //不换了,还是取消
		tbExchangeOasStatusMap.put("待发出换货商品",1);
		tbExchangeOasStatusMap.put("换货成功",1);
		tbExchangeOasStatusMap.put("待买家收货",1);
		return tbExchangeOasStatusMap;
	}


	//换货状态，具体包括：换货待处理(1), 待买家退货(2), 买家已退货，待收货(3), 换货关闭(4), 换货成功(5), 待买家修改(6), 待发出换货商品(12), 待买家收货(13), 请退款(14)
	private static Map<String,String> tbExchangeStatusMap;
	public static  Map<String,String> getTbExchangeStatusMap(){
		if(tbExchangeStatusMap!=null){
			return tbExchangeStatusMap;
		}
		tbExchangeStatusMap = new HashMap<>();
		tbExchangeStatusMap.put("换货待处理","1");
		tbExchangeStatusMap.put("待买家退货","2");
		tbExchangeStatusMap.put("买家已退货，待收货","3");
		tbExchangeStatusMap.put("换货关闭","4");
		tbExchangeStatusMap.put("换货成功","5");
		tbExchangeStatusMap.put("待买家修改","6");
		tbExchangeStatusMap.put("待发出换货商品","12");
		tbExchangeStatusMap.put("待买家收货","13");
		tbExchangeStatusMap.put("请退款","14");
		return tbExchangeStatusMap;
	}



	private static HashMap<String,String> PddExpressMap ;
	public static HashMap<String,String> getPddExpressMap(){
		if(PddExpressMap!=null) {
			return PddExpressMap;
		}
		PddExpressMap = new HashMap<String, String>();
		PddExpressMap.put("SF","44");//顺丰
		PddExpressMap.put("STO","1");//申通
		PddExpressMap.put("YTO","85");//圆通
		PddExpressMap.put("YUNDA","344");//韵达
		PddExpressMap.put("ZJS","129");//宅急送1549
		PddExpressMap.put("GTO","124");//国通
		PddExpressMap.put("DBKD","131");//德邦快递
		PddExpressMap.put("EMS","118");//EMS
		PddExpressMap.put("QFKD","116");//全峰快递
		PddExpressMap.put("UC","117");//优速
		PddExpressMap.put("POSTB","132");//邮政快递包裹
		PddExpressMap.put("SURE","155");//速尔
		PddExpressMap.put("ZTO","115");//中通 680414
		PddExpressMap.put("FAST","122");//快捷快递
		PddExpressMap.put("KJKD","122");//快捷快递
		PddExpressMap.put("QY","201");//全一快递
		PddExpressMap.put("SE","");//快捷快递
		PddExpressMap.put("EMSBZ","118");//邮政EMS标准快递
		PddExpressMap.put("ZGYZZHDD","");//中国邮政小包
		PddExpressMap.put("KYE","183");//跨越速运
		PddExpressMap.put("ANXB","208");//安能快递速运
		PddExpressMap.put("JD","120");//京东快递
		PddExpressMap.put("5000000007756","324");//邮政标准快递
		PddExpressMap.put("2608021499_235","345");//安能
		PddExpressMap.put("CN7000001003751","183");//跨越
		return PddExpressMap;
	}
}
