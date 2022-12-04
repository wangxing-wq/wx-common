package com.wx.util;

/**
 * @version 1.0
 * @author 王兴
 * @date 2022/9/24 20:24
 * 执行一个功能
 */
public class Assert {

	public Assert() {
	}

	public static void state(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}




}
