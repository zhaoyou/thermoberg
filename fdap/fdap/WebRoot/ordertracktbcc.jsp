<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品轨迹信息</title>
<link href="css/order/ordertracktbcc.less" rel="stylesheet" type="text/less"/>
<%@ include file="layout/asset.html" %>
</head>
<body>
<%@ include file="layout/header.html" %>
<ul class="breadcrumb">
  <li><a href="#">首页</a> <span class="divider">/</span></li>
  <li><a href="#">${orderName }</a><span class="divider">/</span></li>
  <li>${goodsName } 药品信息追踪</li>
</ul>
<form action="org.do" name="myform" id="myform" method="post">
<input type="hidden" name="ope"  id="ope"  value="toHigherOrg"/>
<input  type="hidden" name="oid"  id="oid" value="${oid }"/>
<input type="hidden" name="ids" id="ids" value="${ids }"/>
<input type="hidden" name="orgName_statistics" id="orgName_statistics" value="${orgName }" />
</form>
<div>
        <table class="table table-striped" style="text-align: center;">
        	<tr id="r_table_tr">
	        	<td>品名</td>
	        	<td>入时</td>
	        	<td>出时</td>
	        	<td>存储环境</td>
	        	<td>存储类型</td>
	        	<td>存储载体</td>
	        	<td>退换货</td>
	        	<%-- 
	        	<td>报警状态</td>
	        	<td>平均温度</td>
	        	<td>平均湿度</td>
	        	--%>
        	</tr>
        	<c:forEach var="t" items="${tracklist}">
        		<tr>
		        	<td>${goodsName }</td>
		        	<td>${t.inTimeStr }</td>
		        	<td>${t.outTimeStr }</td>
		        	<td>${t.storeEnv }</td>
		        	<td>${t.storeType }</td>
		        	<td><a href="order.do?ope=toOrdertbccRef&oid=${param.oid }&start=${t.inTimeStr }&end=${t.outTimeStr }&refId=${t.storeId }">${t.storeName }</a></td>
		        	<td>
						<c:if test="${t.inoutType == 0}">
							正常
						</c:if>
						<c:if test="${t.inoutType == 1}">
							退货
						</c:if>
						<c:if test="${t.inoutType == 2}">
							换货换入
						</c:if>
						<c:if test="${t.inoutType == 3}">
							换货换出
						</c:if>
					</td>
        		</tr>
        	</c:forEach>
        	
        	<c:forEach var="r" items="${refList}">
        		<tr>
		        	<td>${goodsName }</td>
		        	<td>${r.inTimeStr }</td>
		        	<td>${r.outTimeStr }</td>
		        	<td>${r.storeEnv }</td>
		        	<td>${r.storeType }</td>
		        	<td><a  href="order.do?ope=toOrdertbccRef&oid=${param.oid }&start=${r.inTimeStr }&end=${r.outTimeStr }&refId=${r.storeId }">${r.storeName }</a></td>
		        	<td>
						<c:if test="${subOrder.inoutType == 0}">
							正常
						</c:if>
						<c:if test="${subOrder.inoutType == 1}">
							退货
						</c:if>
						<c:if test="${subOrder.inoutType == 2}">
							换货换入
						</c:if>
						<c:if test="${subOrder.inoutType == 3}">
							换货换出
						</c:if>
					</td>
        		</tr>
        	</c:forEach>     
        	
        	
        	<c:forEach var="c" items="${carList}">
        		<tr>
		        	<td>${goodsName }</td>
		        	<td>${c.inTimeStr }</td>
		        	<td>${c.outTimeStr }</td>
		        	<td>${c.storeEnv }</td>
		        	<td>${c.storeType }</td>
		        	<td><a  href="order.do?ope=toOrdertbccRef&oid=${param.oid }&start=${c.inTimeStr }&end=${c.outTimeStr }&refId=${c.storeId }">${c.storeName }</a></td>
		        	<td>
						<c:if test="${subOrder.inoutType == 0}">
							正常
						</c:if>
						<c:if test="${subOrder.inoutType == 1}">
							退货
						</c:if>
						<c:if test="${subOrder.inoutType == 2}">
							换货换入
						</c:if>
						<c:if test="${subOrder.inoutType == 3}">
							换货换出
						</c:if>
					</td>
        		</tr>
        	</c:forEach>     
        	
        </table>
</div>
<div class="split"></div>
<%@include file="layout/footer.html" %>
</body>
</html>
