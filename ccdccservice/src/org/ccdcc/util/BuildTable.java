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
		
		public static String buildRefHisTable(String projectId, String devId,
				String devType) {
			if (devType.equalsIgnoreCase(DevType.dev_standard.toString())) {
				return "TbccHistData_Ref_" + projectId + "_" + devId;
			} else if (devType.equalsIgnoreCase(DevType.dev_ex.toString())){
				return "TbccHistData_Ref_Ex_" + projectId + "_" + devId;
			} else {
				throw new RuntimeException("unsupport this refDevType " + devType);
			}
		}
}
