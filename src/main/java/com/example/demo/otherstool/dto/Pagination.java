package com.example.demo.otherstool.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Pagination
 * @Description: TODO 分页
 * @author Chen Xinjie chenxinjie@bosideng.com
 * @date 2016年3月7日 下午1:00:18
 * 
 */
@Data
public class Pagination implements Serializable {
	private static final long serialVersionUID = 6177404489651178395L;
	private int total; // 总条目数
}
