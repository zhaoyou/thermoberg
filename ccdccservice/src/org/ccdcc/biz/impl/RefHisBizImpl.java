package org.ccdcc.biz.impl;

import java.util.List;

import org.ccdcc.biz.RefHisBiz;
import org.ccdcc.dao.RefHisDao;
import org.ccdcc.entity.RefHisData;
import org.ccdcc.util.BuildTable;

public class RefHisBizImpl implements RefHisBiz {

	private RefHisDao refHisDao = null;

	public RefHisDao getRefHisDao() {
		return refHisDao;
	}
	public void setRefHisDao(RefHisDao refHisDao) {
		this.refHisDao = refHisDao;
	}



	@Override
	public int upload(String projectId, String devId, String devType,
			List<RefHisData> list) {
		try {
			return refHisDao.insertData(devType, BuildTable.buildRefHisTable(projectId, devId, devType), list);
		} catch(Exception e) {
			System.err.print("update refhis data error: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
}
