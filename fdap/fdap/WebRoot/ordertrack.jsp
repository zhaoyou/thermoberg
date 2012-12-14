<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品信息</title>
<link href="css/order/ordertrack.less" rel="stylesheet" type="text/less"/>
<%@ include file="layout/asset.html" %>
<script src="DatePicker/WdatePicker.js"></script>
<script src="js/front/ordertrack.js"></script>
</head>
<body onload="ordertrack.init()">
<%@ include file="layout/header.html" %>
<ul class="breadcrumb">
  <li><a href="#">首页</a> <span class="divider">/</span></li>
  <li class="active">${org.name }药品订单信息查询</li>
</ul>
<form action="#" name="myform" id="myform" method="get">
<input type="hidden" name="ope"  id="ope"  value="toHigherOrg"/>
<input type="hidden" name="oid"  id="oid" value="${org.oid }"/>
<input type="hidden" name="ids" id="ids" value="${ids }"/>
<input type="hidden" name="orgName_statistics" id="orgName_statistics" value="${orgName }" />
<div class="query_div">
    	<table >
    		<tr border="0">
    			<td class="q_td_first">
    				 开始时间
    			</td>
    			<td>
    			<input  name="startTime" id="startTime" value="${param.startTime }"  class="Wdate input-medium" type="text" size="21"
						 onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:00'})"/>
    			</td>
    			<td class="q_td">
    				结束时间
    			</td>
    			<td>
	    			<input  name="endTime" id="endTime" value="${param.endTime }"  class="Wdate input-medium" type="text" size="21"
						 onClick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:00'})"/>
    			</td>
    			<td class="q_td">
    				订单编号
    			</td>
    			<td>
    				<input id="orderNo" type="text" name="orderNo" class="input-medium" value="${param.orderNo }"/>
    			</td>
    			</tr>
    		<tr>
    			<td class="q_td_first">
    				收货单位
    			</td>
    			<td>
    				<select id="receiver" name="receiver">
    					<option value="-1">全部</option>
	    				<c:forEach var="r" items="${receiverList}">
	    					<option value="${r.rid }" ${param.receiver == r.rid ? "selected" : "" }> ${r.shortName }</option>
	    				</c:forEach> 
	    			</select>
    			</td>
    			<td class="q_td">
    			  生产商
    			</td>
    			<td>
    				<select id="prodArea" name="prodArea">
    					<option value="-1">全部</option>
	    				<c:forEach var="a" items="${areaList}">
	    					<option value="${a }" ${param.prodArea == a ? "selected" : "" }> ${a}</option>
	    				</c:forEach> 
	    			</select>
    			</td>
    			<td class="q_td">
    				生产批号
    			</td>
    			<td>
    				<input id="lotno" type="text" name="lotno" class="input-medium"/>
    			</td>
    		</tr>
    		<tr>
    			<td class="q_td_first">品名
    			</td>
    			<td>
    				<select id="goodsName" name="goodsName">
    					<option value="-1">全部</option>
	    				<c:forEach var="good" items="${goodslist}">
	    					<option value="${good.goodsName }" ${param.goodsName == good.goodsName ? "selected" : ""  }> ${good.goodsName}</option>
	    				</c:forEach> 
	    			</select>
    			</td>
    			<td class="q_td">
    				规格
    			</td>
    			<td>
    				<select id="goodsType" name="goodsType">
    					<option value="-1">全部</option>
	    				<c:forEach var="g" items="${goodTypeList}">
	    					<option value="${g }" ${param.goodsType == g ? "selected" : "" }> ${g}</option>
	    				</c:forEach> 
	    			</select>
    			</td>
    			<td>	
    			</td>
    			<td>
    				<input type="button" class="btn btn-primary" value="查 询"/>
    				<input type="button" class="btn" value="返 回"
    						onclick="javascript:window.location.href='org.do?ope=toHigherOrg&oid=${org.oid }'" />
    			</td>
    		</tr>
    	</table>
</div>
    <div class="container-fluid">
  		<div class="row-fluid">
    		<div class="span2">
    			<logic:empty name="orderList">
    			  <logic:empty name="query">
    			    请先查询订单！
    			  </logic:empty>
    			  <logic:notEmpty name="query">
    			    没有查询到订单！
    			  </logic:notEmpty>
    			</logic:empty>
    			<logic:notEmpty name="orderList">
    				<c:forEach var="orderView" items="${orderList}">
    					<ul class="nav nav-list">
    					<li class="nav-header">${orderView.receiver }</li>
	    					<c:forEach var="view" items="${orderView.list}">
	    						 <li><a href="javascript:ordertrack.getOrderDetail('${view.oid }')">${ view.orderNo}</a></li>
	    					</c:forEach>
    					</ul>
    				</c:forEach>
    				
						 
						
    			</logic:notEmpty>
    		</div>
    	  <div class="span10">
          <table class="table table-striped">
        	<tr>
        	  <td>子订单名称</td>
	        	<td>品名</td>
	        	<td>总数量</td>
	        	<td>整件数</td>
	        	<td>整件单位数</td>
	        	<td>散件件数</td>
	        	<td>散件药品总数</td>
	        	<td>退换货</td>
	        	<td>订单状态</td>
        	</tr>
        	<tbody>
        		<c:forEach var="d" items="${detailList}">
        			<tr >
		        	<td> <a target="_blank" href="order.do?ope=toOrdertbcc&oid=${param.oid }&orderId=${d.msomt.orderId }&kid=${d.kid }&subOrderMid=${d.msomt.subOrderMid }">${d.msomt.subOrderName }</a></td>
		        	<td>${d.megfi.baseInfo.goodsName }</td>
		        	<td>${d.totalNum }</td>
		        	<td>${d.wholePacketNum }</td>
		        	<td>${d.wholePacketUnitNum }</td>
		        	<td>${d.loosePacketNum }</td>
		        	<td>${d.loosePacketTotalNum }</td>
		        	<td>
		        	  <c:if test="${d.msomt.inoutType == 0}">
		        	  	正常发货
		        	  </c:if>
		        	  <c:if test="${d.msomt.inoutType == 1}">
		        	  	退货
		        	  </c:if>
		        	  <c:if test="${d.msomt.inoutType == 2}">
		        	  	换货换入
		        	  </c:if>
		        	  <c:if test="${d.msomt.inoutType == 3}">
		        	  	换货换出
		        	  </c:if>     	
		        	</td>
		        	<td>
		        	  <c:if test="${d.msomt.orderStatus == 1}">
		        	  	尚未包装
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 2}">
		        	  	正在包装
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 3}">
		        	  	包装完毕
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 4}">
		        	  	尚未装车
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 5}">
		        	  	正在装车
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 6}">
		        	  	装车完毕
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus ==7}">
		        	  	正在卸车
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 8}">
		        	  	卸车完毕
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 9}">
		        	  	尚未入库
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 10}">
		        	  	正在入库
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 11}">
		        	  	入库完毕
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 12}">
		        	  	正在出库
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 13}">
		        	  	出库完毕
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 14}">
		        	  	尚未装箱
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 15}">
		        	  	正在装箱
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 16}">
		        	  	装箱完毕
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 17}">
		        	  	正在拆箱
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 18}">
		        	  	拆箱完毕
		        	  </c:if>
		        	  <c:if test="${d.msomt.orderStatus == 19}">
		        	  	运单完成
		        	  </c:if>
		        	</td>
		         </tr>
        		</c:forEach>	
        	</tbody>
        </table>
    	  </div>
  	  </div>
    </div>
</form>
<div class="split"></div>
<%@include file="layout/footer.html" %>
</body>
</html>
