package com.wx.util;

/**
 * @author 22343
 * @version 1.0
 * 替换值工具
 */
public abstract class InterpolationUtil {
	
	private static final char ESCAPES = '\\';
	
	public String parse(String text) {
		if (text == null || text.isEmpty()) {
			return "";
		}
		// search open token
		int start = text.indexOf(getOpenToken());
		if (start == -1) {
			return text;
		}
		char[] src = text.toCharArray();
		int offset = 0;
		final StringBuilder builder = new StringBuilder();
		StringBuilder expression = null;
		do {
			if (start > 0 && src[start - 1] == ESCAPES) {
				// this open token is escaped. remove the backslash and continue.
				builder.append(src,offset,start - offset - 1).append(getOpenToken());
				offset = start + getOpenToken().length();
			} else {
				// found open token. let's search close token.
				if (expression == null) {
					expression = new StringBuilder();
				} else {
					expression.setLength(0);
				}
				builder.append(src,offset,start - offset);
				offset = start + getOpenToken().length();
				int end = text.indexOf(getCloseToken(),offset);
				while (end > -1) {
					if (end > offset && src[end - 1] == '\\') {
						// this close token is escaped. remove the backslash and continue.
						expression.append(src,offset,end - offset - 1).append(getCloseToken());
						offset = end + getCloseToken().length();
						end = text.indexOf(getCloseToken(),offset);
					} else {
						expression.append(src,offset,end - offset);
						break;
					}
				}
				if (end == -1) {
					// close token was not found.
					builder.append(src,start,src.length - start);
					offset = src.length;
				} else {
					builder.append(replace(expression.toString()));
					offset = end + getCloseToken().length();
				}
			}
			start = text.indexOf(getOpenToken(),offset);
		} while (start > -1);
		if (offset < src.length) {
			builder.append(src,offset,src.length - offset);
		}
		return builder.toString();
	}
	
	
	protected abstract String getOpenToken();
	
	protected abstract String getCloseToken();
	
	
	protected abstract String replace(String content);
}
