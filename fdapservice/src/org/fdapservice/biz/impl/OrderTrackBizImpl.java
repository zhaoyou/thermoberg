package org.fdapservice.biz.impl;

import java.util.List;

import org.fdapservice.dao.order.GoodsOperatorDao;
import org.fdapservice.dao.order.OrderTrackOperateDao;
import org.fdapservice.dao.order.RefAndCarOperatorDao;
import org.fdapservice.entity.PbmCallResult;
import org.fdapservice.entity.PbmCarInOutTrack;
import org.fdapservice.entity.PbmErpRefInOutTrack;
import org.fdapservice.entity.PbmMiBarCodeDetailTrack;
import org.fdapservice.entity.PbmMiBarCodeTrack;
import org.fdapservice.entity.PbmMiCar;
import org.fdapservice.entity.PbmMiGoodsBaseInfo;
import org.fdapservice.entity.PbmMiGoodsFullInfo;
import org.fdapservice.entity.PbmMiOrderTrack;
import org.fdapservice.entity.PbmMiReceiver;
import org.fdapservice.entity.PbmMiRef;
import org.fdapservice.entity.PbmRefInOutTrack;
import org.fdapservice.entity.PbmSubOrderDetailTrack;
import org.fdapservice.entity.PbmSubOrderMainTrack;

public class OrderTrackBizImpl {
	private GoodsOperatorDao goodsDao = new GoodsOperatorDao();
	private OrderTrackOperateDao ordertrackDao = new OrderTrackOperateDao();
	private RefAndCarOperatorDao refandCarDao = new RefAndCarOperatorDao();
	
	public PbmCallResult uploadMiCar(List<PbmMiCar> carList) {
		return refandCarDao.uploadMiCar(carList);
	}
	
	public PbmCallResult uploadMiRef(List<PbmMiRef> reflist) {
		return refandCarDao.uploadMiRef(reflist);
	}
	
	public PbmCallResult uploadReceiver(List<PbmMiReceiver> receiverList) {
		return refandCarDao.uploadMiReceiver(receiverList);
	}
	
	public PbmCallResult uploadBasicGoods(List<PbmMiGoodsBaseInfo> basicList) {
		return goodsDao.uploadBasicGoods(basicList);
	}
	
	public PbmCallResult uploadFullGoods(List<PbmMiGoodsFullInfo> fulllist) {
		return goodsDao.uploadFullGoods(fulllist);
	}
	
	public PbmCallResult uploadOrder(PbmMiOrderTrack order,
			List<PbmCarInOutTrack> carList,
			List<PbmRefInOutTrack> refList,
			List<PbmErpRefInOutTrack> erpList,
			List<PbmMiBarCodeTrack> barcodeList,
			List<PbmMiBarCodeDetailTrack> barcodeDetailList,
			List<PbmSubOrderMainTrack> subOrderList,
			List<PbmSubOrderDetailTrack> subOrderDeList) {
		return ordertrackDao.uploadOrder(order, carList, refList, erpList, barcodeList, barcodeDetailList, subOrderList, subOrderDeList);
	}
	
	
}
