package org.fdapservice.service.impl;

import java.util.List;

import org.fdapservice.biz.CarHisDataBiz;
import org.fdapservice.biz.CarRealDataBiz;
import org.fdapservice.biz.CarStartupBiz;
import org.fdapservice.biz.RefHisDataBiz;
import org.fdapservice.biz.RefRealDataBiz;
import org.fdapservice.biz.impl.CarHisDataBizImpl;
import org.fdapservice.biz.impl.CarRealDataBizImpl;
import org.fdapservice.biz.impl.CarStartupBizImpl;
import org.fdapservice.biz.impl.OrderTrackBizImpl;
import org.fdapservice.biz.impl.RefHisDataBizImpl;
import org.fdapservice.biz.impl.RefRealDataBizImpl;
import org.fdapservice.entity.CarHisData;
import org.fdapservice.entity.CarRealData;
import org.fdapservice.entity.CarStartup;
import org.fdapservice.entity.HisCarData;
import org.fdapservice.entity.HisStartup;
import org.fdapservice.entity.PbmCallResult;
import org.fdapservice.entity.PbmCarInOutTrack;
import org.fdapservice.entity.PbmErpRefInOutTrack;
import org.fdapservice.entity.PbmMiBarCodeDetailTrack;
import org.fdapservice.entity.PbmMiBarCodeTrack;
import org.fdapservice.entity.PbmMiCar;
import org.fdapservice.entity.PbmMiGoodsBaseInfo;
import org.fdapservice.entity.PbmMiGoodsFullInfo;
import org.fdapservice.entity.PbmMiOrderTrack;
import org.fdapservice.entity.PbmMiReceiver;
import org.fdapservice.entity.PbmMiRef;
import org.fdapservice.entity.PbmRefInOutTrack;
import org.fdapservice.entity.PbmSubOrderDetailTrack;
import org.fdapservice.entity.PbmSubOrderMainTrack;
import org.fdapservice.entity.RefHisData;
import org.fdapservice.entity.RefRealData;
import org.fdapservice.entity.Test;
import org.fdapservice.service.UploadService;

public class UploadServiceImpl implements UploadService {

	public RefRealDataBiz refRealDataBiz = new RefRealDataBizImpl();
	
	public CarRealDataBiz carRealDataBiz = new CarRealDataBizImpl();
	
	public CarStartupBiz carStartupBiz = new CarStartupBizImpl();
	
	public CarHisDataBiz carhisdatabiz = new CarHisDataBizImpl();
	
	public RefHisDataBiz refhisdatabiz = new RefHisDataBizImpl();
	
	public OrderTrackBizImpl orderTrackBiz = new OrderTrackBizImpl();
	
	@Override
	public Integer uploadRefRealData(String code, List<RefRealData> list,String Time) {
		return refRealDataBiz.uploadRealData(code, list,Time);
	}
	
	@Override
	public Integer uploadCarRealData(String code, List<CarRealData> carrealList) {
		/*for(CarRealData carreal : carrealList){
			System.out.println(carreal.getAid1()+" "+carreal.getAid2()+" "+carreal.getAid3()+" "+carreal.getIsalarm()+" "
					+carreal.getLatitude_dir()+" "+carreal.getLongitude_dir()+" "+carreal.getTime()+" "+carreal.getLatitude()+" "
					+carreal.getLongitude()+" "+carreal.getRunstatus()+" "+carreal.getT1()+" "+carreal.getT2()+" "+carreal.getT3());
		}*/
		return carRealDataBiz.uploadCarReal(code, carrealList);
	}

	@Override
	public Integer uploadCarStartup(String code, List<CarStartup> startuplist) {
		return carStartupBiz.uploadStartup(code, startuplist);
	}

	@Override
	public Integer uploadCarHisData(String code, List<CarHisData> carHislist) {
		return carhisdatabiz.uploadCarHisData(code, carHislist);
	}

	@Override
	public List<RefRealData> getRefReal(String code) {
		return refRealDataBiz.getRefReal(code);
	}
	
	@Override
	public List<RefHisData> getRefHis(String code, String startTime,
			String endTime, Long refId) {
		return refhisdatabiz.getRefHis(code, startTime, endTime, refId);
	}

	@Override
	public List<CarRealData> getCarReal(String code) {
		return carRealDataBiz.getCarReal(code);
	}

	@Override
	public List<HisStartup> getStartUp(String code, String startTime,
			String endTime, Long refId) {
		return carStartupBiz.getStartUp(code, startTime, endTime, refId);
	}

	@Override
	public List<HisCarData> getCarHis(String code, Long startupid) {
		return carhisdatabiz.getCarHis(code, startupid);
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
		Integer i = new UploadServiceImpl().uploadRefRealData(code, list);
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

	@Override
	public PbmCallResult uploadBasicGoods(List<PbmMiGoodsBaseInfo> basicList) {
		return orderTrackBiz.uploadBasicGoods(basicList);
	}

	@Override
	public PbmCallResult uploadFullGoods(List<PbmMiGoodsFullInfo> fullList) {
		return orderTrackBiz.uploadFullGoods(fullList);
	}

	@Override
	public PbmCallResult uploadMiCar(List<PbmMiCar> carList) {
		return orderTrackBiz.uploadMiCar(carList);
	}

	@Override
	public PbmCallResult uploadMiRef(List<PbmMiRef> refList) {
		return orderTrackBiz.uploadMiRef(refList);
	}

	@Override
	public PbmCallResult uploadOrder(PbmMiOrderTrack order,
			List<PbmCarInOutTrack> carList, List<PbmRefInOutTrack> refList,
			List<PbmErpRefInOutTrack> erpLIst,
			List<PbmMiBarCodeTrack> barcodeList,
			List<PbmMiBarCodeDetailTrack> barcodeDetailList,
			List<PbmSubOrderMainTrack> subOrderList,
			List<PbmSubOrderDetailTrack> subOrderDetailList) {
		return orderTrackBiz.uploadOrder(order, carList, refList, erpLIst, barcodeList,
				barcodeDetailList, subOrderList, subOrderDetailList);
	}

	@Override
	public PbmCallResult uploadReceiver(List<PbmMiReceiver> receiverList) {
		return orderTrackBiz.uploadReceiver(receiverList);
	}

}
