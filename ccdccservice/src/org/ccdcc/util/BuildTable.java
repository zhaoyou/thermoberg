package org.ccdcc.util;

/**
 * 这是用来动态构造表达通用类
 * @author Administrator
 *
 */
public class BuildTable {
		public static String BuildStartUpTable(String projectId){
			return "TbccHistStartUp_"+projectId+"_1" ;
		}
		
		public static String BuildCarHisTable(String projectId){
			return "TbccHistData_Car_"+projectId+"_1" ;
		}
}
