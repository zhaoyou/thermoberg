package org.ccdcc.test;

import java.util.List;

import org.ccdcc.biz.CarHisBiz;
import org.ccdcc.entity.CarHisView;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class CarHisTest extends TestCase {

	private static String a[] = {"applicationContext.xml","org/codehaus/xfire/spring/xfire.xml"} ;
	private static ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private CarHisBiz carHisBiz=null;
	
	private String projectId = "3021" ;
	
	private String key = "123" ;
	
	private Integer parentId=5;
	
	private String afterTime="2009-12-18 16:06:30";
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//System.out.println("setup...");
		carHisBiz = (CarHisBiz)context.getBean("carhisbiz");
	}
	
	
	//*************测试车载的历史记录********************
	//**********下面是测试获取某个时间(afterTime)后,某个车载某条起停记录(parentId)的历史记录**************
	
	//*******************加了授权码的****************************
	@Test
	public void testGetCarHis_authOK(){
		List<CarHisView> list=carHisBiz.getCarList2(key, projectId,parentId,afterTime);
		for(CarHisView chv:list){
			System.out.print("CarHis OK flag:"+chv.getFlag());
			System.out.print("\tId:"+chv.getId());
			System.out.print("\t工程Id:"+chv.getParentId());
			System.out.print("\t ai1:"+chv.getAi1());
			System.out.print("\t ai2:"+chv.getAi2());
			System.out.print("\t ai3:"+chv.getAi3());
			System.out.print("\t ai4:"+chv.getAi4());
			System.out.print("\t报警状态:"+chv.getAlarmStatus());
			System.out.println("\t更新时间"+chv.getUpdateTime());
		}
		System.out.println("\n");
	}
	
	
	@Test
	public void testGetCarHis_authnull(){
		List<CarHisView> list=carHisBiz.getCarList2(null, projectId,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null key flag0:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull1(){
		List<CarHisView> list=carHisBiz.getCarList2(key, null,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null projectId flag1:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull2(){
		List<CarHisView> list=carHisBiz.getCarList2(key, projectId,null,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null parentId flag2:"+chv.getFlag());
		}
	}
	
	
	@Test
	public void testGetCarHis_authnull3(){
		List<CarHisView> list=carHisBiz.getCarList2(key, projectId,parentId,null);
		for(CarHisView chv:list){
		System.out.println("CarHis null afterTime flag3:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull4(){
		List<CarHisView> list=carHisBiz.getCarList2("", projectId,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis default key flag0:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull5(){
		List<CarHisView> list=carHisBiz.getCarList2(key, "",parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis default projectId flag1:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull6(){
		List<CarHisView> list=carHisBiz.getCarList2(key, projectId,parentId,"");
		for(CarHisView chv:list){
		System.out.println("CarHis default afterTime flag2:"+chv.getFlag());
		}
	}
	
	
	
	@Test
	public void testGetCarHis_authValid(){
		List<CarHisView> list=carHisBiz.getCarList2(key+"22", projectId,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("Valid flag0:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authValid1(){
		List<CarHisView> list=carHisBiz.getCarList2(key, projectId+"2",parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("Valid flag1:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authNoExists(){
		List<CarHisView> list=carHisBiz.getCarList2(key, projectId,98988,afterTime);
		for(CarHisView chv:list){
		System.out.println("NoExist flag0:"+chv.getFlag());
		}
	}
	
	
	
	//**************没加授权码的********************
	
	
	@Test
	public void testGetCarHisOk(){
		List<CarHisView> list=carHisBiz.getCarList2(projectId,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("Ok? falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull(){
		List<CarHisView> list=carHisBiz.getCarList2(null,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("null falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull1(){
		List<CarHisView> list=carHisBiz.getCarList2(projectId,null,afterTime);
		for(CarHisView chv:list){
		System.out.println("null1 falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull2(){
		List<CarHisView> list=carHisBiz.getCarList2(projectId,parentId,null);
		for(CarHisView chv:list){
		System.out.println("null2 falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull3(){
		List<CarHisView> list=carHisBiz.getCarList2("",parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("null3 falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull4(){
		List<CarHisView> list=carHisBiz.getCarList2(projectId,0,afterTime);
		for(CarHisView chv:list){
		System.out.println("null4 falg:"+chv.getFlag());
		}
	}
	
	
	@Test
	public void testGetCarHisnull5(){
		List<CarHisView> list=carHisBiz.getCarList2(projectId,parentId,"");
		for(CarHisView chv:list){
		System.out.println("null5 falg:"+chv.getFlag());
		}
	}
	
	
	@Test
	public void testGetCarHisValid(){
		List<CarHisView> list=carHisBiz.getCarList2(projectId+"22",parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("Valid falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisNoExists(){
		List<CarHisView> list=carHisBiz.getCarList2(projectId,parentId+5000,afterTime);
		for(CarHisView chv:list){
		System.out.println("NoExists falg:"+chv.getFlag());
		}
	}
	
	
	
}
