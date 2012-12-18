package org.fdapservice.dao.order;

public class OrderTrackOperateDao {
	String orderTrackSql = "insert into pbmOrderTrack (orderId, orderName, receiverId," +
			"orderTime, isDelete, oid) values (?, ?, ?, ?, ?, ?)";
	
	String carInOutTrackSql = "insert into PbmCarInOutTrack (cdid, orderId," +
			"pid, inTime, outTime, demandTime, inPdaId, outPdaId, miCarId," +
			"subOrderMid, inOutStatus, isDelete, oid) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String refInOUtStrackSql = "insert into pbmRefInOutTrack (kdid, kid, orderid, " +
			"pid,inTime, outTime,demandTime,inpdaid,outpdaid,erprefid,erpposid," +
			"subOrderMid,inoutStatus,isdelete,oid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	String erpRefInoutSql = "insert into pbmErpRefInOutTrack (kdid, kid, orderid, inTime," +
			"outtime,goodfullid,totalnum,erprefid,erpposid,ispacketrec,inouttype,suborderMid," +
			"inoutStatus,isdelete,oid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
}
