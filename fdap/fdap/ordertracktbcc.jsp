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
	
	
	
	
	
	#r_table {
		width: 960px;
	}
	
	#r_table tr {
		cursor: pointer;
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
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置：订单201207112134 的狂犬疫苗冷链环境：
          	<span id="errorTip" style="color: red;display: none;" >(获取报警数据失败 ...)</span>
          </span>
          
          <a href="javascript:window.location.href='order.do?ope=toOrder&oid=${param.oid }'">
          <img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a>
        </td>
        </tr>
      </table>
    </div>
  
    <div id="right_content">
        <table id="r_table">
        	<tr id="r_table_tr">
	        	<td>品名</td>
	        	<td>入时</td>
	        	<td>出时</td>
	        	<td>存储环境</td>
	        	<td>存储载体</td>
	        	<td>报警状态</td>
	        	<td>最大温度</td>
	        	<td>最小温度</td>
	        	<td>平均温度</td>
	        	<td>最大湿度</td>
	        	<td>最小湿度</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
	        	<td>66%</td>
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
