package com.li.support.enums;

import org.apache.commons.lang3.StringUtils;

public enum ErrorCodeEnum {
	
	ERROR("1000", "系统错误"),
	NULL("1001", "必填项为空"),
	ILLEGAL("1002", "非法的参数"),
	NO_RECORD("1003", "记录不存在"),
	NO_AUTH("1004", "没有权限"),
	ILLEGAL_OPERATE("1005", "非法的操作"),
	NO_USER("1006", "用户不存在"),
	INVALID_CODE("1007", "无效的登录码"),
	USED_CODE("1008", "登录码已失效"),
	LOGIN_TIME_OUT("1009", "请重新登录"),
	RECORD_EXIST("1010", "记录已存在");

	private final String key;
	private final String value;

	private ErrorCodeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	/**
	 * 根据key获取value
	 */
	public static String getValueByKey(String key) {
		if( StringUtils.isNotBlank(key) ) {
			for( ErrorCodeEnum e : ErrorCodeEnum.values() ) {
				 if( e.getKey().equals(key) ) {
					 return e.getValue();
				 }
			}
		}
		return null;
	}

	/**
	 * 根据value获取key
	 */
	public static String getKeyByValue(String value) {
		if( StringUtils.isNotBlank(value) ) {
			for( ErrorCodeEnum e : ErrorCodeEnum.values() ) {
				 if( e.getValue().equals(value) ) {
					 return e.getKey();
				 }
			}
		}
		return null;
	}
}
