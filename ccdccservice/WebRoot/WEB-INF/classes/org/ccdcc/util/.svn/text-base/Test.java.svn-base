package org.ccdcc.util;

import java.util.List;

import org.ccdcc.biz.StartUpBiz;
import org.ccdcc.dao.StartUpDao;
import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.StartUpView;
import org.ccdcc.service.AllService;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
		
		public static String [] a = {"applicationContext.xml","org/codehaus/xfire/spring/xfire.xml"} ;
	
		public static	ApplicationContext context  =
			new ClassPathXmlApplicationContext(a) ;
	
		public static void main(String[] args) {
			testStartUp() ;
			//testCarHis() ;
//			
//			StartUpBiz startupbiz = (StartUpBiz)context.getBean("startupbiz");
//			List<StartUpView> list =  startupbiz.getListByAfterId("1200", 1);
//			System.out.println(list.size());
//			StartUpView view = list.get(2);
//			System.out.println(view.getId()+view.getBeginTime().toString());
//			System.out.println(view.getShipper()) ;
//			System.out.println(view.getShipper().length());
			
		
		}
		
		/**
		 * 测试移动车载
			 */
		public static void testStartUp(){
			AllService s = (AllService)context.getBean("allservice") ;
			
			List<StartUpView> list = s.getStartUpList("3006",0)  ;
			
			for (StartUpView startUpView : list) {
				if(startUpView.getFlag().equals(2)){					
//					System.out.println(startUpView.getBeginAddress());
//					System.out.println(startUpView.getEndAddress());
//					System.out.println(startUpView.getRecordInterval());
				//	byte [] zi = startUpView.getCarrier().getBytes() ;
					System.out.println("承运人: "+startUpView.getCarrier());
//					for(int i=0;i<zi.length;i++)
//					System.out.print(zi[i]);
//					System.out.println("\r ");
//					System.out.println("\n");
					//System.out.println("是否存在："+(startUpView.getCarrier().indexOf("0")));
					//System.out.println(startUpView.getCarrier());		
//					System.out.println(startUpView.getReceiver());
//					System.out.println(startUpView.getShipper());
//					System.out.println(startUpView.getBeginTime());
//					System.out.println(startUpView.getEndTime());
//					System.out.println(startUpView.getFlag());
//					System.out.println(startUpView.getId());
//					System.out.println(startUpView.getRecordInterval());
//					System.out.println(startUpView.getUpdateStatus());
				}	
			}
		}
	
		
		/**
		 * 测试移动车载历史数据
		
		public static void testCarHis(){
			CarHisService service = (CarHisService)context.getBean("carHisService") ;
			
			List<CarHisView> list = service.getCarHis("1200", "2009-8-24 16:56:04", "2009-8-25 16:56:04") ;
			
			for (CarHisView carHisView : list) {
				if(carHisView.getFlag().equals(new Integer(2))){
					System.out.println(carHisView.getAi1());
					System.out.println(carHisView.getAi2());
					System.out.println(carHisView.getAi3());
					System.out.println(carHisView.getAi4());
					System.out.println(carHisView.getAlarmStatus());
					System.out.println(carHisView.getFlag());
					System.out.println(carHisView.getId());
					System.out.println(carHisView.getLatitude());
					System.out.println(carHisView.getLatitude_dir());
					System.out.println(carHisView.getLongitude());
					System.out.println(carHisView.getLatitude_dir());
					System.out.println(carHisView.getParentId());
					System.out.println(carHisView.getUpdateTime());
				}
				
			}
		}
		
		 */
		
		
		
}
