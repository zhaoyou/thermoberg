package org.ccdcc.biz;

import java.util.Arrays;
import java.util.List;

import org.ccdcc.entity.CarHisView;
import org.ccdcc.entity.CarHisView_new;

/**
 * 车载历史数据业务接口
 * @author Administrator
 *
 */
public interface CarHisBiz {
	/**
	 * 当前操作的状态码 1	还存在历史数据尚未完成
	 */
	public final static Integer NONE = 1 ;
	
	/**
	 * 当前操作的状态码 0   服务器发生了错误
	 */
	public final static Integer ERROR = 0 ;
	
	/**
	 * 当前操作的状态码 2 	  已经获取该启停所有的历史数据
	 */
	public final static Integer NORMAL = 2;
	
	/**
	 * 老版本涉及到的工程编号
	 */
	public final static List<String> EXISTSPRJS = Arrays.asList("3000","1200","1210","1211");
	
	/**
	 * 根据项目的projectid 、启停开始时间、某个时间点查询车载历史数据50条
	 * @param projectId		移动车载的工程标示
	 * @param time			启停的启动时间
	 * @param afterTime		启停过程中的某个时间点
	 * @return				移动车载历史数据
	 */
	public List<CarHisView> getCarList(String projectId,String time ,String afterTime) ;
	
	/**
	 * 根据项目的projectId 、历史数据的启停Id、以及该启停记录中的某个时间点查询车载历史数据50条
	 * @param projectId	工程标识
	 * @param id		启停记录的标识
	 * @param afterTime		启停记录中的某个时间点
	 * @return
	 */
	public List<CarHisView> getCarList2(String projectId,Integer id,String afterTime);
	
	/**
	 * 根据项目标识Id，对应的授权码，起停Id，和起停中的某一刻时间获取历史数据
	 * @param key			工程对应的授权码
	 * @param projectId		工程标识Id
	 * @param id			起停Id
	 * @param afterTime		某一刻时间
	 * @return
	 */
	public List<CarHisView> getCarList2(String key,String projectId,Integer id,String afterTime) ;
	
	
	/**
	 * 根据项目标识Id，对应的授权码，起停Id，和起停中的某一刻时间获取历史数据
	 * @param key			工程对应的授权码
	 * @param projectId		工程标识Id
	 * @param id			起停Id
	 * @param afterTime		某一刻时间
	 * @return
	 */
	public List<CarHisView_new> getCarList2_sy(String key,String projectId,Integer id,String afterTime) ;
}
