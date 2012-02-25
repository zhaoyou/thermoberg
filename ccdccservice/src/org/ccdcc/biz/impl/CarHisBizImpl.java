package org.ccdcc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.ccdcc.biz.CarHisBiz;
import org.ccdcc.biz.StartUpBiz;
import org.ccdcc.dao.CarHisDao;
import org.ccdcc.dao.ProjectAuthDao;
import org.ccdcc.dao.StartUpDao;
import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.CarHisView_new;
import org.ccdcc.entity.StartUpView;
import org.ccdcc.util.BuildTable;
import org.ccdcc.util.ProjectOperate;
import org.ccdcc.util.Util;

/**
 * 车载历史数据业务接口
 * @author Administrator
 *
 */
public class CarHisBizImpl implements CarHisBiz {
	
	private CarHisDao carhisdao = null  ;

	private StartUpDao startupdao = null ;
	
	private ProjectAuthDao projectAuthDao = null ;
	
	private Util util = null ;
	
	private Logger logger = Logger.getLogger(CarHisBizImpl.class);
	
	public void setUtil(Util util) {
		this.util = util;
	}

	public void setStartupdao(StartUpDao startupdao) {
		this.startupdao = startupdao;
	}

	public void setCarhisdao(CarHisDao carhisdao) {
		this.carhisdao = carhisdao;
	}
	
	public void setProjectAuthDao(ProjectAuthDao projectAuthDao) {
		this.projectAuthDao = projectAuthDao;
	}


	public List<CarHisView> getCarList(String projectId, String time,
			String afterTime) {
		
		List<CarHisView> list = new ArrayList<CarHisView>() ;		//保存移动车载历史数据集合
		
		CarHisView view  = new CarHisView();			//用来标识结果状态的实例对象
		
		try {
			//参数非法，没有数据返回
			if(projectId==null || projectId.trim().equals("") || time==null ||time.trim().equals("") 
					||afterTime==null || afterTime.equals("")){
				view.setFlag(NONE) ;
				list.add(view) ;
			}else{
				
				if(util.getToDate(time)==null || util.getToDate(afterTime)==null){
					throw new Exception("日期格式不对! "+time +" ;" + afterTime);
				}
				
				String startupTable = BuildTable.BuildStartUpTable(projectId) ;
				String hisdataTable = BuildTable.BuildCarHisTable(projectId) ;
				
				int sid = startupdao.getId(startupTable, time) ;
				
				if(sid==0){
					view.setFlag(NONE) ;
					list.add(view) ;
				}else{
					List<CarHisView> sourceList = carhisdao.getCarhis(hisdataTable, sid, afterTime) ;
					
					if(sourceList == null || sourceList.size()==0){
						view.setFlag(NONE) ;
						list.add(view) ;
					}else{
						for (CarHisView carHisView : sourceList) {
							carHisView.setFlag(NORMAL) ;
							list.add(carHisView);
						}
					}
					
				}
			}
		} catch (Exception e) {
			logger.warn("获取车载"+projectId+"历史数据错误： "+e.getMessage());
			list.clear() ;
			view.setFlag(ERROR) ;
			list.add(view) ;
		}
		
		return list;
	}
	//根据项目的projectId 、历史数据的启停Id、以及该启停记录中的某个时间点查询车载历史数据50条
	public List<CarHisView> getCarList2(String projectId, Integer id,
			String afterTime) {
		
		List<CarHisView> list = new ArrayList<CarHisView>() ;		//保存移动车载历史数据集合
		
		CarHisView view  = new CarHisView();						//用来标识结果状态的实例对象
			//参数非法，没有数据返回  || !EXISTSPRJS.contains(projectId)
			if(!ProjectOperate.EXISTSPRJS(projectId)||projectId==null || projectId.trim().equals("") || id==null || id.equals("") 
					||afterTime==null || afterTime.trim().equals("") ){
				view.setFlag(NONE) ;
				list.add(view) ;
			}else{
				list=getCarList2Old(projectId, id, afterTime);
			}
		return list;
	}
	//根据项目标识Id，对应的授权码，起停Id，和起停中的某一刻时间获取历史数据
	public List<CarHisView> getCarList2(String key, String projectId,
			Integer id, String afterTime) {
		
		List<CarHisView> list = new ArrayList<CarHisView>(); 
		CarHisView view = new CarHisView() ;
		
		try {
			
			//验证参数
			if(key==null || key.equals("") || projectId==null || projectId.equals("")|| id==null || id.equals("")
					|| afterTime==null || afterTime.equals("")){
				view.setFlag(NONE) ;
				list.add(view) ;
				return list ;
			}
			
			//验证授权码
			if(!this.projectAuthDao.queryAuthCode(projectId, key)){
				view.setFlag(NONE);
				list.add(view);
				return list ;
			}
			
			return getCarList2Old(projectId, id, afterTime);
			
		} catch (Exception e) {
			logger.warn("获取"+projectId+"车载历史数据错误： "+e.getMessage());
			view.setFlag(ERROR) ;
			list.clear() ;
			list.add(view);
			return list ;
		}

	}

