package com.example.demo.otherstool;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.URLDecoder;


public class RequestUtil {
	
	/**
	 * 获取头信息
	 * @param request
	 * @return
	 */
	public static ReqHeaderDTO getHeader(HttpServletRequest request) {
		ReqHeaderDTO dto = new ReqHeaderDTO();
		try {
			String userNickName = request.getHeader("UserName");
			if(StringUtils.isNotEmpty(userNickName)) {
				userNickName = URLDecoder.decode(userNickName, "UTF-8");
			}
			String ati = request.getHeader("Ati");
			if(StringUtils.isEmpty(ati)) {
				ati = request.getHeader("ati");
			}
	        String userNo = request.getHeader("UserNo");
	        String userId = request.getHeader("UserId");
			//公司编号
	        String companyNo = request.getHeader("CompanyNo");
	        if (CheckUtil.checkPositiveInteger(companyNo)) {
                dto.setCompanyNo(Integer.valueOf(companyNo));
            }else {
            	dto.setCompanyNo(-1);
            }
	        
	        dto.setUserNick(userNickName);
	        dto.setAti(StringUtils.isEmpty(ati)?"":ati);
	        dto.setUserNo(userNo);
	        dto.setIp(StringUtil.getIpAddress(request));
	        dto.setUserId(userId);
	        
		}catch(Exception e) {
			String errMsg = StringUtil.getExecptionMessage(e);
			dto.setError(errMsg);
		}
		
		return dto;
	}
	
	
	
	public static String getUrl(HttpServletRequest request) {
		String requestUrl = request.getScheme()    //当前链接使用的协议
			    +"://" + request.getServerName()   //服务器地址
			    + ":" + request.getServerPort()    //端口号
			    + request.getContextPath()         //应用名称，如果应用名称为
			    + request.getServletPath()         //请求的相对url
			    + "?" + request.getQueryString();  //请求参数
		return requestUrl;
	}
	
	
	/**
	 * 请求头DTO
	 * @author wellswang
	 *
	 */
	@Data
	public static class ReqHeaderDTO implements Serializable{
		
		private static final long serialVersionUID = 1426264308631553608L;
		//公司编号
		private Integer companyNo;
		
		//用户昵称
		private String userNick;
		
		//用户登录编号
		private String userNo;
		
		//淘宝御城河用
		//(1)对B/S架构的应用，接入孔明锁后，会在域名下生成一个cookie _ati。在服务器端，可从HTTP请求中获取名称为_ati的cookie的值。
		private String ati;
		
		private String ip;
		//用于存放获取的过程中的错误信息
		private String error;
		
		private String userId;
	}
}
