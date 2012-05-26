package org.ccdcc.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.ccdcc.biz.RefHisBiz;
import org.ccdcc.entity.RefHisData;
import org.ccdcc.service.AllService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class RefHisTest extends TestCase {
	private static String a[] = {"applicationContext.xml","org/codehaus/xfire/spring/xfire.xml"} ;
	private static ApplicationContext context = new ClassPathXmlApplicationContext(a);
	
	private RefHisBiz refHisBiz = null;
	
	private AllService allService = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//System.out.println("setup...");
		allService = (AllService)context.getBean("allservice");
	}
	
	@Test
	public void testUploadStandard() throws Exception {
		String projectId = "1000";
		String devId = "1";
		String devType = "dev_standard";
		List<RefHisData> list = new ArrayList<RefHisData>();
		RefHisData his = new RefHisData();
		his.setHDate(new Date());
		his.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-05-13 12:00:00"));
		his.setAi1(12.12);
		his.setAi2(22.12);
		his.setAi3(32.12);
		his.setAi4(42.12);
		his.setAi5(52.12);
		his.setAi6(62.12);
		his.setAi7(72.12);
		his.setAi8(82.12);
		his.setAi9(92.12);
		his.setAi10(102.12);
		his.setAi11(112.12);
		his.setAi12(122.12);
		his.setAi13(123.12);
		his.setAi14(124.12);
		his.setAi15(125.12);
		his.setAi16(126.12);

		his.setRef1AlarmStatus(0);
		his.setRef2AlarmStatus(1);
		his.setRef3AlarmStatus(2);
		his.setRef4AlarmStatus(3);

		list.add(his);

		System.out.println(allService.uploadRefHisData(projectId, devId, devType, list));
	}
	
	@Test
	public void testUploadEx() throws Exception {
		String projectId = "1000";
		String devId = "100";
		String devType = "dev_ex";
		List<RefHisData> list = new ArrayList<RefHisData>();
		RefHisData his = new RefHisData();
		his.setHDate(new Date());
		his.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-05-13 13:00:00"));
		his.setAi1(12.12);
		his.setAi2(22.12);
		his.setAi3(32.12);
		his.setAi4(42.12);
		his.setAi5(52.12);
		his.setAi6(62.12);
		his.setAi7(72.12);
		his.setAi8(82.12);
		his.setAi9(92.12);
		his.setAi10(102.12);
		his.setAi11(112.12);
		his.setAi12(122.12);
		his.setAi13(123.12);
		his.setAi14(124.12);
		his.setAi15(125.12);
		his.setAi16(126.12);
		his.setAi17(17.12);
		his.setAi18(18.12);
		his.setAi19(19.12);
		his.setAi20(20.12);
		his.setAi21(21.12);
		his.setAi22(22.12);
		his.setAi23(23.12);
		his.setAi24(24.12);
		his.setAi25(25.12);
		his.setAi26(26.12);
		his.setAi27(27.12);
		his.setAi28(28.12);
		his.setAi29(29.12);
		his.setAi30(30.12);
		his.setAi31(31.12);
		his.setAi32(32.12);

		his.setRef1AlarmStatus(0);
		his.setRef2AlarmStatus(1);
		his.setRef3AlarmStatus(2);
		his.setRef4AlarmStatus(3);
		
		his.setRef5AlarmStatus(4);
		his.setRef6AlarmStatus(5);
		his.setRef7AlarmStatus(6);
		his.setRef8AlarmStatus(7);
		
		his.setRef9AlarmStatus(8);
		his.setRef10AlarmStatus(9);
		his.setRef11AlarmStatus(10);
		his.setRef12AlarmStatus(11);
		
		his.setRef13AlarmStatus(12);
		his.setRef14AlarmStatus(13);
		his.setRef15AlarmStatus(14);
		his.setRef16AlarmStatus(15);

		list.add(his);

		System.out.println(allService.uploadRefHisData(projectId, devId, devType, list));
	}
}
