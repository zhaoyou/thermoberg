package org.ccdcc.test;

import java.util.List;

import org.ccdcc.biz.StartUpBiz;
import org.ccdcc.entity.StartUpView;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class StartUpTest extends TestCase {
	private static String a[] = {"applicationContext.xml","org/codehaus/xfire/spring/xfire.xml"} ;
	private static ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private StartUpBiz startUpBiz=null;
	
	private String projectId = "3021" ;
	
	private String key = "123" ;
	
	private Integer id=5;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//System.out.println("setup...");
		startUpBiz = (StartUpBiz)context.getBean("startupbiz");
	}
	
	//*************测试车载的起停记录********************
	//**********下面是测试获取某个车载在某条起停记录(id)之后的5条起停记录**************
	
	//*****************加了授权码的***********************
	@Test
	public void testGetStartUpList_authOK(){
		System.out.println("加了授权码的");
		List<StartUpView> list=startUpBiz.getListByAfterId(key, projectId, id);
		System.out.println(list.size());
		for(StartUpView suv:list){
			System.out.print("Startup OK Flag:"+suv.getFlag());
			System.out.print("\tId:"+suv.getId());
			System.out.print("\t开始时间:"+suv.getBeginTime());
			System.out.print("\t结束时间:"+suv.getEndTime());
			System.out.print("\t记录条数:"+suv.getRecordInterval());
			System.out.print("\t收货人:"+suv.getReceiver());
			System.out.print("\tCarrier:"+suv.getCarrier());
			
			System.out.print("\ttlimitType:"+suv.getTlimitType());
			System.out.println("\t更新状态:"+suv.getUpdateStatus());
			
		}
	}
	
	@Test
	public void testGetStartUpList_authnull(){
		List<StartUpView> list=startUpBiz.getListByAfterId(null, projectId, id);
		for(StartUpView suv:list){
			System.out.println("startup null key flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authnull1(){
		List<StartUpView> list=startUpBiz.getListByAfterId(key, null, id);
		for(StartUpView suv:list){
			System.out.println("startup null project flag1:"+suv.getFlag());
		}
	}
	
	
	@Test
	public void testGetStartUpList_authnull2(){
		List<StartUpView> list=startUpBiz.getListByAfterId(key, projectId, null);
		for(StartUpView suv:list){
			System.out.println("startup null id flag2:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authnull3(){
		List<StartUpView> list=startUpBiz.getListByAfterId("", projectId, id);
		for(StartUpView suv:list){
			System.out.println("startup default key flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authnull4(){
		List<StartUpView> list=startUpBiz.getListByAfterId(key, "", id);
		for(StartUpView suv:list){
			System.out.println("startup default project flag1:"+suv.getFlag());
		}
	}
	
	
	@Test
	public void testGetStartUpList_authValid(){
		List<StartUpView> list=startUpBiz.getListByAfterId(key+"22", projectId, id);
		for(StartUpView suv:list){
			System.out.println("startup valid flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authValid1(){
		List<StartUpView> list=startUpBiz.getListByAfterId(key, projectId+"22", id);
		for(StartUpView suv:list){
			System.out.println("startup valid flag1:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authNoExists(){
		List<StartUpView> list=startUpBiz.getListByAfterId(key, projectId, id+10000);
		for(StartUpView suv:list){
			System.out.println("startup noExist flag2:"+suv.getFlag()+"\n\n");
		}
	}
	
	
	//*****************没加授权码的************************
	@Test
	public void testGetStartUpListOk(){
		System.out.println("没加授权码的");
		List<StartUpView> list=startUpBiz.getListByAfterId(projectId, id);
		for(StartUpView suv:list){
			System.out.print("Startup OK Flag:"+suv.getFlag());
			System.out.print("\tId:"+suv.getId());
			System.out.print("\t开始时间:"+suv.getBeginTime());
			System.out.print("\t结束时间:"+suv.getEndTime());
			System.out.print("\t记录条数:"+suv.getRecordInterval());
			System.out.print("\t收货人:"+suv.getReceiver());
			System.out.print("\tCarrier:"+suv.getCarrier());
			System.out.print("\ttlimitType:"+suv.getTlimitType());
			System.out.println("\t更新状态:"+suv.getUpdateStatus());
		}
	}
	
	
	@Test
	public void testGetStartUpListnull(){
		List<StartUpView> list=startUpBiz.getListByAfterId(null, id);
		for(StartUpView suv:list){
			System.out.println("startup null projectId flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpListnull1(){
		List<StartUpView> list=startUpBiz.getListByAfterId(projectId, null);
		for(StartUpView suv:list){
			System.out.println("startup null Id flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpListDefault(){
		List<StartUpView> list=startUpBiz.getListByAfterId("", id);
		for(StartUpView suv:list){
			System.out.println("startup default projectId flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpListvalid(){
		List<StartUpView> list=startUpBiz.getListByAfterId(projectId+"22", id);
		for(StartUpView suv:list){
			System.out.println("startup valid projectId flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpListvalid1(){
		List<StartUpView> list=startUpBiz.getListByAfterId(projectId, id+5000);
		for(StartUpView suv:list){
			System.out.println("startup valid id flag0:"+suv.getFlag());
		}
	}
	
}
