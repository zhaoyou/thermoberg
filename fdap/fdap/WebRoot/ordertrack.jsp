<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品信息</title>
<link href="css/order/ordertrack.less" rel="stylesheet" type="text/less"/>
<%@ include file="layout/asset.html" %>
</head>
<body>
<%@ include file="layout/header.html" %>
<ul class="breadcrumb">
  <li><a href="#">首页</a> <span class="divider">/</span></li>
  <li class="active">A公司药品订单信息查询</li>
</ul>
<form action="org.do" name="myform" id="myform" method="post">
<input type="hidden" name="ope"  id="ope"  value="toHigherOrg"/>
<input  type="hidden" name="oid"  id="oid" value="${oid }"/>
<input type="hidden" name="ids" id="ids" value="${ids }"/>
<input type="hidden" name="orgName_statistics" id="orgName_statistics" value="${orgName }" />
</form>
<div class="query_div">
    	<table >
    		<tr border="0">
    			<td>
    				 订单开始时间
    			</td>
    			<td>
    			 <select>
	    			  <option>2012-07-11</option><option>2012-07-12</option>
	    			</select>
    			</td>
    			<td class="q_td">
    				订单结束时间
    			</td>
    			<td>
	    			<select>
	    			  <option>2012-07-11</option><option>2012-07-12</option>
	    			</select>	
    			</td>
    			<td class="q_td">
    				订单编号
    			</td>
    			<td>
    				<input type="date" />
    			</td>
    			</tr>
    		<tr>
    			<td>
    				收货单位
    			</td>
    			<td>
    				<select>
	    			  <option>上海儿童医学中心</option><option>上海东方医院</option>
	    			</select>
    			</td>
    			<td class="q_td">
    			  生产商
    			</td>
    			<td>
    				<select>
	    			  <option>西安杨森</option><option>华东宁波</option>
	    			</select>
    			</td>
    			<td class="q_td">
    				生产批号
    			</td>
    			<td>
    				<input type="text" placeholder="Number"/>
    			</td>
    		</tr>
    		<tr>
    			<td>品名
    			</td>
    			<td>
    				<select>
	    			  <option>胰岛素aaa</option><option>狂犬育苗#22</option>
	    			</select>
    			</td>
    			<td class="q_td">
    				规格
    			</td>
    			<td>
    				<select>
	    			  <option>100ml/瓶</option><option>400ml/瓶</option>
	    			</select>
    			</td>
    			<td>
    			</td>
    			<td>
    				<input type="button" class="btn btn-primary" value="查 询" onclick="alert('根据条件查询相应的订单信息！');" />
    				<input type="button" class="btn" value="返回" />
    			</td>
    		</tr>
    	</table>
</div>
    <div class="container-fluid">
  		<div class="row-fluid">
    		<div class="span2">
    			<ul class="nav nav-list">
					  <li class="nav-header">上海东方医院</li>
					  <li><a href="#">20120711382</a></li>
					  <li><a href="#">20120713990</a></li>
					  <li><a href="#">20120713990</a></li>
					</ul>
					<ul class="nav nav-list">
					  <li class="nav-header">同济医院</li>
					  <li><a href="#">20120711382</a></li>
					  <li><a href="#">20120713990</a></li>
					  <li><a href="#">20120713990</a></li>
					</ul>
					<ul class="nav nav-list">
					  <li class="nav-header">上海杨思医院</li>
					  <li><a href="#">20120711382</a></li>
					  <li><a href="#">20120713990</a></li>
					  <li><a href="#">20120713990</a></li>
					</ul>
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
	        	<tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
		         <tr >
		        	<td> <a href="javascript:window.location.href='order.do?ope=toOrdertbcc&oid=${param.oid }'">狂犬育苗#22</a></td>
		        	<td>只订单1</td>
		        	<td>西四命2323</td>
		        	<td>220</td>
		        	<td>10</td>
		        	<td>20</td>
		        	<td>200</td>
		        	<td>正常发货</td>
		        	<td>已发货</td>
		         </tr>
        	</tbody>
        </table>
    	  </div>
  	  </div>
    </div>
<%@include file="layout/footer.html" %>
</body>
</html>
