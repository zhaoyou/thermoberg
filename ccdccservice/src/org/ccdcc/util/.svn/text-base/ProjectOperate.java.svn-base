package org.ccdcc.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * 为了解决旧版本之前没有加授权码
 * 之后的版本都必须加授权码调用数据，如果是与自定义的几个工程
 * 则直接通过，允许不授权直接调用
 * @author zhaoyou
 *
 */
public class ProjectOperate {
	
	private static  List<String> exists  = new ArrayList<String>();
	
	static {
		try {
			Properties pro = new Properties();
			pro.load(ProjectOperate.class.getResourceAsStream("projects.properties")) ;
			String count = pro.getProperty("count") ;
			if(count!=null && !count.equals("0")){
				Integer number = Integer.parseInt(count) ;
				for (int i = 1; i <= number; i++) {
					exists.add(pro.getProperty("projectId"+i));
				}
			}
	} catch (Exception e) {
		System.out.println("初始化连接池失败: "+e.getMessage());
	}
	}
	
	/**
	 * 是否过滤传递过来的工程标识Id
	 * @param projectId				工程标识Id
	 * @return
	 */
	public static boolean EXISTSPRJS(String projectId){
		
		if(projectId==null || projectId.equals(""))
			return false ;
		
		return exists.contains(projectId);
	}

}
