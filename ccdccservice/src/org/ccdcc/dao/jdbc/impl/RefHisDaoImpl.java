package org.ccdcc.dao.jdbc.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import org.ccdcc.dao.RefHisDao;
import org.ccdcc.entity.RefHisData;
import org.ccdcc.util.ConnectionProxyFactory;
import org.ccdcc.util.DevType;

public class RefHisDaoImpl implements RefHisDao {

private ConnectionProxyFactory connectionfaotory = null;

private static Logger logger = Logger.getRootLogger() ;
	
	private final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public ConnectionProxyFactory getConnectionfaotory() {
		return connectionfaotory;
	}

	public void setConnectionfaotory(ConnectionProxyFactory connectionfaotory) {
		this.connectionfaotory = connectionfaotory;
	}
	
	private final String standardSQL = "" +
			"insert into @table (hDate, UpdateTime, ai1, ai2, ai3, ai4, ai5, ai6, ai7, ai8 , ai9," +
			"ai10, ai11, ai12, Ref1_refAlarmState, Ref2_refAlarmState, Ref3_refAlarmState, Ref4_refAlarmState)" +
			" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private final String exSQL = "" +
	"insert into @table (hDate, UpdateTime, ai1, ai2, ai3, ai4, ai5, ai6, ai7, ai8 , ai9," +
	"ai10, ai11, ai12, ai13, ai14, ai15, ai16, ai17, ai18 , ai19," +
	"ai20, ai21, ai22,ai23, ai24,  ai25, ai26, ai27, ai28 , ai29," +
	"ai30, ai31, ai32, Ref1_refAlarmState, Ref2_refAlarmState, Ref3_refAlarmState, Ref4_refAlarmState," +
	"Ref5_refAlarmState, Ref6_refAlarmState, Ref7_refAlarmState, Ref8_refAlarmState," +
	"Ref9_refAlarmState, Ref10_refAlarmState, Ref11_refAlarmState, Ref12_refAlarmState," +
	"Ref13_refAlarmState, Ref14_refAlarmState, Ref15_refAlarmState, Ref16_refAlarmState)" +
	" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
	"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	
	@Override
	public int insertData(String type, String tableName, List<RefHisData> list) {
		if (type.equalsIgnoreCase(DevType.dev_standard.toString())) {
			return forStandardData(tableName, list);
		} else {
			return forExData(tableName, list);
		}
	}
	
