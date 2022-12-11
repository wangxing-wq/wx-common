package com.wx.util;

import com.wx.enums.ResponseCodeEnum;
import com.wx.exception.HandlerException;
import com.wx.exception.NotifyException;
import com.wx.inter.StateCode;
import com.wx.web.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 22343
 * @version 1.0
 * <br/> 如果存在校验,直接通过
 */
public class ExceptionHolder {
	
	private static final ThreadLocal<ExceptionHolder> LOCAL = new ThreadLocal<>();
	private HandlerException handlerException;
	private final List<NotifyException> notifyExceptionList = new ArrayList<>();
	
	private Result fail;
	
	public static ExceptionHolder instance() {
		ExceptionHolder exceptionUtil = LOCAL.get();
		if (exceptionUtil == null) {
			LOCAL.set(new ExceptionHolder());
			return LOCAL.get();
		}
		return exceptionUtil;
	}
	public static void remove() {
		LOCAL.remove();
	}
	
	private ExceptionHolder() {
	}
	
	private void setHandlerException(HandlerException e) {
		this.handlerException = e;
	}
	
	public HandlerException getHandlerException() {
		return handlerException;
	}
	
	public void addNotifyException(NotifyException notifyException) {
		this.notifyExceptionList.add(notifyException);
	}
	
	public void addNotifyException(StateCode stateCode) {
		this.notifyExceptionList.add(new NotifyException(stateCode));
	}
	
	public List<NotifyException> getNotifyExceptionList() {
		return notifyExceptionList;
	}
	
	
	public Result processResult() {
		Result result = Result.success();
		if (this.handlerException != null) {
			result = Result.fail(handlerException);
		}
		if (!notifyExceptionList.isEmpty()) {
			result = Result.fail(notifyExceptionList.get(0));
		}
		if (fail != null){
			result = fail;
		}
		remove();
		return result;
	}
	
	public ExceptionHolder fail(){
		return fail(null,(Object[]) null);
	}
	
	public ExceptionHolder fail(StateCode stateCode){
		return fail(stateCode,(Object[]) null);
	}
	
	public ExceptionHolder fail(StateCode stateCode,Object ... data){
		if (stateCode == null) {
			this.fail = Result.fail(ResponseCodeEnum.FAIL,data);
		} else {
			this.fail = Result.fail(stateCode,data);
		}
		return this;
	}
	
	
	
	public ExceptionHolder isTrue(boolean expression,StateCode stateCode) {
		if (!expression) {
			throw new HandlerException(stateCode);
		}
		return this;
	}
	
	public ExceptionHolder isTrueN(boolean expression,StateCode stateCode) {
		try {
			if (!expression) {
				throw new NotifyException(stateCode);
			}
		} catch (NotifyException e) {
			this.addNotifyException(e);
		}
		return this;
	}
	
	
	
}
