<%@ page language="java"  pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�鿴�ƶ����ص���ͣ��¼</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/chezai/gpslog1.css" rel="stylesheet" type="text/css" />
<link href="css/chezai/gpslog2.css" rel="stylesheet" type="text/css" />
<script src="DatePicker/WdatePicker.js">
</script>
<style type="text/css">
  #query {
  	margin-top: 10px;
		padding-bottom: 20px;
		border-bottom: 1px dotted #CCC;
		text-align: center;
  }
	#querybtn {
		height: 30px;
		width: 100px;
		margin-left: 30px;
	}
	
	#result {
		padding-left: 273px;
		padding-top: 10px;
	}
	
	#head_tr {
		height: 20px;
		background-color: #E4F0FC;
	}
	#mytable {
		width: 440px;
	}
	
	#statusDiv {
		margin-bottom: 40px;
	  text-align: center;
		width: 400px;
	}
	
	#noResult {
		background-color: #E4F0FC;
		margin-bottom: 2px;		
	}
	
	
</style>
<script type="text/javascript">
	function query() {
		var s = document.getElementById('start').value;
		var e = document.getElementById('end').value;
		if (s === '' || e === '') {
			alert('��������ʼ���ڲ�ѯ ��');
			reutrn;
		}
		var branchId = document.getElementById('branchId').value
		window.location.href = "gpslog.do?ope=toLog&branchId=" + branchId + "&start=" + s + "&end=" + e;
	}
	
	function goBack() {
		var branchId = document.getElementById('branchId').value;
		window.location.href = "pro.do?ope=toCarList&branchId=" + branchId;
	}
</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<input name="branchId" id="branchId" type="hidden" value="${param.branchId}"/>
<div id="right">
  <div id="top"><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15"  class="tb4"/><strong>λ��: </strong>�����ڲ鿴����GPS����</a>
  <img src="images/chezai/back.gif" width="48" height="20"  class="tb3"/>
  <img src="img/util/back.gif" width="48" height="20"  class="tb3" style="cursor: pointer;" onclick="goBack()"/>
  </div>
<div id="gpsContainer">
  <div id="main">
  	<div id="query">
  		<input  name="start" id="start" value="${param.start}"  class="Wdate" type="text" height="25"
										 onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00'})"/>	 <b> �� </b>
  		<input  name="end" id="end" value="${param.end}"  class="Wdate" type="text" height="25"
										 onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00'})"/>
			<input id="querybtn" type="button" value="�� ѯ" onclick="query()"/>
  	</div>
  	<div id="result">
  		<table id="mytable">
  				
  			<tbody>
  			  <tr id="head_tr"><th>ʱ��</th><th>���ƺ���</th><th>״̬</th></tr>
  			  <c:forEach var="log" items="${list}">
  			     <tr>
  			     <td>${log.recordTime }</td>
  			     <td>${log.carNO }</td>
  			     <td>${log.returnFlag == 1 ? "�ɹ�" : "ʧ��" }</td>
  			     </tr>
  			  </c:forEach>
  			</tbody>
  		</table>
  		<div id="statusDiv">
  			<logic:empty name="list">
  				<c:if test="${first != true}">
  				  <div id="noResult">û�������ˣ�</div>
  				</c:if>
  			</logic:empty>	
  		</div>
  		
  	</div>
  </div>
</div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
