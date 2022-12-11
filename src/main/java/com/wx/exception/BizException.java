package com.wx.exception;

import com.wx.enums.ResponseCodeEnum;
import com.wx.inter.StateCode;

import java.io.Serializable;

/**
 * @author 22343
 * @version 1.0
 */
public class BizException extends RuntimeException implements Serializable, StateCode {
	
	protected static final long serialVersionUID = 8935197089745865786L;
	protected final String code;
	
	protected Throwable cause;
	
	public BizException() {
		this(ResponseCodeEnum.FAIL);
	}
	
	public BizException(Throwable cause) {
		this(ResponseCodeEnum.FAIL,cause);
	}
	
	public BizException(StateCode stateCode) {
		this(stateCode,null);
	}
	
	public BizException(StateCode stateCode, Throwable cause) {
		super(stateCode.getMessage(),cause);
		this.code = stateCode.getCode();
	}
	
	public Throwable getTarget(){
		return super.getCause();
	}
	
	public Throwable getRootCause(){
		if (cause != null){
			return cause;
		}
		Throwable supperCause = super.getCause();
		for (cause = supperCause;cause.getCause() != null;cause = cause.getCause()){};
		return supperCause;
	}
	
	
	@Override
	public String getCode() {
		return this.code;
	}
	
}
