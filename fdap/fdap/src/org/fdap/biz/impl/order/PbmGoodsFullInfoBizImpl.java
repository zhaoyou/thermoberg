package org.fdap.biz.impl.order;

import org.fdap.biz.order.PbmGoodsFullInfoBiz;
import org.fdap.dao.order.PbmGoodsBaseInfoDao;
import org.fdap.dao.order.PbmGoodsFullInfoDao;
import org.fdap.entity.order.PbmMiGoodsFullInfo;

public class PbmGoodsFullInfoBizImpl implements PbmGoodsFullInfoBiz {

	private PbmGoodsBaseInfoDao baseDao;
	private PbmGoodsFullInfoDao fullDao;
		
	public PbmGoodsBaseInfoDao getBaseDao() {
  	return baseDao;
  }

	public void setBaseDao(PbmGoodsBaseInfoDao baseDao) {
  	this.baseDao = baseDao;
  }

	public PbmGoodsFullInfoDao getFullDao() {
  	return fullDao;
  }

	public void setFullDao(PbmGoodsFullInfoDao fullDao) {
  	this.fullDao = fullDao;
  }



	@Override
	public PbmMiGoodsFullInfo getById(Long id) {
		PbmMiGoodsFullInfo info  = fullDao.get(id);
		info.setBaseInfo(baseDao.get(info.getGoodId()));
		return info;
	}

}
