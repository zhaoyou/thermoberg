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
 * 这个是为了DWR 使用 JavaScript 访问的java类，
 * 这些方法是为了显示小批零历史数据所调用的。
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
	 * 获取未上传的小批零启停记录
	 * @param projectId	小批零设备工程编号
	 * @param devSsData	设备中存在的启停记录集合
	 * @return		小批零未上传的启停记录集合
	 */
	public  List<TbccBaseHisStartUp> getUploadStartupList(String projectId,List<TbccBaseHisStartUp> devHistStartUpList){
	    //检索设备中存在的启停记录的最小和最大启动时间
		String minStartupTime,maxStartupTime;
		int removeIndex,count=-1;
		
		if(devHistStartUpList==null)
		  return null;
		
		if(devHistStartUpList.size()==0)
		  return null;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  
		minStartupTime= devHistStartUpList.get(0).getTransBeginTime();
		maxStartupTime= devHistStartUpList.get(devHistStartUpList.size()-1).getTransBeginTime();		
		
		//根据设备中的最小和最大启动时间，获取数据库中已经上传的此区间的启停记录
		List<TbccBaseHisStartUp> existHistStartUpList= this.hisStartUpBiz.getHistBoxSsByMinMaxStartTime("TbccHistStartUp_"+projectId+"_1",minStartupTime,maxStartupTime);
				
		//从设备启停时间列表中剔除已经上传的启停记录
		if(existHistStartUpList==null)
		{
		   return devHistStartUpList;
		}						   		   
	    
		if(existHistStartUpList.size()==0){
		   return devHistStartUpList;
		}
		
		
		for (TbccBaseHisStartUp existHistStartUp : existHistStartUpList){  //剔除掉已经上传过的启停记录
		    removeIndex=-1;
		    count=-1;		    
			for (TbccBaseHisStartUp devHistStartUp : devHistStartUpList){
			   count=count+1;
			   if(df.format(existHistStartUp.getBeginTime()).equals(devHistStartUp.getTransBeginTime())){  //该条数据已经上传
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
	 * 新增未上传过的小批零启停记录和历史数据
	 * @param projectId	小批零设备工程编号
	 * @param devSsData	需要上传的小批零设备中的启停记录
	 * @param devHistData	需要上传的小批零设备中的历史数据
	 * @return	上传结果(0：上传成功；-1：上传失败)
	 */
	public int uploadPacketHistData(String projectId,List<TbccBaseHisStartUp> devHistStartUpList,List<TbccBaseHisBox> devHistDataList){
	     long maxId,oldId;
	     Integer updateResult;
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
		//获取当前该设备启停表中最大的启停记录ID
		   maxId=this.hisStartUpBiz.getHistBoxMaxStartupId("TbccHistStartUp_"+projectId+"_1");
           maxId=maxId+1;  //下一个可以使用的启停记录编号
			
		//更新设备上传的所有启停止记录ID和历史数据的parentid编号为当前数据库可以使用的编号
		for (TbccBaseHisStartUp devHistStartUp : devHistStartUpList){  
		    oldId=devHistStartUp.getId();
			devHistStartUp.setId(maxId);
			//更新Date类型的beginTime和endTime数据
			try{
			devHistStartUp.setBeginTime(df.parse(devHistStartUp.getTransBeginTime()));
			devHistStartUp.setEndTime(df.parse(devHistStartUp.getTransEndTime()));
			}
			catch (ParseException e) {
			   return -1;		
			}

			for (TbccBaseHisBox devHistData : devHistDataList){  //设置历史数据Id为当前启停记录的ID（保存到数据库中的ID）
				//更新Date类型的UpdateTime数据
				try{
				   devHistData.setUpdateTime(df.parse(devHistData.getTransUpdateTime()));
				}
				catch (ParseException e) {
				   return -1;		
				}				
				
			   if(oldId == devHistData.getParentId()){  //该条历史数据属于当前启停记录
		           devHistData.setParentId(maxId);	         
			   }
			}
			maxId=maxId+1;
		}
		
		//增加所有启停记录
		for (TbccBaseHisStartUp devHistStartUp : devHistStartUpList){		
		  updateResult= this.hisStartUpBiz.uploadHistBoxStartup("TbccHistStartUp_"+projectId+"_1",devHistStartUp);		
		  if(updateResult==-1)
		    return -1;
		}
		
		//增加所有历史数据记录
		for (TbccBaseHisBox devHistData : devHistDataList){			
		  updateResult= this.hisBoxBiz.uploadHistBoxData("TbccHistData_Box_"+projectId+"_1",devHistData);		
		  if(updateResult==-1)
		    return -1;
		}
		
		return 0;
	}	
}