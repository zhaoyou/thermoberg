package com.thermoberg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 这是一个日期格式化对象
 * @author Administrator
 *
 */
public class DateUtil {
	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss" ;
	
	private static SimpleDateFormat sf = new SimpleDateFormat(FORMAT);
	
	/**
	 * 日期转化成字符串格式 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return sf.format(date) ;
	}
	
	public static Date	stringToDate(String str){
		try {
			return sf.parse(str) ;
		} catch (Exception e) {
			System.out.println("解析日期格式("+FORMAT+")发生错误："+str);
			return null ;
		}
	}
}
