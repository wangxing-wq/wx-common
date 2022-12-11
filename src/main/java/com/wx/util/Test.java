package com.wx.util;

import com.wx.inter.StateCode;
import com.wx.web.Result;

import static com.wx.util.ExceptionHolder.*;

/**
 * @author 22343
 * @version 1.0
 */
public class Test {
	
	public static void main(String[] args) {
		Console.log("test {},{}", "wx");
		process();
		Result result = instance().processResult();
		Console.log("{}",result);
	//	lease
	}
	
	public static void process(){
		instance().fail().fail(new StateCode() {
			@Override
			public String getCode() {
				return "自定义Code";
			}
			
			@Override
			public String getMessage() {
				return "自定义消息";
			}
		});
	}
	
	
	
}
