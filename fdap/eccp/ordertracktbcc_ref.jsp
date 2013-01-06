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
		var refId = document.getElementById('checkedrefId').value;
		var start = document.getElementById('checkedrefstartTime').value;
		var end = document.getElementById('checkedrefendTime').value;
		var page = document.getElementById('refpage').value;
		var allPage = document.getElementById('refpagecount').value;
		window.location.href='order.do?ope=doOrdertbccRef&oid=' + oid + '&checkedrefId=' +
		 refId + '&checkedrefstartTime=' + start + '&checkedrefendTime=' + end + '&refpage=' +
		 count + '&refpagecount=' + allPage ;
	}
</script>
</head>
<body>
<%@ include file="layout/header.html" %>
<ul class="breadcrumb">
  <li><a href="#">首页</a> <span class="divider">/</span></li>
  <li><a href="#">${orgname }</a><span class="divider">/</span></li>
  <li>${ref.name } 冷链环境追踪</li>
</ul>
<form action="org.do" name="myform" id="myform" method="post">
	<input type="hidden" name="oid"	id="oid" value="${param.oid }"/>
    	
    <input type="hidden" name="checkedrefId" id="checkedrefId" value="${checkedrefId }"/>
    <input type="hidden" name="checkedrefstartTime" id="checkedrefstartTime" value="${checkedrefstartTime }"/>
    <input type="hidden" name="checkedrefendTime" id="checkedrefendTime" value="${checkedrefendTime }"/>
    	
    <input type="hidden" id="refpage" name="refpage" value="${refpage }"/>
    <input type="hidden" id="refpagecount" name="refpagecount" value="${refpagecount }"/>
</form>
<div class="query_div">
	<table>
		<tr>
			<td>开始时间</td>
			<td><input class="input-time" type="text" disabled="disabled" value="${checkedrefstartTime }"></td>
			<td>结束时间</td>
			<td><input class="input-time" type="text" disabled="disabled" value="${checkedrefendTime}"></td>
			<td>&nbsp;</td>  
		</tr>
	</table>
</div>
    <div id="right_content">
        <table id="r_table" class="table table-striped">
        	<tr id="r_table_tr">
	        	<td>时间</td>
	        	 <c:forEach var="aiinfo" items="${refAiList}">
	        	 	<td>${aiinfo.name }</td>
	        	 </c:forEach>
	        	<td>报警状态</td>
        	</tr>
        	
        	<c:forEach var="hisref" items="${refhisList}" varStatus="irow">	
					<tr ${irow.count%2==0?"bgcolor='#f1f4f8'":"" }>
						<td align="center"  nowrap="nowrap">
						 	${hisref[fn:length(refAiList)]}
						</td>
						<c:forEach begin="1" end="${fn:length(refAiList) }" var="rrow">
						<td align="center" nowrap="nowrap">	
								${hisref[rrow-1]==-300?"--":hisref[rrow-1] }
						</td>
						</c:forEach>
						 <td align="center"  nowrap="nowrap">
						 	<c:if test="${hisref[fn:length(refAiList)+1]==0 }">
						 		<img src="images/index/29.gif" title='表示处于报警状态' width="16" height="10"/>
						 	</c:if>
						 	<c:if test="${hisref[fn:length(refAiList)+1]==1 }">
						 		<img src="images/index/28.gif" title='表示处于正常状态' width="16" height="10"/>
						 	</c:if>
						 </td>
					</tr>
					</c:forEach> 	
        </table>
    </div>
    
    <div class="pagination pagination-large">
		  <ul>
		  	<c:if test="${refpage==1||refpage==null }">
		    	<li class="disabled"><a href="#">首页</a></li>
		    </c:if>
		    <c:if test="${refpage!=1&&refpage!=null }">
		    	<li ><a href="javascript: query('1');">首页</a></li>
		    </c:if>
		    <c:if test="${refpage==1||refpage==null }">
		    	<li class="disabled"><a href="#">上一页</a></li>
		    </c:if>
		    <c:if test="${refpage!=1&&refpage!=null }">
		    	<li ><a href="javascript: query('${refpage - 1 }');">上一页</a></li>
		    </c:if>
		    <c:if test="${refpage==refpagecount }">
		    	<li class="disabled"><a href="#">下一页</a></li>
		    </c:if>
		    <c:if test="${refpage!=refpagecount }">
		    	<li ><a href="javascript:query('${refpage + 1 }');">下一页</a></li>
		    </c:if>
		    <c:if test="${refpage==refpagecount }">
		    	<li class="disabled" ><a href="#">末页</a></li>
		    </c:if>
		    <c:if test="${refpage!=refpagecount }">
		    	<li ><a href="javascript:query('${ refpagecount}');">末页</a></li>
		    </c:if>
		  </ul>
</div>

<%@include file="layout/footer.html" %>
</body>
</html>
