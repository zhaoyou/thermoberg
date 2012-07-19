<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单药品信息</title>
<link href="css/ordertrack/tcd.css" rel="stylesheet" type="text/css" />
<link href="css/ordertrack/input5.css" rel="stylesheet" type="text/css" />
<link href="css/ordertrack/con_ri.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div>
  <iframe scrolling="no" src="common/header2.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<div id="content">
  <div id="center" >
    <div id="top"><a href="#"><img src="images/chezai/icon_c.gif" width="16" height="15" / class="tb4" /><strong><b> 当前位置</b></strong>：A公司订单信息</a>
      <%-- 
          <!-- 暂时不用机构企业地图 -->
          <span style="display: ${isshowmap==0?'none':'inline' }"><a href="org.do?ope=toChangeDisplay&oid=${oid }&showmap=2" style="text-decoration: none;">地图显示</a></span>--%>
    </div>
    <div id="center2">
      <table width="100%" id="bd4">
        <tr>
          <td width="196" ><span>订单开始时间:</span>
            </td>
          <td width="129" align="right" ><span class="q_td">
            <select name="select3" id="select" class="select01">
              <option>2012-07-11</option>
              <option>2012-07-12</option>
            </select>
            </span></td>
          <td width="13">&nbsp;</td>
          <td width="104"><span class="q_td">订单结束时间:</span></td>
          <td width="143"><span class="q_td">
            <select name="select3" class="select01">
              <option>2012-07-11</option>
              <option>2012-07-12</option>
            </select>
            </span></td>
          <td width="120"><span class="q_td">订单编号：</span></td>
          <td width="171" align="left"><input  class="input01" /></td>
          <td width="66" >&nbsp;</td>
        </tr>
      </table>
    </div>
    <div id="center3">
      <table width="100%" id="bd">
        <tr>
          <td width="197" height="22" ><span class="q_td">收货单位:</span>
            </td>
          <td width="130" align="right" ><span class="q_td">
            <select name="select" class="select01">
              <option>上海儿童医学中心</option>
              <option>上海东方医院</option>
            </select>
            </span></td>
          <td width="58">&nbsp;</td>
          <td width="58" ><span class="q_td">生产商:</span></td>
          <td width="138" ><span class="q_td">
            <select name="select" class="select01">
              <option>西安杨森</option>
              <option>华东宁波</option>
            </select>
            </span></td>
          <td width="127" align="right"><span class="q_td">生产批号：</span></td>
          <td width="151" style="text-align:left;"><p>
              <input  class="input01"/>
            </p></td>
          <td width="83">&nbsp;</td>
        </tr>
      </table>
    </div>
    <div id="center4">
      <table width="100%" id="bd2">
        <tr>
          <td width="189" height="22"><span>品名:</span></td>
          <td width="137" ><span class="q_td">
            <select name="select6" class="select01">
              <option>胰岛素aaa</option>
              <option>狂犬育苗#22</option>
            </select>
            </span></td>
          <td width="129" ><span class="q_td">规格：</span></td>
          <td width="129" ><span class="q_td">
            <select name="select4" class="select01">
              <option>100ml/瓶</option>
              <option>400ml/瓶</option>
            </select>
            </span></td>
          <td width="118" align="right">生产批号：</td>
          <td width="126" align="left" class="q_td"><input type="button" value="查 询" onclick="alert('根据条件查询相应的订单信息！');" />
            <input type="button" value="返 回" onclick="window.location.href='user.do?ope=toIndex'"/></td>
          <td width="118" class="q_td">&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p>
    </div>
  </div>
</div>
<div id="con">
  <div id="right_content" >
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tb" >
      <tr id="tb1">
        <td width="74" height="26">编号</td>
        <td width="69">开启时间</td>
        <td width="67">结束时间 </td>
        <td width="64">出发地 </td>
        <td width="57">目的地 </td>
        <td width="54">发货人</td>
        <td width="58">承运人 </td>
        <td width="67">收货人</td>
        <td width="46">&nbsp;</td>
        <td width="43">有效期</td>
        <td width="80">&nbsp;</td>
        <td width="52">有效期</td>
        <td width="67">有效期</td>
      </tr>
      <tr>
        <td width="74">品名</td>
        <td width="69">总数量</td>
        <td width="67">整件数</td>
        <td width="64">整件单位</td>
        <td width="57">散件数</td>
        <td width="54">散件单位</td>
        <td width="58">运单状态</td>
        <td width="67">批号</td>
        <td width="46">产地</td>
        <td width="43">规格</td>
        <td width="80">有效期</td>
        <td width="52">单位</td>
        <td width="67">剂型</td>
      </tr>
      <tbody id="t_table_display">
        <tr class="altrow">
          <td><a href="ordertracktbcc.jsp">狂犬育苗#22</a></td>
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
        <tr class="altrow">
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
        <tr class="altrow">
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
        <tr class="altrow">
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
        <tr class="altrow">
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
        <tr class="altrow">
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
        <tr class="altrow">
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
        <tr class="altrow">
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
        <tr class="altrow">
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
  <div id="left_tree">
    <div id="left_tree_display"> 上海东方医院
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
  <iframe scrolling="no" src="common/footer2.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
