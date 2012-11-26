<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品轨迹信息</title>
<link href="css/order/ordertrack_ref.less" rel="stylesheet" type="text/less"/>
<%@ include file="layout/asset.html" %>
</head>
<body>
<%@ include file="layout/header.html" %>
<ul class="breadcrumb">
  <li><a href="#">首页</a> <span class="divider">/</span></li>
  <li><a href="#">订单 100200</a><span class="divider">/</span></li>
  <li><a href="#">药品 西四命</a><span class="divider">/</span></li>
  <li>F1C1 冷库</li>
</ul>
<form action="org.do" name="myform" id="myform" method="post">
<input type="hidden" name="ope"  id="ope"  value="toHigherOrg"/>
<input  type="hidden" name="oid"  id="oid" value="${oid }"/>
<input type="hidden" name="ids" id="ids" value="${ids }"/>
<input type="hidden" name="orgName_statistics" id="orgName_statistics" value="${orgName }" />
</form>
<div class="query_div">
	<table>
		<tr>
			<td>开始时间</td>
			<td><input class="input-time" type="text" placeholder="2012-11-11 12:12:12"></td>
			<td>结束时间</td>
			<td><input class="input-time" type="text" placeholder="2012-11-12 11:11:11"></td>
			<td><input type="button" value="返回" class="btn"/></td>  
		</tr>
	</table>
</div>
    <div id="right_content">
        <table id="r_table" class="table table-striped">
        	<tr id="r_table_tr">
	        	<td>时间</td>
	        	<td>T51</td>
	        	<td>T52</td>
	        	<td>T53</td>
	        	<td>RH51</td>
	        	<td>最大温度</td>
	        	<td>最小温度</td>
	        	<td>平均温度</td>
	        	<td>最大湿度</td>
	        	<td>最小湿度</td>
	        	<td>平均湿度</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 12:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 13:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 14:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 15:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 16:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 17:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 18:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 19:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 20:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 21:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 22:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 22:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 22:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 22:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 22:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 22:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	<tr>
	        	<td>2012-07-11 22:00:00</td>
	        	<td>12.21</td>
	        	<td>10.91</td>
	        	<td>12.22</td>
	        	<td>84%</td>
	        	<td>13.99</td>
	        	<td>8.99</td>
	        	<td>9.12</td>
	        	<td>75%</td>
	        	<td>60%</td>
	        	<td>66%</td>
        	</tr>
        	
        </table>
    </div>
    
    <div class="pagination pagination-large">
		  <ul>
		    <li class="disabled"><a href="#">Prev</a></li>
		    <li class="active"><a href="#">1</a></li>
		    <li ><a href="#">2</a></li>
		    <li ><a href="#">3</a></li>
		    <li ><a href="#">4</a></li>
		    <li ><a href="#">5</a></li>
		    <li ><a href="#">6</a></li>
		    <li ><a href="#">7</a></li>
		    <li ><a href="#">Next</a></li>  
		  </ul>
</div>

<%@include file="layout/footer.html" %>
</body>
</html>
