package org.fdapservice.util;

import java.sql.CallableStatement;
import java.sql.Connection;

public class DealCarhis implements Runnable {
	/*dealhisMsg字符串格式为：{oid;aid1,aid2,aid3,startupid;aid1,aid2,aid3,startupid;。。。}*/
	private String dealhisMsg = null;
	
	public DealCarhis(){
		super();
	}
	
	public DealCarhis(String dealhisMsg){
		super();
		this.dealhisMsg = dealhisMsg;
	}
	
	@Override
	public void run() {
		Connection conn = null;
		CallableStatement call = null;
		Integer res = null;
		
//		System.out.println(dealhisMsg);
		String strf[] = dealhisMsg.split(";");
		String strd[] = null;
		StringBuffer sb = new StringBuffer("");
		
		//执行是否成功
		boolean b = true;
		
		try {
			conn = DBUtil.getCon();
			call = conn.prepareCall("{call proc_dealCarhis(?,?)}");
			
			for(int i =1;i<strf.length;i++){
				strd = strf[i].split(",");
				//字符串sb中存在启停记录id，则表明已经处理过了。
				if(sb.toString().indexOf(strd[3])==-1){
					sb.append(strd[3]+",");
					//组装成{oid,aid1,aid2,aid3,startupid}的字符串作为参数
					call.setString(1, strf[0]+","+strf[i]);
					call.registerOutParameter(2, java.sql.Types.INTEGER);
					call.execute();
					res = call.getInt(2);
					if(res == 1){
						b=false;
						break;
					}
				}
			}
			if(!b){
				System.out.println("更新车载历史数据报警状态失败");
			}
		} catch (Exception e) {
			System.out.println("调用车载历史数据过程失败"+e.getMessage());
		}finally{
			DBUtil.closeStatement(call);
			DBUtil.closeCon(conn);
		}
	
	}
}
