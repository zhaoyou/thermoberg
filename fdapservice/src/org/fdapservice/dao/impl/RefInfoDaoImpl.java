package org.fdapservice.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.fdapservice.dao.RefInfoDao;
import org.fdapservice.entity.RefInfo;
import org.fdapservice.util.DBUtil;

public class RefInfoDaoImpl implements RefInfoDao {

	public RefInfo getRefById(Long refId) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<RefInfo> list = new ArrayList<RefInfo>() ;
		RefInfo info = null ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement() ;
			
			String sql = "select fr.refId,fr.name,fr.floorType,fr.floorNo,fr.remark from fdapref fr where fr.refId = "+refId;
			rs = st.executeQuery(sql);		//现在都是直接查询声光报警，之前是查询冷库报警ALARM
			if(rs!=null){
				while(rs.next()){
					info = new RefInfo();
					info.setRefId(rs.getLong("refid"));
					info.setName(rs.getString("name"));
					info.setFloorType(rs.getInt("floorType"));
					info.setFloorNo(rs.getInt("floorNo"));
					info.setRemark(rs.getString("remark"));
					list.add(info);
				}
			}
		} catch (Exception e) {
			System.out.println("获取车载信息发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list.get(0);
	}
}
