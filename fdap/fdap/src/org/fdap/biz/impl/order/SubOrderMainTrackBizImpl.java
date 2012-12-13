package org.fdap.biz.impl.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fdap.biz.order.PbmGoodsFullInfoBiz;
import org.fdap.biz.order.SubOrderMainTrackBiz;
import org.fdap.dao.order.BarCodeTrackDao;
import org.fdap.dao.order.BarCodeTrackDetailDao;
import org.fdap.dao.order.PbmGoodsFullInfoDao;
import org.fdap.dao.order.SubOrderMainDetailDao;
import org.fdap.dao.order.SubOrderMainTrackDao;
import org.fdap.entity.order.PbmMedicineSummary;
import org.fdap.entity.order.PbmMiBarCodeDetailTrack;
import org.fdap.entity.order.PbmMiBarCodeTrack;
import org.fdap.entity.order.PbmMiGoodsFullInfo;
import org.fdap.entity.order.PbmSubOrderDetailTrack;
import org.fdap.entity.order.PbmSubOrderMainTrack;
import org.fdap.util.GsonUtil;

import com.google.gson.Gson;


public class SubOrderMainTrackBizImpl implements SubOrderMainTrackBiz {

	private SubOrderMainTrackDao subOrderMainDao = null;
	private SubOrderMainDetailDao subOrderMailDetailDao = null;
	private BarCodeTrackDao barCodeTrackDao = null;
	private BarCodeTrackDetailDao barCodeTrackDetailDao = null;
	private PbmGoodsFullInfoBiz fullInfoBiz = null;
	private GsonUtil out = null;
	
	public void setOut(GsonUtil out) {
		this.out = out;
	}
	public void setFullInfoBiz(PbmGoodsFullInfoBiz fullInfoBiz) {
		this.fullInfoBiz = fullInfoBiz;
	}
	public void setSubOrderMailDetailDao(SubOrderMainDetailDao subOrderMailDetailDao) {
  	this.subOrderMailDetailDao = subOrderMailDetailDao;
  }
	public void setBarCodeTrackDao(BarCodeTrackDao barCodeTrackDao) {
  	this.barCodeTrackDao = barCodeTrackDao;
  }
	public void setBarCodeTrackDetailDao(BarCodeTrackDetailDao barCodeTrackDetailDao) {
  	this.barCodeTrackDetailDao = barCodeTrackDetailDao;
  }
	public void setSubOrderMainDao(SubOrderMainTrackDao subOrderMainDao) {
  	this.subOrderMainDao = subOrderMainDao;
  }

	@Override
	public List<PbmSubOrderMainTrack> getByOrderId(Long orderId) {
		return subOrderMainDao.getByOrder(orderId);
	}

