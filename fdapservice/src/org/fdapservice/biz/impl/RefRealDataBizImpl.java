package org.fdapservice.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fdapservice.biz.RefRealDataBiz;
import org.fdapservice.dao.CallPhoneDao;
import org.fdapservice.dao.RefInfoDao;
import org.fdapservice.dao.RefRealDataDao;
import org.fdapservice.dao.impl.CallPhoneDaoImpl;
import org.fdapservice.dao.impl.RefInfoDaoImpl;
import org.fdapservice.dao.impl.RefRealDataDaoImpl;
import org.fdapservice.entity.CallPhone;
import org.fdapservice.entity.Project;
import org.fdapservice.entity.RefInfo;
import org.fdapservice.entity.RefRealData;
import org.fdapservice.phone.ITelephone;
import org.fdapservice.service.UploadService;
import org.fdapservice.util.TelePhone;


/**
 * 冷库实时数据上传业务实现类
 * @author zhaoyou
 *
 */
public class RefRealDataBizImpl implements RefRealDataBiz {

	private RefRealDataDao refRealDataDao = new RefRealDataDaoImpl();
	
	private RefInfoDao refinfodao = new RefInfoDaoImpl();
	
	private CallPhoneDao callphonedao = new CallPhoneDaoImpl();
	
	private Logger logger =Logger.getLogger(RefRealDataBizImpl.class);
	
	
	public ITelephone telphone = TelePhone.itelphone;

	/*static{
		try {
			telphone = new PhoneCaller().getBasicHttpBindingITelephone();
		} catch (Exception e) {
			System.out.println("创建ITelephone失败"+e.getStackTrace());
		}
	}*/
	
	@Override
	public Integer uploadRealData(String code, List<RefRealData> list,String Time) {
		
		//验证参数
		if(code==null || code.equals("") || list==null || list.size()==0||Time==null||Time.equals("")){
			return UploadService.FAIL ;
		}
		
		//构造探头的字符串对象
		StringBuffer sb = new StringBuffer();
			try {
				for (Integer i=0 ;i<list.size();i++) {
					sb.append(buildInfo(list.get(i))) ;
					if(i!=list.size()-1)
						sb.append(";");
				}
				
				/*for(RefRealData refreal : list){
					System.out.println(refreal.getId()+" "+refreal.getValue()+" "+refreal.getStatus());
				}
				
				System.out.println(code+" "+Time);
				System.out.println(sb.toString());*/
				
			//上传数据前，真正报警的车载refid列表,用于判断是否有恢复正常的车载
			List<Long> palarmProIds = null;
			//上传数据后，真正报警的车载refid列表,用于判断是否有恢复正常的车载
			List<Long> alarmProIds = null;
				
			palarmProIds = refRealDataDao.getAlarmPro(code);
			//上传仓库冷库实时数据
			List<Object> list2 = refRealDataDao.addRefRealData(code, sb.toString(),Time);
			//保存执行存储过程的结果
			Integer result = (Integer)list2.get(0);
			
			if(result.toString().equals("0")){
				alarmProIds = refRealDataDao.getAlarmPro(code);
				iscall(list2.get(1).toString(),palarmProIds,alarmProIds);
				return UploadService.SUCCESS ;
			}
			return UploadService.FAIL ;
		} catch (Exception e) {
			logger.error("上传冷库实时数据失败:"+e.getMessage()) ;
			return UploadService.ERROR ;
		}
	}
	
