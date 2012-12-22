package org.fdapservice.service;

import java.util.List;

import org.fdapservice.entity.CarHisData;
import org.fdapservice.entity.CarRealData;
import org.fdapservice.entity.CarStartup;
import org.fdapservice.entity.HisCarData;
import org.fdapservice.entity.HisRetailData;
import org.fdapservice.entity.HisRetailStartup;
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
import org.fdapservice.entity.RetailHisData;
import org.fdapservice.entity.RetailRealData;
import org.fdapservice.entity.RetailStartup;


/**
 * 上传数据接口
 * @author zhaoyou
 *
 */
public interface UploadService {
	/**
	 * 上传数据发生失败	1
	 */
	public final static Integer FAIL = 1 ;
	
	/**
	 * 上传数据成功 	0
	 */
	public final static Integer SUCCESS = 0 ;
	
	/**
	 * 上传数据服务器发生错误	2
	 */
	public final static Integer ERROR = 2 ;
	
	/**
	 * 上传冷库实时数据
	 * @param code			企业授权码
	 * @param list			冷库探头的实时数据集合
	 * @param Time			数据时刻
	 * @return					上传操作的状态码
	 */
	public Integer uploadRefRealData(String code ,List<RefRealData> list,String Time) ;
	
	/**
	 * 上传车载实时数据
	 * @param code				企业授权码
	 * @param list				车载实时数据集合
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadCarRealData(String code,List<CarRealData> carrealList);
	
	/**
	 * 上传车载启停记录
	 * @param code				企业授权码
	 * @param list				车载启停记录集合
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadCarStartup(String code,List<CarStartup> startuplist);
	
	/**
	 * 上传车载历史数据
	 * @param code				企业授权码
	 * @param list				车载历史数据信息集合
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadCarHisData(String code,List<CarHisData> carHislist);
	
	/**
	 * 上传小批零实时数据
	 * @param code				企业授权码
	 * @param list				小批零实时数据集合
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadRetailRealData(String code,List<RetailRealData> retailrealList);
	
	/**
	 * 上传小批零启停记录
	 * @param code				企业授权码
	 * @param list				小批零启停记录集合
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadRetailStartup(String code,List<RetailStartup> retailstartuplist);
	
	/**
	 * 上传小批零历史数据
	 * @param code				企业授权码
	 * @param list				小批零历史数据信息集合
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public Integer uploadRetailHisData(String code,List<RetailHisData> retailHislist);
	
	
	/**下面是几个get接口**---------------------------------------------------------/
	
	
	/**
	 * 根据企业授权码获取该企业下所有的仓库实时数据
	 * @param code			企业授权码
	 * return
	 */
	public List<RefRealData> getRefReal(String code);
	
	
	
	/**
	 * 根据企业授权码获取该企业下所有的仓库历史数据
	 * @param code			企业授权码
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param refId			冷库标识ID
	 * return
	 */
	public List<RefHisData> getRefHis(String code,String startTime,String endTime,Long refId);
	
	
	/**
	 * 根据企业授权码获取该企业下所有车载的实时数据
	 * @param code			企业授权码
	 * @return
	 */
	public List<CarRealData> getCarReal(String code);
	
	
	/**
	 * 根据时间与车载冷库ID，获取对应车载历史启停记录
	 * @param code			企业授权码
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param refId			冷库标识ID
	 * @return
	 */
	public List<HisStartup> getStartUp(String code,String startTime,String endTime,Long refId);
	
	
	
	/**
	 * 通过车载历史启停记录标识ID获取车载历史数据
	 * @param code				企业授权码
	 * @param startupid			车载历史启停记录标识ID
	 * @return
	 */
	public List<HisCarData> getCarHis(String code,Long startupid);
	
	/**
	 * 根据企业授权码获取该企业下所有小批零的实时数据
	 * @param code			企业授权码
	 * @return
	 */
	public List<RetailRealData> getRetailReal(String code);
	
	
	/**
	 * 根据时间与小批零冷库ID，获取对应小批零历史启停记录
	 * @param code			企业授权码
	 * @param startTime		开始时间
	 * @param endTime		结束时间
	 * @param refId			冷库标识ID
	 * @return
	 */
	public List<HisRetailStartup> getRetailStartUp(String code,String startTime,String endTime,Long refId);
	
	
	
	/**
	 * 通过车载历史启停记录标识ID获取车载历史数据
	 * @param code				企业授权码
	 * @param startupid			车载历史启停记录标识ID
	 * @return
	 */
	public List<HisRetailData> getRetailHis(String code,Long startupid);
	
	
	/**
	 * 用于测试的接口
	 */
	public Integer Test(String str,List<org.fdapservice.entity.Test> tlist);
	
	// ====================================== for order track
	
	public PbmCallResult uploadMiCar(List<PbmMiCar> carList);
	public PbmCallResult uploadMiRef(List<PbmMiRef> refList);
	public PbmCallResult uploadReceiver(List<PbmMiReceiver> receiverList);
	public PbmCallResult uploadBasicGoods(List<PbmMiGoodsBaseInfo> basicList);
	public PbmCallResult uploadFullGoods(List<PbmMiGoodsFullInfo> fullList);
	public PbmCallResult uploadOrder(PbmMiOrderTrack order,
									 List<PbmCarInOutTrack> carList,
									 List<PbmRefInOutTrack> refList,
									 List<PbmErpRefInOutTrack> erpLIst,
									 List<PbmMiBarCodeTrack> barcodeList,
									 List<PbmMiBarCodeDetailTrack> barcodeDetailList,
									 List<PbmSubOrderMainTrack> subOrderList,
									 List<PbmSubOrderDetailTrack> subOrderDetailList);
	
}
