<%@ page language="java"  pageEncoding="gbk"%>
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
	  <li class="active">图层实时数据</li>
	</ul>
	<iframe src="http://localhost:8080/ccdcc/map/fdap_project_image.jsp?projectId=2001" 
	width="1024px" height="740px;" frameborder="0">
	</iframe>
	<%@include file="layout/footer.html" %>
</body>
</html>
