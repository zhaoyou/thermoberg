package org.tbcc.dao.impl;

import java.util.List;
import java.sql.*;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tbcc.dao.HisBoxDao;
import org.tbcc.entity.TbccBaseHisBox;

/**
 * ����С�������ʷ���ݷ���ʵ����
 * @author Administrator
 *
 */
public class HisBoxDaoImpl extends HibernateDaoSupport implements HisBoxDao {

	
	public List<TbccBaseHisBox> getHisBoxData(String tableName, String startTime, String endTime,
			int value) {
		String sql = "select * from "+tableName+" where updateTime between '"+ startTime +
		"' and  '" + endTime +"'"+
		" and ((datepart(hour, updatetime)*3600+ datepart(minute,updatetime)*60+datepart(second,updatetime)) " + 
		" - (datepart(hour, '"+startTime+"')*3600+ datepart(minute,'"+startTime+"')*60+datepart(second,'"+startTime+"'))) % " + value +" = 0";
		Session session = this.getSession() ;
		SQLQuery query = session.createSQLQuery(sql);
		return query.list() ;
	}
	
// modify by aftermath begin
     public int uploadHistBoxData(String tableName,TbccBaseHisBox devHistData){
       Statement stm=null;   
	   try{
			String sql = "insert into " + tableName +"(ParentId,UpdateTime,AI1,latitude_dir,latitude,longitude_dir,longitude,AlarmStatus)" +
			" values(" + devHistData.getParentId() + ",'" + devHistData.getTransUpdateTime().toString() + "',"  + devHistData.getAi1().toString() + 
			",-300,-300,-300,-300," + devHistData.getAlarmStatus().toString()+")";
			
			stm=this.getSession().connection().createStatement();
			
	    	if(stm.execute(sql)==true){ //����ִ�в����������,���Ƿ������ݼ�¼����
	    		return -1;	
	    	}	    		
	    	
    		if(stm.getUpdateCount()!=1) //����ɹ���������1��
	    		return -1;
    		
		}
		catch(SQLException e){
		    return -1;		
		}
		
		return 0;
	}
// modify by aftermath end		

}
