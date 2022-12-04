package com.wx.exception;

import com.wx.enums.ResponseCodeEnum;
import com.wx.inter.StateCode;

/**
 * @author 22343
 * @version 1.0
 */
public class NotifyException extends BizException {
	
	public NotifyException() {
		this(ResponseCodeEnum.FAIL);
	}
	
	public NotifyException(Throwable cause) {
		this(ResponseCodeEnum.FAIL,cause);
	}
	
	public NotifyException(StateCode stateCode) {
		this(stateCode,null);
	}
	
	public NotifyException(StateCode stateCode, Throwable cause) {
		super(stateCode,cause);
	}
	
}
