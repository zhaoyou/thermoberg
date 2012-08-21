package com.thermoberg.dao;

import java.util.List;

import com.thermoberg.entity.AlarmInfo;
import com.thermoberg.entity.AlarmSerious;
import com.thermoberg.entity.CarAlarmInfo;
import com.thermoberg.entity.CarDataNoChange;
import com.thermoberg.entity.HistRefData;
import com.thermoberg.entity.TimeOutAlarm;

/**
 * ����һ�����������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface AlarmDao {
	
		public final static String ALARM = "SELECT  b.branchId, b.branchName,r.updatetime ,r.netid ,r.AlarmStatus_Sound1"+
			"FROM    dbo.TbccBranchPrjRelation rel INNER JOIN "+
			"dbo.TbccBranchType b ON rel.BranchID = b.BranchID INNER JOIN "+
			"dbo.TbccRealData_Ref r ON rel.projectId = r.projectId where b.branchId !=29 and b.branchId !=36 and b.branchId !=31 and b.branchId !=34 and  "+
			"(r.AlarmStatus_Ref1=1 or  r.AlarmStatus_Ref2=1  or  r.AlarmStatus_Ref3=1 "+ 
					"or r.AlarmStatus_Ref4=1 or r.AlarmStatus_Ref5=1 or r.AlarmStatus_Ref6=1 or r.AlarmStatus_Ref7=1 or r.AlarmStatus_Ref8=1 " +
					"or r.AlarmStatus_Ref9=1 or r.AlarmStatus_Ref10=1 or r.AlarmStatus_Ref11=1 or r.AlarmStatus_Ref12=1 or r.AlarmStatus_Ref13=1 " +
					"or r.AlarmStatus_Ref14=1 or r.AlarmStatus_Ref15=1 or r.AlarmStatus_Ref16=1) and r.ConnectStatus = 2 order by b.branchName,r.updatetime " ;
	
		
		public final static String ALARM_SOUND = "SELECT  b.branchId , b.branchName,r.updatetime "+
			" FROM    dbo.TbccBranchPrjRelation rel INNER JOIN "+
			" dbo.TbccBranchType b ON rel.BranchID = b.BranchID INNER JOIN " + 
			" dbo.TbccRealData_Ref r ON rel.projectId = r.projectId where b.branchId !=29 and b.branchId !=36 and b.branchId !=31 and b.branchId !=34 and " +  
			" (r.AlarmStatus_Sound1 = 0 or r.AlarmStatus_Sound1=1) and r.ConnectStatus = 2   ;" ;
		
		
		
		public final static String ALARM_IMPORTTANT =" SELECT  b.branchId, b.branchName,r.updatetime "+
		" FROM    dbo.TbccBranchPrjRelation rel INNER JOIN "+
		" dbo.TbccBranchType b ON rel.BranchID = b.BranchID INNER JOIN " + 
		" dbo.TbccRealData_Ref r ON rel.projectId = r.projectId where b.branchId !=29 and b.branchId !=36 and b.branchId !=31 and b.branchId !=34 and " +  
		 "(r.ai1 between 45 and 126  or  r.ai2 between 45 and 126  or  r.ai3 between 45 and 126 "+
		"	or r.ai4 between 45 and	126 or r.ai5 between 45 and 126 or r.ai6 between 45 and 126  " + 
		"	or r.ai7 between 45 and 126  or r.ai8 between 45 and 126  " + 
		"	or r.ai9 between 45 and 126 or r.ai10 between 45 and 126 or r.ai11 between 45 and 126 "+
		"	 or r.ai12 between 45 and 126  or r.ai13 between 45 and 126 " +
		"	or r.ai14 between 45 and 126  or r.ai15 between 45 and 126  or r.ai16 between 45 and 126 ) and r.ConnectStatus = 2 and r.netid>=100 order by b.branchName,r.updatetime " ;


		public final static String ALARM_IMPORTTANT2 = "SELECT  b.branchId, b.branchName,r.updatetime "+
			" FROM    dbo.TbccBranchPrjRelation rel INNER JOIN  " +
			" dbo.TbccBranchType b ON rel.BranchID = b.BranchID INNER JOIN "+  
			" dbo.TbccRealData_Ref r ON rel.projectId = r.projectId where b.branchId !=29 and b.branchId !=36 and b.branchId !=31 and b.branchId !=34 and "+   
			" (r.ai1 between 45 and 126  or  r.ai2 between 45 and 126  or  r.ai3 between 45 and 126" +
			"	or ((r.ai4 between 45 and 126) and (r.netid!=3 or r.projectId!='20E0')) or r.ai5 between 45 and 126 or r.ai6 between 45 and 126  "+
			"	or r.ai7 between 45 and 126  or r.ai8 between 45 and 126  "+
			"	or r.ai9 between 45 and 126 ) and r.ConnectStatus = 2 and r.netid<100 order by b.branchName,r.updatetime  ;" ;
		
		
		public final static String CONNECTION = "SELECT   b.branchId, b.branchName , r.updatetime "+
			"FROM    dbo.TbccBranchPrjRelation rel INNER JOIN "+
			"dbo.TbccBranchType b ON rel.BranchID = b.BranchID INNER JOIN " +
			"dbo.TbccRealData_Ref r ON rel.projectId = r.projectId INNER JOIN " +
			"dbo.TbccPrjType pr ON r.projectid = pr.projectid where b.branchId !=29 and b.branchId !=36 and b.branchId !=31 and b.branchId !=34 and  "+
			"r.ConnectStatus !=2 and pr.projectActive = 1 order by b.branchName,r.updatetime" ;
		
		
		public final static String CARALARM = "SELECT  prjtype.projectId AS 'projectId', branch.BranchName AS 'branchName',prjtype.projectName as 'projectName', realdata.UpdateTime AS 'updateTime'"+ 
					" FROM     TbccBranchPrjRelation relation INNER JOIN"+
                    " TbccBranchType branch ON relation.BranchID = branch.BranchID INNER JOIN"+
                    " TbccPrjType prjtype ON relation.ProjectID = prjtype.ProjectID INNER JOIN"+
                    " TbccRealData_Car realdata ON prjtype.ProjectID = realdata.ProjectID where (realdata.alarmStatus=0 or realdata.alarmStatus=1) and "+
					" realdata.runStatus = 2 and realdata.connectStatus = 2 and branch.branchId !=29 and branch.branchId !=36 and branch.branchId !=31 and branch.branchId !=34 ;" ;
		
		
		
		public final static String CARALARM_IMPORTTANT = "select branch.branchid*200 AS 'branchId', branch.branchname+prjtype.projectName as 'branchName', realdata.UpdateTime AS 'updatetime' "+
					" FROM     TbccBranchPrjRelation relation INNER JOIN"+
                    " TbccBranchType branch ON relation.BranchID = branch.BranchID INNER JOIN"+
                    " TbccPrjType prjtype ON relation.ProjectID = prjtype.ProjectID INNER JOIN"+
                    " TbccRealData_Car realdata ON prjtype.ProjectID = realdata.ProjectID where (realdata.ai1>=45 or realdata.ai2>=45 or realdata.ai3>=45) and "+
					" realdata.runStatus = 2 and realdata.connectStatus = 2 and branch.branchId !=29 and branch.branchId !=36 and branch.branchId !=31 and branch.branchId !=34 " ;
		
		public final static String ALARM_LOSTPOWER = "SELECT  b.branchId , b.branchName,r.updatetime "+
		" FROM    dbo.TbccBranchPrjRelation rel INNER JOIN "+
		" dbo.TbccBranchType b ON rel.BranchID = b.BranchID INNER JOIN " + 
		" dbo.TbccRealData_Ref r ON rel.projectId = r.projectId where b.branchId !=29 and b.branchId !=36 and b.branchId !=31 and b.branchId !=34 and " +  
		" (r.AlarmStatus_LostPower1 = 0 or r.AlarmStatus_LostPower1=1) and r.ConnectStatus = 2   ;" ;
		
		//�ֿ�ʵʱ�����Ƿ�������䳬��10����
		public final static String ALARM_TIMEOUT = "select bt.branchname AS 'branchName',pt.projectname AS 'projectName',pt.projectid AS 'projectId',rd.netid AS 'netId',GetDate() AS 'startTime' from tbccrealdata_ref rd " +
				" inner join tbccprjtype pt on rd.projectid=pt.projectid inner join tbccbranchprjrelation bpr on pt.projectid=bpr.projectid inner join tbccbranchtype bt on bt.branchid=bpr.branchid where " +
				" bt.branchId !=29 and bt.branchId !=36 and bt.branchId !=31 and bt.branchId !=34 and rd.connectstatus = 2 and DateDiff(second,rd.updatetime,GetDate())>600 order by pt.projectid";
		
		
		public final static String REF_REAL="select * from tbccrealdata_ref where projectid='20E0'";
		
		
		
		/**
		 * ��ȡ���е��Ѿ�������������Ϣ�б�
		 * @return
		 */
		public List<AlarmInfo> getAlarmList() ;
		
		/**
		 * ��ȡ���е��Ѿ������ĶϿ����ӵ���Ϣ�б�
		 * @return
		 */
		public List<AlarmInfo> getLostConnection() ;
		
		/**
		 * ��ȡ���ر�������Ϣ�б�
		 * @return
		 */
		public List<CarAlarmInfo> getCarAlarmList();
		
		/**
		 *  ��ȡ�ֿ⡢�������ر����б�
		 * @param ��Ҫִ�е�sql���
		 * @return
		 */
		public List<AlarmSerious> getAlarmSeriousList(String sql) ;
		
		
		/**
		 * ��ȡ���е��Ѿ��ϵ����Ϣ�б�
		 * @return
		 */
		public List<AlarmSerious> getLostPower() ;
		
		
		/**
		 * ��ȡʵʱ���ݳ������䳬��10���ӵ�������Ϣ�б�
		 * @return
		 */
		public List<TimeOutAlarm> getTimeoutAlarmList() ;
		
		
		/**
		 * ��ȡ����ʵʱ����(��ʱ���еģ�ֻ��ѯ�Ͼ�ҽҩ20E0�ֿ������)
		 * @return
		 */
		public List<HistRefData> getHistRefData();
		
		/**
		 * ��ȡ�ڹ̶�������û�и��µĳ���
		 * @param	����
		 * @return	
		 */
		public List<CarDataNoChange> getCar_noChange(Integer day);
}
