package org.ccdcc.biz;

import java.util.Arrays;
import java.util.List;

import org.ccdcc.entity.StartUpView;

/**
 * 车载历史启停记录业务接口
 * @author Administrator
 *
 */
public interface StartUpBiz {
	/**
	 * 当前操作的状态码 1	没有相应的实时数据
	 */
	public final static Integer NONE = 1 ;
	
	/**
	 * 当前操作的状态码 0    服务端发生了错误
	 */
	public final static Integer ERROR = 0 ;
	
	/**
	 * 当前操作的状态码 2 	  成功获取了实时数据
	 */
	public final static Integer NORMAL = 2;
	
	/**
	 * 表示当前启停记录在服务器已经上传完成
	 */
	public final static Integer FINISH = 2 ;
	
	/**
	 * 老版本涉及到的工程编号
	 */
	public final static List<String> EXISTSPRJS = Arrays.asList("3000","1200","1210","1211");
	
	/**
	 * 根据移动终端工程编号,获取某个时间点之后的10条起停记录
	 * @param projectId		工程标识
	 * @param time			某个时间点
	 * @return				起停记录集合
	 */
	public List<StartUpView>	getStartUpList(String projectId ,String time) ;
	
	
	
	/**
	 * 根据移动终端工程编号 ，某条启停记录标识Id或的启停记录
	 * @param projectId		移动终端工程标识
	 * @param Id			启停记录的标识Id
	 * @return
	 */
	public List<StartUpView> 	getListByAfterId(String projectId,Integer Id);
	
	/**
	 * 获取车载的历史起停记录
	 * @param key			工程授权码
	 * @param projectId		工程编号
	 * @param Id			起停的标识Id
	 * @return
	 */
	public List<StartUpView>   getListByAfterId(String key,String projectId,Integer Id) ;
	
	/**
	 * 获取车载历史起停记录
	 * @param key		工程授权码
	 * @param projectId	工程标识Id
	 * @param Id				起停标识id
	 * @return
	 */
	public List<StartUpView>  getListByAfterId_tupdw(String key ,String projectId ,Integer Id) ;
}
