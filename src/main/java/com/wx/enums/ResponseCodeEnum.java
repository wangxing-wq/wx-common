package com.wx.enums;

import com.wx.inter.StateCode;

/**
 * @author 22343
 * @version 1.0
 */
public enum ResponseCodeEnum implements StateCode {
	/**
	 * SUCCESS 返回成功信息
	 * FAIL    返回失败信息
	 */
	SUCCESS("200","success"),FAIL("-1","fail");
	
	private String message;
	private String code;
	private ResponseCodeEnum(String code,String message){
		this.code = code;
		this.message = message;
	}
	
	@Override
	public String getCode() {
		return code;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
