<%@ page language="java"  pageEncoding="utf-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${fdaporg.name }仓库图层实时数据页面</title>
<link rel="Shortcut Icon" href="images/logo.ico" />
<link href="css/index/real_car.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
	
		//执行跳转
		function goback(url,method){
				document.myform.action = url ;
				document.getElementById('ope').value = method ;
				document.myform.submit() ;
		}
</script>
</head>
<body>
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<div style="padding:0;width:1024px; margin:0 auto;">
<form name="myform" id="myform" action="" method="post">
<input  type="hidden" name="ope" id="ope" value=""/> 
<input type="hidden" name="oid"  id="oid" value="${fdaporg.oid }"/>
<input type="hidden" name="projectIds" id="projectIds" value="${projectIds }" />

</form>
<div id="content">
  <div id="center">
    <div class="top">
      <table width="985" border="0" cellpadding="0" cellspacing="0" id="tb" >
        <tr id="tb1" >
          <td width="983" colspan="7">
          <span style="text-align:left; float:left;padding-left:4px; margin-left:4px; color:#141414;">
          <img src=images/index/icon.gif width="9" height="10" /> 当前位置： 查看${fdaporg.name }的仓库图层实时数据</span>
          <a href="javascript:goback('org.do','toHigherOrg')"><img src="images/index/back.gif" width="58" height="21" 
        class="pho"/></a>
        </td>
        </tr>
      </table>
    </div>
    <div class="bottom">
      <table id="tb" border="0" cellspacing="0" cellpadding="0">
        <tr >
          <td height="20" colspan="14" align="left" style="text-align:left;">
          <table width="100%" style="height:35px;">
              <tr>
                <td width="10%" style="border:none;"><a href="javascript:goback('refReal.do','toRealRef');"><img src="images/index/canku.gif" width="100" height="24" /></a></td>
                <td width="13%" style="border:none;"><a href="javascript:goback('carReal.do','toRealCar');"><img src="images/index/car.gif" width="100" height="24" /></a></td>
                <td width="77%" style="border:none; text-align: left;"><a href="javascript:;">仓库图层</a></td>
              </tr>
            </table></td>
        </tr>  
      </table>
    </div>
    <iframe src="http://www.thermoberg.com/ccdcc/map/fdap_project_image.jsp?projectId=${projectId }" 
	width="1024px" height="740px;" frameborder="0">
	</iframe>
  </div>
</div>
</div>

<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=26 frameborder=0></iframe >
</div>
</body>
</html>
