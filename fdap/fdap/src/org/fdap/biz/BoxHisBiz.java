package org.fdap.biz;

import java.util.List;

import org.fdap.entity.FdapBoxHisData;
import org.fdap.entity.FdapCarHisData;
import org.fdap.entity.Fdapref;

public interface BoxHisBiz {
	public abstract List<Fdapref> getBoxRefByOid(String oid);
	
	/**
	 * 根据启停记录的Id，获取该启停记录下的所有车载历史数据，来计算相应探头的最大、最小和平均值
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停id
	 * @return
	 */
	public abstract List<Object> getBoxHisMMA(String tableName,Integer parentId);
	
	/**
	 * 根据启停记录的Id，分页获取该启停记录下的车载历史数据
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停id
	 * @param startRow         	查询车载历史数据开始位置
	 * @param pagesize			查询车载历史数据每页的条数
	 * @return
	 */
	public abstract List<FdapBoxHisData> getBoxHisbyStartup(String tableName,Integer parentId,Integer startRow,Integer pagesize);
	
	/**
	 * 根据启停记录的Id，获取所有车载历史数据的总条数
	 * @param tableName         车载历史数据表名
	 * @param parentId			车载启停id
	 * @return
	 */
	public abstract Integer getBoxHisCount(String tableName,Integer parentId);
	
	
	/**
	 * 根据车载冷库的Id，获取车载冷库详细信息
	 * @param refId			车载冷库id
	 * @return
	 */
	public abstract Fdapref getBoxRefById(String refId);
	
	
	/**
	 * 根据企业oid和启停记录的Id，获取所有车载历史数据(flex调用)
	 * @param oid         		企业oid
	 * @param sid				车载启停标识id
	 * @return
	 */
	public abstract List<FdapBoxHisData> getBoxHis(String oid,String sid);
}
