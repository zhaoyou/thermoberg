package org.ccdcc.test;

import java.util.List;

import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.CarRealView;
import org.ccdcc.entity.StartUpView;
import org.junit.Test;
import org.ccdcc.service.AllService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class CarTest extends TestCase {
	private static String a[] = {"applicationContext.xml","org/codehaus/xfire/spring/xfire.xml"} ;
	private static ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private AllService allService=null;
	
	private String projectId = "3021" ;
	
	private String key = "123" ;
	
	private Integer parentId=5;
	
	private String afterTime="2009-12-18 16:06:30";
	
	
	private Integer id=5;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//System.out.println("setup...");
		allService = (AllService)context.getBean("allservice");
	}
	//*************测试车载的实时数据********************
	
	
	//***********下面是测试获取某个车载实时数据的第一条************
	
	
	//***************加授权码验证的*************************
	@Test
	public void testGetCarRealData_authOK(){
		System.out.println();
		CarRealView crv=allService.getCarRealData_auth(key, projectId);
		System.out.print("加了授权码的\ncarReal OK flag0:"+crv.getFlag());
		System.out.print("\tID:"+crv.getId());
		System.out.print("\t工程Id"+crv.getProjectId());
		System.out.print("\t ai1"+crv.getAi1());
		System.out.print("\t ai2"+crv.getAi2());
		System.out.print("\t ai3"+crv.getAi3());
		System.out.print("\t ai4"+crv.getAi4());
		System.out.println("\t更新时间"+crv.getUpdateTime());
	}
	
	@Test
	public void testGetCarRealData_authParamNull(){
		CarRealView crv=allService.getCarRealData_auth(null, projectId);
		System.out.println("carReal null key flag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authParamNull1(){
		CarRealView crv=allService.getCarRealData_auth(key, null);
		System.out.println("carReal null projectId flag1:"+crv.getFlag());
	}
	
	
	@Test
	public void testGetCarRealData_authParamNull2(){
		CarRealView crv=allService.getCarRealData_auth("", projectId);
		System.out.println("carReal key default flag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authParamNull3(){
		CarRealView crv=allService.getCarRealData_auth(key, "");
		System.out.println("carReal projectId default flag1:"+crv.getFlag());
	}
	
	
	@Test
	public void testGetCarRealData_authDataValid(){
		CarRealView crv=allService.getCarRealData_auth(key+"20", projectId);
		System.out.println("carReal validflag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authDataValid1(){
		CarRealView crv=allService.getCarRealData_auth(key, projectId+"20");
		System.out.println("carReal validflag1:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authDataNoExists(){
		CarRealView crv=allService.getCarRealData_auth("6549875", projectId);
		System.out.println("carReal exists flag0:"+crv.getFlag());
	}
	
	//***************没加授权码验证的*************************
	@Test
	public void testGetCarRealDataOK(){
		System.out.println("没加授权码验证的测试");
		CarRealView crv=allService.getCarRealData(projectId);
		//CarRealView crv=allService.getCarRealData("1210");
		System.out.print("carReal OK flag0:"+crv.getFlag());
		System.out.print("\tID:"+crv.getId());
		System.out.print("\t工程Id:"+crv.getProjectId());
		System.out.print("\t ai1:"+crv.getAi1());
		System.out.print("\t ai2:"+crv.getAi2());
		System.out.print("\t ai3:"+crv.getAi3());
		System.out.print("\t ai4:"+crv.getAi4());
		System.out.println("\t更新时间:"+crv.getUpdateTime());
	}
	
	@Test
	public void testGetCarRealDataParamNull(){
		CarRealView crv=allService.getCarRealData(null);
		System.out.println("carReal null flag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealDataParamNull2(){
		CarRealView crv=allService.getCarRealData("");
		System.out.println("carReal default flag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealDataDataValid(){
		CarRealView crv=allService.getCarRealData(projectId+"20");
		System.out.println("carReal validflag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealDataDataNoExists(){
		CarRealView crv=allService.getCarRealData("3002");
		System.out.println("carReal exists flag0:"+crv.getFlag()+"\n\n");
	}
	
	
	
	//***********下面是测试获取某个车载实时数据的第一条记录的平均温度(加授权码)************
	@Test
	public void testGetExCarRealDataOK(){
		System.out.println("下面是测试获取某个车载实时数据的第一条记录的平均温度(加授权码)");
		String xml=allService.getExCarRealData(key, projectId);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDatanull(){
		String xml=allService.getExCarRealData(null, projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDatanull1(){
		String xml=allService.getExCarRealData(key, null);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDatanull2(){
		String xml=allService.getExCarRealData("", projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDatanull3(){
		String xml=allService.getExCarRealData(key, "");
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDataValid(){
		String xml=allService.getExCarRealData(key+"89", projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDataValid1(){
		String xml=allService.getExCarRealData(key, projectId+"89");
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDataNoExists(){
		String xml=allService.getExCarRealData(key, "989798");
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
		System.out.println();
	}
	
	
	
	
	
	
	//*************测试车载的历史记录********************
	
	
	
	
	//**********下面是测试获取某个时间(afterTime)后,某个车载某条起停记录(parentId)的历史记录**************
	//*******************加了授权码的****************************
	
	@Test
	public void testGetCarHis_authOK(){
		List<CarHisView> list=allService.getCarHis_auth(key, projectId,parentId,afterTime);
		System.out.println("加了授权码的");
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
	}
	
	
	@Test
	public void testGetCarHis_authnull(){
		List<CarHisView> list=allService.getCarHis_auth(null, projectId,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null key flag0:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull1(){
		List<CarHisView> list=allService.getCarHis_auth(key, null,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null projectId flag1:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull2(){
		List<CarHisView> list=allService.getCarHis_auth(key, projectId,null,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null parentId flag2:"+chv.getFlag());
		}
	}
	
	
	@Test
	public void testGetCarHis_authnull3(){
		List<CarHisView> list=allService.getCarHis_auth(key, projectId,parentId,null);
		for(CarHisView chv:list){
		System.out.println("CarHis null afterTime flag3:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull4(){
		List<CarHisView> list=allService.getCarHis_auth("", projectId,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis default key flag0:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull5(){
		List<CarHisView> list=allService.getCarHis_auth(key, "",parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis default projectId flag1:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authnull6(){
		List<CarHisView> list=allService.getCarHis_auth(key, projectId,parentId,"");
		for(CarHisView chv:list){
		System.out.println("CarHis default afterTime flag2:"+chv.getFlag());
		}
	}
	
	
	@Test
	public void testGetCarHis_authValid(){
		List<CarHisView> list=allService.getCarHis_auth(key+"22", projectId,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis Valid flag0:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authValid1(){
		List<CarHisView> list=allService.getCarHis_auth(key, projectId+"22",parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis Valid flag1:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHis_authNoExists(){
		List<CarHisView> list=allService.getCarHis_auth(key, projectId,98988,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis NoExist flag0:"+chv.getFlag());
		}
		System.out.println();
	}
	

	
	//**************没加授权码的********************
	
	
	@Test
	public void testGetCarHisOk(){
		List<CarHisView> list=allService.getCarHis(projectId,parentId,afterTime);
		System.out.println("没加授权码的");
		for(CarHisView chv:list){
		System.out.println("CarHis Ok? falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull(){
		List<CarHisView> list=allService.getCarHis(null,parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull1(){
		List<CarHisView> list=allService.getCarHis(projectId,null,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null1 falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull2(){
		List<CarHisView> list=allService.getCarHis(projectId,parentId,null);
		for(CarHisView chv:list){
		System.out.println("CarHis null2 falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull3(){
		List<CarHisView> list=allService.getCarHis("",parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null3 falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisnull4(){
		List<CarHisView> list=allService.getCarHis(projectId,0,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis null4 falg:"+chv.getFlag());
		}
	}
	
	
	@Test
	public void testGetCarHisnull5(){
		List<CarHisView> list=allService.getCarHis(projectId,parentId,"");
		for(CarHisView chv:list){
		System.out.println("CarHis null5 falg:"+chv.getFlag());
		}
	}
	
	
	@Test
	public void testGetCarHisValid(){
		List<CarHisView> list=allService.getCarHis(projectId+"22",parentId,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis Valid falg:"+chv.getFlag());
		}
	}
	
	@Test
	public void testGetCarHisNoExists(){
		List<CarHisView> list=allService.getCarHis(projectId,parentId+5000,afterTime);
		for(CarHisView chv:list){
		System.out.println("CarHis NoExists falg:"+chv.getFlag());
		}
	}
	
	
	
	
	
	//*************测试车载的起停记录********************
	
	
	
	
	//**********下面是测试获取某个车载在某条起停记录(id)之后的5条起停记录**************
	
	//*****************加了授权码的***********************
	@Test
	public void testGetStartUpList_authOK(){
		List<StartUpView> list=allService.getStartUpList_auth(key, projectId, id);
		System.out.println("加了授权码的");
		for(StartUpView suv:list){
			System.out.print("Startup OK Flag:"+suv.getFlag());
			System.out.print("\tId:"+suv.getId());
			System.out.print("\t开始时间:"+suv.getBeginTime());
			System.out.print("\t结束时间:"+suv.getEndTime());
			System.out.print("\t记录条数:"+suv.getRecordInterval());
			System.out.print("\t收货人:"+suv.getReceiver());
			System.out.print("\tCarrier:"+suv.getCarrier());
			System.out.println("\t更新状态:"+suv.getUpdateStatus());
		}
	}
	
	@Test
	public void testGetStartUpList_authnull(){
		List<StartUpView> list=allService.getStartUpList_auth(null, projectId, id);
		for(StartUpView suv:list){
			System.out.println("startup null key flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authnull1(){
		List<StartUpView> list=allService.getStartUpList_auth(key, null, id);
		for(StartUpView suv:list){
			System.out.println("startup null project flag1:"+suv.getFlag());
		}
	}
	
	
	@Test
	public void testGetStartUpList_authnull2(){
		List<StartUpView> list=allService.getStartUpList_auth(key, projectId, null);
		for(StartUpView suv:list){
			System.out.println("startup null id flag2:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authnull3(){
		List<StartUpView> list=allService.getStartUpList_auth("", projectId, id);
		for(StartUpView suv:list){
			System.out.println("startup default key flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authnull4(){
		List<StartUpView> list=allService.getStartUpList_auth(key, "", id);
		for(StartUpView suv:list){
			System.out.println("startup default project flag1:"+suv.getFlag());
		}
	}
	
	
	@Test
	public void testGetStartUpList_authValid(){
		List<StartUpView> list=allService.getStartUpList_auth(key+"22", projectId, id);
		for(StartUpView suv:list){
			System.out.println("startup valid flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authValid1(){
		List<StartUpView> list=allService.getStartUpList_auth(key, projectId+"22", id);
		for(StartUpView suv:list){
			System.out.println("startup valid flag1:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpList_authNoExists(){
		List<StartUpView> list=allService.getStartUpList_auth(key, projectId, id+10000);
		for(StartUpView suv:list){
			System.out.println("startup noExist flag2:"+suv.getFlag());
		}
	}
	
	
	//*****************没加授权码的************************
	@Test
	public void testGetStartUpListOk(){
		List<StartUpView> list=allService.getStartUpList(projectId, id);
		System.out.println("没加授权码的");
		for(StartUpView suv:list){
			System.out.print("Startup OK Flag:"+suv.getFlag());
			System.out.print("\tId:"+suv.getId());
			System.out.print("\t开始时间:"+suv.getBeginTime());
			System.out.print("\t结束时间:"+suv.getEndTime());
			System.out.print("\t记录条数:"+suv.getRecordInterval());
			System.out.print("\t收货人:"+suv.getReceiver());
			System.out.print("\tCarrier:"+suv.getCarrier());
			System.out.println("\t更新状态:"+suv.getUpdateStatus());
		}
	}
	
	@Test
	public void testGetStartUpListnull(){
		List<StartUpView> list=allService.getStartUpList(null, id);
		for(StartUpView suv:list){
			System.out.println("startup null projectId flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpListnull1(){
		List<StartUpView> list=allService.getStartUpList(projectId, null);
		for(StartUpView suv:list){
			System.out.println("startup null Id flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpListDefault(){
		List<StartUpView> list=allService.getStartUpList("", id);
		for(StartUpView suv:list){
			System.out.println("startup default projectId flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpListvalid(){
		List<StartUpView> list=allService.getStartUpList(projectId+"22", id);
		for(StartUpView suv:list){
			System.out.println("startup valid projectId flag0:"+suv.getFlag());
		}
	}
	
	@Test
	public void testGetStartUpListvalid1(){
		List<StartUpView> list=allService.getStartUpList(projectId, id+5000);
		for(StartUpView suv:list){
			System.out.println("startup valid id flag0:"+suv.getFlag());
		}
	}
	
}
