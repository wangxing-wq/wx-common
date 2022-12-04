package com.wx.util;

import com.wx.inter.ReplaceHandler;

/**
 * @author 22343
 * @version 1.0
 */
public class Console extends InterpolationUtil{

	private String openToken = "{";
	private String closeToken = "}";
	private LogReplaceHandler replace;
	
	private static final Console CONSOLE = new Console();
	
	public static Console instance(){
		CONSOLE.replace = new LogReplaceHandler();
		return CONSOLE;
	}
	
	public static void log(String text,Object... args){
		instance().replace.setArgs(args);
		System.out.println(CONSOLE.parse(text));
		instance().replace.clear();
	}
	
	@Override
	public String getOpenToken() {
		return openToken;
	}
	
	public void setOpenToken(String openToken) {
		this.openToken = openToken;
	}
	
	@Override
	public String getCloseToken() {
		return closeToken;
	}
	
	@Override
	protected String replace(String content) {
		return replace.replace(content);
	}
	
	public void setCloseToken(String closeToken) {
		this.closeToken = closeToken;
	}
	
	public LogReplaceHandler getReplace() {
		return replace;
	}
	
	private static class LogReplaceHandler implements ReplaceHandler{
		
		private Object [] args = {};
		private int index;
		
		public LogReplaceHandler(){}
		
		public LogReplaceHandler(Object[] args){
			this.args = args;
		}
		
		@Override
		public String replace(String str) {
			if (args.length == index) {
				return "";
			}
			return args[index++].toString();
		}
		
		public void setArgs(Object[] args) {
			this.args = args;
		}
		public void clear(){
			index = 0;
		}
		
	}
	
	
	
}
