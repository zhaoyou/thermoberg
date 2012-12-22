package org.fdapservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.fdapservice.biz.*;
import org.fdapservice.biz.impl.*;
import org.fdapservice.entity.*;
import org.fdapservice.service.UploadService;
import org.fdapservice.util.DateUtil;



public class UploadServiceImpl implements UploadService {

	public RefRealDataBiz refRealDataBiz = new RefRealDataBizImpl();
	
	public CarRealDataBiz carRealDataBiz = new CarRealDataBizImpl();
	
	public CarStartupBiz carStartupBiz = new CarStartupBizImpl();
	
	public CarHisDataBiz carhisdatabiz = new CarHisDataBizImpl();
	
	public RefHisDataBiz refhisdatabiz = new RefHisDataBizImpl();	
	/*wjt小批零---------------------*/
	
    public RetailRealDataBiz retailRealDataBiz = new RetailRealDataBizImpl();
	
	public RetailStartupBiz retailStartupBiz = new RetailStartupBizImpl();
	
	public RetailHisDataBiz retailhisdatabiz = new RetailHisDataBizImpl();
	@Override
	/**
	 * 上传冷库实时数据
	 */
	public Integer uploadRefRealData(String code, List<RefRealData> list,String Time) {
		return refRealDataBiz.uploadRealData(code, list,Time);
	}
	/**
	 * 上传车载实时数据
	 */
	@Override
	public Integer uploadCarRealData(String code, List<CarRealData> carrealList) {
		/*for(CarRealData carreal : carrealList){
			System.out.println(carreal.getAid1()+" "+carreal.getAid2()+" "+carreal.getAid3()+" "+carreal.getIsalarm()+" "
					+carreal.getLatitude_dir()+" "+carreal.getLongitude_dir()+" "+carreal.getTime()+" "+carreal.getLatitude()+" "
					+carreal.getLongitude()+" "+carreal.getRunstatus()+" "+carreal.getT1()+" "+carreal.getT2()+" "+carreal.getT3());
		}*/
		return carRealDataBiz.uploadCarReal(code, carrealList);
	}
	/**
	 * 上传车载启停记录
	 */
	@Override
	public Integer uploadCarStartup(String code, List<CarStartup> startuplist) {
		return carStartupBiz.uploadStartup(code, startuplist);
	}
	/**
	 * 上传车载历史数据
	 */
	@Override
	public Integer uploadCarHisData(String code, List<CarHisData> carHislist) {
		return carhisdatabiz.uploadCarHisData(code, carHislist);
	}
	
	/**
	 * 上传小批零实时数据
	 */
	@Override
	public Integer uploadRetailRealData(String code, List<RetailRealData> retailrealList) {
		/*for(CarRealData carreal : carrealList){
			System.out.println(carreal.getAid1()+" "+carreal.getAid2()+" "+carreal.getAid3()+" "+carreal.getIsalarm()+" "
					+carreal.getLatitude_dir()+" "+carreal.getLongitude_dir()+" "+carreal.getTime()+" "+carreal.getLatitude()+" "
					+carreal.getLongitude()+" "+carreal.getRunstatus()+" "+carreal.getT1()+" "+carreal.getT2()+" "+carreal.getT3());
		}*/
		return retailRealDataBiz.uploadRetailReal(code, retailrealList);
	}
	/**
	 * 上传小批零启停记录
	 */
	@Override
	public Integer uploadRetailStartup(String code, List<RetailStartup> retailstartuplist) {
		return retailStartupBiz.uploadRetailStartup(code, retailstartuplist);
	}
	/**
	 * 上传小批零历史数据
	 */
	@Override
	public Integer uploadRetailHisData(String code, List<RetailHisData> retailHislist) {
		return retailhisdatabiz.uploadRetailHisData(code, retailHislist);
	}
	
	/*----------------以下是get方法-------------------------------*/
	/**
	 * 获得冷库实时数据
	 */
	@Override
	public List<RefRealData> getRefReal(String code) {
		return refRealDataBiz.getRefReal(code);
	}
	/**
	 * 获得冷库历史数据
	 */
	@Override
	public List<RefHisData> getRefHis(String code, String startTime,
			String endTime, Long refId) {
		return refhisdatabiz.getRefHis(code, startTime, endTime, refId);
	}
	/**
	 * 获得车载实时数据
	 */
	@Override
	public List<CarRealData> getCarReal(String code) {
		return carRealDataBiz.getCarReal(code);
	}
	/**
	 * 获得车载启停数据
	 */
	@Override
	public List<HisStartup> getStartUp(String code, String startTime,
			String endTime, Long refId) {
		return carStartupBiz.getStartUp(code, startTime, endTime, refId);
	}
	/**
	 * 获得车载历史
	 */
	@Override
	public List<HisCarData> getCarHis(String code, Long startupid) {
		return carhisdatabiz.getCarHis(code, startupid);
	}
	/**
	 * 获得小批零实时数据
	 */
	@Override
	public List<RetailRealData> getRetailReal(String code) {
		return retailRealDataBiz.getRetailReal(code);
	}
	/**
	 * 获得小批零启停数据
	 */
	@Override
	public List<HisRetailStartup> getRetailStartUp(String code, String startTime,
			String endTime, Long refId) {
		return retailStartupBiz.getRetailStartUp(code, startTime, endTime, refId);
	}
	/**
	 * 获得小批零历史
	 */
	@Override
	public List<HisRetailData> getRetailHis(String code, Long startupid) {
		return retailhisdatabiz.getRetailHis(code, startupid);
	}

	@Override
	public Integer Test(String str,List<Test> tlist) {
		if(str==null||str.equals("")||tlist==null||tlist.size()==0){
			return 1;
		}
		for(Test t : tlist){
			System.out.println(t.getName()+" "+t.getId()+" "+t.getTt());
		}
		return 0;
	}
	
	public static void main(String[] args) {
		/*String code = "9c219869-2229-4a00-9e51-fad53d0c4ca6" ;
		List<RefRealData> list = new ArrayList<RefRealData>();
		RefRealData d =new RefRealData();
		d.setId(1) ;
		d.setStatus(0);
		d.setTime(DateUtil.getToString(new java.util.Date()));
		d.setValue(12.2);
		list.add(d);
		Integer i = new UploadServiceImpl().uploadRefRealData(code, list, d.getTime());
		System.out.println(i);*/
		String code = "ed9d07ed-968c-4bc0-a154-caee9b5565dd";
		List<RefRealData> list = null;
		try {
			list = new UploadServiceImpl().getRefReal(code);
			if(list!=null&&list.size()!=0){
				for(RefRealData refreal:list){
					System.out.println(refreal.getId()+" :"+refreal.getValue());
				}
			}
		} catch (Exception e) {
			System.out.println("查询冷库实时数据失败");
		}
	}

}
