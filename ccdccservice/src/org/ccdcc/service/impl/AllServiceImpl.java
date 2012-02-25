package org.ccdcc.service.impl;

import java.util.List;

import org.ccdcc.biz.CarHisBiz;
import org.ccdcc.biz.CarRealBiz;
import org.ccdcc.biz.RefRealBiz;
import org.ccdcc.biz.StartUpBiz;
import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.CarHisView_new;
import org.ccdcc.entity.CarRealData;
import org.ccdcc.entity.CarRealView;
import org.ccdcc.entity.CarRealView_new;
import org.ccdcc.entity.StartUpView;
import org.ccdcc.service.AllService;

/**
 * 包括所有的服务接口的服务类
 * @author Administrator
 *
 */
public class AllServiceImpl implements AllService {
	
	private CarHisBiz carhisbiz = null ;	
	
	private CarRealBiz carrealbiz = null ;

	private StartUpBiz startupbiz = null ;
	
	private RefRealBiz	refrealbiz = null ;

	public void setRefrealbiz(RefRealBiz refrealbiz) {
		this.refrealbiz = refrealbiz;
	}

	public void setCarhisbiz(CarHisBiz carhisbiz) {
		this.carhisbiz = carhisbiz;
	}

	public void setCarrealbiz(CarRealBiz carrealbiz) {
		this.carrealbiz = carrealbiz;
	}

	public void setStartupbiz(StartUpBiz startupbiz) {
		this.startupbiz = startupbiz;
	}

	
	public List<CarHisView> getCarHis(String projectId, Integer parentId,
			String afterTime) {
		return this.carhisbiz.getCarList2(projectId, parentId, afterTime);		
	}

	public CarRealView getCarRealData(String projectId) {
		return this.carrealbiz.getRealData(projectId) ;
	}

	public List<StartUpView> getStartUpList(String projectId, Integer id) {
		return this.startupbiz.getListByAfterId(projectId, id);
	}

	public String getRefRealData(String key, String projectId,
			Integer realRefId, Integer refType, Integer floorType,
			Integer floorNo) {
		return this.refrealbiz.getRefReal(key, projectId, realRefId, refType, floorType, floorNo);
	}

	public String getAllRefRealData(String key, String projectId) {
		return this.refrealbiz.getRefReal(key, projectId);
	}

	public String getExCarRealData(String key, String projectId) {
		return this.carrealbiz.getExRealData(key, projectId);
	}

	
	public CarRealView getCarRealData_auth(String key, String projectId) {
		return this.carrealbiz.getRealData(key, projectId);
	}

	public List<CarHisView> getCarHis_auth(String key, String projectId,
			Integer parentId, String afterTime) {
		return this.carhisbiz.getCarList2(key, projectId, parentId, afterTime);
	}

	public List<StartUpView> getStartUpList_auth(String key, String projectId,
			Integer id) {
		return this.startupbiz.getListByAfterId(key, projectId, id);
	}

	@Override
	public List<CarRealData> getCarRealData_company(String CompanyId) {
		return carrealbiz.getRealDataByHqid(CompanyId);
	}
	
	@Override
	public List<CarRealData> getCarRealData_companytype(String CompanyId,
			int type) {
		return carrealbiz.getRealDataByIdAndType(CompanyId,type);
	}

	@Override
	public List<StartUpView> getStartUpList_auth_tupdw(String key,
			String projectId, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<CarHisView_new> getCarHis_sy(String key, String projectId,
			Integer parentId, String afterTime) {
		return carhisbiz.getCarList2_sy(key, projectId, parentId, afterTime);
	}

	@Override
	public CarRealView_new getCarRealData_sy(String key, String projectId) {
		return carrealbiz.getRealData_sy(key, projectId);
	}
	
}
