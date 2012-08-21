package com.thermoberg.util;

import java.util.List;

import com.thermoberg.entity.AlarmInfo;
import com.thermoberg.entity.CarAlarmInfo;
import com.thermoberg.entity.CarDataNoChange;
import com.thermoberg.entity.HistRefData;
import com.thermoberg.flex.DataService;

public class Test {
	
		private static DataService d = new DataService();
	
		public static void main(String[] args) {
//			testAlarm();
//			testConnection();
//			testCarAlarm() ;
//			testRefHist();
			testCarNoChange();
		}
		
		public static void testCarNoChange(){
			Integer day=30;
			List<CarDataNoChange> list = d.getCar_noChange(day);
			System.out.println("Name\t\t持续时间");
			for(CarDataNoChange cdc:list){
				System.out.println(cdc.getNamestr()+"\t\t"+cdc.getTimediff()+"天");
			}
		}
		
		public static void testAlarm(){
			List<AlarmInfo> list =  d.getAlarm() ;
			System.out.println("\t\t以下机构发生了报警");
			System.out.println("BranchId\tBranchName\tUpdateTime");
			for (AlarmInfo alarmInfo : list) {
				System.out.print(""+alarmInfo.getBranchId());
				System.out.print("\t\t"+alarmInfo.getBranchName());
				System.out.println("\t"+alarmInfo.getUpdatetime());
			}
		}
		
		public static void testConnection(){
			
			List<AlarmInfo> list =  d.getConnection();
			System.out.println("\t\t以下机构断开了连接");
			System.out.println("BranchId\tBranchName\tUpdateTime");
			for (AlarmInfo alarmInfo : list) {
				System.out.print(""+alarmInfo.getBranchId());
				System.out.print("\t\t"+alarmInfo.getBranchName());
				System.out.println("\t"+alarmInfo.getUpdatetime());
			}
		}
		
		public static void testCarAlarm(){
			List<CarAlarmInfo> list = d.getCarAlarm() ;
			System.out.println("\t\t车载发生报警");
			System.out.println("");
			for (CarAlarmInfo carAlarmInfo : list) {
				System.out.print(""+carAlarmInfo.getProjectId());
				System.out.print("\t\t"+carAlarmInfo.getBranchName());
				System.out.println(carAlarmInfo.getUpdateTime());
			}
		}
		
		public static void testRefHist(){
			List<HistRefData> refHislist = d.getHistRefData();
			for(HistRefData refhis:refHislist){
				System.out.println(refhis.getProjectId()+" "+refhis.getUpdateTime()+" "+refhis.getNeiId()+" "+refhis.getAi1()+" "
						+refhis.getAi2()+" "+refhis.getAi3()+" "+refhis.getAi4()+" "+refhis.getAi5()+" "+refhis.getAi6()+" "
						+refhis.getAi7()+" "+refhis.getAi8()+" "+refhis.getAi9()+" "+refhis.getAi10()+" "+refhis.getAi11()+" "
						+refhis.getAi12()+" "+refhis.getAi13()+" "+refhis.getAi14()+" "+refhis.getAi15()+" "+refhis.getAi16()+" "
						+refhis.getAi17()+" "+refhis.getAi18()+" "+refhis.getAi19()+" "+refhis.getAi20()+" "+refhis.getAi21()+" "
						+refhis.getAi22()+" "+refhis.getAi23()+" "+refhis.getAi24()+" "+refhis.getAi25()+" "+refhis.getAi26()+" "
						+refhis.getAi27()+" "+refhis.getAi28()+" "+refhis.getAi29()+" "+refhis.getAi30()+" "+refhis.getAi31()+" "+refhis.getAi32());
			}
		}
}
