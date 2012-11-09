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
  <li><a href="#">订单100200</a><span class="divider">/</span></li>
  <li>A公司药品订单信息查询</li>
</ul>
<form action="org.do" name="myform" id="myform" method="post">
<input type="hidden" name="ope"  id="ope"  value="toHigherOrg"/>
<input  type="hidden" name="oid"  id="oid" value="${oid }"/>
<input type="hidden" name="ids" id="ids" value="${ids }"/>
<input type="hidden" name="orgName_statistics" id="orgName_statistics" value="${orgName }" />
</form>
<div>
        <table class="table table-striped">
        	<tr id="r_table_tr">
	        	<td>品名</td>
	        	<td>条形编码</td>
	        	<td>入时</td>
	        	<td>出时</td>
	        	<td>存储环境</td>
	        	<td>存储类型</td>
	        	<td>存储载体</td>
	        	<td>退换货</td>
	        	<td>报警状态</td>
	        	<td>平均温度</td>
	        	<td>平均湿度</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#22</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#23</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#23</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#24</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#25</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#26</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#27</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#28</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
        	</tr>
        	<tr>
	        	<td>狂犬育苗#29</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
	   
        	</tr>
        	<tr>
	        	<td>狂犬育苗#32</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
	       
        	</tr>
        	<tr>
	        	<td>狂犬育苗#33</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	
        	</tr>
        	<tr>
	        	<td>狂犬育苗#22</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	
        	</tr>
        	<tr>
	        	<td>狂犬育苗#22</td>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>2012-07-18 18:10:00</td>
	        	<td>2-8</td>
	        	<td><a href="javascript:window.location.href='order.do?ope=toOrdertbccRef&oid=${param.oid }'">冷藏间</a></td>
	        	<td>正常</td>
	        	<td>7.9</td>
	        	<td>3.6</td>
	        	<td>5.7</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	
        	</tr>
        	
        </table>
</div>
<%@include file="layout/footer.html" %>
</body>
</html>
