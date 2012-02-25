package org.ccdcc.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.ccdcc.biz.CarRealBiz;
import org.ccdcc.dao.CarRealDao;
import org.ccdcc.dao.ProjectAuthDao;
import org.ccdcc.entity.CarRealData;
import org.ccdcc.entity.CarRealView;
import org.ccdcc.entity.CarRealView_new;
import org.ccdcc.util.ProjectOperate;
import org.ccdcc.util.Util;

/**
 * 车载实时数据业务实现类
 * @author Administrator
 *
 */
public class CarRealBizImpl implements CarRealBiz {
	
		private CarRealDao carrealdao = null ;
		
		private Logger logger = Logger.getLogger(CarRealBizImpl.class);
		
		public void setCarrealdao(CarRealDao dao){
			this.carrealdao  = dao ;
		}
		
		private ProjectAuthDao	projectAuthDao = null ;
	

		public void setProjectAuthDao(ProjectAuthDao projectAuthDao) {
			this.projectAuthDao = projectAuthDao;
		}
		
		private Util util = null ;
		
		public void setUtil(Util util) {
			this.util = util;
		}

		
		//获取车载实时数据，没有授权码key的，老用户调用
		public CarRealView getRealData(String projectId) {
			CarRealView carrealView = new CarRealView();
			if(!ProjectOperate.EXISTSPRJS(projectId)||projectId==null || projectId.trim().equals(""))
			{
				carrealView.setFlag(NONE);
			}
			else{
				//调用getRealDataOld方法,以获取数据
				carrealView=getRealDataOld(projectId);
			}
			return carrealView;
		}

		
		public String getExRealData(String key, String projectId) {
			
			try {	
				
				//验证参数是否合法
				if(key==null || key.trim().equals("") || projectId ==null || projectId.trim().equals("")){
					return ERROR_XML ;
				}
				
				
				//验证授权码是否正确
				if(!this.projectAuthDao.queryAuthCode(projectId, key))
				{
					return ERROR_XML ;
				}
				
				//获取车载实时数据
				List<CarRealView> list = this.carrealdao.getByProject(projectId);
				
				if(list==null || list.size()==0){
					return ERROR_XML ;
				}
				
				return getCarRealXML(list.get(0));
			
			} catch (Exception e) {
				logger.warn("获取车载"+projectId+"实时平均数据错误: 	"+e.getMessage());
				return ERROR_XML ;
			}
		}
		//获取车载实时数据，有授权码key的
		public CarRealView getRealData(String key, String projectId) {
			CarRealView view = new CarRealView();
			try {
				//判断参数
				if(key==null || key.equals("")|| projectId==null ||projectId.equals("")){
					view.setFlag(NONE);
					return view ;
				}
				
				//验证授权码是否正确
				if(!this.projectAuthDao.queryAuthCode(projectId, key)){
					view.setFlag(NONE);
					return view ;
				}
				
				return getRealDataOld(projectId);
			} catch (Exception e) {
				logger.warn("获取车载"+projectId+"实时数据错误 ： "+e.getMessage()) ;
				view.setFlag(ERROR);
				return view ;
			}
			
		}
		
		
		/**
		 * 获取车载实时数据的xml字符串
		 */
		private String getCarRealXML(CarRealView real){
			
			Double tavg = 0.0 ;
			Integer tcount = 0 ;
			StringBuffer sb = new StringBuffer();
			
			if(real.getAi1()!=-300){
				tavg += real.getAi1() ;
				tcount ++ ;
			}
			
			if(real.getAi2()!=-300){
				tavg += real.getAi2() ;
				tcount ++ ;
			}
			
			if(real.getAi3()!=-300){
				tavg +=real.getAi3() ;
				tcount ++ ;
			}
			
			tavg = tcount!=0?tavg/tcount:-300 ;
			
			sb.append("<?xml version='1.0' encoding='utf-8' ?>");
			sb.append("<datas>");
			sb.append("<result_state>1</result_state>");
			sb.append("<carReal>");
			sb.append("<updateTime>"+util.getToString(real.getUpdateTime())+"</updateTime>");
			sb.append("<tavg>"+tavg+"</tavg>");	
			sb.append("</carReal>");
			sb.append("</datas>");
			
			return sb.toString() ;
		}
		
		
		
		
		/**
		 * 老版本和新版本共同调用的一个方法，或者车载的实时数据的基本方法
		 * @param projectId		车载工程标识Id
		 * @return
		 */
		private  CarRealView getRealDataOld(String projectId){
			CarRealView carrealView = new CarRealView();
			
			try {
							List<CarRealView>	list = carrealdao.getByProject(projectId);
							if(list==null || list.size()==0){
								carrealView.setFlag(NONE) ;			//设置当前实时数据为空
							}else{
								CarRealView source = list.get(0) ;		//获取当前的实时数据集合的第一条
								
								carrealView.setAi1(source.getAi1()) ;
								carrealView.setAi2(source.getAi2()) ;
								carrealView.setAi3(source.getAi3()) ;
								carrealView.setAi4(source.getAi4()) ;
								
								carrealView.setAlarmStatus(source.getAlarmStatus()) ;
								carrealView.setConnectionStatus(source.getConnectionStatus()) ;
								carrealView.setRunStatus(source.getRunStatus()) ;
								carrealView.setUpdateTime(source.getUpdateTime()) ;
								
			
								carrealView.setLatitude(source.getLatitude()) ;
								carrealView.setLatitude_dir(source.getLatitude_dir()) ;
								carrealView.setLongitude(source.getLongitude()) ;
								carrealView.setLongitude_dir(source.getLongitude_dir()) ;
								
								carrealView.setProjectId(source.getProjectId());
								carrealView.setId(source.getId()) ;
								
								carrealView.setFlag(NORMAL) ;		//设置当前实时数据为正常			
							}
			} catch (Exception e) {
				logger.warn("获取车载"+projectId+"实时数据错误："+e.getMessage());
				carrealView.setFlag(ERROR);							//设置为当前实时数据为错误
			}	
			return carrealView  ;
		}


