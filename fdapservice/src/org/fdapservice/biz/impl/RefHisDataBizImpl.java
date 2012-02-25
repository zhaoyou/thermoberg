package org.fdapservice.biz.impl;

import java.util.List;

import org.fdapservice.biz.RefHisDataBiz;
import org.fdapservice.dao.OrgCodeDao;
import org.fdapservice.dao.RefHisDataDao;
import org.fdapservice.dao.impl.OrgCodeDaoImpl;
import org.fdapservice.dao.impl.RefHisDataDaoImpl;
import org.fdapservice.entity.RefHisData;

public class RefHisDataBizImpl implements RefHisDataBiz {
	private RefHisDataDao refhisdatadao = new RefHisDataDaoImpl();
	
	private OrgCodeDao orgcodedao = new OrgCodeDaoImpl();
	@Override
	public List<RefHisData> getRefHis(String code, String startTime,
			String endTime, Long refId) {
		if(code==null||code.equals("")||startTime==null||startTime.equals("")||endTime==null||endTime.equals("")
				||refId==null||refId.toString().equals("0")){
			System.out.println("传递了非法参数");
			return null;
		}
		Long oid = orgcodedao.queryOidByCode(code);
		if(oid!=null&&!oid.toString().equals("0")){
			String tableName = "Fdaprefhisdata_"+oid.toString();
//			System.out.println(tableName);
			return refhisdatadao.queryRefHis(tableName, startTime, endTime, refId);
		}
		else{
			return null;
		}
	}
}
