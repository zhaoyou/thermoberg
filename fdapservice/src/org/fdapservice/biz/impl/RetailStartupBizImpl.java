package org.fdapservice.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.biz.RetailStartupBiz;
import org.fdapservice.dao.OrgCodeDao;
import org.fdapservice.dao.RetailStartupDao;
import org.fdapservice.dao.impl.OrgCodeDaoImpl;
import org.fdapservice.dao.impl.RetailStartupDaoImpl;
import org.fdapservice.entity.HisRetailStartup;
import org.fdapservice.entity.RetailStartup;
import org.fdapservice.service.UploadService;

/**
 * 小批零启停记录上传业务实现类
 * @author wjt
 */
public class RetailStartupBizImpl implements RetailStartupBiz {
	private RetailStartupDao retailStartupDao = new RetailStartupDaoImpl();
	
	private OrgCodeDao orgcodedao = new OrgCodeDaoImpl();
	
	private Logger logger = Logger.getLogger(RetailStartupBizImpl.class);
	
	@Override
	public Integer uploadRetailStartup(String code, List<RetailStartup> retailstartuplist) {
		if(code==null||code.equals("")||retailstartuplist==null||retailstartuplist.size()==0){
			return UploadService.FAIL;
		}
		
		StringBuffer sb = new StringBuffer();
		try {
			for(int i = 0;i<retailstartuplist.size();i++){
				sb.append(buildStr(retailstartuplist.get(i)));
				if(i!=retailstartuplist.size()-1){
					sb.append(";");
				}
			}
			Integer result = retailStartupDao.uploadRetailStartup(code, sb.toString());
			
			if(result.toString().equals("0")){
				return UploadService.SUCCESS;
			}
			return UploadService.FAIL;
		} catch (Exception e) {
			logger.error("小批零启停记录上传失败"+e.getMessage());
			return UploadService.ERROR;
		}
		
	}

	/**
	 * 构造启停记录信息的字符串
	 * @param carstartup			单条启停记录信息
	 * @return						返回字符串格式：{aid1,startTime,endTime,carrier,intervalValue}
	 */
	private String buildStr(RetailStartup retailstartup){
		StringBuffer sb = new StringBuffer("");
		sb.append(retailstartup.getAid1()+","+retailstartup.getStartTime()+","+
				(retailstartup.getEndTime()==null||retailstartup.getEndTime().equals("")?"":retailstartup.getEndTime())+","+retailstartup.getCarrier()+","+retailstartup.getIntervalValue());
		return sb.toString();
	}

	@Override
	public List<HisRetailStartup> getRetailStartUp(String code, String startTime,
			String endTime, Long refId) {
		if(code==null||code.equals("")||startTime==null||startTime.equals("")||endTime==null||endTime.equals("")
				||refId==null||refId.toString().equals("0")){
			System.out.println("传递了非法参数");
			return null;
		}
		Long oid = orgcodedao.queryOidByCode(code);
		if(oid!=null&&!oid.toString().equals("0")){
			String tableName = "Fdapretailstartup_"+oid.toString();
			return retailStartupDao.queryRetailStartup(tableName, startTime, endTime, refId);
		}
		else{
			return null;
		}
	}

}
