<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>欢迎登陆冷链管理系统</title>
<%@ include file="layout/asset.html" %>
<script src="js/front/login.js"></script>
</head>
<body onload="login.init();">
		<div class="container">
				<div class="alert alert-info">
						<h2>欢迎登陆企业冷链监管平台</h2>
				</div>
				<c:if test="${param.msg != null && param.msg == 'fail'}">
					<div class="alert alert-error">
						用户登录失败！
    			</div>
				</c:if>
				<div class="span12">
					<form id="myform" class="form-horizontal" action="user.do" method="POST">
					  <div class="control-group">
					    <label class="control-label" for="clientName">帐号</label>
					    <div class="controls">
					      <input type="text" name="clientName" id="clientName" placeholder="Account">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="userName">用户名</label>
					    <div class="controls">
					      <input type="text" name="userName" id="userName" placeholder="Username">
					    </div>
					  </div>
					  <div class="control-group">
					    <label class="control-label" for="password">密码</label>
					    <div class="controls">
					      <input type="password" name="password" id="password" placeholder="Password">
					    </div>
					  </div>
					  <div class="control-group">
					    <div class="controls">
					      <button type="button" class="btn btn-primary">Sign in</button>
					      <input type="hidden" name="ope" value="doLogin"/>
					    </div>
					  </div>
					</form>
				<div>
	</div>
</body>
</html>
