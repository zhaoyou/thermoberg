package org.fdapservice.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.fdapservice.dao.CallPhoneDao;
import org.fdapservice.entity.CallPhone;
import org.fdapservice.util.DBUtil;

public class CallPhoneDaoImpl implements CallPhoneDao {

	@Override
	public List<CallPhone> getPhoneCall() {
		Connection conn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		List<CallPhone> list = new ArrayList<CallPhone>() ;
		CallPhone callphone = null;
		try {
			conn = DBUtil.getCon() ;
			st = conn.createStatement() ;
			String sql = "select * from Fdapphone";
			rs = st.executeQuery(sql);		//现在都是直接查询声光报警，之前是查询冷库报警ALARM
			if(rs!=null){
				while(rs.next()){
					callphone = new CallPhone();
					callphone.setPhoneid(rs.getLong("phoneid"));
					callphone.setCallcode(rs.getString("callcode"));
					callphone.setPhonenumber(rs.getString("phonenumber"));
					callphone.setMessagenumber(rs.getString("messagenumber"));
					callphone.setPhoneActive(rs.getInt("phoneActive"));
					callphone.setMessageActive(rs.getInt("messageActive"));
					list.add(callphone);
				}
			}
		} catch (Exception e) {
			System.out.println("获取电话信息发生错误:" +e.getMessage());
			return null ;
		}finally{
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(st);
			DBUtil.closeCon(conn);
		}
		return list;
	}

}
