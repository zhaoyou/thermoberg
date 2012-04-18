package org.tbcc.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.HisStartUpDao;
import org.tbcc.entity.TbccBaseHisStartUp;
//import org.tbcc.entity.TbccBaseHisBox;
/**
 *  这个是启停操作的数据访问实现类
 * @author Administrator
 *
 */
public class HisStartUpDaoImpl extends HibernateDaoSupport implements
		HisStartUpDao {

	
	public List getStartUpList(String tableName,String startTime,String endTime) {
		String sql = "select Id   , BeginTime   , EndTime   , PageIndex   , PageCount  , RecordInterval   , LastPageIndex"+ 
 ", LastPageRecNum , BeginAddress , EndAddress  , Shipper , Carrier  , Receiver "+
  ", LastRecordTime , UpdateStatus,tuplimit,tdwlimit  from " +tableName+" where beginTime between '"+startTime 
		+"' and '"+endTime+"' or endTime between '"+startTime+"' and '"+endTime+"' order by beginTime";
		Session  session =  this.getSessionFactory().getCurrentSession() ;
		SQLQuery query = session.createSQLQuery(sql);
		return query.list() ;	
	}
	
	
	public List getStartUp(String tableName, long id) {
		String sql = "select Id   , BeginTime   , EndTime   , PageIndex   , PageCount  , RecordInterval   , LastPageIndex"+ 
 ", LastPageRecNum , BeginAddress , EndAddress  , Shipper , Carrier  , Receiver "+
  ", LastRecordTime , UpdateStatus,tuplimit,tdwlimit  from "+tableName+"  where id = "+id ;
		SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.list();
	}


	@SuppressWarnings("unchecked")
	public List<TbccBaseHisStartUp> getStartUpById(String tableName, long id) {
		String sql = "select {startup.*} from "+tableName+" startup where startup.id = "+id ;
		return this.getSession().createSQLQuery(sql).addEntity("startup", TbccBaseHisStartUp.class).list() ;
	}


	@SuppressWarnings("unchecked")
	public List<TbccBaseHisStartUp> getStartUpListByTime(String tableName,
			String startTime, String endTime) {
		String sql = "select {startup.*} from "+tableName+" startup where startup.beginTime " +
		" between '"+startTime +"' and '"+endTime+"'" +
		" or startup.endTime between '"+startTime+"' and '"+endTime+"' order by startup.beginTime";
		return this.getSession().createSQLQuery(sql).addEntity("startup", TbccBaseHisStartUp.class).list() ;
	}


	@SuppressWarnings("unchecked")
	public List<Long> queryStartupidByBeginTime(String tableName,String beginTime) {
		String sql = "select Id from "+tableName+" where beginTime = '"+beginTime+"'";
		SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

// modify by aftermath begin
	@SuppressWarnings("unchecked")		
	public List getHistBoxSsByMinMaxStartTime(String tableName,String minStartupTime,String maxStartupTime) {
		String sql = "select * from "+tableName+" where BeginTime between '"+ minStartupTime +
		"' and  '" + maxStartupTime +"'";
		Session session = this.getSession() ;
		return session.createSQLQuery(sql).addEntity(TbccBaseHisStartUp.class).list() ;
	}	
		
	@SuppressWarnings("unchecked")		
	public long getHistBoxMaxStartupId(String tableName){
	    Object obj;
		String sql = "select max(Id) as maxId from "+tableName;
		Session session = this.getSession() ;
		SQLQuery query = session.createSQLQuery(sql);
		obj= query.uniqueResult() ;
		//this.getSession().createSQLQuery(sql).addScalar("maxId", Hibernate.LONG ).uniqueResult();
		
		if(obj!=null){  
		  return Long.parseLong(obj.toString()); 
		}
		 return 0;
	}
		
	@SuppressWarnings("unchecked")	
    public int uploadHistBoxStartup(String tableName,TbccBaseHisStartUp devHistStartUp){	
	       Statement stm=null;   
		   try{
				String sql = "insert into " + tableName +"(Id,BeginTime,EndTime,PageIndex,PageCount,RecordInterval,LastPageIndex,LastPageRecNum,tlimitType,tUplimit,tDwlimit,LastRecordTime,UpdateStatus)" +
				" values("+ devHistStartUp.getId() + ",'" + devHistStartUp.getTransBeginTime().toString() + "','" + devHistStartUp.getTransEndTime().toString()  + "',0,0,"  + devHistStartUp.getRecordInterval() + 
				",0,0,0," + devHistStartUp.getTuplimit().toString() + "," + devHistStartUp.getTdwlimit() + ",'"+ devHistStartUp.getTransEndTime().toString()+"',"+ devHistStartUp.getUpdateStatus()+")";
				
				stm=this.getSession().connection().createStatement();
				
		    	if(stm.execute(sql)==true){ //不是执行插入或更新语句,而是返回数据记录集合
		    		return -1;	
		    	}	    		
		    	
	    		if(stm.getUpdateCount()!=1) //插入成功数量不是1条
		    		return -1;
	    		
			}
			catch(SQLException e){
			    return -1;		
			}
			
			return 0;
	}
	
/*	@SuppressWarnings("unchecked")	
     public int updateHistBoxStartup(String projectId,List<TbccBaseHisStartUp> devHistStartUpList,List<TbccBaseHisBox> devHistDataList){
		   Session ssn= null;
	       Statement stm=null;
	       Connection conn = null;
	       Transaction ts= null;
		   try{
			  //增加所有启停记录
			  for (TbccBaseHisStartUp devHistStartUp : devHistStartUpList){							
				
				String sql = "insert into " + tableName +"(BeginTime,EndTime,PageIndex,PageCount,RecordInterval,LastPageIndex,LastPageRecNum,tlimitType,tUplimit,tDwlimit,LastRecordTime,UpdateStatus)" +
				" values('" + devHistStartUp.getBeginTime().toString() + "','" + devHistStartUp.getEndTime().toString()  + "',0,0,"  + devHistStartUp.getRecordInterval() + 
				",0,0,0," + devHistStartUp.getTuplimit().toString() + "," + devHistStartUp.getTdwlimit() + ",'"+ devHistStartUp.getEndTime().toString()+"',"+ devHistStartUp.getUpdateStatus()+")";
				
				ssn=this.getSession();
				conn = ssn.connection();
				ts= ssn.beginTransaction();
				
				stm=ssn.connection().createStatement();
				
		    	if(stm.execute(sql)==true){ //不是执行插入或更新语句,而是返回数据记录集合
		    		return -1;	
		    	}	    		
		    	
	    		if(stm.getUpdateCount()!=1) //插入成功数量不是1条
		    		return -1;
	    		
	    		ts.commit();
			  }
			  
			}
			catch(SQLException e){
				if(ts!=null){
				  ts.rollback();
				}
				
			    return -1;		
			}
			finally{
			  try {
				  
				  
			  }	
			  catch(SQLException e){
				  
				  
			  }
				
			}
			
			return 0;
	}*/	
	
// modify by aftermath end		
	
}
