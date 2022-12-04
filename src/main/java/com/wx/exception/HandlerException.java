package com.wx.exception;

import com.wx.enums.ResponseCodeEnum;
import com.wx.inter.StateCode;

/**
 * @author 22343
 * @version 1.0
 */
public class HandlerException extends BizException {
	
	public HandlerException() {
		this(ResponseCodeEnum.FAIL);
	}
	
	public HandlerException(Throwable cause) {
		this(ResponseCodeEnum.FAIL,cause);
	}
	
	public HandlerException(StateCode stateCode) {
		this(stateCode,null);
	}
	
	public HandlerException(StateCode stateCode, Throwable cause) {
		super(stateCode,cause);
	}
}
