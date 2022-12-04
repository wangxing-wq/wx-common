package com.wx.util;

import com.wx.exception.HandlerException;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 22343
 * @version 1.0
 */
public class URLUtil {


	public static List<URL> scanPackage(Annotation [] annotations,String...packages){
		// 扫描给定包
		if (Objects.isNull(annotations)){
			annotations = new Annotation[]{};
		}
		if (Objects.isNull(packages)){
			throw new HandlerException();
		}
		List<URL> urls = new ArrayList<>();
		for (String scanPackage : packages) {
			// 处理扫描包,获取真正要处理的包
			System.out.println(scanPackage);
			URL resource = Thread.currentThread().getClass().getResource("/" + scanPackage.replace(".","/"));
			if (resource == null || resource.getFile() == null || new File(resource.getPath()).isFile()){
				continue;
			}
			// 解析文件夹
			List<String> strings = parsePackage(new File(resource.getPath()),scanPackage);
			System.out.println(strings);
			
		}
		return urls;
	}
	
	private static List<String> parsePackage(File file,String scanPackage){
		List<String> urls = new ArrayList<String>();
		
		return urls;
	}
	
	
	public static void main(String[] args) {
		System.out.println(scanPackage(null,"com.wx"));
	}
	
}
