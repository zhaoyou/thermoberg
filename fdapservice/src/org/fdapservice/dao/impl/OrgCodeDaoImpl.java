package org.fdapservice.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.fdapservice.dao.OrgCodeDao;
import org.fdapservice.util.DBUtil;

public class OrgCodeDaoImpl implements OrgCodeDao {

	@Override
	public Long queryOidByCode(String code) {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<Long> list = new ArrayList<Long>() ;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement() ;
			String sql = "select oid from fdapauthcode where code='"+code+"'";
			rs = st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					list.add(rs.getLong("oid"));
				}
			}
		} catch (Exception e) {
			System.out.println("根据code获取该企业ID发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list.get(0);
	}

}
