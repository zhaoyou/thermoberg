<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看订单信息</title>
<link href="css/index/his_data.css" rel="stylesheet" type="text/css" />

	
	<script type="text/javascript" src="dwr/interface/realcar.js"></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
	<script src="DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript" language="javascript">
	</script>

<link rel="Shortcut Icon" href="images/logo.ico" />
</head>
<body>
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>


<div id="content">
  <div id="center" style="height:660px">
  	<div class="top">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：查看企业药品信息</span>
          <a href="javascript:goData('org.do','toHigherOrg');">
          <img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a></td>
        </tr>
      </table>
    </div>
    <div class="bottom">
    <form action="" name="myform" id="myform" method="post">
		<input type="hidden" name="ope" id="ope" value=""/> 
		<input type="hidden" name="oid"	id="oid" value="${param.oid }"/>
    	
    	<input type="hidden" name="checkedprojectid" id="checkedprojectid" value="${checkedprojectid }"/>
    	<input type="hidden" name="checkedrefId" id="checkedrefId" value="${checkedrefId }"/>
    	<input type="hidden" name="checkedrefstartTime" id="checkedrefstartTime" value="${checkedrefstartTime }"/>
    	<input type="hidden" name="checkedrefendTime" id="checkedrefendTime" value="${checkedrefendTime }"/>
    	
    	<input type="hidden" id="refpage" name="refpage" value="${refpage }"/>
    	<input type="hidden" id="refpagecount" name="refpagecount" value="${refpagecount }"/>
      
      </form>
      
      <div align="center"><span id="msg">${msg }</span></div>
    </div>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
  </div>
</div>

<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
