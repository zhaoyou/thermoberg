package org.ccdcc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统通用的类，执行常用的操作
 * @author Administrator
 *
 */
public class Util {
	public final static String FMTDATE = "yyyy-MM-dd HH:mm:ss" ;
	
	public final static SimpleDateFormat sf = new SimpleDateFormat(FMTDATE);
	/**
	 * 日期对象转化成yyyy-MM-dd HH:mm:ss格式
	 * @param date
	 * @return
	 */
	public  String getToString(Date date){
		return sf.format(date);
	}
	
	/**
	 * 把yyyy-MM-dd HH:mm:ss格式的字符串解析成Date
	 * @param source
	 * @return
	 */
	public  Date getToDate(String source) throws Exception{
		try {
			return  sf.parse(source);
		} catch (Exception e) {
			return null ;
		}
	}
	
}
