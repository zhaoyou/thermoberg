package org.ccdcc.dao.jdbc.impl;






import java.sql.Connection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.log4j.Logger;
import org.ccdcc.dao.ProjectAuthDao;
import org.ccdcc.entity.ProjectView;
import org.ccdcc.util.ConnectionProxyFactory;



public class ProjectAuthDaoImpl  implements ProjectAuthDao {
	
	private static  Logger logger = Logger.getRootLogger();

	private ConnectionProxyFactory connectionfactory = null ;
	
	public void setConnectionfactory(ConnectionProxyFactory connectionfactory) {
		this.connectionfactory = connectionfactory;
	}

	
	
	public boolean queryAuthCode(String projectId, String authCode) {	
		Connection conn=null;
		try {			
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfactory.getConnection();
			String sql = "select projectId ,projectAuthCode from tbccprjType where projectid = ? and projectauthcode= ? " ;	
			ProjectView pv = (ProjectView)queryRun.query(conn, sql, new BeanHandler <ProjectView>(ProjectView.class),	new Object[]{projectId,authCode});	
			if(pv==null)
				return false ;	
		} catch (Exception e) {
			logger.error("获取验证失败！"+e.getMessage());
			return false ;
		}
		finally{
			connectionfactory.closeConn(conn);
		}
		return true;
	}

}
