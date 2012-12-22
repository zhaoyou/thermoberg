package org.fdapservice.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.fdapservice.entity.CarRealData;
import org.fdapservice.entity.RetailRealData;

/**
 * 小批零实时数据上传数据访问接口
 * @author wjt
 */

public interface RetailRealDataDao {
	
	public static final String UPLOADRETAIL = "{call proc_loadRetailRealData(?,?,?,?,?)}";
	
	public static final String REALALARMREF="select DISTINCT fr.refid from fdapauthcode fc INNER JOIN fdapproject fp ON fc.oid=fp.oid " +
			"INNER JOIN fdapref fr ON fp.projectid=fr.projectid INNER JOIN fdapaiinfo fi ON fr.refid=fi.refid INNER JOIN " +
			"fdaprealalarm freal ON fi.aiguid=freal.aiguid where fp.type=2 and freal.isrealalarm=1 and fc.code =";
	
	public static final String RETAILREAL = "select fcr.aid1,fcr.t1,fcr.latitude,fcr.latitude_dir,fcr.longitude,fcr.longitude_dir,fcr.time,fcr.isalarm " +
					"from fdapretailrealdata fcr inner join fdapref fr on fr.refid=fcr.refid inner join fdapproject fp on fr.projectid=fp.projectid " +
					"inner join fdaporg fo on fo.oid=fp.oid inner join fdapauthcode fc on fc.oid=fo.oid where fc.code=";
	
	/**
	 * 小批零实时数据上传
	 * @param code				企业授权码
	 * @param psource			源数据{aid1,t1,latitude,latitude_dir,longitude,longitude_dir,time}
	 * @return					上传的操作状态 0 成功 1 失败 2 服务器发生错误
	 */
	public List<Object> uploadRetailReal(String code,String psource);
	
	/**
	 * 记录日志
	 * @param result			操作状态值
	 * @param msg				操作信息
	 * @param log				日志对象
	 */
	public void addLog(Integer result,String msg,Logger log);
	
	
	/**
	 * 根据企业授权码获取该企业下所有报警的小批零ID列表
	 * @param code
	 * @return
	 */
	public List<Long> getAlarmRef(String code);
	
	
	/**
	 * 根据企业授权码获取该企业下所有小批零的实时数据
	 * @param code			企业授权码
	 * @return
	 */
	public List<RetailRealData> queryRetailReal(String code);
}
