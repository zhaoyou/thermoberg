package org.fdapservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转化处理函数
 * @author zhaoyou
 *
 */
public class DateUtil {
public final static String FMTDATE = "yyyy-MM-dd HH:mm:ss" ;
	
	public final static SimpleDateFormat sf = new SimpleDateFormat(FMTDATE);
	/**
	 * 日期对象转化成yyyy-MM-dd HH:mm:ss格式
	 * @param date
	 * @return
	 */
	public static String getToString(Date date){
		return sf.format(date);
	}
	
	/**
	 * 把yyyy-MM-dd HH:mm:ss格式的字符串解析成Date
	 * @param source
	 * @return
	 */
	public  static  Date getToDate(String source) throws Exception{
		try {
			return  sf.parse(source);
		} catch (Exception e) {
			return null ;
		}
	}
	
}
