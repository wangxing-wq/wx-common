package com.wx.web;

import com.wx.enums.ResponseCodeEnum;
import com.wx.inter.StateCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王兴
 * @version 1.0
 * @date 2022/9/24 18:39
 * 返回值结果,这个类的作用是为对请求端的响应定义
 * 必定包含的结构
 * 响应码
 * 响应信息
 * 响应数据
 */
public class Result extends HashMap<String, Object>{

	private static final Object [] DATA_EMPTY = {};
	
	private Result(String code, String message, Map<String, Object> data) {
		this.put("code", code);
		this.put("message", message);
		this.putAll(data);
	}

	public static Result success(Object... data) {
		return success(ResponseCodeEnum.SUCCESS, data);
	}

	public static Result success(StateCode status, Object... data) {
		return define(status, data);
	}

	public static Result fail(Object...data) {
		return fail(ResponseCodeEnum.FAIL, data);
	}

	public static Result fail(StateCode status, Object... data) {
		return define(status, data);
	}

	public static Result define(StateCode status, Object ... data) {
		if (status == null){
			status = ResponseCodeEnum.FAIL;
		}
		if (data == null){
			data = DATA_EMPTY;
		}
		int length = data.length >> 2;
		if (length >> 1 == 1 && data.length != 1) {
			// 这个不是常规意义上的错误,要抛出异常
			throw new IllegalArgumentException("K V 对无效,键值对不匹配,请检查");
		}
 		Map<String, Object> map = new HashMap<>(length);
		if (data.length == 1){
			map.put("data",data[0]);
		}else{
			for (int i = 0; i < data.length; i = i + 2) {
				map.put(data[i].toString(), data[i + 1]);
			}
		}
		return new Result(status.getCode(), status.getMessage(), map);
	}

	public String getCode() {
		return (String) this.get("code");
	}

	public void setCode(String code) {
		this.put("code",code);
	}

	public String getMessage() {
		return (String) this.get("message");
	}

	public void setMessage(String message) {
		this.put("message",message);
	}

	public Result add(String key, Object value) {
		this.put(key, value);
		return this;
	}


}
