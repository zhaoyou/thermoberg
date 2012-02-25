package org.ccdcc.test;

import org.ccdcc.biz.CarRealBiz;
import org.ccdcc.entity.CarRealView;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class CarRealTest extends TestCase {
	private static String a[] = {"applicationContext.xml","org/codehaus/xfire/spring/xfire.xml"} ;
	private static ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private CarRealBiz carRealbiz=null;
	
	private String projectId = "3021" ;
	
	private String key = "123" ;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//System.out.println("setup...");
		carRealbiz = (CarRealBiz)context.getBean("carrealbiz");
	}
	
	//*************测试车载的实时数据********************
	
	
	//***********下面是测试获取某个车载实时数据的第一条************
	
	//***************加授权码验证的*************************
	@Test
	public void testGetCarRealData_authOK(){
		System.out.println("加授权码验证的测试");
		CarRealView crv=carRealbiz.getRealData(key, projectId);
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
	public void testGetCarRealData_authParamNull(){
		CarRealView crv=carRealbiz.getRealData(null, projectId);
		System.out.println("carReal null key flag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authParamNull1(){
		CarRealView crv=carRealbiz.getRealData(key, null);
		System.out.println("carReal null projectId flag1:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authParamNull2(){
		CarRealView crv=carRealbiz.getRealData("", projectId);
		System.out.println("carReal key default flag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authParamNull3(){
		CarRealView crv=carRealbiz.getRealData(key, "");
		System.out.println("carReal projectId default flag1:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authDataValid(){
		CarRealView crv=carRealbiz.getRealData(key+"20", projectId);
		System.out.println("carReal validflag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authDataValid1(){
		CarRealView crv=carRealbiz.getRealData(key, projectId+"20");
		System.out.println("carReal validflag1:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealData_authDataNoExists(){
		CarRealView crv=carRealbiz.getRealData("6549875", projectId);
		System.out.println("carReal exists flag0:"+crv.getFlag()+"\n\n");
	}
	
	//***************没加授权码验证的*************************
	@Test
	public void testGetCarRealDataOK(){
		System.out.println("没加授权码验证的测试");
		//CarRealView crv=carRealbiz.getRealData(projectId);
		CarRealView crv=carRealbiz.getRealData(projectId);
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
		CarRealView crv=carRealbiz.getRealData(null);
		System.out.println("carReal null flag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealDataParamNull2(){
		CarRealView crv=carRealbiz.getRealData("");
		System.out.println("carReal default flag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealDataDataValid(){
		CarRealView crv=carRealbiz.getRealData(projectId+"20");
		System.out.println("carReal validflag0:"+crv.getFlag());
	}
	
	@Test
	public void testGetCarRealDataDataNoExists(){
		CarRealView crv=carRealbiz.getRealData("3002");
		System.out.println("carReal exists flag0:"+crv.getFlag()+"\n\n");
	}
	
	
	
	//***********下面是测试获取某个车载实时数据的第一条记录的平均温度(加授权码)************
	@Test
	public void testGetExCarRealDataOK(){
		String xml=carRealbiz.getExRealData(key, projectId);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDatanull(){
		String xml=carRealbiz.getExRealData(null, projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDatanull1(){
		String xml=carRealbiz.getExRealData(key, null);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDatanull2(){
		String xml=carRealbiz.getExRealData("", projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDatanull3(){
		String xml=carRealbiz.getExRealData(key, "");
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	
	@Test
	public void testGetExCarRealDataValid(){
		String xml=carRealbiz.getExRealData(key+"89", projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDataValid1(){
		String xml=carRealbiz.getExRealData(key, projectId+"89");
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetExCarRealDataNoExists(){
		String xml=carRealbiz.getExRealData(key, "989798");
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
}