	@Override
  public List<PbmMedicineSummary> getInfo(Long orderId, String goodsName, String goodTypeName,
  		String prodArea, String lotno) {
	  List<PbmSubOrderMainTrack> mainlist = subOrderMainDao.getByOrder(orderId);
	  List<PbmSubOrderDetailTrack> mainDetailList = subOrderMailDetailDao.getByOrder(orderId);
	  
	  List<PbmMiBarCodeTrack> barCodeList = barCodeTrackDao.getBy(orderId);
	  List<PbmMiBarCodeDetailTrack> barCodeDetailList = barCodeTrackDetailDao.getByOrder(orderId);
	  Map<Long, PbmMedicineSummary> ht = new HashMap<Long, PbmMedicineSummary>();
	  PbmMedicineSummary mms = new PbmMedicineSummary();
	  System.out.println("mainList: " + mainlist.size());
	  System.out.println("mainDetailList: " + mainDetailList.size());
	  System.out.println("barCodeList: " + barCodeList.size());
	  System.out.println("baCodeDetailList: " + barCodeDetailList.size());
	  // for each 每一个子订单主记录
	  for (PbmSubOrderMainTrack msomt: mainlist ) {
	  	if (msomt.getInoutType() == 0 && msomt.getInwholeOrder() == 1) { // 完整订单.
	  		// 遍历所有的条码主跟踪记录
	  		for (PbmMiBarCodeTrack mbt: barCodeList) {
	  			// 遍历条码详细记录信息
	  			
	  			for (PbmMiBarCodeDetailTrack mbdt: barCodeDetailList) {
	  				if (!mbdt.getBcId().toString().equals(mbt.getBcId().toString())) { //不属于当前条码
	  					continue;
	  				}
	  				
	  				PbmMiGoodsFullInfo fullInfo = fullInfoBiz.getById(mbdt.getGoodsFullId());		
	  				
	  				// 药品基本信息过滤
	  				if (!isProductValid(fullInfo, goodsName, goodTypeName, prodArea, lotno)) {
	  					if (mbt.getPacketType() == 2) {  //整件
	  						break;  //查找下一条码
	  					} else { // 散件
	  						continue; // 继续查找当前条码其他信息
	  					}
	  				}
	  				
	  				
	  				
	  				if (ht.containsKey(mbdt.getKid()) == false) {
	  					mms = new PbmMedicineSummary();
	  				} else {
	  					mms = ht.get(mbdt.getKid());
	  				}
	  				
	  				if (mbt.getPacketType() == 2) { //整件
	  					mms.setWholePacketNum(mms.getWholePacketNum() + mbt.getPacketNum());
	  					mms.setWholePacketUnitNum(mbdt.getTotalNum());
	  				} else { // 散件
	  					mms.setLoosePacketNum(mms.getLoosePacketNum() + 1);
	  					mms.setLoosePacketTotalNum(mms.getLoosePacketTotalNum() + mbdt.getTotalNum());
	  				}
	  				mms.setTotalNum(mms.getWholePacketNum() * mms.getWholePacketUnitNum() + mms.getLoosePacketTotalNum());
	  				mms.setKid(mbdt.getKid());
	  				mms.setMsomt(msomt);
	  				mms.setMegfi(fullInfo);
	  				
	  				if (ht.containsKey(mbdt.getKid()) == false) {
	  					ht.put(mbdt.getKid(), mms);
	  				}
	  				
	  				if (mbt.getPacketType() == 2) { //整件
	  					break; // 查找下一条码
	  				} else { // 散件
	  					continue;
	  				}
	  			}
	  		}
	  	} else { // 退换货，订单
	  		// 所有子订单详细记录信息
	  		for (PbmSubOrderDetailTrack msodt: mainDetailList) {
	  			if (msodt.getSubOrderMid() != msomt.getSubOrderMid()) { //不是子订单主记录对应的子订单详细记录
	  				continue;
	  			}
	  			
	  			for (PbmMiBarCodeDetailTrack mbdt: barCodeDetailList) {
	  				if (msodt.getBcdId() != mbdt.getBcdId()) { //不是子订单详细记录对应的详细条码信息
	  					continue;
	  				}
	  				
	  				PbmMiGoodsFullInfo megfi = fullInfoBiz.getById(mbdt.getGoodsFullId());
	  				
	  				if (isProductValid(megfi, goodsName, goodTypeName, prodArea, lotno) == false) {
	  					break;
	  				}
	  				
	  				// 遍历所有条码主跟踪记录
	  				for (PbmMiBarCodeTrack mbt: barCodeList) {
	  					if (mbdt.getBcId() != mbt.getBcId()) { // 不属于当前条码
	  						continue;
	  					}
	  					
	  					if (ht.containsKey(mbdt.getKid()) == false) {
	  						mms = new PbmMedicineSummary();
	  					} else {
	  						mms = ht.get(mbdt.getKid());
	  					}
	  					
	  					if (mbt.getPacketType() == 2) { // 整件
	  						if (msodt.getTotalNum() / mbdt.getTotalNum() > 0){ //存在整件
	  							mms.setWholePacketNum(mms.getWholePacketNum() + msodt.getTotalNum() / mbdt.getTotalNum());
	  							mms.setWholePacketUnitNum(mbdt.getTotalNum());
	  						}
	  						
	  						if (msodt.getTotalNum() % mbdt.getTotalNum() > 0) { // 存在散件
	  							mms.setLoosePacketNum(mms.getLoosePacketNum() + 1); // 散件件数增加
	  							mms.setLoosePacketTotalNum(mms.getLoosePacketTotalNum() + msodt.getTotalNum() % mbdt.getTotalNum()); // 整件散件药品中数量
	  						}
	  					} else { // 散件
	  						mms.setLoosePacketNum(mms.getLoosePacketNum() + 1);
	  						mms.setLoosePacketTotalNum(mms.getLoosePacketTotalNum() + msodt.getTotalNum());
	  					}
	  					
	  					mms.setTotalNum(mms.getWholePacketNum() * mms.getWholePacketUnitNum() + mms.getLoosePacketTotalNum() );
	  					mms.setKid(mbdt.getKid());
	  					mms.setMsomt(msomt);
	  					mms.setMegfi(megfi);
	  					
	  					if (ht.containsKey(mbdt.getKid()) == false) {
	  						ht.put(mbdt.getKid(), mms);
	  					}
	  					break;
	  				}
	  				break;
	  			}
	  		}
	  	}
	  }
	  
	  List<PbmMedicineSummary> resultList = new ArrayList<PbmMedicineSummary>();
	  for(PbmMedicineSummary s: ht.values()) {
		out.print(s);
		resultList.add(s);
	  }
	  System.out.println("list: " + resultList.size());
	  return resultList;
  }
	
	private boolean isProductValid(PbmMiGoodsFullInfo info, String goodsName, String typeName,
			String prodArea, String lotno) {
		if (!"-1".equals(goodsName) && !info.getBaseInfo().getGoodsName().equals(goodsName)) {
			return false;
		}
		
		if (!"-1".equals(typeName) && !info.getBaseInfo().getGoodsType().equals(typeName)) {
			return false;
		}
		
		if (!"-1".equals(prodArea)&& !info.getBaseInfo().getProdarea().equals(prodArea)) {
			return false;
		}
		
		if (!"".equals(lotno) && !info.getLotno().contains(lotno)) {
			return false;
		}
		return true;
	}

}
