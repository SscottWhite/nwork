package com.example.demo.otherstool.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApiAuthorizeDTO implements Serializable{

	private static final long serialVersionUID = -1219290326925053967L;
	private Integer autoNo;
    private String siteName;
    private String shortName;
    private String appNo;
    private String appKey;
    private Integer defaultExpireTime;
    private String authorizeAddress;
    private Date updateTime;
    private String updateUser;
    private Date ts;

}