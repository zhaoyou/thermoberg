package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.biz.order.PbmGoodsBaseInfoBiz;
import org.fdap.dao.order.PbmGoodsBaseInfoDao;
import org.fdap.entity.order.PbmMiGoodsBaseInfo;

public class PbmGoodsBaseInfoBizImpl implements PbmGoodsBaseInfoBiz {
	
	private PbmGoodsBaseInfoDao goodsBaseInfoDao; 
	

	public void setGoodsBaseInfoDao(PbmGoodsBaseInfoDao goodsBaseInfoDao) {
  	this.goodsBaseInfoDao = goodsBaseInfoDao;
  }

	@Override
  public List<PbmMiGoodsBaseInfo> getList(Long oid) {
	  return goodsBaseInfoDao.getGoodsBaseInfoList(oid);
  }

}
