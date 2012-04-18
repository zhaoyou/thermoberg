package org.tbcc.dwr;

import java.util.*;
import org.tbcc.biz.HisBoxBiz;
import org.tbcc.biz.HisStartUpBiz;
import org.tbcc.entity.TbccBaseHisBox;
import org.tbcc.entity.TbccBaseHisStartUp;
import org.tbcc.util.MySpringFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


/** 
 * �����Ϊ��DWR ʹ�� JavaScript ���ʵ�java�࣬
 * ��Щ������Ϊ����ʾС������ʷ���������õġ�
 * @author Administrator
 *
 */
public class HistDwr {
	
	private  HisStartUpBiz hisStartUpBiz = null ;
	
	private  HisBoxBiz hisBoxBiz = null ;	
	
	public HistDwr(){
		hisStartUpBiz = (HisStartUpBiz)MySpringFactory.getInstance().getBean("startUpBiz");
		hisBoxBiz = (HisBoxBiz)MySpringFactory.getInstance().getBean("hisboxBiz");
	}
	
	/**
	 * ��ȡδ�ϴ���С������ͣ��¼
	 * @param projectId	С�����豸���̱��
	 * @param devSsData	�豸�д��ڵ���ͣ��¼����
	 * @return		С����δ�ϴ�����ͣ��¼����
	 */
	public  List<TbccBaseHisStartUp> getUploadStartupList(String projectId,List<TbccBaseHisStartUp> devHistStartUpList){
	    //�����豸�д��ڵ���ͣ��¼����С���������ʱ��
		String minStartupTime,maxStartupTime;
		int removeIndex,count=-1;
		
		if(devHistStartUpList==null)
		  return null;
		
		if(devHistStartUpList.size()==0)
		  return null;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  
		minStartupTime= devHistStartUpList.get(0).getTransBeginTime();
		maxStartupTime= devHistStartUpList.get(devHistStartUpList.size()-1).getTransBeginTime();		
		
		//�����豸�е���С���������ʱ�䣬��ȡ���ݿ����Ѿ��ϴ��Ĵ��������ͣ��¼
		List<TbccBaseHisStartUp> existHistStartUpList= this.hisStartUpBiz.getHistBoxSsByMinMaxStartTime("TbccHistStartUp_"+projectId+"_1",minStartupTime,maxStartupTime);
				
		//���豸��ͣʱ���б����޳��Ѿ��ϴ�����ͣ��¼
		if(existHistStartUpList==null)
		{
		   return devHistStartUpList;
		}						   		   
	    
		if(existHistStartUpList.size()==0){
		   return devHistStartUpList;
		}
		
		
		for (TbccBaseHisStartUp existHistStartUp : existHistStartUpList){  //�޳����Ѿ��ϴ�������ͣ��¼
		    removeIndex=-1;
		    count=-1;		    
			for (TbccBaseHisStartUp devHistStartUp : devHistStartUpList){
			   count=count+1;
			   if(df.format(existHistStartUp.getBeginTime()).equals(devHistStartUp.getTransBeginTime())){  //���������Ѿ��ϴ�
		          removeIndex=count;			       
				  break;
			   }
			}		
			if(removeIndex>=0){
			devHistStartUpList.remove(removeIndex);
			}
		}
		
		return devHistStartUpList;		
	}
	
	/**
	 * ����δ�ϴ�����С������ͣ��¼����ʷ����
	 * @param projectId	С�����豸���̱��
	 * @param devSsData	��Ҫ�ϴ���С�����豸�е���ͣ��¼
	 * @param devHistData	��Ҫ�ϴ���С�����豸�е���ʷ����
	 * @return	�ϴ����(0���ϴ��ɹ���-1���ϴ�ʧ��)
	 */
	public int uploadPacketHistData(String projectId,List<TbccBaseHisStartUp> devHistStartUpList,List<TbccBaseHisBox> devHistDataList){
	     long maxId,oldId;
	     Integer updateResult;
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
		//��ȡ��ǰ���豸��ͣ����������ͣ��¼ID
		   maxId=this.hisStartUpBiz.getHistBoxMaxStartupId("TbccHistStartUp_"+projectId+"_1");
           maxId=maxId+1;  //��һ������ʹ�õ���ͣ��¼���
			
		//�����豸�ϴ���������ֹͣ��¼ID����ʷ���ݵ�parentid���Ϊ��ǰ���ݿ����ʹ�õı��
		for (TbccBaseHisStartUp devHistStartUp : devHistStartUpList){  
		    oldId=devHistStartUp.getId();
			devHistStartUp.setId(maxId);
			//����Date���͵�beginTime��endTime����
			try{
			devHistStartUp.setBeginTime(df.parse(devHistStartUp.getTransBeginTime()));
			devHistStartUp.setEndTime(df.parse(devHistStartUp.getTransEndTime()));
			}
			catch (ParseException e) {
			   return -1;		
			}

			for (TbccBaseHisBox devHistData : devHistDataList){  //������ʷ����IdΪ��ǰ��ͣ��¼��ID�����浽���ݿ��е�ID��
				//����Date���͵�UpdateTime����
				try{
				   devHistData.setUpdateTime(df.parse(devHistData.getTransUpdateTime()));
				}
				catch (ParseException e) {
				   return -1;		
				}				
				
			   if(oldId == devHistData.getParentId()){  //������ʷ�������ڵ�ǰ��ͣ��¼
		           devHistData.setParentId(maxId);	         
			   }
			}
			maxId=maxId+1;
		}
		
		//����������ͣ��¼
		for (TbccBaseHisStartUp devHistStartUp : devHistStartUpList){		
		  updateResult= this.hisStartUpBiz.uploadHistBoxStartup("TbccHistStartUp_"+projectId+"_1",devHistStartUp);		
		  if(updateResult==-1)
		    return -1;
		}
		
		//����������ʷ���ݼ�¼
		for (TbccBaseHisBox devHistData : devHistDataList){			
		  updateResult= this.hisBoxBiz.uploadHistBoxData("TbccHistData_Box_"+projectId+"_1",devHistData);		
		  if(updateResult==-1)
		    return -1;
		}
		
		return 0;
	}	
}