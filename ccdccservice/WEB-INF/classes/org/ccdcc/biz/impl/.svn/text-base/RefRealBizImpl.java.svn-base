package org.ccdcc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.ccdcc.biz.RefRealBiz;
import org.ccdcc.dao.ProjectAuthDao;
import org.ccdcc.dao.RefRealDao;
import org.ccdcc.entity.AiInfoView;
import org.ccdcc.entity.RefInfoView;
import org.ccdcc.entity.RefRealView;
import org.ccdcc.util.Util;

/**
 * 冷库实时数据业务实现类
 * @author zhaoyou
 *
 */
public class RefRealBizImpl implements RefRealBiz {
	
	
	
	
	private static Logger logger = Logger.getRootLogger() ;
	
	private ProjectAuthDao projectAuthDao = null ;
	
	private RefRealDao		refRealDao = null ;
	
	private Util 			util = null ;

	public void setProjectAuthDao(ProjectAuthDao projectAuthDao) {
		this.projectAuthDao = projectAuthDao;
	}

	public void setRefRealDao(RefRealDao refRealDao) {
		this.refRealDao = refRealDao;
	}
	
	public void setUtil(Util util) {
		this.util = util;
	}

	
	
	public String getRefReal(String key, String projectId, Integer realRefId,
			Integer refType, Integer floorType, Integer floorNo) {
		
		try {
			//如果参数为空或者非法则直接返回标示失败xml字符串
			if(key==null || key.equals("") || projectId==null || projectId.equals("") ||realRefId==null || realRefId.equals("")||
					refType==null || refType.equals("")|| floorType==null ||floorType.equals("")|| floorNo==null || floorNo.equals(""))
				return ERROR_XML ;
			
			//如果工程授权码不正确则直接返回标示失败xml字符串
			if(! projectAuthDao.queryAuthCode(projectId, key))
				return ERROR_XML ;
			
			//以后扩展限制用户调用的频率，所采取的策略
			
			
			RefInfoView rvObj = refRealDao.queryRefByInfo(projectId, realRefId, refType, floorType, floorNo);
			
			//如果当前冷库不存在、直接返回
			if(rvObj==null)
				return ERROR_XML ;
			
			List<AiInfoView> aiList = refRealDao.queryAiInfoByRid(rvObj.getId());
			
			//如果当前冷库没有配置相应的探头、直接返回
			if(aiList==null || aiList.size()==0)
				return ERROR_XML ;
			
			RefRealView realDataObj = refRealDao.queryRefReal(projectId, rvObj.getNetId());
			
			//如果当前冷库所在的设备不存在实时数据、直接返回
			if(realDataObj==null)
				return ERROR_XML ;
			
			//获取平均温湿度
			Double [] avgValue = getAvgValue(aiList, realDataObj);
			
			
			//构造结果字符串
			StringBuffer sb = new StringBuffer("<?xml version='1.0' encoding='utf-8' ?>");
			sb.append("<datas>");
			sb.append("<result_state>1</result_state>");
			//构造结果中间部分xml字符串
			String resultXML = buildRefRealStr(util.getToString(realDataObj.getUpdateTime()), realRefId, refType, floorType, floorNo, avgValue[0], avgValue[1]);
			sb.append(resultXML);
			sb.append("</datas>");
			
			
			
			return sb.toString();
			
		} catch (Exception e) {
			logger.error("获取冷库实时数据失败: "+e.getMessage());
			return ERROR_XML ;
		}
	}

