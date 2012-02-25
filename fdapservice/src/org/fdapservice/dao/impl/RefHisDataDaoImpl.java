package org.fdapservice.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.fdapservice.dao.RefHisDataDao;
import org.fdapservice.entity.RefHisData;
import org.fdapservice.util.DBUtil;

public class RefHisDataDaoImpl implements RefHisDataDao {

	@Override
	public List<RefHisData> queryRefHis(String tableName, String startTime,
			String endTime, Long refId) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<RefHisData> list = new ArrayList<RefHisData>() ;
		RefHisData refHis = null ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement();
			String sql = "select fa.reid,fhis.data,fhis.time,fhis.isalarm from "+tableName+" fhis inner join fdapaiinfo fa on fhis.aiguid=fa.aiguid " +
					"where time>='"+startTime+"' and time<='"+endTime+"' and fa.refid="+refId;
			rs = st.executeQuery(sql);	
			if(rs!=null){
				while(rs.next()){
					refHis = new RefHisData();
					refHis.setId(rs.getInt("reid"));
					refHis.setValue(rs.getDouble("data"));
					refHis.setTime(rs.getString("time"));
					refHis.setStatus(rs.getInt("isalarm"));
					list.add(refHis);
				}
			}
		} catch (Exception e) {
			System.out.println("获取冷库历史数据发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}
}
