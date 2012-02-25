package org.fdapservice.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.entity.Project;
import org.fdapservice.entity.RefRealData;

/**
 * 冷库实时数据上传数据访问接口
 * @author zhaoyou
 *
 */
public interface RefRealDataDao {
	
			public static final String UPLOADREF = "{ call proc_loadRefRealData(?,?,?,?,?,?) }" ;
			
			public static final String REALALARMPRO="select DISTINCT fp.projectid from fdapauthcode fc INNER JOIN fdapproject fp ON fc.oid=fp.oid " +
			"INNER JOIN fdapref fr ON fp.projectid=fr.projectid INNER JOIN fdapaiinfo fi ON fr.refid=fi.refid INNER JOIN " +
			"fdaprealalarm freal ON fi.aiguid=freal.aiguid where fp.type=1 and freal.isrealalarm=1 and fc.code =";
	
			public static final String REFREAL = "select fa.reid,frr.data,frr.time,frr.isalarm from fdaprefrealdata frr inner join fdapaiinfo fa on frr.aiguid=fa.aiguid " +
					"inner join fdapref fr on fa.refid=fr.refid inner join fdapproject fp on fr.projectid=fp.projectid " +
					"inner join fdaporg fo on fo.oid=fp.oid inner join fdapauthcode fc on fc.oid=fo.oid where fc.code=";
			
			/**
			 * 添加冷库实时数据
			 * @param code			企业授权码
			 * @param source		探头实时数据的信息构造的字符串对象{id,value,time,status;id,value,time,status}
			 * @param Time			数据时刻
			 * @return					操作的状态码 : 0 操作成功  1 操作失败  2 程序发生错误
			 */
			public List<Object> addRefRealData(String code ,String source,String Time) ;
			
			/**
			 * 记录日志信息
			 * @param presult			操作的状态值
			 * @param pmsg			操作的信息
			 * @param logger			日志操作对象
			 */
			public void addLog(Integer presult,String pmsg,Logger logger);
			
			/**
			 * 根据企业授权码获取该企业下所有真正报警的工程ID列表
			 * @param code		企业授权码
			 * @return
			 */
			public List<Long> getAlarmPro(String code);
			
			/**
			 * 根据工程标识ID获取工程信息
			 * @param projectId		工程标识ID
			 * @return
			 */
			public Project getProById(Long projectId);
			
			
			
			/**
			 * 根据企业授权码获取该企业下所有的仓库实时数据
			 * @param code			企业授权码
			 * return
			 */
			public List<RefRealData> queryRefReal(String code);
}
