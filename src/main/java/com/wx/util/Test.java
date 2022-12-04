package com.wx.util;

import com.wx.inter.StateCode;

/**
 * @author 22343
 * @version 1.0
 */
public class Test {
	
	public static void main(String[] args) {
		Console.log("test {},{}", "wx");
		ExceptionHolder.instance().isTrue(false,new StateCode() {
			
			@Override
			public String getCode() {
				return "10001";
			}
			
			@Override
			public String getMessage() {
				return "测试失败";
			}
		});
		System.out.println(ExceptionHolder.instance().processResult());
	}
	
}