	/**
	 * 老版本和新版本共同调用的一个方法，或者车载的历史数据的基本方法
	 * @param projectId		车载工程标识Id
	 * @param id		    车载启停标识Id
	 * @param afterTime		启停记录中的某个时间点
	 * @return
	 */
	private List<CarHisView> getCarList2Old(String projectId, Integer id,
			String afterTime)
	{
		List<CarHisView> list = new ArrayList<CarHisView>() ;		//保存移动车载历史数据集合
		
		CarHisView view  = new CarHisView();						//用来标识结果状态的实例对象
		
		try {
				if(util.getToDate(afterTime)==null ){
					throw new Exception("日期格式不对!"+afterTime );
				}
				
				String startupTable = BuildTable.BuildStartUpTable(projectId) ;
				String hisdataTable = BuildTable.BuildCarHisTable(projectId) ;
				
				//获取历史数据
				List<CarHisView> sourceList = 
					carhisdao.getCarhis2(hisdataTable, id, afterTime);
				
				//首先集合必须不为空
				if(sourceList==null){
					view.setFlag(NONE);
					list.add(view);
				}else{
					
					//判断当前集合是否有数据
					if(sourceList.size()==0){
						
						StartUpView startUpObj = startupdao.get(startupTable,id);
						
						//此处的判断是为了解决不同数据库数据间的导入导出，自动增长所引起的不匹配。
						if(startUpObj==null){
							view.setFlag(NONE);
							list.add(view);
						}else{
								//判断当前启停记录是否已经上传完成
							if(startUpObj.getUpdateStatus().equals(StartUpBiz.FINISH)){
								view.setFlag(NORMAL);
								view.setEndTime(startUpObj.getEndTime()) ;
								list.add(view);
							}else{
								view.setFlag(NONE);
								list.add(view);
							}
						}	
						
					//当前集合至少存在一条历史数据	
					}
					else{
						for (CarHisView carHisView : sourceList) {
							carHisView.setFlag(NONE);
							list.add(carHisView);
						}
					}
				
			}
		} catch (Exception e) {
			logger.warn("获取"+projectId+"车载历史数据错误： "+e.getMessage());
			list.clear() ;
			view.setFlag(ERROR) ;
			list.add(view) ;
		}	
		return list;
	}
	
	
	//根据项目标识Id，对应的授权码，起停Id，和起停中的某一刻时间获取历史数据
	public List<CarHisView_new> getCarList2_sy(String key, String projectId,
			Integer id, String afterTime) {
		
		List<CarHisView_new> list = new ArrayList<CarHisView_new>(); 
		CarHisView_new view = new CarHisView_new() ;
		
		try {
			//验证参数
			if(key==null || key.equals("") || projectId==null || projectId.equals("")|| id==null || id.equals("")
					|| afterTime==null || afterTime.equals("")){
				view.setFlag(NONE) ;
				list.add(view) ;
				return list ;
			}
			
			//验证授权码
			if(!this.projectAuthDao.queryAuthCode(projectId, key)){
				view.setFlag(NONE);
				list.add(view);
				return list ;
			}
			
			if(util.getToDate(afterTime)==null ){
				throw new Exception("日期格式不对!"+afterTime );
			}
			
			String startupTable = BuildTable.BuildStartUpTable(projectId) ;
			String hisdataTable = BuildTable.BuildCarHisTable(projectId) ;
			
			//获取历史数据
			List<CarHisView_new> sourceList = carhisdao.getCarhis_sy(hisdataTable, id, afterTime);

			
			//首先集合必须不为空
			if(sourceList==null){
				view.setFlag(NONE);
				list.add(view);
			}else{
				//判断当前集合是否有数据
				if(sourceList.size()==0){
					
					StartUpView startUpObj = startupdao.get(startupTable,id);
					
					//此处的判断是为了解决不同数据库数据间的导入导出，自动增长所引起的不匹配。
					if(startUpObj==null){
						view.setFlag(NONE);
						list.add(view);
					}else{
							//判断当前启停记录是否已经上传完成
						if(startUpObj.getUpdateStatus().equals(StartUpBiz.FINISH)){
							view.setFlag(NORMAL);
							view.setEndTime(startUpObj.getEndTime()) ;
							list.add(view);
						}else{
							view.setFlag(NONE);
							list.add(view);
						}
					}	
					
				//当前集合至少存在一条历史数据
				}
				else{
					for (CarHisView_new carHisView : sourceList) {
						carHisView.setFlag(NONE);
						list.add(carHisView);
					}
				}
			
			}
			
		} catch (Exception e) {
			logger.warn("获取"+projectId+"车载历史数据错误： "+e.getMessage());
			list.clear() ;
			view.setFlag(ERROR) ;
			list.add(view) ;
		}
		return list;
	}
	
	
	
	
	
}
