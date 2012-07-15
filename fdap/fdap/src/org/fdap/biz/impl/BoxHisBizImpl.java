package org.fdap.biz.impl;

import java.util.List;

import org.fdap.biz.BoxHisBiz;
import org.fdap.biz.CarHisBiz;
import org.fdap.dao.BoxHisDao;
import org.fdap.dao.RefDao;
import org.fdap.entity.FdapBoxHisData;
import org.fdap.entity.FdapCarHisData;
import org.fdap.entity.Fdapref;
import org.fdap.util.GetMaxMinAvg;

public class BoxHisBizImpl implements BoxHisBiz {

	private RefDao refdao;
	private GetMaxMinAvg getmaxminavg;
	private BoxHisDao boxHisDao;
	
	public BoxHisDao getBoxHisDao() {
		return boxHisDao;
	}

	public void setBoxHisDao(BoxHisDao boxHisDao) {
		this.boxHisDao = boxHisDao;
	}

	public GetMaxMinAvg getGetmaxminavg() {
		return getmaxminavg;
	}

	public void setGetmaxminavg(GetMaxMinAvg getmaxminavg) {
		this.getmaxminavg = getmaxminavg;
	}
	
	public RefDao getRefdao() {
		return refdao;
	}

	public void setRefdao(RefDao refdao) {
		this.refdao = refdao;
	}
	
	@Override
	public List<Fdapref> getBoxRefByOid(String oid) {
		return refdao.queryRefByOidAndType(oid, CarHisBiz.BOX_TYPE);
	}

	@Override
	public List<FdapBoxHisData> getBoxHis(String oid, String sid) {
		if(oid==null||oid.equals("")||sid==null||sid.equals("")){
			return null;
		}
		String tableName = "fdapboxhisdata_"+oid;
		
		List<FdapBoxHisData> carhisList = boxHisDao.queryBoxHis(tableName, Integer.parseInt(sid));
		for(FdapBoxHisData carhis : carhisList){
			if(carhis.getT1()==-300){
				carhis.setT1(null);
			}
		}
		
		return carhisList;
	}

	@Override
	public Integer getBoxHisCount(String tableName, Integer parentId) {
		return boxHisDao.queryBoxHisCount(tableName, parentId);
	}

	@Override
	public List<Object> getBoxHisMMA(String tableName, Integer parentId) {
		List<FdapBoxHisData> list= boxHisDao.queryBoxHis(tableName, parentId);
//		System.out.println("记录数"+list.size());
		if(list!=null&&list.size()!=0)
			return getmaxminavg.getBoxMaxMinAvg(list);
		return null;
	}

	@Override
	public List<FdapBoxHisData> getBoxHisbyStartup(String tableName,
			Integer parentId, Integer startRow, Integer pagesize) {
		return boxHisDao.queryBoxHisbyStartupPage(tableName, parentId, startRow,pagesize);
	}

	@Override
	public Fdapref getBoxRefById(String refId) {
		if(refId!=null){
			return refdao.queryByRefId(Long.parseLong(refId));
		}
		else{
			return null;
		}
	}

}
