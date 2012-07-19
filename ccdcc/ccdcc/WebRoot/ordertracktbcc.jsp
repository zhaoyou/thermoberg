<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品的冷链环境</title>
<style type="text/css">
#content {
	font-size: 13px;
	height: 370px;
}
#content #center .altrow_a {
	background-image: url(images/index/bg3.gif);
	background-repeat: repeat-x;
	background-position: 0px 0px;
	height: 40px;
}
table td {
	border: 0
}
.top {
	margin-top: 8px;
}
#right_content {
	margin: 12px 0 50px 0;
}
#r_table {
	width: 960px;
	text-align: center;
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
ul.a {
	list-style-type:circle;
}
#back_span {
	margin-left: 500px;
	font-size: 14px;
}
</style>
<link href="css/ordertracktbcc/con_ri.css" rel="stylesheet" type="text/css" />
<link href="css/ordertracktbcc/tcd.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=128 frameborder=0></iframe >
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
          <td colspan="7"><span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;"> <img src=images/index/icon.gif width="9" height="10" /> <b>当前位置</b>：订单201207112134 的狂犬疫苗冷链环境： </span> <span id="back_span"><a href="ordertrack.jsp">返回</a></span></td>
        </tr>
      </table>
    </div>
    <div id="right_content">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tb3" >
        <tr id="tb3">
          <td width="82" height="25">品名</td>
          <td width="140">入时</td>
          <td width="140">出时</td>
          <td width="58">存储环境</td>
          <td width="58">存储载体</td>
          <td width="70">报警状态</td>
          <td width="68">最大温度</td>
          <td width="72">最小温度</td>
          <td width="86">平均温度</td>
          <td width="80">最大湿度</td>
          <td width="69">最小湿度</td>
          <td width="65">平均湿度</td>
        </tr>
        <tr>
          <td width="82">狂犬育苗#22</td>
          <td width="140">2012-07-11 12:00:00</td>
          <td width="140">2012-07-18 18:10:00</td>
          <td width="58">2-8</td>
          <td width="58"><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
          <td width="70">正常</td>
          <td width="68">7.9</td>
          <td width="72">3.6</td>
          <td width="86">5.7</td>
          <td width="80">75%</td>
          <td width="69">60%</td>
          <td width="65">66%</td>
        </tr>
        <tbody id="t_table_display">
          <tr class="altrow">
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td class="altrow"><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
          </tr>
          <tr >
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
          </tr>
          <tr class="altrow">
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
          </tr>
          <tr >
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
          </tr>
          <tr class="altrow">
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
          </tr>
          <tr >
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
          </tr>
          <tr class="altrow">
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
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
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
          </tr>
          <tr class="altrow">
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
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
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
          </tr>
          <tr class="altrow">
            <td>狂犬育苗#22</td>
            <td>2012-07-11 12:00:00</td>
            <td>2012-07-18 18:10:00</td>
            <td>2-8</td>
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
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
            <td><a href="ordertracktbcc_ref.jsp">冷藏间</a></td>
            <td>正常</td>
            <td>7.9</td>
            <td>3.6</td>
            <td>5.7</td>
            <td>75%</td>
            <td>60%</td>
            <td>66%</td>
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
