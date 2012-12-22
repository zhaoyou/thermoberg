<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品轨迹信息</title>
<link href="css/order/ordertrack_ref.less" rel="stylesheet" type="text/less"/>
<%@ include file="layout/asset.html" %>
<script type="text/javascript">
	function query(count) {
		var oid = document.getElementById('oid').value;
		var refId = document.getElementById('refId').value;
		var start = document.getElementById('start').value;
		var end = document.getElementById('end').value;
		var page = document.getElementById('page').value;
		var sid = document.getElementById('sid').value;
		var carname = document.getElementById('carname').value;	
		var allPage = document.getElementById('carpagecount').value;
		window.location.href='order.do?ope=doCarHisPage&oid=' + oid + '&refId=' +
		 refId + '&start=' + start + '&end=' + end + '&page=' +
		 count + '&carpagecount=' + allPage + '&sid=' + sid + '&carname=' + carname ;
	}
</script>
</head>
<body>
<%@ include file="layout/header.html" %>
<ul class="breadcrumb">
  <li><a href="#">首页</a> <span class="divider">/</span></li>
  <li><a href="#">${carname } 冷链环境追踪</a><span class="divider">/</span></li>
</ul>
<form action="org.do" name="myform" id="myform" method="post">
	<input type="hidden" name="oid"	id="oid" value="${param.oid }"/>
    	
    <input type="hidden" name="refId" id="refId" value="${param.refId }"/>
    <input type="hidden" name="start" id="start" value="${param.start }"/>
    <input type="hidden" name="end" id="end" value="${param.end }"/>
    <Input type="hidden" name="sid" id = "sid" value="${sid }"/>
    <Input type="hidden" name="carname" id = "carname" value="${carname }"/>	
    <input type="hidden" id="page" name="page" value="${page }"/>
    <input type="hidden" id="carpagecount" name="carpagecount" value="${carpagecount }"/>
</form>
<div class="query_div">
	<table>
		<tr>
			<td>开始时间</td>
			<td><input class="input-time" type="text" disabled="disabled" value="${param.start }"></td>
			<td>结束时间</td>
			<td><input class="input-time" type="text" disabled="disabled" value="${param.end}"></td>
			<td>&nbsp;</td>  
		</tr>
	</table>
</div>
    <div id="right_content">
        <table id="r_table" class="table table-striped">
        	<tr id="r_table_tr">
	        	<td>时间</td>
	        	<td>T1</td>
	        	<td>T2</td>
	        	<td>T2</td>
	        	<td>报警状态</td>
        	</tr>
        	
        	<c:forEach var="hiscar" items="${hiscarList}" varStatus="irow">	
					<tr>
						<td>
							<bean:write name="hiscar" property="time" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td align="center" nowrap>	
							${hiscar.t1==-300?"--":hiscar.t1 }
						</td>
						
						<td align="center" nowrap="nowrap">
							${hiscar.t2==-300?"--":hiscar.t2 }
						</td>
						
						<td align="center"  nowrap="nowrap">
							${hiscar.t3==-300?"--":hiscar.t3 }
						</td>
						<td align="center"  nowrap="nowrap">
						 	<c:if test="${hiscar.isAlarm==0}">
						 	<img src="images/index/29.gif" title='表示处于报警状态' width="16" height="10"/>
							 </c:if>
							 <c:if test="${hiscar.isAlarm==1}">
							 	<img src="images/index/28.gif" title='表示处于正常状态' width="16" height="10"/>
							 </c:if>
						</td>
					</tr>
					</c:forEach> 	
        </table>
    </div>
    
    <div class="pagination pagination-large">
		  <ul>
		  	<c:if test="${page==1||page==null }">
		    	<li class="disabled"><a href="#">首页</a></li>
		    </c:if>
		    <c:if test="${page!=1&&page!=null }">
		    	<li ><a href="javascript: query('1');">首页</a></li>
		    </c:if>
		    <c:if test="${page==1||page==null }">
		    	<li class="disabled"><a href="#">上一页</a></li>
		    </c:if>
		    <c:if test="${page!=1&&page!=null }">
		    	<li ><a href="javascript: query('${page - 1 }');">上一页</a></li>
		    </c:if>
		    <c:if test="${page==carpagecount }">
		    	<li class="disabled"><a href="#">下一页</a></li>
		    </c:if>
		    <c:if test="${page!=carpagecount }">
		    	<li ><a href="javascript:query('${page + 1 }');">下一页</a></li>
		    </c:if>
		    <c:if test="${page==carpagecount }">
		    	<li class="disabled" ><a href="#">末页</a></li>
		    </c:if>
		    <c:if test="${page!=carpagecount }">
		    	<li ><a href="javascript:query('${ carpagecount}');">末页</a></li>
		    </c:if>
		  </ul>
</div>

<%@include file="layout/footer.html" %>
</body>
</html>