	private int forExData(String tableName, List<RefHisData> list) {
		Connection conn=null;
		try {
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = exSQL.replace("@table", tableName);
			Object[][] params = new Object[list.size()][50];
			for(int i = 0; i < list.size(); i++) {
				params[i][0] = sf.format(list.get(i).getHDate());
				params[i][1] = sf.format(list.get(i).getUpdateTime());
				params[i][2] = list.get(i).getAi1() != null ? list.get(i).getAi1() : -300;
				params[i][3] = list.get(i).getAi2() != null ? list.get(i).getAi2() : -300;
				params[i][4] = list.get(i).getAi3() != null ? list.get(i).getAi3() : -300;
				params[i][5] = list.get(i).getAi4() != null ? list.get(i).getAi4() : -300;
				params[i][6] = list.get(i).getAi5() != null ? list.get(i).getAi5() : -300;
				params[i][7] = list.get(i).getAi6() != null ? list.get(i).getAi6() : -300;
				params[i][8] = list.get(i).getAi7() != null ? list.get(i).getAi7() : -300;
				params[i][9] = list.get(i).getAi8() != null ? list.get(i).getAi8() : -300;
				params[i][10] = list.get(i).getAi9() != null ? list.get(i).getAi9() : -300;
				params[i][11] = list.get(i).getAi10() != null ? list.get(i).getAi10() : -300;
				params[i][12] = list.get(i).getAi11() != null ? list.get(i).getAi11() : -300;
				params[i][13] = list.get(i).getAi12() != null ? list.get(i).getAi12() : -300;
				params[i][14] = list.get(i).getAi13() != null ? list.get(i).getAi13() : -300;
				params[i][15] = list.get(i).getAi14() != null ? list.get(i).getAi14() : -300;
				params[i][16] = list.get(i).getAi15() != null ? list.get(i).getAi15() : -300;
				params[i][17] = list.get(i).getAi16() != null ? list.get(i).getAi16() : -300;
				params[i][18] = list.get(i).getAi17() != null ? list.get(i).getAi17() : -300;
				params[i][19] = list.get(i).getAi18() != null ? list.get(i).getAi18() : -300;
				params[i][20] = list.get(i).getAi19() != null ? list.get(i).getAi19() : -300;
				params[i][21] = list.get(i).getAi20() != null ? list.get(i).getAi20() : -300;
				params[i][22] = list.get(i).getAi21() != null ? list.get(i).getAi21() : -300;
				params[i][23] = list.get(i).getAi22() != null ? list.get(i).getAi22() : -300;
				params[i][24] = list.get(i).getAi23() != null ? list.get(i).getAi23() : -300;
				params[i][25] = list.get(i).getAi24() != null ? list.get(i).getAi24() : -300;
				params[i][26] = list.get(i).getAi25() != null ? list.get(i).getAi25() : -300;
				params[i][27] = list.get(i).getAi26() != null ? list.get(i).getAi26() : -300;
				params[i][28] = list.get(i).getAi27() != null ? list.get(i).getAi27() : -300;
				params[i][29] = list.get(i).getAi28() != null ? list.get(i).getAi28() : -300;
				params[i][30] = list.get(i).getAi29() != null ? list.get(i).getAi29() : -300;
				params[i][31] = list.get(i).getAi30() != null ? list.get(i).getAi30() : -300;
				params[i][32] = list.get(i).getAi31() != null ? list.get(i).getAi31() : -300;
				params[i][33] = list.get(i).getAi32() != null ? list.get(i).getAi32() : -300;

				
				params[i][34] = list.get(i).getRef1AlarmStatus() != null ? list.get(i).getRef1AlarmStatus() : -1;
				params[i][35] = list.get(i).getRef2AlarmStatus() != null ? list.get(i).getRef2AlarmStatus() : -1;
				params[i][36] = list.get(i).getRef3AlarmStatus() != null ? list.get(i).getRef3AlarmStatus() : -1;
				params[i][37] = list.get(i).getRef4AlarmStatus() != null ? list.get(i).getRef4AlarmStatus() : -1;
				
				params[i][38] = list.get(i).getRef5AlarmStatus() != null ? list.get(i).getRef5AlarmStatus() : -1;
				params[i][39] = list.get(i).getRef6AlarmStatus() != null ? list.get(i).getRef6AlarmStatus() : -1;
				params[i][40] = list.get(i).getRef7AlarmStatus() != null ? list.get(i).getRef7AlarmStatus() : -1;
				params[i][41] = list.get(i).getRef8AlarmStatus() != null ? list.get(i).getRef8AlarmStatus() : -1;
				
				params[i][42] = list.get(i).getRef9AlarmStatus() != null ? list.get(i).getRef9AlarmStatus() : -1;
				params[i][43] = list.get(i).getRef10AlarmStatus() != null ? list.get(i).getRef10AlarmStatus() : -1;
				params[i][44] = list.get(i).getRef11AlarmStatus() != null ? list.get(i).getRef11AlarmStatus() : -1;
				params[i][45] = list.get(i).getRef12AlarmStatus() != null ? list.get(i).getRef12AlarmStatus() : -1;
				
				params[i][46] = list.get(i).getRef13AlarmStatus() != null ? list.get(i).getRef13AlarmStatus() : -1;
				params[i][47] = list.get(i).getRef14AlarmStatus() != null ? list.get(i).getRef14AlarmStatus() : -1;
				params[i][48] = list.get(i).getRef15AlarmStatus() != null ? list.get(i).getRef15AlarmStatus() : -1;
				params[i][49] = list.get(i).getRef16AlarmStatus() != null ? list.get(i).getRef16AlarmStatus() : -1;
			}
		    queryRun.batch(conn,sql, params);
			return 1;
		}
		catch (Exception e) {
			logger.error("批量上传兼容模块冷库历史数据失败: "+e.getMessage());
			return 0;
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
	}
	
	private int forStandardData(String tableName, List<RefHisData> list) {
		Connection conn=null;
		try {
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = standardSQL.replace("@table", tableName);
			Object[][] params = new Object[list.size()][18];
			for(int i = 0; i < list.size(); i++) {
				params[i][0] = sf.format(list.get(i).getHDate());
				params[i][1] = sf.format(list.get(i).getUpdateTime());
				params[i][2] = list.get(i).getAi1() != null ? list.get(i).getAi1() : -300;
				params[i][3] = list.get(i).getAi2()!= null ? list.get(i).getAi2() : -300;
				params[i][4] = list.get(i).getAi3()!= null ? list.get(i).getAi3() : -300;
				params[i][5] = list.get(i).getAi4()!= null ? list.get(i).getAi4() : -300;
				params[i][6] = list.get(i).getAi5()!= null ? list.get(i).getAi5() : -300;
				params[i][7] = list.get(i).getAi6()!= null ? list.get(i).getAi6() : -300;
				params[i][8] = list.get(i).getAi7()!= null ? list.get(i).getAi7() : -300;
				params[i][9] = list.get(i).getAi8()!= null ? list.get(i).getAi8() : -300;
				params[i][10] = list.get(i).getAi9()!= null ? list.get(i).getAi9() : -300;
				params[i][11] = list.get(i).getAi10()!= null ? list.get(i).getAi10() : -300;
				params[i][12] = list.get(i).getAi11()!= null ? list.get(i).getAi11() : -300;
				params[i][13] = list.get(i).getAi12()!= null ? list.get(i).getAi12() : -300;
				params[i][14] = list.get(i).getRef1AlarmStatus() != null ? list.get(i).getRef1AlarmStatus() : -1;
				params[i][15] = list.get(i).getRef2AlarmStatus() != null ? list.get(i).getRef2AlarmStatus() : -1;;
				params[i][16] = list.get(i).getRef3AlarmStatus() != null ? list.get(i).getRef3AlarmStatus() : -1;;
				params[i][17] = list.get(i).getRef4AlarmStatus() != null ? list.get(i).getRef4AlarmStatus() : -1;;
			}
			queryRun.batch(conn, sql, params);
			return 1;
		}
		catch (Exception e) {
			logger.error("批量上传冷库历史数据失败: "+e.getMessage());
			return 0;
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
	}

}
