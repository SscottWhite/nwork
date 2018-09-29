package com.example.demo.otherstool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class StringUtil {
	
	
    protected static final String CHINESE = "[\u4E00-\u9FA5]"; // 中文匹配
    protected static final String NUMBER = "[0-9]"; // 数字匹配
    protected static final String EN = "[a-zA-Z]"; // 英文匹配
    protected static final String COMMA = ","; // 逗号
    protected static final Pattern SPECIAL_CHAR = Pattern.compile("[^0-9a-zA-Z\u4E00-\u9FA5]");

    /**
     * 字符串转List
     *
     * @param str
     * @param split
     * @return
     */
    public static List<String> toList(String str,String split) {
        if(ObjectUtil.isNotEmpty(str)){
            return Arrays.asList(str.split(split));
        }
        return new ArrayList<>();
    }
    public static void toList(String str,String split,List<String> list) {
        if(ObjectUtil.isNotEmpty(str)){
            String[] noArr = str.split(split);
            for(String no:noArr) {
                if(StringUtils.isNotBlank(no) && !list.contains(no)) {
                    list.add(no);
                }
            }
        }
    }

    /**
     * 去掉指定字符串的开头的指定字符
     * @param stream 原始字符串
     * @param trim 要删除的字符串
     * @return
     */
    public static String trimStart(String stream, String trim) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trim == null || trim.length() == 0) {
            return stream;
        }
        // 要删除的字符串结束位置
        int end;
        // 正规表达式
        String regPattern = "[" + trim + "]*+";
        Pattern pattern = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        // 去掉原始字符串开头位置的指定字符
        Matcher matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            end = matcher.end();
            stream = stream.substring(end);
        }
        // 返回处理后的字符串
        return stream;
    }

    /**
     * 去除前后指定字符
     * @param source 目标字符串
     * @param beTrim 要删除的指定字符
     * @return 删除之后的字符串
     * 调用示例：System.out.println(trim(", ashuh  ",","));
     */
    public static String trim(String source, String beTrim) {
        if(StringUtils.isBlank(source)){
            return "";
        }
        source = source.trim(); // 去空格
        if(source.isEmpty()){
            return "";
        }
        String beginChar = source.substring(0, 1);
        if (beginChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(1, source.length());
            //beginChar = source.substring(0, 1);
        }
        // 循环去掉字符串尾的beTrim字符
        String endChar = source.substring(source.length() - 1, source.length());
        if (endChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(0, source.length() - 1);
            //endChar = source.substring(source.length() - 1, source.length());
        }
        return source;
    }
    
    

    /**
     * 处理字符串不能为空,当字符串为null 时返回 new String("");
     * 
     * @param strSource 源字符串
     * @param bTrim 如果字符串不为空是否要删除首尾的空格，true:删除首尾的空格，false:不删除
     * @return 返回一个不为null的字符串
     */
    public static String notNull(String strSource, boolean bTrim) {
        return null == strSource ? "" : bTrim ? strSource.trim() : strSource;
    }

    public static String notNull(String theString) {
        return notNull(theString, true);
    }

    /**
     * <p>
     * 剥离富文本中的Html标签
     * </p>
     * 
     * @param strIn
     * @param keepBr
     * @return
     * @author sunlida 2013-9-25 下午06:07:12
     */
    public static String dealWithHTML4List(String strIn, boolean keepBr) {
        if (null == strIn) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int len = strIn.length();
        for (int i = 0; i < len; i++) {
            if (Character.isWhitespace(strIn.charAt(i))) {
                continue;
            }
            sb.append(strIn.charAt(i));
        }
        strIn = sb.toString();
        // 将段落分离
        String[] infos = strIn.split("(\t\n|\r\n)+");
        String regStr = "</?[^>]*+>";
        sb.delete(0, sb.length());
        for (int i = 0; i < infos.length; i++) {
            // 去段首空格
            infos[i] = notNull(infos[i]);
            // 过滤HTML标签
            if (keepBr) {
                regStr = "</?([^>]|^(br))*+>";
            }
            infos[i] = infos[i].replaceAll(regStr, "");
            infos[i] = infos[i].replaceAll("\t|\r|\n", "");
            // 当不是空格独占一行的情况时进行处理
            if (!"".equals(infos[i])) {
                sb.append(infos[i].trim());
            }
        }
        return sb.toString();
    }

    /**
     * 校验字符是否为中文
     * 
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        String chinese = str.trim();
        if (chinese == null || chinese.equals(""))
            return false;
        else {
            if (chinese.matches(CHINESE))
                return true;
            else
                return false;
        }
    }

    /**
     * 校验字符是否为英文
     * 
     * @param str
     * @return
     */
    public static boolean isEn(String str) {
        String en = str.trim();
        if (en == null || en.equals(""))
            return false;
        else {
            if (en.matches(EN))
                return true;
            else
                return false;
        }
    }

    /**
     * 校验字符是否为0-9的数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        String number = str.trim();
        if (number == null || number.equals(""))
            return false;
        else {
            if (number.matches(NUMBER))
                return true;
            else
                return false;
        }
    }

    /**
     * 根据逗号拆分字符串
     * 
     * @param str
     * @return
     */
    public static String[] splitByComma(String str) {
        if (str == null || str.equals(""))
            return null;

        return str.split(COMMA);
    }

    /**
     * 字符串数组拼接成逗号分隔的字符串，如下：<br>
     * Array[1,2,3,4,5,6,a] => String str = "1,2,3,4,5,6,a"
     * 
     * @return
     */
    public static String arrayToStringByComma(String[] strArray) {
        String str = "";

        if (strArray == null || strArray.length < 1) {
            return null;
        }
        else {
            for (int i = 0; i < strArray.length; i++) {
                if (strArray.length == (i + 1)) {
                    str = str + strArray[i];
                }
                else {
                    str = str + strArray[i] + ",";
                }
            }
        }
        return str;
    }
    

    /**
     * 替换特殊字符(非数字英文和汉字)
     * 
     * @param srcString
     * @return
     */
    public static String stringFilter(String srcString) {
        return SPECIAL_CHAR.matcher(srcString).replaceAll("").replaceAll("[url=]\\\\[/url]", "").trim();
    }
    
    /**
     * 
     * @Description: 字符串编码成Unicode编码
     * @param: @param src
     * @param: @return
     * @param: @throws Exception      
     * @return: String    
     * @author: james  
     * @throws
     */
    public static String encode(String src) throws Exception {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < src.length(); i++) {
            c = src.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128)
                str.append("\\u" + strHex);
            else
                str.append("\\u00" + strHex); // 低位在前面补00
        }
        return str.toString();
    }
    
    /**
     * 
     * @Description: Unicode解码成字符串
     * @param: @param src
     * @param: @return      
     * @return: String    
     * @author: james  
     * @throws
     */
    public static String decode(String src) {
        int t =  src.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = src.substring(i * 6, (i + 1) * 6); // 每6位描述一个字节
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }
    
    public static String getUniqueCode() {
    	//yyyyMMdd HH:mm:ss:SSS
    	Date d = new Date();
    	String bn = new SimpleDateFormat("yyyyMMdd").format(d); //8位=日期
    	bn += ""+(int)((Math.random()*9+1)*1000); //+4位随机数
    	bn += new SimpleDateFormat("HHmmssSSSS").format(d); //10位=时间6位+4位毫秒级
    	bn += ""+(int)((Math.random()*9+1)*1000); //+4位随机数
    	return bn;
    }
    
    /**
     * 分割字符串,将结果装入Map中
     * created by: wangzh 2018-05-26 13:28
     * @param v   字符串
     * @param firstSpliter  第一分隔符
     * @param secondSpliter 第二分隔符
     * @return
     */
    public static Map<String, String> splitToMap(String v,String firstSpliter,String secondSpliter,String sort) {
    	if(StringUtils.isEmpty(v) || StringUtils.isEmpty(firstSpliter) || StringUtils.isEmpty(secondSpliter)) {
    		return null;	
    	}
    	Map<String,String> map = new LinkedHashMap<>();
    	
    	String[] vArr = v.split(firstSpliter);
    	if(vArr.length<=1) {
    		return null;
    	}
    	List<String> vList = Arrays.asList(vArr);
    	if("desc".equals(sort)) {
    		//倒叙排列
    		Collections.reverse(vList);
    	}
    	
    	String[] groupArr;
    	for(String s : vList) {
    		if(StringUtils.isEmpty(s)) {
    			continue;
    		}
    		groupArr = s.split(secondSpliter);
    		if(groupArr.length!=2) {
    			continue;
    		}
    		//加入到map中
    		map.put(groupArr[0], groupArr[1]);
    	}
    	return map;
    	
    }
    
    /**
     * 捕捉异常基本消息
     * created by: wangzh 2018-06-01 14:28
     * @param ex 异常
     * @return
     */
    public static String getExecptionMessage(Exception ex) {
    	String fullException = ExceptionUtils.getStackTrace(ex);
    	log.error(fullException);
    	String message = StringUtils.isEmpty(ex.getMessage())?ex.getLocalizedMessage():ex.getMessage();
		if(StringUtils.isEmpty(message)) {
			message = ex.toString();
		}
    	if(fullException.indexOf("HSFServiceAddressNotFoundException")>=0) {
        	message = "无法与服务中心建立连接";
        }else if(fullException.indexOf("HSFTimeOutException")>=0){
        	message = "与服务中心连接超时";
        }else if(fullException.indexOf("SQLException")>=0) {
        	if(fullException.indexOf("TxcException")>=0 && fullException.indexOf("Trx TimeOut")>=0) {
        		message = "事务执行超时";
        	}else if(fullException.indexOf("Data too long for column")>=0) {
        		message = "数据太长,超出字段容量";
        	}else {
            	message = "SQL语句错误";
        	}
        }else if(fullException.indexOf("DruidDataSource")>=0) {
        	message = "数据源连接失败";
        }else if(fullException.indexOf("Given final block not properly padded")>0) {
        	message = "加密解密的KEY不是同一个";
        }
		return message;
    }
    
    
    
    /**
     * 获取IP
     * created by: wangzh 2018-06-04 15:48
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
            
	    String ipAddress = request.getHeader("x-forwarded-for");
	    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getHeader("X-Real-Ip");
	    }
	    if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getHeader("Proxy-Client-IP");
	    }
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	        ipAddress = request.getRemoteAddr();
	        
	        if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
	            //根据网卡获取本机配置的IP地址
	            InetAddress inetAddress = null;
	            try {
	                inetAddress = InetAddress.getLocalHost();
	            } catch (UnknownHostException e) {
	                e.printStackTrace();
	            }
	            ipAddress = inetAddress.getHostAddress();
	        }
	    }
	    
	    //对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
	    if(null != ipAddress && ipAddress.length() > 15){
	        //"***.***.***.***".length() = 15
	        if(ipAddress.indexOf(",") > 0){
	            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
	        }
	    }
	    
	    return ipAddress;
	}
    
	/**
	 * 校验密码是否满足
	 * 		密码长度至少8位以上，包含字母大小写+数字+特殊字符，不要使用连续字母或单纯数字，不要使用键盘上连续字符
	 * @param password
	 * @return
	 */
	public static boolean validPassword(String password){
		Integer pwdLength = password.length();
		if (pwdLength < 8){
			return false;
		}

		if (Pattern.matches("^[A-Za-z0-9-_`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]+$",password) && Pattern.matches(".*[a-zA-Z]{1,}.*",password) 
				&& Pattern.matches(".*[0-9]{1,}.*",password) &&  Pattern.matches(".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？].*",password)){
			return true;
		}
		return false;
	}
	
	/**
	 * 生成随机数字和字母
	 * @author james
	 * @param length
	 * @return
	 */
	public static String getStringRandom(int length) {
		String val = "";
		Random random = new Random();

		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Long createAutoNo() throws Exception{
		String str1 = String.valueOf(System.currentTimeMillis());
		int max = 999999;
		int min = 100000;
		String str2 = String.valueOf((int)(new Random().nextInt(max-min)+min));
		String auto_no = str1+str2;
		if (auto_no.length()!= 19){
			log.error("程序自动生成的主键规则长度异常只允许生成19位:"+"["+auto_no+"]");
			return null;
		}
		return Long.valueOf(auto_no);
	}
	
	public static String filterSpecialChar(String str) { 
		// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]"; 
		// 清除掉所有特殊字符 
		try {
			String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
			Pattern p = Pattern.compile(regEx); 
			Matcher m = p.matcher(str);
			return m.replaceAll("").trim();
		}catch(Exception e) {
			log.error("过滤字符串出错:"+e.getMessage());
			return str;
		}
		
	} 
	/**
	 * 按长度分割字符串
	 * created by: wangzh 2018-06-28 16:26
	 * @param text   字符
	 * @param length 分割长度
	 * @return
	 */
	public static List<String> splitByLength(String text, int length){
        if (text == null) {
            return null;
        }
        List<String> list=new ArrayList<>();
        if (text.length()<length || length==0) {
            list.add(text);
            return list;
        }
        Integer tLen =  text.length(),subLen = 0,start=0;
        Double l = Math.ceil(tLen/ (double) length);
        for (int i=0;i<l.intValue();i++){
            subLen=(i+1)*length;
            if(subLen-tLen>0){
                subLen = tLen;
            }
            start = i*length;
            String rowStr = text.substring(start, subLen);
            list.add(rowStr);
        }
        return list;
    }
	
	
	/**
	 * 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * @return 是整数返回true,否则返回false created by liwen
	 */

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	  
	public static Map<String, String> getUrlParams(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();

		String[] arrSplit = null;

		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组 www.2cto.com
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 *
	 * @param strURL url地址
	 * @return url请求参数部分
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;

		strURL = strURL.trim();

		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}

		return strAllParam;
	}


    /**
     * @Description 将现有时间重新排版
     * @param date
     * @return
     * @author liwen
     * @date 2018年8月23日14:23:30
     */
    public String newDate(String date) {
        String newdate = "";
        String str = date.replaceAll("/","").replaceAll(":","").replaceAll(" ","");
        if(str.length() == 8) {
            newdate = str.substring(0, 4)+"-"+str.substring(4, 6)+"-"+str.substring(6, 8);
        }
        if(str.length() > 8) {
            newdate = str.substring(0, 4)+"-"+str.substring(4, 6)+"-"+str.substring(6, 8)+" "+str.substring(8, 10)+":"+str.substring(10, 12)+":"+str.substring(12, 14);
        }
        return newdate;
    }
	
	public static void main(String[] args) {
//		HashMap<Integer,String> map1 = new HashMap<>();
//		map1.put(0, "n0");
//		doM(map1);
//		System.out.println(FastJsonUtil.toJsonString(map1));
//		
//		List<String> list1 = splitByLength("城区黑龙江佳木斯市富锦市城区新天地花园小区2号楼五单元702室",18);
//		for(String s : list1) {
//			System.out.println(s);
//		}
//		
//		List<String> list2 = new ArrayList<>();
//		list2.add("1");
//		list2.add("3");
//		list2.addAll(Arrays.asList("2","5","8"));
//		if(list2.contains("2")) {
//			int i = list2.indexOf("2");
//			list2.remove(i);
//		}
//		System.out.println(FastJsonUtil.toJsonString(list2));
//		Integer i=2;
//		System.out.println(i==2);
//		BigDecimal k = new  BigDecimal(1).divide(new BigDecimal(3),2,RoundingMode.HALF_UP);
//		BigDecimal k2 = new  BigDecimal(2).divide(new BigDecimal(3),2,RoundingMode.HALF_UP);
//		System.out.println(k+","+k2);
//		String presaleSku = "a123456==2018-08-15";
//		String[] pskuArr = presaleSku.split("==");
//		System.out.println(pskuArr[0]+","+pskuArr[1]);
//		System.out.println(presaleSku.contains("*"));
//		
		
//		System.out.println(DateUtil.addTime("2018-07-16 00:00:00", -20, "minute"));
//		System.out.println(DateUtil.addTime("2018-07-16 00:00:00", 20, "minute"));
//		String startDate = DateUtil.Date2Str(DateUtil.plusMinute(-20));              //开始日期,把日期转短格式
//        String endDate = DateUtil.Date2Str(DateUtil.plusMinute(20));  //结束日期
//        System.out.println(startDate+","+endDate);
//        System.out.println(new BigDecimal("0.2").add(new BigDecimal("-0.2")));
//        Object o =FastJsonUtil.toObject(FastJsonUtil.toJsonString(null), Object.class);
//        System.out.println(o.toString());
//        final List<String> arr = new ArrayList<>();
//        arr.add("123");
//        arr.add("456");
//        System.out.println(StringUtils.join(arr,"-"));
//        String url1 = "https://detail.tmall.com/item.htm?spm=a220o.1000855.w5003-17838581353.8.66e54e96NiaT3L&id=538431268484&rn=b9e6a023613989a0f1cbd22a353dfb39&abbucket=15&scene=taobao_shop";
//        System.out.println(getUrlParams(url1));
//        
        

       String abc ="123$456";
       String[] arr = abc.split("$");
       for(String a : arr){
           System.out.println("args = [" + a + "]");
       }

	}
	
	private static void doM(HashMap<Integer,String> m) {
		for(int i=1;i<5;i++) {
			m.put(i, "n"+i);
		}
	}
      
}
