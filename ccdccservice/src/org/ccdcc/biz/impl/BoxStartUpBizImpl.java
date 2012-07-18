package org.ccdcc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.ccdcc.biz.BoxStartUpBiz;
import org.ccdcc.dao.BoxStartUpDao;
import org.ccdcc.dao.ProjectAuthDao;
import org.ccdcc.entity.BoxStartUpView;
import org.ccdcc.entity.StartUpView;
import org.ccdcc.util.BuildTable;
import org.ccdcc.util.ProjectOperate;
import org.ccdcc.util.Util;

public class BoxStartUpBizImpl implements BoxStartUpBiz {

	private BoxStartUpDao startupdao = null ;
	
	private Util util = null ;
	
	private ProjectAuthDao projectAuthDao = null ;
	
	private Logger logger = Logger.getLogger(StartUpBizImpl.class);
	
	

	public void setProjectAuthDao(ProjectAuthDao projectAuthDao) {
		this.projectAuthDao = projectAuthDao;
	}
	public void setUtil(Util util) {
		this.util = util;
	}
	public void setStartupdao(BoxStartUpDao startupdao) {
		this.startupdao = startupdao;
	}
	/**
	 * 为了解决启停记录中某些16进制的字符问题
	 * @param source
	 * @return
	*/
	private String doEncode(String source){
		if(source==null)
			return source ;
		byte[] mybyte  = source.getBytes() ;
		int len = mybyte.length ;
		if(len>4)
		{
			if(mybyte[len-1]==0 && mybyte[len-2]==0 && mybyte[len-3]==0 && mybyte[len-4]==0)
				return  "***" ;
		}	
		return source ;		
	}
	
	/**
	 * 老版本和新版本共同调用的一个方法，获取车载的历史起停记录的基本方法
	 * @param projectId		工程编号
	 * @param Id			起停的标识Id
	 * @return
	 */
	
	public List<BoxStartUpView> getListByAfterIdOld(String projectId, Integer Id) {
				
				List<BoxStartUpView> list = new ArrayList<BoxStartUpView>();				//保存结果的集合
				
				BoxStartUpView view = new BoxStartUpView() ;								//保存状态的对象
			
				try {
						//一切正常情况下，获取所有的起停数据
						List<BoxStartUpView> sourcelist = startupdao.getBoxStartUpById(BuildTable.BuildStartUpTable(projectId), Id);
						
						if(sourcelist==null || sourcelist.size()==0){
							view.setFlag(NONE) ;
							list.add(view) ;
						}else{
							for (BoxStartUpView startUpView : sourcelist) {
								
								//凡是没有上传完成的统一设置为未上传完整
								if(!startUpView.getUpdateStatus().equals(2))
									startUpView.setUpdateStatus(new Integer(1)) ;
								
								startUpView.setBeginAddress(doEncode(startUpView.getBeginAddress())) ;
								startUpView.setEndAddress(doEncode(startUpView.getEndAddress())) ;
								startUpView.setCarrier(doEncode(startUpView.getCarrier())) ;
								startUpView.setShipper(doEncode(startUpView.getShipper())) ;
								startUpView.setReceiver(doEncode(startUpView.getReceiver())) ;
								startUpView.setFlag(NORMAL) ;
								list.add(startUpView) ;	
								}
							}
						} catch (Exception e) {
							//如果发生异常，所有list集合中的数据清空、加入一条标识结果状态为错误的对象
							logger.warn("获取"+projectId+"历史起停记录发生错误: "+e.getMessage());
							view.setFlag(ERROR) ;
							list.clear() ;
							list.add(view) ;
							}
						return list;
						}
	
	@Override
	public List<BoxStartUpView> getBoxListByAfterId(String key,
			String projectId, Integer Id) {
		List<BoxStartUpView> list = new ArrayList<BoxStartUpView>() ;
		
		BoxStartUpView startup = new BoxStartUpView() ;
		
		try {
			//验证参数
			if(key==null || key.equals("") || projectId==null || projectId.equals("")||Id==null || Id.equals("")){
				startup.setFlag(NONE); 
				list.add(startup);
				return list ;
			}
			
			//验证授权码
			if(!this.projectAuthDao.queryAuthCode(projectId, key)){
				startup.setFlag(NONE) ;
				list.add(startup);
				return list ;
			}
			
			return getListByAfterIdOld( projectId, Id);
			
		} catch (Exception e) {
			logger.warn("获取车载"+projectId+"历史起停记录失败 ： 	"+e.getMessage());
			startup.setFlag(ERROR) ;
			list.clear() ;
			list.add(startup) ;
			return list ;
		}
	}
	@Override
	public List<BoxStartUpView> getBoxStartUpList(String projectId, String time) {
		List<BoxStartUpView> list = new ArrayList<BoxStartUpView>();				//保存结果的集合
		
		BoxStartUpView view = new BoxStartUpView() ;								//保存状态的对象

		
		try {
			//参数非法，则返回没有数据状态
			if(projectId==null || projectId.trim().equals("") || time==null || time.trim().equals("")){
					view.setFlag(NONE) ;
					list.add(view) ;
			}else{
				
				//检验时间格式是否为yyyy-MM-dd HH:mm:ss
				if(util.getToDate(time)==null){
					throw new Exception("日期格式不对! "+time);
				}
					
				//一切正常情况下，获取所有的起停数据
				List<BoxStartUpView> sourcelist = startupdao.getBoxStartUpByTime(BuildTable.BuildStartUpTable(projectId), time);
				
				if(sourcelist==null || sourcelist.size()==0){
					view.setFlag(NONE) ;
					list.add(view) ;
				}else{
					for (BoxStartUpView startUpView : sourcelist) {
						//凡是没有上传完成的统一设置为未上传完整
						if(!startUpView.getUpdateStatus().equals(2))
							startUpView.setUpdateStatus(new Integer(1)) ;
						startUpView.setBeginAddress("***") ;
						startUpView.setEndAddress("***") ;
						startUpView.setCarrier("***") ;
						startUpView.setShipper("***") ;
						startUpView.setReceiver("***") ;
						
						startUpView.setFlag(NORMAL) ;
						list.add(startUpView) ;	
					}
				}
				
			}
		} catch (Exception e) {
			//如果发生异常，所有list集合中的数据清空、加入一条标识结果状态为错误的对象
			logger.warn("获取"+projectId+"移动终端历史起停记录发生错误: "+e.getMessage());
			view.setFlag(ERROR) ;
			list.clear() ;
			list.add(view) ;
		}
		return list;
	}
	
	
	

}
