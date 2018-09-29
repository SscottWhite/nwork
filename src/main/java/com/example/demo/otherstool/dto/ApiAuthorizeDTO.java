package com.example.demo.otherstool.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiAuthorizeDTO implements Serializable{

	private static final long serialVersionUID = -1219290326925053967L;
	private Integer autoNo;    //int(10) unsigned  自增编号 
    private String siteName;   //varchar(50)  站点名称 
    private String shortName;  //varchar(20)  站点简称 
    private String appNo;      //varchar(50)  应用编号 
    private String appKey;     //varchar(100)  应用密匙 
    private Integer defaultExpireTime; //int(11)  默认过期时间(天) 
    private String authorizeAddress;   //varchar(200)  授权地址 
    private Date updateTime;           //datetime  修改时间 
    private String updateUser;         //varchar(50)  修改人员 
    private Date ts;                   //timestamp  时间戳 

}