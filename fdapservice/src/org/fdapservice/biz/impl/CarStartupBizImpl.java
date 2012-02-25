package org.fdapservice.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.biz.CarStartupBiz;
import org.fdapservice.dao.CarStartupDao;
import org.fdapservice.dao.OrgCodeDao;
import org.fdapservice.dao.impl.CarStartupDaoImpl;
import org.fdapservice.dao.impl.OrgCodeDaoImpl;
import org.fdapservice.entity.CarStartup;
import org.fdapservice.entity.HisStartup;
import org.fdapservice.service.UploadService;
/**
 * 车载启停记录上传业务实现类
 * @author zhoukuanwei
 */
public class CarStartupBizImpl implements CarStartupBiz {
	private CarStartupDao carStartupDao = new CarStartupDaoImpl();
	
	private OrgCodeDao orgcodedao = new OrgCodeDaoImpl();
	
	private Logger logger = Logger.getLogger(CarStartupBizImpl.class);
	
	@Override
	public Integer uploadStartup(String code, List<CarStartup> startuplist) {
		if(code==null||code.equals("")||startuplist==null||startuplist.size()==0){
			return UploadService.FAIL;
		}
		
		StringBuffer sb = new StringBuffer();
		try {
			for(int i = 0;i<startuplist.size();i++){
				sb.append(buildStr(startuplist.get(i)));
				if(i!=startuplist.size()-1){
					sb.append(";");
				}
			}
			Integer result = carStartupDao.uploadStartup(code, sb.toString());
			
			if(result.toString().equals("0")){
				return UploadService.SUCCESS;
			}
			return UploadService.FAIL;
		} catch (Exception e) {
			logger.error("车载启停记录上传失败"+e.getMessage());
			return UploadService.ERROR;
		}
		
	}

	/**
	 * 构造启停记录信息的字符串
	 * @param carstartup			单条启停记录信息
	 * @return						返回字符串格式：{aid1,aid2,aid3,startTime,endTime,carrier,intervalValue}
	 */
	private String buildStr(CarStartup carstartup){
		StringBuffer sb = new StringBuffer("");
		sb.append(carstartup.getAid1()+","+carstartup.getAid2()+","+carstartup.getAid3()+","+carstartup.getStartTime()+","+
				(carstartup.getEndTime()==null||carstartup.getEndTime().equals("")?"":carstartup.getEndTime())+","+carstartup.getCarrier()+","+carstartup.getIntervalValue());
		return sb.toString();
	}

	@Override
	public List<HisStartup> getStartUp(String code, String startTime,
			String endTime, Long refId) {
		if(code==null||code.equals("")||startTime==null||startTime.equals("")||endTime==null||endTime.equals("")
				||refId==null||refId.toString().equals("0")){
			System.out.println("传递了非法参数");
			return null;
		}
		Long oid = orgcodedao.queryOidByCode(code);
		if(oid!=null&&!oid.toString().equals("0")){
			String tableName = "Fdapstartup_"+oid.toString();
			return carStartupDao.queryCarStartup(tableName, startTime, endTime, refId);
		}
		else{
			return null;
		}
	}

}
