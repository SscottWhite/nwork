package com.example.demo.otherstool.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class Pagination implements Serializable {
	private static final long serialVersionUID = 6177404489651178395L;
	private int total; // 总条目数
}
