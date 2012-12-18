package org.fdapservice.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.fdapservice.entity.PbmCallResult;
import org.fdapservice.entity.PbmMiCar;
import org.fdapservice.entity.PbmMiReceiver;
import org.fdapservice.entity.PbmMiRef;
import org.fdapservice.util.DBUtil;

public class RefAndCarOperatorDao {
	String carSql = "insert into PbmMiCar (micarid, micarName, isdelete, oid) " +
			"values (?, ? ,?, ? )";
	
	String refSql = "insert into PbmMiRef (mirefId, mirefName, isdelete, oid) " +
			"values (? , ?, ?, ?)";
	
	String receiverSql = "insert into pbmMiReceiver (rid, shortname, fullname, isdelete, oid ) values" +
			" (?, ?, ?, ?, ?)";
	
	public PbmCallResult uploadMiCar(List<PbmMiCar> carList) {
		PbmCallResult result = new PbmCallResult();
		result.setFlag(1);
		Connection conn = null ;
		PreparedStatement  st = null ;
		try {
			conn = DBUtil.getCon();
			conn.setAutoCommit(false);
			for (PbmMiCar car: carList) {
				st = conn.prepareStatement(carSql);
				st.setLong(1, car.getMicarId());
				st.setString(2, car.getMicarName());
				st.setShort(3, car.getIsdelete());
				st.setLong(4, car.getOid());
				st.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
		   System.out.println("上传PbmMICar 失败!" + e.getMessage());
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
	
	public PbmCallResult uploadMiRef(List<PbmMiRef> refList) {
		PbmCallResult result = new PbmCallResult();
		result.setFlag(1);
		Connection conn = null ;
		PreparedStatement  st = null ;
		try {
			conn = DBUtil.getCon();
			conn.setAutoCommit(false);
			for (PbmMiRef ref: refList) {
				st = conn.prepareStatement(refSql);
				st.setLong(1, ref.getMirefId());
				st.setString(2, ref.getMirefName());
				st.setShort(3, ref.getIsDelete());
				st.setLong(4, ref.getOid());
				st.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
		   System.out.println("上传PbmMIRef 失败!" + e.getMessage());
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
	
	public PbmCallResult uploadMiReceiver(List<PbmMiReceiver> receiverList) {
		PbmCallResult result = new PbmCallResult();
		result.setFlag(1);
		Connection conn = null ;
		PreparedStatement  st = null ;
		try {
			conn = DBUtil.getCon();
			conn.setAutoCommit(false);
			for (PbmMiReceiver re: receiverList) {
				st = conn.prepareStatement(refSql);
				st.setLong(1, re.getRid());
				st.setString(2, re.getShortName());
				st.setString(3, re.getFullName());
				st.setShort(4, re.getIsDelete());
				st.setLong(5, re.getOid());
				st.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
		   System.out.println("上传PbmMiReceiver 失败!" + e.getMessage());
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
