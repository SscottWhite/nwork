package com.example.demo.otherstool.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;


@Data
public class JwtDto{
	private Map<String,Object> header;
	private String uuid;
	private String subject;
	private String issuser;
	private Date issuedAt;
	private Date expiration;

	
	
	
}
