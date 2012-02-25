<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	<script src="jquery-1.4.3.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
    var oBtnTest = $("#btnTest");
    oBtnTest.click(btnAjaxPost);
}); 


function btnAjaxPost(event) {
    $.ajax({
        type: "POST",
        contentType:"text/xml",
        url:"http://www.thermoberg.com/ccdccservice/services/AllService",
        data:getPostData(),//这里不该用JSON格式
        dataType:'xml',//这里设成XML或者不设。设成JSON格式会让返回值变成NULL
        success: function(xml) {
        	alert("aa");
	        //对结果做XML解析。
	        //浏览器判断 (IE和非IE完全不同)
	        if($.browser.msie){
	        	//$("#result").append(xml.getElementsByTagName("ns1:out")[0].childNodes[0].nodeValue+"<br/>");
	        	$("#result").append("asfsdf<br/>dfasf<br/>");
	        	//$("#result").append(xml.getElementsByTagName("ns1:out")[0]+"<br/>");
	        	//$("#result").append($(xml).find("ns1:out").children('flag').text());
	        	//alert($(xml).find("updateTime").text());
	        	//alert($(xml).find("id").text());
	        	$("#result").append($(xml).find("ai1").text()+"<br/>");
	        	$("#result").append($(xml).find("ai2").text()+"<br/>");
	        	$("#result").append($(xml).find("ai3").text()+"<br/>");
	        	$("#result").append($(xml).find("ai4").text()+"<br/>");
	        	$("#result").append($(xml).find("alarmStatus").text()+"<br/>");
	        	$("#result").append($(xml).find("connectionStatus").text()+"<br/>");
	        	$("#result").append($(xml).find("id").text()+"<br/>");
	        	$("#result").append($(xml).find("latitude").text()+"<br/>");
	        	$("#result").append($(xml).find("latitude_dir").text()+"<br/>");
	        	$("#result").append($(xml).find("longitude").text()+"<br/>");
	        	$("#result").append($(xml).find("longitude_dir").text()+"<br/>");
	        	$("#result").append($(xml).find("projectId").text()+"<br/>");
	        	$("#result").append($(xml).find("runStatus").text()+"<br/>");
	        	$("#result").append($(xml).find("updateTime").text()+"<br/>");
	        }
			else{
				//$(xml).find("out").each(function(){
				//	$("#result").append($(this).text()+"<br/>");
				//});
				var time1 = $(xml).find("updateTime").text();
	        	alert(time1);
			}
		},
        error: function(x, e) {
        	alert('error:'+x.responseText);
		},
		complete: function(x) {
           //alert('complete:'+x.responseText);
        }
    });
}
//定义满足SOAP协议的参数。
function getPostData()
{
	//根据WSDL分析sayHelloWorld是方法名，parameters是传入参数名
	var postdata="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	postdata+="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ccdcc.org\">";
   	postdata+="<soapenv:Header/>";
	postdata+="<soapenv:Body>";
	postdata+="<ser:getCarRealData>";
	postdata+="<ser:in0>3021</ser:in0>";
	postdata+="</ser:getCarRealData>";
	postdata+="</soapenv:Body>";
	postdata+="</soapenv:Envelope>";
	return postdata;
	//var postdata="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	//postdata+="<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
	//postdata+="<soap:Body><getCarRealData xmlns=\"http://service.ccdcc.org/\">";
    //postdata+="<parameters>3021</parameters>";
	//postdata+="</getCarRealData></soap:Body>";
	//postdata+="</soap:Envelope>";
	//return postdata;
}


   
</script>
	
	
  </head>
  
  <body>
    <input type="text" id="txtName" /><br/>
	<button id="btnTest">BtnTest</button>
	<div id="result"></div>
  </body>
</html>
