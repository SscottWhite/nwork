package com.example.demo.otherstool.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EcomResultDO<T> implements Serializable {

	private static final long serialVersionUID = -3263389845933976059L;
	private boolean success = false;
	private String resultCode;
	private String errorMessage;
	private T data;

	public EcomResultDO() {
	}

	public EcomResultDO(String key, boolean result) {
		resultCode = key;
		errorMessage = EcomResultCode.getValueWithKey(key);
		success = result;
	}

	public EcomResultDO(String key, String message, boolean result) {
		resultCode = key;
		errorMessage = message;
		success = result;
	}

}
