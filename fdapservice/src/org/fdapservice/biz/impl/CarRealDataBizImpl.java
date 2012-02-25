package org.fdapservice.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fdapservice.biz.CarRealDataBiz;
import org.fdapservice.dao.CallPhoneDao;
import org.fdapservice.dao.CarRealDataDao;
import org.fdapservice.dao.RefInfoDao;
import org.fdapservice.dao.impl.CallPhoneDaoImpl;
import org.fdapservice.dao.impl.CarRealDataDaoImpl;
import org.fdapservice.dao.impl.RefInfoDaoImpl;
import org.fdapservice.entity.CallPhone;
import org.fdapservice.entity.CarRealData;
import org.fdapservice.entity.RefInfo;
import org.fdapservice.phone.ITelephone;
import org.fdapservice.service.UploadService;
import org.fdapservice.util.TelePhone;
/**
 * 车载实时数据上传业务实现类
 * @author zhoukuanwei
 */
public class CarRealDataBizImpl implements CarRealDataBiz {

	private CarRealDataDao carrealdao = new CarRealDataDaoImpl();
	
	private RefInfoDao refinfodao = new RefInfoDaoImpl();
	
	private CallPhoneDao callphonedao = new CallPhoneDaoImpl();
	
	private Logger logger = Logger.getLogger(CarRealDataBizImpl.class);
	
	public ITelephone telphone = TelePhone.itelphone;

	/*static{
		try {
			telphone = new PhoneCaller().getBasicHttpBindingITelephone();
		} catch (Exception e) {
			System.out.println("创建ITelephone失败"+e.getStackTrace());
		}
	}*/
	
	public Integer uploadCarReal(String code, List<CarRealData> carReallist) {
		if(code == null||code.equals("") ||carReallist ==null || carReallist.size() == 0){
			logger.warn("param is null") ;
			return UploadService.FAIL;
		}
		
		StringBuffer sb = new StringBuffer();
		try {
			for(int i=0;i<carReallist.size();i++){
				sb.append(buildString(carReallist.get(i)));
				if(i!=carReallist.size()-1){
					sb.append(";");
				}
			}
			
			//上传数据前，真正报警的车载refid列表,用于判断是否有恢复正常的车载
			List<Long> palarmRefIds = null;
			//上传数据后，真正报警的车载refid列表,用于判断是否有恢复正常的车载
			List<Long> alarmRefIds = null;
			
			palarmRefIds = carrealdao.getAlarmRef(code);
			
			List<Object> list = carrealdao.uploadCarReal(code, sb.toString());
			
			Integer result = (Integer)list.get(0);
			if(result.toString().equals("0")){
				//上传实时数据后，真正报警的车载refid列表
				alarmRefIds = carrealdao.getAlarmRef(code);
				//判断是否需要拨打电话和发送短信,如果需要则拨打电话并发送短信
				iscall(list.get(1).toString(),palarmRefIds,alarmRefIds);
				return UploadService.SUCCESS;
			}
			return UploadService.FAIL;
			
		} catch (Exception e) {
			logger.error("上传车载实时数据失败"+e.getMessage());
			return UploadService.ERROR;
		}
	}
	
	/**
	 * 构造车载实时数据信息字符串
	 * @param carreal			车载实时数据信息
	 * @return					返回结果字符串：{aid1,aid2,aid3,t1,t2,t3,latitude,latitude_dir,longitude,longitude_dir,time}
	 */
	private String buildString(CarRealData carreal){
		StringBuffer sb = new StringBuffer("");
		sb.append(carreal.getAid1()+","+carreal.getAid2()+","+carreal.getAid3()+","+
				carreal.getT1()+","+carreal.getT2()+","+carreal.getT3()+","+carreal.getLatitude()+","+
				carreal.getLatitude_dir()+","+carreal.getLongitude()+","+carreal.getLongitude_dir()+","+
				carreal.getTime()+","+carreal.getRunstatus());
		return sb.toString();
	}
	
	
	private void iscall(String alarmRefs,List<Long> palarmRefIds,List<Long> alarmRefIds){
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
			message.append("等车载发生了报警");
		}
		
		StringBuffer goodmessage = new StringBuffer("");
		
		//System.out.println("我真的来了"+(palarmRefIds!=null&&palarmRefIds.size()!=0?(palarmRefIds.size()+palarmRefIds.get(0).toString()):"alarmRefIds空的"));
		if(palarmRefIds!=null&&palarmRefIds.size()!=0){
			//上传实时数据前，真正报警的车载列表
			List<RefInfo> prefList = new ArrayList<RefInfo>();
			RefInfo pinfo = null;
			
			for(int i=0;i<palarmRefIds.size();i++){
				pinfo = refinfodao.getRefById(palarmRefIds.get(i));
				if(pinfo!=null){
					prefList.add(pinfo);
				}
			}
			//如果上传实时数据后，还有真正报警的车载，则找出恢复正常的车载
			if(alarmRefIds!=null&&alarmRefIds.size()!=0){
				//更新以前真正报警的某车载在更新后是否还在报警，默认不再报警
				boolean b = false;
				//上传实时数据后，真正报警的车载列表
				List<RefInfo> afRefList = new ArrayList<RefInfo>();
				RefInfo afInfo = null;
				for(int i=0;i<alarmRefIds.size();i++){
					afInfo = refinfodao.getRefById(alarmRefIds.get(i));
					if(afInfo!=null){
						afRefList.add(afInfo);
					}
				}
				
				for(int i=0;i<prefList.size();i++){
					b = false;
					for(int j=0;j<afRefList.size();j++){
						//判断已经超过报警延时的车载是否还在报警
						if(prefList.get(i).getRefId().longValue()==afRefList.get(j).getRefId().longValue()){
							b = true;
						}
					}
					//已经超过报警延时的车载不再报警了
					if(!b){
						goodmessage.append(prefList.get(i).getName()+" ");
					}
				}
			}
			//如果上传实时数据后，没有真正报警的车载了，则上传实时数据前真正报警的车载全部恢复正常。
			else{
				for(int i=0;i<prefList.size();i++)
					goodmessage.append(prefList.get(i).getName()+" ");
			}
			if(!goodmessage.toString().equals("")){
				goodmessage.append("等车载恢复正常");
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
	
	@Override
	public List<CarRealData> getCarReal(String code) {
		if(code==null||code.equals("")){
			logger.warn("getCarReal:param is null");
			return null;
		}
		return carrealdao.queryCarReal(code);
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
	
}
