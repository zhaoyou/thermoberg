package org.ccdcc.test;

import org.ccdcc.dao.jdbc.impl.ProjectAuthDaoImpl;
import org.ccdcc.util.ConnectionProxyFactory;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * 测试工程授权码操作
 * @author zhaoyou
 *
 */
public class ProjectAuthTest extends TestCase{
	
	private ProjectAuthDaoImpl pauthDao = null ;
	
	String projectId = "2015" ;
	String authCode = "1234" ;
	
	
	public void init(){
		ConnectionProxyFactory cpf = new ConnectionProxyFactory();
		pauthDao = new ProjectAuthDaoImpl();
		pauthDao.setConnectionfactory(cpf) ;
	}
	
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		init();
	}
	
	@Test
	public void testOK(){	
	assertEquals(true, pauthDao.queryAuthCode(projectId, authCode));	
	}
	
	
	
}
