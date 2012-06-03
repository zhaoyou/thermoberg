package org.ccdcc.test;

import org.ccdcc.biz.RefRealBiz;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class RefRealBizTest extends TestCase {
	
	private static String a[] = {"applicationContext.xml","org/codehaus/xfire/spring/xfire.xml"} ;
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private RefRealBiz refRealBiz = null ;
	
	private String projectId = "2015" ;
	
	private String key = "1234" ;
	
	private Integer realRefId = 1;
	private Integer refType = 1 ;
	private Integer floorNo = 1 ;
	private Integer floorType = 1 ;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//System.out.println("setup...");
		refRealBiz = (RefRealBiz)context.getBean("refrealbiz");
	}
	
	
	@Test
	public void testGetRealDataOK(){
		System.out.println();
		String xml = refRealBiz.getRefReal(key, projectId,realRefId,refType,floorType,floorNo);
		System.out.println(xml) ;
	}
	
	@Test
	public void testGetRealDataParamNull1(){
		String xml = refRealBiz.getRefReal(null, projectId, realRefId, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetRealDataParamNull2(){
		String xml = refRealBiz.getRefReal(key, null, realRefId, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetRealDataParamNull3(){
		String xml = refRealBiz.getRefReal(key, projectId, null, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetRealDataParamNull4(){
		String xml = refRealBiz.getRefReal(key, projectId, realRefId, null, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetRealDataParamNull5(){
		String xml = refRealBiz.getRefReal(key, projectId, realRefId, refType, null, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetRealDataParamNull6(){
		String xml = refRealBiz.getRefReal(key, projectId, realRefId, refType, floorType, null);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetRealDataParamNull7(){
		String xml = refRealBiz.getRefReal("", projectId, realRefId, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetRealDataParamNull8(){
		String xml = refRealBiz.getRefReal(key, "", realRefId, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetRealDataCodeValid(){
		String xml = refRealBiz.getRefReal(key+"2", projectId, realRefId, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	
	@Test
	public void testGetRealDataCodeValid2(){
		String xml = refRealBiz.getRefReal(key, projectId+"2", realRefId, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetRealDataRefNoExists(){
		//当前冷库编号自定义设置为大于100的数、不存在这样的冷库编号
		String xml = refRealBiz.getRefReal(key, projectId, realRefId+100, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}

	
	@Test
	public void testGetRealDataRefDataNoExists(){
		//当前实际冷库编号为4的冷库没有实时数据
		String xml = refRealBiz.getRefReal(key, projectId, realRefId+20, refType, floorType, floorNo);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	
	@Test
	public void testGetRealDataAiNoExists(){
		//测试该用例的时候，需要操作数据库。建立一个不配置探头的冷库
		String xml = refRealBiz.getRefReal(key, projectId, 4, 2, 1, 1);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
						"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}

	
	
	
	//***********下面是测试获取一个工程的所有冷库数据***********
	
	
	
	@Test 
	public void testGetAllRealDataOK(){
		String xml = refRealBiz.getRefReal(key, projectId);
		System.out.println(xml);
	}
	
	
	@Test
	public void testGetAllRealDataParamNull(){
		String xml = refRealBiz.getRefReal(null, projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		System.out.println(xml);
	}
	
	@Test
	public void testGetAllRealDataParamNull2(){
		String xml = refRealBiz.getRefReal(key, null);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		
	}
	
	@Test
	public void testGetAllRealDataParamNull3(){
		String xml = refRealBiz.getRefReal(key, "");
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		
	}
	
	@Test
	public void testGetAllRealDataParamNull4(){
		String xml = refRealBiz.getRefReal("", projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
		
	}
	
	
	@Test
	public void testGetAllRealDataValid(){
		String xml = refRealBiz.getRefReal(key+"2", projectId);
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
	}
	
	
	@Test
	public void testGetAllRealDataValid2(){
		String xml = refRealBiz.getRefReal(key, projectId+"2");
		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
				"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetAllRealDataRefNoExists(){
		
		//测试该步骤时，需要访问没有配置冷库的工程、
//		String xml = refRealBiz.getRefReal(key, projectId+"2");
//		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
//				"<datas><result_state>0</result_state></datas>", xml);
	}
	
	
	@Test
	public void testGetAllRealDataAiNoExists(){
		//测试该步骤时，需要配置一个冷库，且该冷库没有配置任何探头
//		String xml = refRealBiz.getRefReal(key, projectId+"2");
//		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
//				"<datas><result_state>0</result_state></datas>", xml);
	}
	
	@Test
	public void testGetAllRealDataRefDataNoExists(){
		//测试该步骤是，需要把该工程的所有实时数据全部清空
//		String xml = refRealBiz.getRefReal(key, projectId);
//		assertEquals("<?xml version='1.0' encoding='utf-8' ?>"+
//				"<datas><result_state>0</result_state></datas>", xml);
	}
	
	
	
	
}
