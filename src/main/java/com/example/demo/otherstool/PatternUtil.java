package com.example.demo.otherstool;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: PatternUtil
 * @Description: TODO 正则表达式工具
 * @author Chen Xinjie chenxinjie@bosideng.com
 * @date 2016年3月18日 上午9:06:13
 * 
 */
public final class PatternUtil {
	
//	public static final String SL_REGEX_DOUHAO= "(([0-9]{1,2}|[A-Z]{1})-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1}\\,*)*([0-9]{2}-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1})$";
//	public static final String SL_REGEX_DOUHAO_D= "(([0-9]{1,2}|[A-Z]{1})-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1}\\，*)*([0-9]{2}-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1})$";
//	public static final String SL_REGEX_FENHAO= "(([0-9]{1,2}|[A-Z]{1})-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1}\\;*)*([0-9]{2}-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1})$";
//	public static final String SL_REGEX_FENHAO_D= "(([0-9]{1,2}|[A-Z]{1})-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1}\\；*)*([0-9]{2}-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1})$";
//	public static final String SL_REGEX_KONGGE= "(([0-9]{1,2}|[A-Z]{1})-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1}\\s*)*([0-9]{2}-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1})$";
//	public static final String SL_REGEX_HUICHE= "(([0-9]{1,2}|[A-Z]{1})-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1}\\r*)*([0-9]{2}-[0-9]{1}-[0-9]{1}-[0-9]{1}-[0-9]{1})$";
	
	public static final String SL_REGEX_DOUHAO= ".*[\\,].*";
	public static final String SL_REGEX_DOUHAO_D= ".*[\\;].*";
	public static final String SL_REGEX_FENHAO= ".*[\\，].*";
	public static final String SL_REGEX_FENHAO_D= ".*[\\；].*";
	public static final String SL_REGEX_KONGGE= ".*[\\s].*";
	public static final String SL_REGEX_HUICHE= ".*[\\回车].*";
	
	public static final Integer REGEX_DOUHAO = 1;
	public static final Integer REGEX_DOUHAO_D = 2;
	public static final Integer REGEX_FENHAO = 3;
	public static final Integer REGEX_FENHAO_D = 4;
	public static final Integer REGEX_KONGGE = 5;
	public static final Integer REGEX_HUICHE = 6;
	public static final Integer REGEX_WUFGF = 7;//无分隔符
	public static final Integer REGEX_OTHER = 9;
	
	
	/**
	 * 验证是否满足
	 * 
	 * @param exclusions
	 * @param uri
	 * @return
	 */
	public static Boolean isExcluded(List<Pattern> exclusions, String uri) {
		for (Pattern exclusion : exclusions) {
			Matcher matcher = exclusion.matcher(uri);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}

	

	/**
	 * 过滤sql字符串
	 * @param str
	 * @return
	 */
	private final static String regex = "[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]";
	public static String sqlValidate(String str) {
		if(StringUtils.isNotBlank(str)){
            return str.replaceAll(regex, "*"); // 过滤emoji表情
        }else{  
            return str;  
        }  
	}
	
	/**
	 * 
	 * @Description: 判断库位设置是以哪种方式分隔[, ; 空格 回车]
	 * @param: @param str
	 * @param: @return      
	 * @return: int    
	 * @author: james  
	 * @throws
	 */
	public static int storageLocationValidate(String str){
		if(str.matches(SL_REGEX_DOUHAO) && !str.matches(SL_REGEX_DOUHAO_D) && !str.matches(SL_REGEX_FENHAO)
				&& !str.matches(SL_REGEX_FENHAO_D)&& !str.matches(SL_REGEX_KONGGE) && !str.matches(SL_REGEX_HUICHE)){
			return REGEX_DOUHAO;
		}else if (!str.matches(SL_REGEX_DOUHAO) && str.matches(SL_REGEX_DOUHAO_D) && !str.matches(SL_REGEX_FENHAO)
				&& !str.matches(SL_REGEX_FENHAO_D)&& !str.matches(SL_REGEX_KONGGE) && !str.matches(SL_REGEX_HUICHE)){
			return REGEX_DOUHAO_D;
			
		}else if (!str.matches(SL_REGEX_DOUHAO) && !str.matches(SL_REGEX_DOUHAO_D) && str.matches(SL_REGEX_FENHAO)
				&& !str.matches(SL_REGEX_FENHAO_D)&& !str.matches(SL_REGEX_KONGGE) && !str.matches(SL_REGEX_HUICHE)){
			return REGEX_FENHAO;
			
		}else if (!str.matches(SL_REGEX_DOUHAO) && !str.matches(SL_REGEX_DOUHAO_D) && !str.matches(SL_REGEX_FENHAO)
			   && str.matches(SL_REGEX_FENHAO_D)&& !str.matches(SL_REGEX_KONGGE) && !str.matches(SL_REGEX_HUICHE)){
			return REGEX_FENHAO_D;
			
		}else if (!str.matches(SL_REGEX_DOUHAO) && !str.matches(SL_REGEX_DOUHAO_D) && !str.matches(SL_REGEX_FENHAO)
				&& !str.matches(SL_REGEX_FENHAO_D)&& str.matches(SL_REGEX_KONGGE) && !str.matches(SL_REGEX_HUICHE)){
			return REGEX_KONGGE;
			
		}else if (!str.matches(SL_REGEX_DOUHAO) && !str.matches(SL_REGEX_DOUHAO_D) && !str.matches(SL_REGEX_FENHAO)
				&& !str.matches(SL_REGEX_FENHAO_D)&& !str.matches(SL_REGEX_KONGGE) && str.matches(SL_REGEX_HUICHE)){
			return REGEX_HUICHE;
		}else if(!str.matches(SL_REGEX_DOUHAO) && !str.matches(SL_REGEX_DOUHAO_D) && !str.matches(SL_REGEX_FENHAO)
				&& !str.matches(SL_REGEX_FENHAO_D)&& !str.matches(SL_REGEX_KONGGE) && !str.matches(SL_REGEX_HUICHE)){
			return REGEX_WUFGF;
		}else{
			return REGEX_OTHER;
		}
		
	}
	/**
	 * 验证数字输入
	 * wangzh
	 * @param str 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isNumber(String str) {
		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 电话号码验证
	 * ni cheng
	 * @param telNo
	 * @return
	 */
	public static boolean isTelNo(String telNo){
		String regex = "^(?:(?:0\\d{2,4})-)?(?:\\d{7,8})(-(?:\\d{3,}))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(telNo);
		return matcher.matches();
	}

	/**
	 * 手机号码验证
	 * ni cheng
	 * @param mobileNo
	 * @return
	 */
	public static boolean isMobileNo(String mobileNo){
		String regex = "^(13[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mobileNo);
		return matcher.matches();
	}
	
	/**
	 * 银行卡号验证
	 * @param mobileNo
	 * @return
	 */
	public static boolean isBankAccount(String mobileNo){
		String regex = "^([1-9]{1})(\\d{14}|\\d{18}|\\d{16})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mobileNo);
		return matcher.matches();
	}

	/**
	 * 传真验证
	 * @param mobileNo
	 * @return
	 */
	public static boolean isFaxNo(String mobileNo){
		String regex = "^(\\d{3,4}-)?\\d{7,8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mobileNo);
		return matcher.matches();
	}
	
	public static boolean isUrl(String value) {
		String regex = "^(http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&*=]*))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
