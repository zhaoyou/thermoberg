<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品信息</title>
<link href="css/index/company.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />


 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
 
<script type="text/javascript" language="javascript">
			
</script>
<style type="text/css">
	#content #center .altrow_a {
	background-image: url(images/index/bg3.gif);
	background-repeat: repeat-x;
	background-position: 0px 0px;
	height: 40px;
	}
	
	table td {
		border: 0
	}
	
	.q_td {
		text-align: right;
	}
	
	#q_panle {
	  border-bottom: 1px dotted #999;
	  width: 960px;
	  margin-left: 12px;
	  padding-bottom: 8px;
	}
	
	#q_table {
		width: 750px;
	}
	
	#left_tree {
	  position: relative;
      display: inline-block;
      width: 180px;
      margin-left: 10px;
      margin-top: 10px;
      vertical-align: top;
	}
	
	#right_content {
	  position: relative;
      display: inline-block;
      border-left: 1px dotted #666;
      padding-left: 10px;
      margin-top: 10px;
      height: 400px;
	}
	
	#r_table {
		width: 780px;
	}
	
	#r_table #r_table_tr {
		background-color: #3087DD;
		height: 20px;
	}
	
	ul {
		margin-left: 40px;
        margin-top: 3px;
	}
	
	ul li {
		line-height: 20px
	}
	ul.a {list-style-type:circle;}

</style>
</head>
<body>
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form action="org.do" name="myform" id="myform" method="post">
<input type="hidden" name="ope"  id="ope"  value="toHigherOrg"/>
<input  type="hidden" name="oid"  id="oid" value="${oid }"/>
<input type="hidden" name="ids" id="ids" value="${ids }"/>
<input type="hidden" name="orgName_statistics" id="orgName_statistics" value="${orgName }" />
</form>
<div id="content">
  <div id="center" >
    <div class="top" style="position:relative; display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：A公司订单信息
          	<span id="errorTip" style="color: red;display: none;" >(获取报警数据失败 ...)</span>
          </span>
          <%-- 
          <!-- 暂时不用机构企业地图 -->
          <span style="display: ${isshowmap==0?'none':'inline' }"><a href="org.do?ope=toChangeDisplay&oid=${oid }&showmap=2" style="text-decoration: none;">地图显示</a></span>--%>
        </td>
        </tr>
      </table>
    </div>
    <div id="q_panle">
    	<table id="q_table" border="0">
    		<tr border="0">
    			<td class="q_td">订单开始时间:
	    			<select>
	    			  <option>2012-07-11</option><option>2012-07-12</option>
	    			</select>
    			</td>
    			<td class="q_td">订单结束时间:
	    			<select>
	    			  <option>2012-07-11</option><option>2012-07-12</option>
	    			</select>	
    			</td>
    			<td>
    				订单编号：<input />
    			</td>
    			</tr>
    		<tr>
    			<td class="q_td">收货单位:
    				<select>
	    			  <option>上海儿童医学中心</option><option>上海东方医院</option>
	    			</select>
    			</td>
    			<td class="q_td">
    			生产商:
    				<select>
	    			  <option>西安杨森</option><option>华东宁波</option>
	    			</select>
    			</td>
    			<td>
    				生产批号：
    				<input />
    			</td>
    		</tr>
    		<tr>
    			<td class="q_td">品名：
    				<select>
	    			  <option>胰岛素aaa</option><option>狂犬育苗#22</option>
	    			</select>
    			</td>
    			<td class="q_td">
    				规格：
    				<select>
	    			  <option>100ml/瓶</option><option>400ml/瓶</option>
	    			</select>
    			</td>
    			<td>
    				<input type="button" value="查 询" />
    			</td>
    		</tr>
    	</table>
    </div>
    <div id="left_tree">
    	上海东方医院
    	<ul class="a">
    		
    				<li><a href="#">20120711382</a></li>
    				<li><a href="#">20120713990</a></li>
    			
    	</ul>
    	  上海儿童医学中心
    	<ul class="a">
    				<li><a href="#">20120715123</a></li>
    				<li><a href="#">20120716591</a></li>
    	</ul>
    </div>
    <div id="right_content">
        <table id="r_table">
        	<tr id="r_table_tr">
	        	<td>品名</td>
	        	<td>总数量</td>
	        	<td>整件数</td>
	        	<td>整件单位</td>
	        	<td>散件数</td>
	        	<td>散件单位</td>
	        	<td>运单状态</td>
	        	<td>批号</td>
	        	<td>产地</td>
	        	<td>规格</td>
	        	<td>有效期</td>
	        	<td>单位</td>
	        	<td>剂型</td>
        	</tr>
        	<tr  onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>狂犬育苗#22</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr  onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr  onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr  onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr  onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr  onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        	<tr onclick="javascript:window.location.href='order.do?ope=toOrdertbcc'">
	        	<td>胰岛素aaa</td>
	        	<td>120</td>
	        	<td>2</td>
	        	<td>20/件</td>
	        	<td>0</td>
	        	<td>0</td>
	        	<td>已装车</td>
	        	<td>201207001</td>
	        	<td>西安</td>
	        	<td>200/盒</td>
	        	<td>2016-12-30</td>
	        	<td>盒</td>
	        	<td>200</td>
        	</tr>
        </table>
    </div>
  </div>
  
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
