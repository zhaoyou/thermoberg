<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小批零工程列表</title>
<link rel="Shortcut Icon" href="img/add/logo.ico" />
<link href="css/piling/piling.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function golist(url,operate){		
		document.getElementById('myform').action = url ;
		document.getElementById('ope').value= operate ;
		document.myform.submit() ;
	}
</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=126 frameborder=0></iframe >
</div>
<form name="myform" id="myform" method="post" action="">
	<input type="hidden" name="branchId" id="branchId" value="${param.branchId }"/>
	<input type="hidden" name="ope" id="ope" value=""/>
</form>
<div id="left">
  <ul id="nav">

    <li class="tab"><a href="#"></a></li>
    <li class="tab_a"> <a href="javascript:golist('branch.do','toMain');"><img src="images/piling/s.gif" width="20" height="22" />首页</a> </li>
    <li class="tab_b"><a href="javascript:golist('pro.do','toRefList');"><img src="images/piling/icon_a.gif" width="22" height="17" />仓库工程</a></li>
    <li class="tab_b"><a href="javascript:golist('pro.do','toCarList')"><img src="images/piling/icon_b.gif" width="18" height="20" />车载工程</a></li>
    <li class="tab_b"><a href="javascript:golist('pro.do', 'toBoxList')"><img src="images/piling/icon.gif" width="19" height="20" />小批零工程</a></li>
    <li class="tab_c"><a href="#">&nbsp;</a></li>

  </ul>
</div>
<div id="line"><img src="images/index/icon2_090.gif" /></div>
<div id="right">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td><table width="100%" align="left" class="datalist">
          <tr>
            <td width="767" height="10" colspan="4" style="float: left;padding-bottom: 4px;padding-top: 5px;" background="images/piling/menu_bga.gif"><div align="right"><img src="images/piling/icon_c.gif" width="16" height="15" / class="pic"><span ><strong>位置:首页&lt;您正在做的业务是：</strong>查看小批零</span></div></td>

          </tr>
        </table></td>
    </tr>
    <tr>
      <td>
      <logic:notEmpty name="boxList">
          <table width="100%" class="datalist">
          <tr class="altrows">
              <td width="444" colspan="4" align="left" style="float: left;padding-bottom: 5px;padding-top: 5px;padding-right: 4px;padding-left: 4px;"><div align="left"><img src="images/piling/bt_detail.gif" style="cursor: pointer;"  
              	onclick="javascript: window.location.href='hisbox.do?ope=toHisBoxStart&branchId=${param.branchId }'"/></div>
                <div align="left"><img src="images/piling/bt_upload.gif" style="cursor: pointer;" 
              	onclick="javascript:golist('pro.do', 'toBoxUploadList')"/></div>
              <%-- TODO (zhaoyou) add other operator button.
              <div align="left"><img src="images/piling/bt_detail.gif" /></div>
              --%>
            </td>     
            </tr>
          <tr>        
              <td colspan="4" class="datalist2"><table width="80%" align="left" class="datalist2">
                <tr>
                  <th width="42" class="datalist4" scope="col">&nbsp;</th>
                  <th width="130" scope="col">设备名称</th>
                  <th width="155" scope="col">设备编号</th>
                  <th width="283" scope="col">备注</th>

                </tr>
               <c:forEach var="p" items="${boxList}" varStatus="i">
	          	<tr>
		            <td>${i.count }</td>
		            <td>${p.projectName }</td>
		            <td>${p.projectCode }</td>
		            <td>${p.projectNote }</td>
	         	 </tr>
	          </c:forEach>
              </table></td>
          </tr>
        </table>
        </logic:notEmpty>
    <logic:empty name="boxList">
    	<div style="color: red;font-size: 14px" align="center">没有配置相应小批零!</div>
    </logic:empty>
        </td>
    </tr>
  </table>

</div>
<div class="clear"></div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=43 frameborder=0></iframe >
</div>
</body>
</html>
