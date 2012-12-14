package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.biz.order.PbmErpRefInOutBiz;
import org.fdap.dao.order.PbmErpRefInOutDao;
import org.fdap.entity.order.PbmErpRefInOutTrack;

public class PbmErpRefInOutBizImpl implements PbmErpRefInOutBiz {

	private PbmErpRefInOutDao pbmErpRefInOutDao = null;
	
	
	public PbmErpRefInOutDao getPbmErpRefInOutDao() {
		return pbmErpRefInOutDao;
	}
	public void setPbmErpRefInOutDao(PbmErpRefInOutDao pbmErpRefInOutDao) {
		this.pbmErpRefInOutDao = pbmErpRefInOutDao;
	}

	@Override
	public List<PbmErpRefInOutTrack> getByKid(Long kid, Long oid) {
		return pbmErpRefInOutDao.getByKid(kid, oid);
	}

}