	private void iscall(String alarmRefs,List<Long> palarmProIds,List<Long> alarmProIds){
		StringBuffer message = new StringBuffer("");
		
		if(alarmRefs!=null&&!alarmRefs.equals("")){
			String refIds[] = alarmRefs.split(",");
			RefInfo info = null;
			List<RefInfo> refList = new ArrayList<RefInfo>();
			for(int i=0;i<refIds.length;i++){
				info = refinfodao.getRefById(new Long(refIds[i]));
				if(info!=null){
					refList.add(info);
				}
			}
			distinctData(refList);
			for(int i=0;i<refList.size();i++){
				message.append(refList.get(i).getName()+" ");
			}
			message.append("等冷库发生了报警。");
		}
		
		StringBuffer goodmessage = new StringBuffer("");
		
		//System.out.println("我真的来了"+(palarmRefIds!=null&&palarmRefIds.size()!=0?(palarmRefIds.size()+palarmRefIds.get(0).toString()):"alarmRefIds空的"));
		if(palarmProIds!=null&&palarmProIds.size()!=0){
			//上传实时数据前，真正报警的车载列表
			List<Project> pproList = new ArrayList<Project>();
			Project pinfo = null;
			
			for(int i=0;i<palarmProIds.size();i++){
				pinfo = refRealDataDao.getProById(palarmProIds.get(i));
				if(pinfo!=null){
					pproList.add(pinfo);
				}
			}
			//如果上传实时数据后，还有真正报警的车载，则找出恢复正常的车载
			if(alarmProIds!=null&&alarmProIds.size()!=0){
				//更新以前真正报警的某车载在更新后是否还在报警，默认不再报警
				boolean b = false;
				//上传实时数据后，真正报警的车载列表
				List<Project> afProList = new ArrayList<Project>();
				Project afInfo = null;
				for(int i=0;i<alarmProIds.size();i++){
					afInfo = refRealDataDao.getProById(alarmProIds.get(i));
					if(afInfo!=null){
						afProList.add(afInfo);
					}
				}
				
				for(int i=0;i<pproList.size();i++){
					b = false;
					for(int j=0;j<afProList.size();j++){
						//判断已经超过报警延时的车载是否还在报警
						if(pproList.get(i).getProjectid().longValue()==afProList.get(j).getProjectid().longValue()){
							b = true;
						}
					}
					//已经超过报警延时的车载不再报警了
					if(!b){
						goodmessage.append(pproList.get(i).getName()+" ");
					}
				}
			}
			//如果上传实时数据后，没有真正报警的车载了，则上传实时数据前真正报警的车载全部恢复正常。
			else{
				for(int i=0;i<pproList.size();i++)
					goodmessage.append(pproList.get(i).getName()+" ");
			}
			if(!goodmessage.toString().equals("")){
				goodmessage.append("等仓库恢复正常。");
				message.append(goodmessage.toString());
			}
		}
		//如果消息不为空，则开始拨打电话和发送短信。
		if(!message.toString().equals("")){
			try {
				List<CallPhone> plist = callphonedao.getPhoneCall();
				if(plist!=null&&plist.size()!=0){
					//取第一个电话信息
					CallPhone callphone = plist.get(0);
					
					//如果电话标识phoneActive为1，则拨打电话
					if(callphone.getPhoneActive()==1){
						telphone.call(callphone.getCallcode(), callphone.getPhonenumber(), 1);
					}
					//如果短信标识phoneActive为1，则发送短信
					if(callphone.getMessageActive()==1){
						telphone.sendMessage(callphone.getCallcode(), callphone.getMessagenumber(), message.toString());
					}
//					System.out.println(callphone.getPhonenumber()+" "+callphone.getPhoneActive()+" "+callphone.getMessagenumber()+" "+callphone.getMessageActive());
				}
				else{
					System.out.println("拨打电话发送短信失败，请确认是否设置电话和短信号码");
				}
			} catch (Exception e) {
				System.out.println("调用电话短信服务失败"+e.getStackTrace());
			}
//			System.out.println("拨打电话和发送短信"+message);
		}
		
	}
	
	/*
	 * 
	 */
	@Override
	public List<RefRealData> getRefReal(String code) {
		//验证参数
		if(code==null || code.equals("") ){
			System.out.println("获取仓库实时数据失败"); ;
		}
		return refRealDataDao.queryRefReal(code);
	}
	
	
	
	/**
	 * 过滤数据
	 * @param list
	 */
	private void distinctData(List<RefInfo> list){
		if(list==null || list.size()==0)
			return  ;
		
		Map<Long,RefInfo> tempMap = new HashMap<Long, RefInfo>();
		
		//循环处理集合重复值
		for (Iterator<RefInfo> iterator = list.iterator(); iterator.hasNext();) {
			RefInfo info =  iterator.next();
			if(tempMap.containsKey(info.getRefId()))
				iterator.remove();
			else 
				tempMap.put(info.getRefId(), info);
		}
	}
	
	
	/**
	 * 构造探头的信息的字符串
	 * @param data		单个探头的信息 
	 * @return				返回的字符串格式:	{id,VALUE,time,status}
	 */
	private String buildInfo(RefRealData data){
		StringBuffer sb = new StringBuffer("");
		sb.append(data.getId()+",") ;
		sb.append(data.getValue()+",");
//		sb.append(data.getTime()+",");
		sb.append(data.getStatus());
		return sb.toString() ;
	}

	
}
