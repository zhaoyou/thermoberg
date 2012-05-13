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
	public void insertData(String type, String tableName, List<RefHisData> list) {
		if (type.equalsIgnoreCase(DevType.dev_standard.toString())) {
			forStandardData(tableName, list);
		} else {
			forExData(tableName, list);
		}
	}
	
	private void forExData(String tableName, List<RefHisData> list) {
		Connection conn=null;
		try {
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = exSQL.replace("@table", tableName);
			Object[][] params = new Object[list.size()][50];
			for(int i = 0; i < list.size(); i++) {
				params[i][0] = sf.format(list.get(i).getHDate());
				params[i][1] = sf.format(list.get(i).getUpdateTime());
				params[i][2] = list.get(i).getAi1();
				params[i][3] = list.get(i).getAi2();
				params[i][4] = list.get(i).getAi3();
				params[i][5] = list.get(i).getAi4();
				params[i][6] = list.get(i).getAi5();
				params[i][7] = list.get(i).getAi6();
				params[i][8] = list.get(i).getAi7();
				params[i][9] = list.get(i).getAi8();
				params[i][10] = list.get(i).getAi9();
				params[i][11] = list.get(i).getAi10();
				params[i][12] = list.get(i).getAi11();
				params[i][13] = list.get(i).getAi12();
				params[i][14] = list.get(i).getAi13();
				params[i][15] = list.get(i).getAi14();
				params[i][16] = list.get(i).getAi15();
				params[i][17] = list.get(i).getAi16();
				params[i][18] = list.get(i).getAi17();
				params[i][19] = list.get(i).getAi18();
				params[i][20] = list.get(i).getAi19();
				params[i][21] = list.get(i).getAi20();
				params[i][22] = list.get(i).getAi21();
				params[i][23] = list.get(i).getAi22();
				params[i][24] = list.get(i).getAi23();
				params[i][25] = list.get(i).getAi24();
				params[i][26] = list.get(i).getAi25();
				params[i][27] = list.get(i).getAi26();
				params[i][28] = list.get(i).getAi27();
				params[i][29] = list.get(i).getAi28();
				params[i][30] = list.get(i).getAi29();
				params[i][31] = list.get(i).getAi30();
				params[i][32] = list.get(i).getAi31();
				params[i][33] = list.get(i).getAi32();

				
				params[i][34] = list.get(i).getRef1AlarmStatus();
				params[i][35] = list.get(i).getRef2AlarmStatus();
				params[i][36] = list.get(i).getRef3AlarmStatus();
				params[i][37] = list.get(i).getRef4AlarmStatus();
				
				params[i][38] = list.get(i).getRef5AlarmStatus();
				params[i][39] = list.get(i).getRef6AlarmStatus();
				params[i][40] = list.get(i).getRef7AlarmStatus();
				params[i][41] = list.get(i).getRef8AlarmStatus();
				
				params[i][42] = list.get(i).getRef9AlarmStatus();
				params[i][43] = list.get(i).getRef10AlarmStatus();
				params[i][44] = list.get(i).getRef11AlarmStatus();
				params[i][45] = list.get(i).getRef12AlarmStatus();
				
				params[i][46] = list.get(i).getRef13AlarmStatus();
				params[i][47] = list.get(i).getRef14AlarmStatus();
				params[i][48] = list.get(i).getRef15AlarmStatus();
				params[i][49] = list.get(i).getRef16AlarmStatus();
			}
			queryRun.batch(conn,sql, params);
		}
		catch (Exception e) {
			logger.error("批量上传兼容模块冷库历史数据失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
	}
	
	private void forStandardData(String tableName, List<RefHisData> list) {
		Connection conn=null;
		try {
			QueryRunner queryRun = new QueryRunner();
			conn=connectionfaotory.getConnection();
			String sql = standardSQL.replace("@table", tableName);
			Object[][] params = new Object[list.size()][18];
			for(int i = 0; i < list.size(); i++) {
				params[i][0] = sf.format(list.get(i).getHDate());
				params[i][1] = sf.format(list.get(i).getUpdateTime());
				params[i][2] = list.get(i).getAi1();
				params[i][3] = list.get(i).getAi2();
				params[i][4] = list.get(i).getAi3();
				params[i][5] = list.get(i).getAi4();
				params[i][6] = list.get(i).getAi5();
				params[i][7] = list.get(i).getAi6();
				params[i][8] = list.get(i).getAi7();
				params[i][9] = list.get(i).getAi8();
				params[i][10] = list.get(i).getAi9();
				params[i][11] = list.get(i).getAi10();
				params[i][12] = list.get(i).getAi11();
				params[i][13] = list.get(i).getAi12();
				params[i][14] = list.get(i).getRef1AlarmStatus();
				params[i][15] = list.get(i).getRef2AlarmStatus();
				params[i][16] = list.get(i).getRef3AlarmStatus();
				params[i][17] = list.get(i).getRef4AlarmStatus();
			}
			queryRun.batch(conn, sql, params);
		}
		catch (Exception e) {
			logger.error("批量上传冷库历史数据失败: "+e.getMessage());
		}
		finally{
			connectionfaotory.closeConn(conn);
		}
	}

}