		@Override
		public List<CarRealData> getRealDataByHqid(String hqid) {
			return carrealdao.getByHqid(hqid);
		}


		@Override
		public List<CarRealData> getRealDataByIdAndType(String companyid,
				int type) {
			if(type==1){
				return carrealdao.getByHqid(companyid);
			}else {
				return carrealdao.getByBranchid(companyid);
			}
		}
		
		
		//获取车载实时数据，有授权码key的
		public CarRealView_new getRealData_sy(String key, String projectId) {
			CarRealView_new view = new CarRealView_new();
			try {
				//判断参数
				if(key==null || key.equals("")|| projectId==null ||projectId.equals("")){
					view.setFlag(NONE);
					return view ;
				}
				
				//验证授权码是否正确
				if(!this.projectAuthDao.queryAuthCode(projectId, key)){
					view.setFlag(NONE);
					return view ;
				}
				
				List<CarRealView_new>	list = carrealdao.getByProject_sy(projectId);
				if(list==null || list.size()==0){
					view.setFlag(NONE) ;			//设置当前实时数据为空
				}else{
					CarRealView_new source = list.get(0) ;		//获取当前的实时数据集合的第一条
					
					view.setAi1(source.getAi1()) ;
					view.setAi2(source.getAi2()) ;
					view.setAi3(source.getAi3()) ;
					view.setAi4(source.getAi4()) ;
					
					view.setAlarmStatus(source.getAlarmStatus()) ;
					view.setConnectionStatus(source.getConnectionStatus()) ;
					view.setRunStatus(source.getRunStatus()) ;
					view.setUpdateTime(source.getUpdateTime()) ;
					
					view.setUnloadStatus(source.getUnloadStatus());
					view.setAlarmData(source.getAlarmData());
					view.setCarSpeed(source.getCarSpeed());
					view.setSpeedStatus(source.getSpeedStatus());

					view.setLatitude(source.getLatitude()) ;
					view.setLatitude_dir(source.getLatitude_dir()) ;
					view.setLongitude(source.getLongitude()) ;
					view.setLongitude_dir(source.getLongitude_dir()) ;
					
					view.setProjectId(source.getProjectId());
					view.setId(source.getId()) ;
					
					view.setFlag(NORMAL) ;		//设置当前实时数据为正常			
				}
			} catch (Exception e) {
				logger.warn("获取车载"+projectId+"实时数据错误 ： "+e.getMessage()) ;
				view.setFlag(ERROR);
			}
			return view;
			
		}
		
		
		
		
}
