package org.fdapservice.biz.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.biz.RetailHisDataBiz;
import org.fdapservice.dao.OrgCodeDao;
import org.fdapservice.dao.RetailHisDataDao;
import org.fdapservice.dao.impl.OrgCodeDaoImpl;
import org.fdapservice.dao.impl.RetailHisDataDaoImpl;
import org.fdapservice.entity.HisRetailData;
import org.fdapservice.entity.RetailHisData;
import org.fdapservice.service.UploadService;

/**
 * 小批零历史数据上传业务实现类
 * @author wjt
 *
 */
public class RetailHisDataBizImpl implements RetailHisDataBiz {
	private RetailHisDataDao retailhisdatadao = new RetailHisDataDaoImpl();
	
	private OrgCodeDao orgcodedao = new OrgCodeDaoImpl();
	
	private Logger logger = Logger.getLogger(RetailHisDataBizImpl.class);
	
	@Override
	public Integer uploadRetailHisData(String code, List<RetailHisData> retailHislist) {
		if(code==null||code.equals("")||retailHislist==null||retailHislist.size()==0){
			return UploadService.FAIL;
		}
		StringBuffer sb = new StringBuffer();
		
		try {
			for(int i=0;i<retailHislist.size();i++){
				sb.append(buildStr(retailHislist.get(i)));
				if(i!=retailHislist.size()-1){
					sb.append(";");
				}
			}
			Integer result = retailhisdatadao.uploadRetailHisData(code, sb.toString());
			
			if(result.toString().equals("0")){
				return UploadService.SUCCESS;
			}
			return UploadService.FAIL;
		} catch (Exception e) {
			logger.error("上传小批零历史数据失败"+e.getMessage());
			return UploadService.ERROR;
		}
		
	}
	
	@Override
	public List<HisRetailData> getRetailHis(String code, Long startupid) {
		if(code==null||code.equals("")||startupid==null||startupid.toString().equals("")){
			logger.error("获取小批零历史数据传递了非法参数");
			return null;
		}
		Long oid = orgcodedao.queryOidByCode(code);
		if(oid!=null&&!oid.toString().equals("")&&!oid.toString().equals("0")){
			String tableName = "Fdapretailhisdata_"+oid.toString();
//			System.out.println(tableName);
			return retailhisdatadao.queryRetailHis(tableName, startupid);
		}
		return null;
	}

	/**
	 * 构造车载历史数据字符串
	 * @param carhis			车载历史数据信息
	 * @return					返回结果字符串:{aid1,t1,latitude,Latitude_dir,longitude,longitude_dir,time,isalarm},其中isalarm默认暂时为2，方便后面历史数据报警的处理
	 */
	private String buildStr(RetailHisData retailhis){
		StringBuffer sb = new StringBuffer();
		sb.append(retailhis.getAid1()+","+retailhis.getT1()+","+
				  retailhis.getLatitude()+","+retailhis.getLatitude_dir()+","+
				  retailhis.getLongitude()+","+retailhis.getLongitude_dir()+","+retailhis.getTime()+",2");
		return sb.toString();
	}
	
}
