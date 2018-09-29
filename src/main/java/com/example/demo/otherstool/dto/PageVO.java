package com.example.demo.otherstool.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class PageVO<T> implements Serializable {
	
	private static final long serialVersionUID = -8269591232053080855L;
	private List<T> list;  //结果list
	private Pagination pagination;  // 处理分页组件
	
	public PageVO() {}
	public PageVO(List<T> list, int total) {
		this.list = list;
		pagination = new Pagination();
		pagination.setTotal(total);
	}
	@Deprecated
	public PageVO(List<T> list, int current, int pageSize, int total) {
		this.list = list;
		pagination = new Pagination();
		pagination.setTotal(total);
	}

}
