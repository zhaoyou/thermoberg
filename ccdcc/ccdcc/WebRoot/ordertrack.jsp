<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品信息</title>
<style type="text/css">
	#content {
		margin-left: 90px;
		font-size: 13px;
	}
	#content #center .altrow_a {
	background-image: url(images/index/bg3.gif);
	background-repeat: repeat-x;
	background-position: 0px 0px;
	height: 40px;
	}
	
	.top {
		margin-top: 8px;
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
	  margin-top: 8px;
	  padding-bottom: 8px;
	  padding-top: 8px;
	  border-top: 1px dotted #999;
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
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<div id="content">
  <div id="center" >
    <div class="top" style="position:relative; display:block;">
      <table id="tb" border="0" cellspacing="0" cellpadding="0" >
        <tr id="tb1" >
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> <b>当前位置</b>：A公司订单信息
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
    			<td class="q_td">
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
    			<td class="q_td">
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
    			<td class="q_td">
    				<input type="button" value="查 询" onclick="alert('根据条件查询相应的订单信息！');" />
    				<input type="button" value="返 回" onclick="window.location.href='user.do?ope=toIndex'"/>
    			</td>
    		</tr>
    	</table>
    </div>
    <div id="left_tree">
    	<div id="left_tree_display">
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
    </div>
    <div id="right_content" >
        <table id="r_table" >
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
        	<tbody id="t_table_display">
        	<tr >
	        	<td> <a href="ordertracktbcc.jsp">狂犬育苗#22</a></td>
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
        	<tr >
	        	<td><a href="ordertracktbcc.jsp">胰岛素A1</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素A2</a></td>
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
        	<tr >
	        	<td><a href="ordertracktbcc.jsp">胰岛素ccc</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素bbb</a></td>
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
        	<tr >
	        	<td><a href="ordertracktbcc.jsp">胰岛素abc</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素123</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素4</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素6</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素777</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素888</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素aaa</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素999</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素aaa</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素aaa</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素aaa</a></td>
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
        	<tr>
	        	<td><a href="ordertracktbcc.jsp">胰岛素aaa</a></td>
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
        	</tbody>
        </table>
    </div>
  </div>
  
</div>
<div>
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