	public String getRefReal(String key, String projectId) {
		
		try {
			//如果参数为空或非法、则直接返回失败信息
			 if(key==null || key.equals("") || projectId==null || projectId.equals("")){
				 return ERROR_XML ;
			 }
			 
			//以后扩展限制用户调用的频率，所采取的策略
			 
			 
			//验证工程授权码是否合法
			 if(!projectAuthDao.queryAuthCode(projectId, key)){
				 return ERROR_XML ;
			 }
			 
			 
			 //如果当前工程下没有配置相应的冷库、则直接返回失败信息
			 List<RefInfoView> refList = refRealDao.queryRefByProjectId(projectId);
			 
			 if(refList==null || refList.size()==0){
				 return ERROR_XML ;
			 }
			 
			 //如果当前工程下没有配置任何探头、则直接返回失败信息
			 List<AiInfoView> aiList = refRealDao.queryAiInfoByProjectId(projectId);
			 
			 if(aiList==null || aiList.size()==0){
				 return ERROR_XML ;
			 }
			 
			 //如果当前工程下没有上传任何实时数据、则直接返回失败信息
			 List<RefRealView> realDataList = refRealDao.queryRefReal(projectId);
			 
			 if(realDataList==null || realDataList.size()==0){
				 return ERROR_XML ;
			 }
			 
			
			 //构造结果xml字符串
			 StringBuffer sb = new StringBuffer("<?xml version='1.0' encoding='utf-8' ?>");
			 sb.append("<datas>");
			 sb.append("<result_state>1</result_state>");
			 sb.append(buildAllRefRealStr(refList, aiList, realDataList));
			 sb.append("</datas>");	 
			 return sb.toString() ;
			 
		} catch (Exception e) {
			logger.error("获取冷库实时数据失败: "+e.getMessage());
			return ERROR_XML ;
		}
		
	}
	
	
	
	
	/**
	 * 构造单个冷库实时数据的xml字符串
	 * @param updateTime		冷库设备当前时间
	 * @param refRealId			冷库的实际编号
	 * @param refType			冷库的类型
	 * @param floorType			冷库所处的楼层类型
	 * @param floorNo			楼层编号
	 * @param tavg				平均温度
	 * @param havg				平均湿度
	 * @return					包含上述信息的xml字符串
	 */
	private String buildRefRealStr(String updateTime,Integer realRefId,Integer refType,Integer floorType,Integer floorNo,Double tavg,Double havg){
		StringBuffer sb = new StringBuffer();
		sb.append("<refReal>");
		sb.append("<updateTime>"+updateTime+"</updateTime>");
		sb.append("<realRefId>"+realRefId+"</realRefId>") ;
		sb.append("<refType>"+refType+"</refType>");
		sb.append("<floorType>"+floorType+"</floorType>");
		sb.append("<floorNo>"+floorNo+"</floorNo>");
		sb.append("<tavg>"+tavg+"</tavg>");
		sb.append("<havg>"+havg+"</havg>");
		sb.append("</refReal>");
		return sb.toString() ;
	}

	
	/**
	 * 根据设备实时数据、探头集合获取该探头对应温湿度的平均值
	 * @param paiList			探头的集合
	 * @param prefRealView		设备实时数据
	 * @return					包含平均温度、平均湿度的数组
	 */
	private Double[] getAvgValue(List<AiInfoView> paiList ,RefRealView prefRealView){
		
		//定义四个变量温度总和、湿度总和、温度探头个数、湿度探头个数
		Double tavg = 0.0 ,havg = 0.0 ;
		Integer tcount = 0 ,hcount = 0 ;
		//定义了一个变量 AI端口号
		Integer port=0;
		
		
		
		for (AiInfoView aiInfoView : paiList) {
			
			
			// 1-9是温度探头
			if(aiInfoView.getDataType().equals(1)){
				port=aiInfoView.getPortNo();
				if(! prefRealView.getAllAi()[port-1].equals(-300)){
					tavg +=prefRealView.getAllAi()[port-1];
					tcount ++ ;
				}
				continue ;
			}
			else if(aiInfoView.getDataType().equals(2))
			{
				port=aiInfoView.getPortNo();
				if(! prefRealView.getAllAi()[port-1].equals(-300)){
					havg += prefRealView.getAllAi()[port-1];
					hcount ++ ;
				}
				continue ;
			}
			
		}
		//判断温度探头合法性的个数
		if(tcount==0){
				tavg = -300.00  ;
		}else{
				tavg = tavg /tcount ;
		}
		
		//判断湿度探头的合法性个数
		if(hcount==0){
			havg = -300.00 ;
		}else {
			havg = havg / hcount ;
		}
		//System.out.println("平均温度:"+tavg+"平均湿度:"+havg);
				
		return new Double[]{tavg,havg} ;
	}
	
	/**
	 * 根据冷库集合、探头集合、实时数据集合，构造每个冷库实平均温湿度的xml字符串
	 * @param pRefInfoList		冷库集合列表
	 * @param pAiInfoList		探头集合列表
	 * @param pRefRealList		冷库实时数据集合列表
	 * @return					构造每个冷库平均温湿度的xml字符串
	 */
	private String buildAllRefRealStr(List<RefInfoView> pRefInfoList,List<AiInfoView> pAiInfoList,List<RefRealView> pRefRealList){
		//定义一个变量保存所有的冷库数据
		StringBuffer sb = new StringBuffer();
	
		
		//外循环遍历所有的冷库列表
		for (RefInfoView refInfoView : pRefInfoList) {
			
			//获取该冷库下的所有探头
			List<AiInfoView> newAiList = checkRefAi(pAiInfoList, refInfoView.getId());
			
			if(newAiList==null || newAiList.size()==0)
				continue ;
			
			//获取该冷库对应的设备的实时数据
			RefRealView 	newRefRealView = checkRefRealData(pRefRealList, refInfoView.getNetId());
			
			if(newRefRealView==null)
				continue ;
			
			//获取当前冷库的平均温湿度值
			Double[] avgValue = getAvgValue(newAiList, newRefRealView);
			
			//构造当前冷库的xml字符串
			sb.append(buildRefRealStr(util.getToString(newRefRealView.getUpdateTime()), refInfoView.getRealRefId(), refInfoView.getRefType(),
					refInfoView.getFloorType(), refInfoView.getFloorNo(), avgValue[0], avgValue[1]));
			
		}
		return sb.toString() ;
	}
	
	/**
	 * 根据冷库标示、在整个工程里探头列表中搜索出该冷库的所有探头
	 * @param pAiList		整个工程探头列表
	 * @param rid			冷库的标识Id
	 * @return				属于冷库Id的探头列表
	 */
	private List<AiInfoView> checkRefAi(List<AiInfoView> pAiList,Long prid){	
		List<AiInfoView> aiList = new ArrayList<AiInfoView>();
		for (AiInfoView aiInfoView : pAiList) {
			if(aiInfoView.getRid().equals(prid))
				aiList.add(aiInfoView);
		}
		return aiList ;
	}
	
	/**
	 * 根据冷库设备的标示，在所有设备的实时数据中搜索出冷库所在设备的实时数据
	 * @param pRefRealDataList		所有的设备实时数据列表
	 * @param pnetId				冷库所在设备的标示
	 * @return						冷库所在设备的实时数据
	 */
	private RefRealView checkRefRealData(List<RefRealView> pRefRealDataList,Integer pnetId){
		for (RefRealView refRealView : pRefRealDataList) {
			if(refRealView.getNetId().equals(pnetId))
				return refRealView ;
		}
		return null ;
	}

}
