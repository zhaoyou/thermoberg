<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小批零数据上传管理</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/piling/tc.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/input.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='dwr/interface/hist.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<link href="css/piling/piling.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		var fso,diskDriver,projectId;	
				
	function golist(url,operate){
		document.getElementById('uploadform').action = url ;
		document.getElementById('ope').value = operate ;
		document.uploadform.submit();
	}
			//设置状态窗口提示
			function StatusTip(isProcessing,isUploading){
			
			if(isProcessing==true)
				document.getElementById('statusdiv_processing').style.display = "inline" ;			
			else
				document.getElementById('statusdiv_processing').style.display = "none" ;						
			
			if(isUploading==true)
				document.getElementById('statusdiv_uploading').style.display = "inline" ;			
			else
				document.getElementById('statusdiv_uploading').style.display = "none" ;					
			}				
			
    //************设备自动上传***************			

			/**
			*	跳转到自动上传
			*/
			function gotoAutoUpload(curProjectId,curDevId){
				var lineStr;
				var isFindDisk=false;				
				
				//检查驱动器
				var drv;

			try{						
				if(fso==null){
				fso= new ActiveXObject("Scripting.FileSystemObject");	
				}
				var colDrives = new Enumerator(fso.Drives);
				for(;!colDrives.atEnd();colDrives.moveNext()){
				  drv = colDrives.item();
				   try{
				    if((drv.VolumeName.indexOf("WASON")==0)&&(drv.TotalSize<10000000)&&(drv.IsReady==true)){//检查是否存在wason命名的卷标，并且磁盘空间小于10M,而且只读
					    diskDriver = drv.Path;  //记录驱动器名
						isFindDisk=true;        //标记找到驱动器
						if(confirm("检测到小批零设备,是否上传数据?")==false){
						 return;
						}						
						break;
				    }			
				   }
				   catch(e){  //跳过不能获取的驱动器（例如没有插入盘的光盘驱动器和软盘驱动器）
				     continue;
				   }
			    }			
			
				  if(isFindDisk==false){  //没有找到小批零设备
				   StatusTip(false,false);							  
				   alert("无法检测到小批零设备，请确保设备与电脑正确连接");
				   return;
				  }				  
			  
			   // 检查是否设备中至少存在一个文件
			      var objFolder = fso.GetFolder(diskDriver);
				  var colFiles = new Enumerator(objFolder.Files);
				  var objFileName;				  
				  var isFindFile=false;					  
				  for(;!colFiles.atEnd();colFiles.moveNext()){
						isFindFile=true;        //标记找到至少一个文件
				        objFileName=colFiles.item().Path;						
						break;
			      }		
						
			      if(isFindFile==false){  //设备中没有一个记录文件
				   StatusTip(false,false);						      
				   alert("小批零设备中不存在数据记录");
				   return;
				  }
				  
			   // 读取第一个文件中的设备编号，文件中不存在设备编号或设备号与本地网站列表中的设备编号不同
			   
					var objStream = fso.OpenTextFile(objFileName);	
					var devId;
					var errDevice=false;
			        while((lineStr=objStream.ReadLine())!=null)
				    {
				       if(lineStr.indexOf("Serial No.,")>=0){  //包括"Serial No"标识
					      devId=lineStr.substring(lineStr.indexOf("Serial No.,")+11);
						 if(devId.indexOf(curDevId)!=-1) {  //设备编号与工程配置一致
						   break;	
						 }							
						 else{//实际接入电脑的设备编号与网站列表中的选择上传的设备编号不同\
						   StatusTip(false,false);			
						   alert("待上传设备与已配置的编号不一致，请重新选择")							
						   errDevice=true;
						   break;
						 }
					   }
				    }
					objStream.Close();

				if(errDevice==true)
				   return;
				   
				//检查并获取当前设备所有启停记录时间数据
				  var devHistStartUpList = new Array();	  				  
				  for(var i=1;!colFiles.atEnd();colFiles.moveNext()){
				     objFileName=fso.GetBaseName(colFiles.item().Path);										  

					//设置启动时间
				     var HistStartUpObj={id:i,transBeginTime:objFileName.replace('.',':').replace('.',':'),transEndTime:objFileName.replace('.',':').replace('.',':'),recordInterval:60,updateStatus:2,tuplimit:8,tdwlimit:2}; 
					 devHistStartUpList.push(HistStartUpObj);		 					 				 
					 i=i+1;
				  }
				  
				// ajax调用查询该设备中需要上传的启停记录数据
					projectId= curProjectId;				
				   getPacketSsRecord(curProjectId,devHistStartUpList);
		   
				//显示正在处理中提示
				//  StatusTip(true,false);	
				  
			    }				  
			   catch(e){
				     StatusTip(false,false);						   
				     alert("小批零设备读取失败");
				     return ;
			   }
				  
			}
			
		//与数据库中已经上传的启停记录进行比较，确定设备中仍需上传的启停数据列表(curProjectId:表示当前小批零设备工程编号;ssData:表示设备中所有的启停记录时间)
			function getPacketSsRecord(curProjectId,ssData){	
				hist.getUploadStartupList(curProjectId,ssData,{
				callback:packetSsDataResultHandler,
				errorHandler:ResultError,
				timeout:15000
				}) ;	
			}
			
			//获取需要上传的设备数据的结果处理(ssData表示从服务器端查询的需要上传的启停记录数据）
			function packetSsDataResultHandler(ssData){
				var lineStr,tempStr;		
				var firstDataTime = new Date();
				var secondDataTime = new Date();				
				var updateTime;	
				var id;
				var minValue,maxValue;
				var upLimit,dwLimit;
				var recordInterval=60;   //默认记录间隔为60秒
				var isAlarm;
				var isFindDisk=false;
				var histDataArray =new Array();  //临时历史数据文件中的暂存数据	
				
			  //检查返回数据中的启停列表
			    if(ssData==null){  //不存在停止记录需要上传
				 StatusTip(false,false);						    
			     alert("设备中的数据记录不需要重复上传");
				 return;
				}
		
				//存在需要上传的启停记录
				StatusTip(false,true);		//设置正在上传提示

				//根据返回的需要上传的启停记录，整合设备中的启停记录进行上传  
				
			  try{			   
			  				  
				  isAlarm=false;				  

			      var objFolder = fso.GetFolder(diskDriver);
				  var colFiles = new Enumerator(objFolder.Files);
				  var devHistStartUpList = new Array();  //定义需要上传的启停暂存列表
				  var devHistDataList= new Array();   //定义需要上传的历史数据暂存列表
				  				  				  
			   // 读取第一个文件中的设备编号，文件中不存在设备编号或设备号与本地网站列表中的设备编号不同
			   	  for(id=1;!colFiles.atEnd();colFiles.moveNext()){  //扫描每一个文件
					  tempStr=fso.GetBaseName(colFiles.item().Path);  //获取不包括文件名称后缀和路径的文件名				  
					for(var i=0;i<ssData.length;i++){  //比较服务器传回的需要上传的启停记录
					
					  var HistStartUpObj={id:ssData[i].id,transBeginTime:ssData[i].transBeginTime,transEndTime:ssData[i].transEndTime,recordInterval:ssData[i].recordInterval,updateStatus:ssData[i].updateStatus,tuplimit:ssData[i].tuplimit,tdwlimit:ssData[i].tdwlimit};
							  
					  if(HistStartUpObj.transBeginTime.indexOf(tempStr.replace('.',':').replace('.',':'))==0)  //启动时间相同
					  {						
						var objStream = fso.OpenTextFile(colFiles.item().Path,1);	
						var devId;			
						var splitStr;									
						while((lineStr=objStream.ReadLine())!=null)  //检查本地文件
				        {
						  if(lineStr.length<=2){ //跳过空行
						      continue;
						  }
						  
						  if(lineStr.indexOf("Date,Time,T.(C),Note")>=0)  //包括"Date,Time,T.(C),Note"标识
						        continue;

						  if(lineStr.indexOf("Model,WS-T10PRO")>=0)  //包括"Model,WS-T10PRO"标识
						        continue;

						  if(lineStr.indexOf("Serial No.,")>=0)  //包括"Serial No.,"标识
						        continue;
								
				          if(lineStr.indexOf("Alarm Max.,,")==0){  //报警上限
					            upLimit=parseFloat(lineStr.substring(lineStr.indexOf("Alarm Max.,,")+12));
						        continue;
						  }
						  
				          if(lineStr.indexOf("Alarm Min.,,")==0){  //报警下限
								dwLimit=parseFloat(lineStr.substring(lineStr.indexOf("Alarm Min.,,")+12));
						        continue;						  
						  }

						  if(lineStr.indexOf("Max.,,")==0){  //最大值
								maxValue=parseFloat(lineStr.substring(lineStr.indexOf("Max.,,")+6));
								if(maxValue>upLimit){  //超高报警
									isAlarm=true;
								}
						        continue;						  
						  }
						  		  
						  if(lineStr.indexOf("Min.,,")==0){  //最小值
								minValue=parseFloat(lineStr.substring(lineStr.indexOf("Min.,,")+6));
								if(minValue<dwLimit){  //超低报警
									isAlarm=true;
								}						  
						        break;			//跳过读取文件最后一行导致抛出异常的情况
						  }
						  
						if(lineStr.length>=18){ //暂存历史数据到临时缓冲区中
						    if(lineStr.indexOf(",A")!=-1){ //该条历史记录中包括报警标识
						      lineStr=lineStr.substring(0,lineStr.length-2);  //过滤掉显示报警的标识
						    }
							histDataArray.push(lineStr);
						    continue;	     	  			
				         }					  
						}
									
						objStream.Close();										
												  						
						for(var i=0;i<histDataArray.length;i++){  //处理暂存的历史数据记录
							 lineStr= histDataArray[i];
									 
							 if(i==0){  //记录第一条记录的时间
							   tempStr= lineStr.substring(0,lineStr.indexOf(","));  //获取年月日
							   splitStr= tempStr.split("-");
							   firstDataTime.setFullYear(splitStr[0],splitStr[1]-1,splitStr[2]);
							   tempStr= lineStr.substring(lineStr.indexOf(",")+1,lineStr.lastIndexOf(",")); //获取时分秒
							   splitStr= tempStr.split(":");
							   firstDataTime.setHours(splitStr[0]);
							   firstDataTime.setMinutes(splitStr[1]);
							   firstDataTime.setSeconds(splitStr[2]);							   							   
						    }
	
						    if(i==1){  //计算第二条记录与第一条之间的时间间隔
							   tempStr= lineStr.substring(0,lineStr.indexOf(","));  //获取年月日
							   splitStr= tempStr.split("-");
							   secondDataTime.setFullYear(splitStr[0],splitStr[1]-1,splitStr[2]);
							   tempStr= lineStr.substring(lineStr.indexOf(",")+1,lineStr.lastIndexOf(",")); //获取时分秒
							   splitStr= tempStr.split(":");
							   secondDataTime.setHours(splitStr[0]);
							   secondDataTime.setMinutes(splitStr[1]);
							   secondDataTime.setSeconds(splitStr[2]);															
							   recordInterval=(secondDataTime.getTime()-firstDataTime.getTime())/1000 ;  //获取以秒为单位的时间间隔			   						  
						    }

						  //记录最后一条记录时间作为停止时间				  
						  tempStr= lineStr.substring(0,lineStr.indexOf(","));  //获取年月日
						  splitStr= tempStr.split("-");
						  if(splitStr[1].length==1){ //月份处理
						     splitStr[1]="0"+splitStr[1];
						  }
						  if(splitStr[2].length==1){  
						     splitStr[2]="0"+splitStr[2];
						  }						  
						  updateTime=splitStr[0]+ "-" +splitStr[1]+ "-" +splitStr[2];
							   
						  tempStr= lineStr.substring(lineStr.indexOf(",")+1,lineStr.lastIndexOf(",")); //获取时分秒
						  splitStr= tempStr.split(":");   
						  if(splitStr[0].length==1){ //小时处理
						     splitStr[0]="0"+splitStr[0];
						  }
						  if(splitStr[1].length==1){ //分钟处理
						     splitStr[1]="0"+splitStr[1];
						  }									  
						  if(splitStr[2].length==1){ //秒钟处理
						     splitStr[2]="0"+splitStr[2];
						  }			
			  					  					   							   
						  updateTime=updateTime+ " " +splitStr[0]+ ":" +splitStr[1]+ ":" +splitStr[2];
						  
						  //计算是否本条历史记录超高限或超低限报警
						 var AlarmStatus;
						 var aiValue;
						 
						 //获取AI值
						  aiValue = parseFloat(lineStr.substring(lineStr.lastIndexOf(",")+1));
						 							  
						  if((aiValue>=upLimit)||(aiValue<=dwLimit)){  //超高限或超低限报警
						    AlarmStatus=1;
						  }
						  else{  //无报警
						    AlarmStatus=2; 			  						  
						  }

						 var hisBoxDataObj= {parentId:id,ai1:aiValue,alarmStatus:AlarmStatus,transUpdateTime:updateTime};
						  devHistDataList.push(hisBoxDataObj);						  	  			
						}				         
	
						histDataArray.splice(0,histDataArray.length); 	//清除缓存中的历史数据
													
					    //更新启停记录
					    var HistStartUpObj2={id:id,transBeginTime:HistStartUpObj.transBeginTime,transEndTime:updateTime,recordInterval:recordInterval,updateStatus:2,tuplimit:upLimit,tdwlimit:dwLimit};
						    
						devHistStartUpList.push(HistStartUpObj2);					    						
					    id=id+1;
			    
						break;
						
					  }
					  
					}
				 }				  

			   }
				  
			   catch(e){
				     StatusTip(false,false);						   
				     alert("小批零设备数据上传失败");
				     return ;
			   }
			   
			     if((devHistDataList.length==0)&&(devHistStartUpList==0)){ //启停记录和历史记录均无数据需要上传
				   StatusTip(false,false);				       			     
			       alert("小批零设备中的数据已经上传过");
			       return ;
			     }
				  
				 //调用后台接口将启停记录和历史数据记录更新到数据库				 
				 UploadPacketHistData(projectId,devHistStartUpList,devHistDataList);

			}					
								
			//上传启停记录和历史数据(data表示需要上传的启停记录和历史数据)
			function UploadPacketHistData(projectId,devHistStartUpList,devHistDataList){
				
				hist.uploadPacketHistData(projectId,devHistStartUpList,devHistDataList,{
				callback:uploadPacketResultHandler,
				errorHandler:ResultError,
				timeout:15000
				}) ;	
			}	
			
			//上传启停和历史数据返回结果处理过程
			function uploadPacketResultHandler(data){
			
				StatusTip(false,false);		
				
			  //检查返回数据中的启停列表
			    if(data==0){  //上传成功
				 StatusTip(false,false);						    
			     alert("上传成功");
				}				
				else{
				 StatusTip(false,false);				   
				alert("上传失败");
				}							
			}
			
			//手动上传
			function gotoManualUpload(){
			  alert("手动上传暂不支持");			
			}			
