package org.fdap.biz.impl.order;

import java.util.List;

import org.fdap.biz.order.PbmMiReceiverBiz;
import org.fdap.dao.order.PbmMiReceiverDao;
import org.fdap.entity.order.PbmMiReceiver;

public class PbmMiReceiverBizImpl implements PbmMiReceiverBiz {
	
	private PbmMiReceiverDao receiverDao;
	
	

	public void setReceiverDao(PbmMiReceiverDao receiverDao) {
  	this.receiverDao = receiverDao;
  }


	@Override
	public List<PbmMiReceiver> getList(Long oid) {
		return receiverDao.getByOid(oid);
	}

}
