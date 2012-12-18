package org.fdapservice.dao.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.fdapservice.entity.PbmCallResult;
import org.fdapservice.entity.PbmMiGoodsBaseInfo;
import org.fdapservice.entity.PbmMiGoodsFullInfo;
import org.fdapservice.util.DBUtil;

public class GoodsOperatorDao {
	
	SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	String basicSql = " insert into pbmGoodsBasicInfo (goodid, goodsname goodstype, goodsunit, isdelete, oid) " +
			" values(?, ?, ?, ?, ?, ?)";
	
	String fullSql = " insert into pbmGoodsFullInfo (goodfullid, goodid, invalidate, lotno, isdelete, oid) " +
			" values(?, ?, ?, ?, ?, ?)";
	
	public PbmCallResult uploadBasicGoods(List<PbmMiGoodsBaseInfo> basicList) {
		PbmCallResult result = new PbmCallResult();
		result.setFlag(1);
		Connection conn = null ;
		PreparedStatement  st = null ;
		try {
			conn = DBUtil.getCon();
			conn.setAutoCommit(false);
			for (PbmMiGoodsBaseInfo basic: basicList) {
				st = conn.prepareStatement(basicSql);
				st.setLong(1, basic.getGoodId());
				st.setString(2, basic.getGoodsName());
				st.setString(3, basic.getGoodsType());
				st.setString(4, basic.getGoodsUnit());
				st.setShort(5, basic.getIsDelete());
				st.setLong(6, basic.getOid());
				st.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
		   System.out.println("上传PbmMIBasicGoods 失败!" + e.getMessage());
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
	
	public PbmCallResult uploadFullGoods(List<PbmMiGoodsFullInfo> fullList) {
		PbmCallResult result = new PbmCallResult();
		result.setFlag(1);
		Connection conn = null ;
		PreparedStatement  st = null ;
		try {
			conn = DBUtil.getCon();
			conn.setAutoCommit(false);
			for (PbmMiGoodsFullInfo full: fullList) {
				st = conn.prepareStatement(fullSql);
				st.setLong(1, full.getGoodFullId());
				st.setLong(2, full.getGoodId());
				st.setTimestamp(3,  new Timestamp(full.getInvaliDate().getTime()));
				st.setString(4, full.getLotno());
				st.setShort(5, full.getIsDelete());
				st.setLong(6, full.getOid());
				st.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
		   System.out.println("上传PbmMiGoodsFullInfo 失败!" + e.getMessage());
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
