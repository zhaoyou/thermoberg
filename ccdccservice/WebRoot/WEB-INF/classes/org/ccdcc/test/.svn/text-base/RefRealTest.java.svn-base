package org.ccdcc.test;

import java.util.List;

import org.ccdcc.dao.jdbc.impl.RefRealDaoImpl;
import org.ccdcc.entity.AiInfoView;
import org.ccdcc.entity.RefInfoView;
import org.ccdcc.entity.RefRealView;
import org.ccdcc.util.ConnectionProxyFactory;
import org.junit.Test;

import junit.framework.TestCase;

public class RefRealTest extends TestCase {

private RefRealDaoImpl refRealDao = null ;
	
	String projectId = "2015" ;
	String authCode = "1234" ;
	Integer realRefId = 1 ;
	Integer refType = 1 ;
	Integer floorType = 1 ;
	Integer floorNo = 1 ;
	
	Long rid = Long.parseLong("13") ;
	
	Integer netId = 1 ;
	
	
	public void init(){
		ConnectionProxyFactory cpf = new ConnectionProxyFactory();
		refRealDao = new RefRealDaoImpl();
		refRealDao.setConnectionfaotory(cpf);
	}
	
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		init();
	}
	
	@Test
	public void testQueryRef(){
		System.out.println("单个冷库查询： ");
		RefInfoView v =   refRealDao.queryRefByInfo(projectId,realRefId, refType, floorType, floorNo) ;
		assertNotNull(v);
		System.out.print(v.getProjectId());
		System.out.print("\t"+v.getId());
		System.out.print("\t"+v.getNetId());
		System.out.print("\t"+v.getRealRefId());
		System.out.print("\t"+v.getRefType());
		System.out.print("\t"+v.getFloorType());
		System.out.println("\t"+v.getFloorNo());
	}
	
	@Test
	public void testQueryRefList(){
		System.out.println("多个冷库查询: ");
		List<RefInfoView> list = refRealDao.queryRefByProjectId(projectId);
		
		assertNotNull(list);
		for (RefInfoView refInfoView : list) {
			System.out.print(refInfoView.getProjectId());
			System.out.print("\t"+refInfoView.getId());
			System.out.print("\t"+refInfoView.getNetId());
			System.out.print("\t"+refInfoView.getRealRefId());
			System.out.print("\t"+refInfoView.getRefType());
			System.out.print("\t"+refInfoView.getFloorType());
			System.out.println("\t"+refInfoView.getFloorNo());
		}
		
	}
	
	@Test
	public void testQueryAiByRid(){
		System.out.println("单个冷库探头查询: ");
		List<AiInfoView> list = refRealDao.queryAiInfoByRid(rid);
		assertNotNull(list);
		for (AiInfoView aiInfoView : list) {
			System.out.print("\t"+aiInfoView.getId());
			System.out.print("\t"+aiInfoView.getProjectId());
			System.out.print("\t"+aiInfoView.getRid());
			System.out.print("\t"+aiInfoView.getPortNo());
			System.out.print("\t"+aiInfoView.getPortName());
			System.out.println("\t"+aiInfoView.getDataType());
			
		}
	}
	
	@Test
	public void testQueryAiByProjectId(){
		System.out.println("单个工程的所有探头查询：");
		List<AiInfoView> list = refRealDao.queryAiInfoByProjectId(projectId);
		assertNotNull(list);
		for (AiInfoView aiInfoView : list) {
			System.out.print("\t"+aiInfoView.getId());
			System.out.print("\t"+aiInfoView.getProjectId());
			System.out.print("\t"+aiInfoView.getRid());
			System.out.print("\t"+aiInfoView.getPortNo());
			System.out.print("\t"+aiInfoView.getPortName());
			System.out.println("\t"+aiInfoView.getDataType());
		}
	}
	
	@Test
	public void testQueryRefRealByPidAndNetId(){
		System.out.println("单个设备实时数据");
		RefRealView v = refRealDao.queryRefReal(projectId, netId) ;
		assertNotNull(v);
		System.out.print("\t"+v.getId());
		System.out.print("\t"+v.getProjectId());
		System.out.print("\t"+v.getUpdateTime());
		for(int i=0;i<v.getAllAi().length;i++)
			System.out.print("\t"+v.getAllAi()[i]);
		System.out.println();
		/*System.out.print("\t"+v.getAi1());
		System.out.print("\t"+v.getAi2());
		System.out.print("\t"+v.getAi3());
		System.out.print("\t"+v.getAi4());
		System.out.print("\t"+v.getAi5());
		System.out.print("\t"+v.getAi6());
		System.out.print("\t"+v.getAi7());
		System.out.print("\t"+v.getAi8());
		System.out.print("\t"+v.getAi9());
		System.out.print("\t"+v.getAi10());
		System.out.print("\t"+v.getAi11());
		System.out.println("\t"+v.getAi12());*/
		
	}
	
	@Test 
	public void testQueryRefRealByProjectId(){
		System.out.println("一个工程所有的设备实时数据： ");
		List<RefRealView> list = refRealDao.queryRefReal(projectId);
		assertNotNull(list);
		for (RefRealView v : list) {
			System.out.print("\t"+v.getId());
			System.out.print("\t"+v.getProjectId());
			System.out.print("\t"+v.getUpdateTime());
			for(int i=0;i<v.getAllAi().length;i++)
				System.out.print("\t"+v.getAllAi()[i]);
			System.out.println();
			/*System.out.print("\t"+v.getAi1());
			System.out.print("\t"+v.getAi2());
			System.out.print("\t"+v.getAi3());
			System.out.print("\t"+v.getAi4());
			System.out.print("\t"+v.getAi5());
			System.out.print("\t"+v.getAi6());
			System.out.print("\t"+v.getAi7());
			System.out.print("\t"+v.getAi8());
			System.out.print("\t"+v.getAi9());
			System.out.print("\t"+v.getAi10());
			System.out.print("\t"+v.getAi11());
			System.out.println("\t"+v.getAi12());*/
		}
	}
	
}