//*********上传过程错误处理***************			

			//获取最后停止时间的错误处理
			function ResultError(message){			
				StatusTip(false,false);				
				alert("网络繁忙，请稍后再试");
			}		
					
		
</script>
</head>
 
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form name="uploadform" id="uploadform" method="post" action="">
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
	<input type="hidden" name="ope" id="ope" value=""/>
</form>
<div style="float: left;height: 496px;width: 984px;padding-top: 5px;padding-left: 4px;border-right-width: 1px;border-left-width: 1px;border-right-style: solid;border-left-style: solid;	border-right-color: #acb4be;border-left-color: #acb4be;">
  <div id="top" ><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15" / class="tb4"><strong>位置:首页</strong>&lt;您正在查看小批零数据上传管理</a><img src="img/util/back.gif" style="cursor: pointer; margin-left:680px;" onclick="javascript:golist('pro.do','toBoxList');" width="48" height="20" / class="tb3"></div> 
  <div id="bottom">
    <table id="tb" width="71%" border="0" cellspacing="0" cellpadding="0">  
      <tr id="tb1">
        <td width="51">序号</td>
        <td width="145" >设备名称</td>
        <td width="183">设备编号</td>
        <td width="150">自动上传</td>
        <td width="142">手动上传</td>
      </tr>	  		  
      <logic:notEmpty name="cList">
        <c:forEach var="p" items="${cList}" varStatus="i">
          <tr>
	        <td>${i.count }</td>
	        <td>${p.projectName }</td>
	        <td>${p.projectCode }</td>
			<td>			
			 <div align="left" style="display: inline">
			 <img src="images/piling/autoUpload.gif" width="16 height="16" alt="自动上传" style="cursor: pointer" onclick="javascript: gotoAutoUpload('${p.projectId}','${p.projectCode}')" />			
			 </div>
			</td>
			<td>
			  <div align="left" style="display: inline">			
			  <img src="images/piling/menualUpload.gif" width="16" height="16" alt="手动上传"  style="cursor: pointer" onclick="javascript: gotoManualUpload()" />		
			 </div>
		    </td>
          </tr>
        </c:forEach>                 
      </logic:notEmpty>
      <logic:empty name="cList">
    	<div style="color: red;font-size: 14px" align="center">尚未配置小批零设备</div>
      </logic:empty>
    </table>
 <div id="statusdiv_uploading" style="position:absolute; z-index:-9999px; left:469px; top:258px; width:269px; height:154px; display:none; display: none;">
  <table width=265 height=151 border=0 cellspacing=0 cellpadding=0>
	<tr height=20>
		<td width="112" align=center bgcolor=#CCCCCC>
			<font color=#ffffff>正在上传...</font>		
		</td>
	</tr>
  </table>
 </div> 
 
 <div id="statusdiv_processing" style="position:absolute; z-index:-9999px; left:469px; top:258px; width:269px; height:154px; display:none; display: none;">
  <table width=265 height=151 border=0 cellspacing=0 cellpadding=0>
	<tr height=20>
		<td width="112" align=center bgcolor=#CCCCCC>
			<font color=#ffffff>正在处理...</font>		
		</td>
	</tr>
  </table>  
 </div>  
 </div> 
 
</div>
<div class="clear"></div>
<iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</body>
</html>