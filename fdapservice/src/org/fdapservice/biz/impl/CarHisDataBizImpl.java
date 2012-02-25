package org.fdapservice.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.biz.CarHisDataBiz;
import org.fdapservice.dao.CarHisDataDao;
import org.fdapservice.dao.OrgCodeDao;
import org.fdapservice.dao.impl.CarHisDataDaoImpl;
import org.fdapservice.dao.impl.OrgCodeDaoImpl;
import org.fdapservice.entity.CarHisData;
import org.fdapservice.entity.HisCarData;
import org.fdapservice.service.UploadService;
/**
 * 车载历史数据上传业务实现类
 * @author zhoukuanwei
 *
 */
public class CarHisDataBizImpl implements CarHisDataBiz {
	private CarHisDataDao carhisdatadao = new CarHisDataDaoImpl();
	
	private OrgCodeDao orgcodedao = new OrgCodeDaoImpl();
	
	private Logger logger = Logger.getLogger(CarHisDataBizImpl.class);
	
	@Override
	public Integer uploadCarHisData(String code, List<CarHisData> carHislist) {
		if(code==null||code.equals("")||carHislist==null||carHislist.size()==0){
			return UploadService.FAIL;
		}
		StringBuffer sb = new StringBuffer();
		
		try {
			for(int i=0;i<carHislist.size();i++){
				sb.append(buildStr(carHislist.get(i)));
				if(i!=carHislist.size()-1){
					sb.append(";");
				}
			}
			Integer result = carhisdatadao.uploadCarHisData(code, sb.toString());
			
			if(result.toString().equals("0")){
				return UploadService.SUCCESS;
			}
			return UploadService.FAIL;
		} catch (Exception e) {
			logger.error("上传车载历史数据失败"+e.getMessage());
			return UploadService.ERROR;
		}
		
	}
	
	@Override
	public List<HisCarData> getCarHis(String code, Long startupid) {
		if(code==null||code.equals("")||startupid==null||startupid.toString().equals("")){
			logger.error("获取车载历史数据传递了非法参数");
			return null;
		}
		Long oid = orgcodedao.queryOidByCode(code);
		if(oid!=null&&!oid.toString().equals("")&&!oid.toString().equals("0")){
			String tableName = "Fdapcarhisdata_"+oid.toString();
//			System.out.println(tableName);
			return carhisdatadao.queryCarHis(tableName, startupid);
		}
		return null;
	}

	/**
	 * 构造车载历史数据字符串
	 * @param carhis			车载历史数据信息
	 * @return					返回结果字符串:{aid1,aid2,aid3,t1,t2,t3,latitude,Latitude_dir,longitude,longitude_dir,time,isalarm},其中isalarm默认暂时为2，方便后面历史数据报警的处理
	 */
	private String buildStr(CarHisData carhis){
		StringBuffer sb = new StringBuffer();
		sb.append(carhis.getAid1()+","+carhis.getAid2()+","+carhis.getAid3()+","+carhis.getT1()+","+
				carhis.getT2()+","+carhis.getT3()+","+carhis.getLatitude()+","+carhis.getLatitude_dir()+","+
				carhis.getLongitude()+","+carhis.getLongitude_dir()+","+carhis.getTime()+",2");
		return sb.toString();
	}
	
}
