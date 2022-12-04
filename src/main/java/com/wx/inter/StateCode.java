package com.wx.inter;

import java.text.MessageFormat;

/**
 * @version 1.0
 * @author 王兴
 * @date 2022/9/24 19:36
 * 状态码
 */
public interface StateCode {
	
	/**
	 * 获取Code
	 * @return 返回一个字符串code
	 */
	String getCode();
	/**
	 * 获取Code Message信息
	 * @return 返回一个字符串code
	 */
	String getMessage();
	
	/**
	 * 支持插值
	 * @param param 插入参数
	 * @return 插入值后模板
	 */
	default String getFormatMessage(Object[] param) {
		if (param == null){
			return getMessage();
		}
		return MessageFormat.format(getMessage(),param);
	}
}
