<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>С���������ϴ�����</title>
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
			//����״̬������ʾ
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
			
    //************�豸�Զ��ϴ�***************			

			/**
			*	��ת���Զ��ϴ�
			*/
			function gotoAutoUpload(curProjectId,curDevId){
				var lineStr;
				var isFindDisk=false;				
				
				//���������
				var drv;

			try{						
				if(fso==null){
				fso= new ActiveXObject("Scripting.FileSystemObject");	
				}
				var colDrives = new Enumerator(fso.Drives);
				for(;!colDrives.atEnd();colDrives.moveNext()){
				  drv = colDrives.item();
				   try{
				    if((drv.VolumeName.indexOf("WASON")==0)&&(drv.TotalSize<10000000)&&(drv.IsReady==true)){//����Ƿ����wason�����ľ�꣬���Ҵ��̿ռ�С��10M,����ֻ��
					    diskDriver = drv.Path;  //��¼��������
						isFindDisk=true;        //����ҵ�������
						if(confirm("��⵽С�����豸,�Ƿ��ϴ�����?")==false){
						 return;
						}						
						break;
				    }			
				   }
				   catch(e){  //�������ܻ�ȡ��������������û�в����̵Ĺ�����������������������
				     continue;
				   }
			    }			
			
				  if(isFindDisk==false){  //û���ҵ�С�����豸
				   StatusTip(false,false);							  
				   alert("�޷���⵽С�����豸����ȷ���豸�������ȷ����");
				   return;
				  }				  
			  
			   // ����Ƿ��豸�����ٴ���һ���ļ�
			      var objFolder = fso.GetFolder(diskDriver);
				  var colFiles = new Enumerator(objFolder.Files);
				  var objFileName;				  
				  var isFindFile=false;					  
				  for(;!colFiles.atEnd();colFiles.moveNext()){
						isFindFile=true;        //����ҵ�����һ���ļ�
				        objFileName=colFiles.item().Path;						
						break;
			      }		
						
			      if(isFindFile==false){  //�豸��û��һ����¼�ļ�
				   StatusTip(false,false);						      
				   alert("С�����豸�в��������ݼ�¼");
				   return;
				  }
				  
			   // ��ȡ��һ���ļ��е��豸��ţ��ļ��в������豸��Ż��豸���뱾����վ�б��е��豸��Ų�ͬ
			   
					var objStream = fso.OpenTextFile(objFileName);	
					var devId;
					var errDevice=false;
			        while((lineStr=objStream.ReadLine())!=null)
				    {
				       if(lineStr.indexOf("Serial No.,")>=0){  //����"Serial No"��ʶ
					      devId=lineStr.substring(lineStr.indexOf("Serial No.,")+11);
						 if(devId.indexOf(curDevId)!=-1) {  //�豸����빤������һ��
						   break;	
						 }							
						 else{//ʵ�ʽ�����Ե��豸�������վ�б��е�ѡ���ϴ����豸��Ų�ͬ\
						   StatusTip(false,false);			
						   alert("���ϴ��豸�������õı�Ų�һ�£�������ѡ��")							
						   errDevice=true;
						   break;
						 }
					   }
				    }
					objStream.Close();

				if(errDevice==true)
				   return;
				   
				//��鲢��ȡ��ǰ�豸������ͣ��¼ʱ������
				  var devHistStartUpList = new Array();	  				  
				  for(var i=1;!colFiles.atEnd();colFiles.moveNext()){
				     objFileName=fso.GetBaseName(colFiles.item().Path);										  

					//��������ʱ��
				     var HistStartUpObj={id:i,transBeginTime:objFileName.replace('.',':').replace('.',':'),transEndTime:objFileName.replace('.',':').replace('.',':'),recordInterval:60,updateStatus:2,tuplimit:8,tdwlimit:2}; 
					 devHistStartUpList.push(HistStartUpObj);		 					 				 
					 i=i+1;
				  }
				  
				// ajax���ò�ѯ���豸����Ҫ�ϴ�����ͣ��¼����
					projectId= curProjectId;				
				   getPacketSsRecord(curProjectId,devHistStartUpList);
		   
				//��ʾ���ڴ�������ʾ
				//  StatusTip(true,false);	
				  
			    }				  
			   catch(e){
				     StatusTip(false,false);						   
				     alert("С�����豸��ȡʧ��");
				     return ;
			   }
				  
			}
			
		//�����ݿ����Ѿ��ϴ�����ͣ��¼���бȽϣ�ȷ���豸�������ϴ�����ͣ�����б�(curProjectId:��ʾ��ǰС�����豸���̱��;ssData:��ʾ�豸�����е���ͣ��¼ʱ��)
			function getPacketSsRecord(curProjectId,ssData){	
				hist.getUploadStartupList(curProjectId,ssData,{
				callback:packetSsDataResultHandler,
				errorHandler:ResultError,
				timeout:15000
				}) ;	
			}
			
			//��ȡ��Ҫ�ϴ����豸���ݵĽ������(ssData��ʾ�ӷ������˲�ѯ����Ҫ�ϴ�����ͣ��¼���ݣ�
			function packetSsDataResultHandler(ssData){
				var lineStr,tempStr;		
				var firstDataTime = new Date();
				var secondDataTime = new Date();				
				var updateTime;	
				var id;
				var minValue,maxValue;
				var upLimit,dwLimit;
				var recordInterval=60;   //Ĭ�ϼ�¼���Ϊ60��
				var isAlarm;
				var isFindDisk=false;
				var histDataArray =new Array();  //��ʱ��ʷ�����ļ��е��ݴ�����	
				
			  //��鷵�������е���ͣ�б�
			    if(ssData==null){  //������ֹͣ��¼��Ҫ�ϴ�
				 StatusTip(false,false);						    
			     alert("�豸�е����ݼ�¼����Ҫ�ظ��ϴ�");
				 return;
				}
		
				//������Ҫ�ϴ�����ͣ��¼
				StatusTip(false,true);		//���������ϴ���ʾ

				//���ݷ��ص���Ҫ�ϴ�����ͣ��¼�������豸�е���ͣ��¼�����ϴ�  
				
			  try{			   
			  				  
				  isAlarm=false;				  

			      var objFolder = fso.GetFolder(diskDriver);
				  var colFiles = new Enumerator(objFolder.Files);
				  var devHistStartUpList = new Array();  //������Ҫ�ϴ�����ͣ�ݴ��б�
				  var devHistDataList= new Array();   //������Ҫ�ϴ�����ʷ�����ݴ��б�
				  				  				  
			   // ��ȡ��һ���ļ��е��豸��ţ��ļ��в������豸��Ż��豸���뱾����վ�б��е��豸��Ų�ͬ
			   	  for(id=1;!colFiles.atEnd();colFiles.moveNext()){  //ɨ��ÿһ���ļ�
					  tempStr=fso.GetBaseName(colFiles.item().Path);  //��ȡ�������ļ����ƺ�׺��·�����ļ���				  
					for(var i=0;i<ssData.length;i++){  //�ȽϷ��������ص���Ҫ�ϴ�����ͣ��¼
					
					  var HistStartUpObj={id:ssData[i].id,transBeginTime:ssData[i].transBeginTime,transEndTime:ssData[i].transEndTime,recordInterval:ssData[i].recordInterval,updateStatus:ssData[i].updateStatus,tuplimit:ssData[i].tuplimit,tdwlimit:ssData[i].tdwlimit};
							  
					  if(HistStartUpObj.transBeginTime.indexOf(tempStr.replace('.',':').replace('.',':'))==0)  //����ʱ����ͬ
					  {						
						var objStream = fso.OpenTextFile(colFiles.item().Path,1);	
						var devId;			
						var splitStr;									
						while((lineStr=objStream.ReadLine())!=null)  //��鱾���ļ�
				        {
						  if(lineStr.length<=2){ //��������
						      continue;
						  }
						  
						  if(lineStr.indexOf("Date,Time,T.(C),Note")>=0)  //����"Date,Time,T.(C),Note"��ʶ
						        continue;

						  if(lineStr.indexOf("Model,WS-T10PRO")>=0)  //����"Model,WS-T10PRO"��ʶ
						        continue;

						  if(lineStr.indexOf("Serial No.,")>=0)  //����"Serial No.,"��ʶ
						        continue;
								
				          if(lineStr.indexOf("Alarm Max.,,")==0){  //��������
					            upLimit=parseFloat(lineStr.substring(lineStr.indexOf("Alarm Max.,,")+12));
						        continue;
						  }
						  
				          if(lineStr.indexOf("Alarm Min.,,")==0){  //��������
								dwLimit=parseFloat(lineStr.substring(lineStr.indexOf("Alarm Min.,,")+12));
						        continue;						  
						  }

						  if(lineStr.indexOf("Max.,,")==0){  //���ֵ
								maxValue=parseFloat(lineStr.substring(lineStr.indexOf("Max.,,")+6));
								if(maxValue>upLimit){  //���߱���
									isAlarm=true;
								}
						        continue;						  
						  }
						  		  
						  if(lineStr.indexOf("Min.,,")==0){  //��Сֵ
								minValue=parseFloat(lineStr.substring(lineStr.indexOf("Min.,,")+6));
								if(minValue<dwLimit){  //���ͱ���
									isAlarm=true;
								}						  
						        break;			//������ȡ�ļ����һ�е����׳��쳣�����
						  }
						  
						if(lineStr.length>=18){ //�ݴ���ʷ���ݵ���ʱ��������
						    if(lineStr.indexOf(",A")!=-1){ //������ʷ��¼�а���������ʶ
						      lineStr=lineStr.substring(0,lineStr.length-2);  //���˵���ʾ�����ı�ʶ
						    }
							histDataArray.push(lineStr);
						    continue;	     	  			
				         }					  
						}
									
						objStream.Close();										
												  						
						for(var i=0;i<histDataArray.length;i++){  //�����ݴ����ʷ���ݼ�¼
							 lineStr= histDataArray[i];
									 
							 if(i==0){  //��¼��һ����¼��ʱ��
							   tempStr= lineStr.substring(0,lineStr.indexOf(","));  //��ȡ������
							   splitStr= tempStr.split("-");
							   firstDataTime.setFullYear(splitStr[0],splitStr[1]-1,splitStr[2]);
							   tempStr= lineStr.substring(lineStr.indexOf(",")+1,lineStr.lastIndexOf(",")); //��ȡʱ����
							   splitStr= tempStr.split(":");
							   firstDataTime.setHours(splitStr[0]);
							   firstDataTime.setMinutes(splitStr[1]);
							   firstDataTime.setSeconds(splitStr[2]);							   							   
						    }
	
						    if(i==1){  //����ڶ�����¼���һ��֮���ʱ����
							   tempStr= lineStr.substring(0,lineStr.indexOf(","));  //��ȡ������
							   splitStr= tempStr.split("-");
							   secondDataTime.setFullYear(splitStr[0],splitStr[1]-1,splitStr[2]);
							   tempStr= lineStr.substring(lineStr.indexOf(",")+1,lineStr.lastIndexOf(",")); //��ȡʱ����
							   splitStr= tempStr.split(":");
							   secondDataTime.setHours(splitStr[0]);
							   secondDataTime.setMinutes(splitStr[1]);
							   secondDataTime.setSeconds(splitStr[2]);															
							   recordInterval=(secondDataTime.getTime()-firstDataTime.getTime())/1000 ;  //��ȡ����Ϊ��λ��ʱ����			   						  
						    }

						  //��¼���һ����¼ʱ����Ϊֹͣʱ��				  
						  tempStr= lineStr.substring(0,lineStr.indexOf(","));  //��ȡ������
						  splitStr= tempStr.split("-");
						  if(splitStr[1].length==1){ //�·ݴ���
						     splitStr[1]="0"+splitStr[1];
						  }
						  if(splitStr[2].length==1){  
						     splitStr[2]="0"+splitStr[2];
						  }						  
						  updateTime=splitStr[0]+ "-" +splitStr[1]+ "-" +splitStr[2];
							   
						  tempStr= lineStr.substring(lineStr.indexOf(",")+1,lineStr.lastIndexOf(",")); //��ȡʱ����
						  splitStr= tempStr.split(":");   
						  if(splitStr[0].length==1){ //Сʱ����
						     splitStr[0]="0"+splitStr[0];
						  }
						  if(splitStr[1].length==1){ //���Ӵ���
						     splitStr[1]="0"+splitStr[1];
						  }									  
						  if(splitStr[2].length==1){ //���Ӵ���
						     splitStr[2]="0"+splitStr[2];
						  }			
			  					  					   							   
						  updateTime=updateTime+ " " +splitStr[0]+ ":" +splitStr[1]+ ":" +splitStr[2];
						  
						  //�����Ƿ�����ʷ��¼�����޻򳬵��ޱ���
						 var AlarmStatus;
						 var aiValue;
						 
						 //��ȡAIֵ
						  aiValue = parseFloat(lineStr.substring(lineStr.lastIndexOf(",")+1));
						 							  
						  if((aiValue>=upLimit)||(aiValue<=dwLimit)){  //�����޻򳬵��ޱ���
						    AlarmStatus=1;
						  }
						  else{  //�ޱ���
						    AlarmStatus=2; 			  						  
						  }

						 var hisBoxDataObj= {parentId:id,ai1:aiValue,alarmStatus:AlarmStatus,transUpdateTime:updateTime};
						  devHistDataList.push(hisBoxDataObj);						  	  			
						}				         
	
						histDataArray.splice(0,histDataArray.length); 	//��������е���ʷ����
													
					    //������ͣ��¼
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
				     alert("С�����豸�����ϴ�ʧ��");
				     return ;
			   }
			   
			     if((devHistDataList.length==0)&&(devHistStartUpList==0)){ //��ͣ��¼����ʷ��¼����������Ҫ�ϴ�
				   StatusTip(false,false);				       			     
			       alert("С�����豸�е������Ѿ��ϴ���");
			       return ;
			     }
				  
				 //���ú�̨�ӿڽ���ͣ��¼����ʷ���ݼ�¼���µ����ݿ�				 
				 UploadPacketHistData(projectId,devHistStartUpList,devHistDataList);

			}					
								
			//�ϴ���ͣ��¼����ʷ����(data��ʾ��Ҫ�ϴ�����ͣ��¼����ʷ����)
			function UploadPacketHistData(projectId,devHistStartUpList,devHistDataList){
				
				hist.uploadPacketHistData(projectId,devHistStartUpList,devHistDataList,{
				callback:uploadPacketResultHandler,
				errorHandler:ResultError,
				timeout:15000
				}) ;	
			}	
			
			//�ϴ���ͣ����ʷ���ݷ��ؽ���������
			function uploadPacketResultHandler(data){
			
				StatusTip(false,false);		
				
			  //��鷵�������е���ͣ�б�
			    if(data==0){  //�ϴ��ɹ�
				 StatusTip(false,false);						    
			     alert("�ϴ��ɹ�");
				}				
				else{
				 StatusTip(false,false);				   
				alert("�ϴ�ʧ��");
				}							
			}
			
			//�ֶ��ϴ�
			function gotoManualUpload(){
			  alert("�ֶ��ϴ��ݲ�֧��");			
			}			
//*********�ϴ����̴�����***************			

			//��ȡ���ֹͣʱ��Ĵ�����
			function ResultError(message){			
				StatusTip(false,false);				
				alert("���緱æ�����Ժ�����");
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
  <div id="top" ><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15" / class="tb4"><strong>λ��:��ҳ</strong>&lt;�����ڲ鿴С���������ϴ�����</a><img src="img/util/back.gif" style="cursor: pointer; margin-left:680px;" onclick="javascript:golist('pro.do','toBoxList');" width="48" height="20" / class="tb3"></div> 
  <div id="bottom">
    <table id="tb" width="71%" border="0" cellspacing="0" cellpadding="0">  
      <tr id="tb1">
        <td width="51">���</td>
        <td width="145" >�豸����</td>
        <td width="183">�豸���</td>
        <td width="150">�Զ��ϴ�</td>
        <td width="142">�ֶ��ϴ�</td>
      </tr>	  		  
      <logic:notEmpty name="cList">
        <c:forEach var="p" items="${cList}" varStatus="i">
          <tr>
	        <td>${i.count }</td>
	        <td>${p.projectName }</td>
	        <td>${p.projectCode }</td>
			<td>			
			 <div align="left" style="display: inline">
			 <img src="images/piling/autoUpload.gif" width="16 height="16" alt="�Զ��ϴ�" style="cursor: pointer" onclick="javascript: gotoAutoUpload('${p.projectId}','${p.projectCode}')" />			
			 </div>
			</td>
			<td>
			  <div align="left" style="display: inline">			
			  <img src="images/piling/menualUpload.gif" width="16" height="16" alt="�ֶ��ϴ�"  style="cursor: pointer" onclick="javascript: gotoManualUpload()" />		
			 </div>
		    </td>
          </tr>
        </c:forEach>                 
      </logic:notEmpty>
      <logic:empty name="cList">
    	<div style="color: red;font-size: 14px" align="center">��δ����С�����豸</div>
      </logic:empty>
    </table>
 <div id="statusdiv_uploading" style="position:absolute; z-index:-9999px; left:469px; top:258px; width:269px; height:154px; display:none; display: none;">
  <table width=265 height=151 border=0 cellspacing=0 cellpadding=0>
	<tr height=20>
		<td width="112" align=center bgcolor=#CCCCCC>
			<font color=#ffffff>�����ϴ�...</font>		
		</td>
	</tr>
  </table>
 </div> 
 
 <div id="statusdiv_processing" style="position:absolute; z-index:-9999px; left:469px; top:258px; width:269px; height:154px; display:none; display: none;">
  <table width=265 height=151 border=0 cellspacing=0 cellpadding=0>
	<tr height=20>
		<td width="112" align=center bgcolor=#CCCCCC>
			<font color=#ffffff>���ڴ���...</font>		
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