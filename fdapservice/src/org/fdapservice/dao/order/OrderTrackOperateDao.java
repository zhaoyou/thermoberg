package org.fdapservice.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import org.fdapservice.entity.PbmCallResult;
import org.fdapservice.entity.PbmCarInOutTrack;
import org.fdapservice.entity.PbmErpRefInOutTrack;
import org.fdapservice.entity.PbmMiBarCodeDetailTrack;
import org.fdapservice.entity.PbmMiBarCodeTrack;
import org.fdapservice.entity.PbmMiOrderTrack;
import org.fdapservice.entity.PbmRefInOutTrack;
import org.fdapservice.entity.PbmSubOrderDetailTrack;
import org.fdapservice.entity.PbmSubOrderMainTrack;
import org.fdapservice.util.DBUtil;

public class OrderTrackOperateDao {
	String orderTrackSql = "insert into pbmOrderTrack (orderId, orderno, receiverId," +
			"orderTime, isDelete, oid) values (?, ?, ?, ?, ?, ?)";
	
	String carInOutTrackSql = "insert into PbmCarInOutTrack (cdid, orderId," +
			"pid, inTime, outTime, demandTime, inPdaId, outPdaId, miCarId," +
			"subOrderMid, inOutStatus, isDelete, oid) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String refInOUtStrackSql = "insert into pbmRefInOutTrack (kdid, orderid, " +
			"pid,inTime, outTime,demandTime,inpdaid,outpdaid,erprefid,erpposid," +
			"subOrderMid,inoutStatus,isdelete,oid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String erpRefInoutSql = "insert into pbmErpRefInOutTrack (kdid, kid, orderid, inTime," +
			"outtime,goodfullid,totalnum,erprefid,erpposid,ispacketrec,inouttype,suborderMid," +
			"inoutStatus,isdelete,oid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String barcodeSql = "insert into pbmBarcodeTrack (bcid, barcode, orderId, packetNum, packetType,isdelete,oid)" +
			" values (?,?,?,?,?,?,?) ";
	
	String barcodeDetailSql = "insert into pbmBarcodeDetailTrack (bcdid, bcid,goodsfullid,kid,orderid,totalnum,oid,isdelete) " +
			"values (?,?,?,?,?,?,?,?)";
	
	String subOrderSql = "insert into PbmSubOrderMainTrack (subOrderMid,suborderparentMid,subordername,inoutType,inwholeOrder,loosepacketnum,orderid,orderstatus,planstatus,plantype,wholepacketnum," +
			"oid,isdelete) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String subOrderDetailSql = "insert into PbmSubOrderDetailTrack (suborderdetailid,subordermid,bcdid,totalnum,orderid,oid,isdelete) values " +
			"(?,?,?,?,?,?,?)";
	
	public PbmCallResult uploadOrder(PbmMiOrderTrack order,
			List<PbmCarInOutTrack> carList,
			List<PbmRefInOutTrack> refList,
			List<PbmErpRefInOutTrack> erpList,
			List<PbmMiBarCodeTrack> barcodeList,
			List<PbmMiBarCodeDetailTrack> barcodeDetailList,
			List<PbmSubOrderMainTrack> subOrderList,
			List<PbmSubOrderDetailTrack> subOrderDetailList) {
		PbmCallResult result = new PbmCallResult();
		result.setFlag(1);
		Connection conn = null ;
		PreparedStatement  st = null ;
		try {
			conn = DBUtil.getCon();
			conn.setAutoCommit(false);
			
			// for order.
			
			st = conn.prepareStatement(orderTrackSql);
			st.setLong(1, order.getOrderId());
			st.setLong(2, order.getOrderNo());
			st.setLong(3, order.getReceiverId());
			st.setTimestamp(4, new Timestamp(order.getOrderTime().getTime()));
			st.setShort(5, order.getIsDelete());
			st.setLong(6, order.getOid());
			st.executeUpdate();
			
			// for carlist
			for (PbmCarInOutTrack car: carList) {
				st = conn.prepareStatement(carInOutTrackSql);
				st.setLong(1, car.getCdId());
				st.setLong(2, car.getOrderId());
				st.setLong(3, car.getPid());
				st.setTimestamp(4, new Timestamp(car.getInTime().getTime()));
				st.setTimestamp(5, new Timestamp(car.getOutTime().getTime()));
				st.setTimestamp(6, new Timestamp(car.getDemandTime().getTime()));
				st.setLong(7, car.getInPdaId());
				st.setLong(8, car.getOutPdaId());
				st.setLong(9, car.getMiCarId());
				st.setLong(10, car.getSubOrderMid());
				st.setShort(11, car.getInOutStatus());
				st.setShort(12, car.getIsDelete());
				st.setLong(13, car.getOid());
				st.executeUpdate();
			}
			
			
			// for reflist.
			for (PbmRefInOutTrack ref: refList) {
				st = conn.prepareStatement(refInOUtStrackSql);
				st.setLong(1, ref.getKdId());
				st.setLong(2, ref.getOrderId());
				st.setLong(3, ref.getPid());
				st.setTimestamp(4, new Timestamp(ref.getInTime().getTime()));
				st.setTimestamp(5, new Timestamp(ref.getOutTime().getTime()));
				st.setTimestamp(6, new Timestamp(ref.getDemandTime().getTime()));
				st.setLong(7, ref.getInPdaId());
				st.setLong(8, ref.getOutPdaId());
				st.setLong(9, ref.getErpRefId());
				st.setLong(10, ref.getErpPosId());
				st.setLong(11, ref.getSubOrderMid());
				st.setShort(12, ref.getInOutStatus());
				st.setShort(13, ref.getIsDelete());
				st.setLong(14, ref.getOid());
				st.executeUpdate();
			}
			
			// for erplist
			for(PbmErpRefInOutTrack ref: erpList) {
				st = conn.prepareStatement(erpRefInoutSql);
				st.setLong(1, ref.getKdId());
				st.setLong(2, ref.getKid());
				st.setLong(3, ref.getOrderId());
				st.setTimestamp(4, new Timestamp(ref.getInTime().getTime()));
				st.setTimestamp(5, new Timestamp(ref.getOutTime().getTime()));
				st.setLong(6, ref.getGoodFullId());
				st.setInt(7, ref.getTotalNum());
				st.setLong(8, ref.getErpRefId());
				st.setLong(9, ref.getErpPosId());
				st.setShort(10, ref.getIsPacketRec());
				st.setShort(11, ref.getInoutType());
				st.setLong(12, ref.getSubOrderMid());
				st.setShort(13, ref.getInoutStatus());
				st.setShort(14, ref.getIsDelete());
				st.setLong(15, ref.getOid());
				st.executeUpdate();
			}
			
			// for barcodeTrack.
			for (PbmMiBarCodeTrack barcode: barcodeList) {
				st = conn.prepareStatement(barcodeSql);
				st.setLong(1, barcode.getBcId());
				st.setString(2, barcode.getBarCode());
				st.setLong(3, barcode.getOrderId());
				st.setInt(4, barcode.getPacketNum());
				st.setInt(5, barcode.getPacketType());
				st.setShort(6, barcode.getIsDelete());
				st.setLong(7, barcode.getOid());
				st.executeUpdate();
			}
			

			// for barcodedetailTrack.
			for (PbmMiBarCodeDetailTrack detail: barcodeDetailList) {
				st = conn.prepareStatement(barcodeDetailSql);
				st.setLong(1, detail.getBcdId());
				st.setLong(2, detail.getBcId());
				st.setLong(3, detail.getGoodsFullId());
				st.setLong(4, detail.getKid());
				st.setLong(5, detail.getOrderId());
				st.setInt(6, detail.getTotalNum());
				st.setLong(7, detail.getOid());
				st.setShort(8, detail.getIsDelete());
				st.executeUpdate();
			}
			
			// for subOrderList
			for (PbmSubOrderMainTrack sub: subOrderList) {
				st = conn.prepareStatement(subOrderSql);
				st.setLong(1, sub.getSubOrderMid());
				st.setLong(2, sub.getSubOrderParentMid());
				st.setString(3, sub.getSubOrderName());
				st.setShort(4, sub.getInoutType());
				st.setShort(5, sub.getInwholeOrder());
				st.setInt(6, sub.getLoosePacketNum());
				st.setLong(7, sub.getOrderId());
				st.setShort(8, sub.getOrderStatus());
				st.setShort(9, sub.getPlanStatus());
				st.setShort(10, sub.getPlanType());
				st.setInt(11, sub.getWholePacketNum());
				st.setLong(12, sub.getOid());
				st.setShort(13, sub.getIsDelete());
				st.executeUpdate();
			}
			
			// for suborderdetailList
			for (PbmSubOrderDetailTrack d: subOrderDetailList) {
				st = conn.prepareStatement(subOrderDetailSql);
				st.setLong(1, d.getSubOrderDetailId());
				st.setLong(2, d.getSubOrderMid());
				st.setLong(3, d.getBcdId());
				st.setInt(4, d.getTotalNum());
				st.setLong(5, d.getOrderId());
				st.setLong(6, d.getOid());
				st.setShort(7, d.getIsDelete());
				
				st.executeUpdate();
			}
	
			
			
			conn.commit();
		} catch (Exception e) {
		   System.out.println("上传PbmOrder 失败!" + e.getMessage());
		   try {
			   conn.rollback();
		   } catch (Exception ex) {
			   e.printStackTrace();
			   System.out.println("回滚事务失败!");
		   }
		   result.setMessage(e.getMessage());
		   result.setFlag(2);
		} finally {
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return result;
	}
}
