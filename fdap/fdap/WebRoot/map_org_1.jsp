<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>全国地图显示</title>
<script type="text/javascript" src="js/common.js"></script>
<link href="css/index/map_b.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="images/logo.ico" />
 <script type='text/javascript' src='dwr/interface/realcar.js'></script>
 <script type='text/javascript' src='dwr/engine.js'></script>
 <script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript"> 
			
			function goOrg(oid){
					document.getElementById('oid').value = oid ;
					document.myform.submit() ;
				}
				
				
				function goHighOrg(oid){
					document.getElementById('ope').value = "toHigherOrg" ;
					document.getElementById('oid').value = oid ;
					document.myform.submit() ;
				}
				
				
				function init(){
					var id = 	document.getElementById('sourceId').value  ;
							if(id==""){
								return ;
							}
							
					realcar.getAlarmTip(id,{
						callback:resultHandler,
						errorHandler:errorHandler,
						timeout:10000
					});
					
					window.setInterval(function (){
						realcar.getAlarmTip(id,{
						callback:resultHandler,
						errorHandler:errorHandler,
						timeout:10000
					});
					},10000);
							
				}
				
					function resultHandler(result){
					document.getElementById('errorTip').style.display = "none" ;
					
					if(result==null || result.length==0){
						//如果没有报警、则不提示
						 document.getElementById('tipDiv').innerHTML = "" ;
						 hideDiv() ;
					}else{
							hideDiv() ;
							var ta = "";
							for(var i =0;i<result.length;i++){
							
									if(document.getElementById(result[i].oid+"_light")!=null){
										document.getElementById(result[i].oid+'_light').style.display = "inline" ;	
								}
								
								ta +=buildTable(result[i].orgName,result[i].msg);
								
							}
							document.getElementById('tipDiv').innerHTML = ta ;
								
					}		
				}
				
				function hideDiv(){
						var ids = document.getElementById("ids").value ;
					if(ids==null || ids==""){
						return ;
					}
					
					var idslist = ids.split(",");
					
					for(var i=0;i<idslist.length;i++){
						if(document.getElementById(idslist[i]+"_light")!=null){
							document.getElementById(idslist[i]+"_light").style.display = "none" ;
						}
					}	
				}
				
				function buildTable(n,tip){
					var str = "<table width='160'  border='0'>" ;
					str +="<tr>" ;
					str +="<td align='left' bgcolor='#99CCFF'><div>"+n+"</div></td>" ;
					str +=" </tr>" ;
					str +="<tr><td>"+tip+"</td></tr></table>" ;
					return str ;
				}
				
				function errorHandler(err){
					document.getElementById('errorTip').style.display = "inline" ;
				}
				
				
				
</script>
<style type="text/css">
<!--
#Layer1 {
	position:absolute;
	left:465px;
	top:409px;
	width:101px;
	height:97px;
	z-index:1;
}
#apDiv1 {
	position:absolute;
	left:532px;
	top:356px;
	width:128px;
	height:49px;
	z-index:1;
}
#apDiv2 {
	position:absolute;
	left:604px;
	top:384px;
	width:23px;
	height:27px;
	z-index:1;
}
#apDiv3 {
	position:absolute;
	left:745px;
	top:346px;
	width:24px;
	height:27px;
	z-index:2;
	position:absolute;
	bottom:0;
	border:#CCCCCC 1px solid;
	background:#EAEAEA;
	height:25px;
	width:-38px;
	line-height:25px;
	color:#666666;
	font-size:12px;
	padding-left:55px;
}
#Layer2 {
	position:absolute;
	left:248px;
	top:213px;
	width:163px;
	height:110px;
	z-index:3;
}
.main{	position:fixed;
	bottom:0;
	border:#CCCCCC 1px solid;
	background:#EAEAEA;
	height:25px;
	width:50%;
	line-height:25px;
	color:#666666;
	font-size:12px;
	padding-left:55px;
	margin-top: 55px;
	margin-left: 55px;}
-->
</style>
</head>
<body onload="init();">
<div id="apDiv2" style="display:none"><img src="images/index/alarm.gif" width="26" height="26" /></div>
<div id="Layer2">
<div id="tipDiv">

  </div>
</div>
<div>
  <iframe scrolling="no" src="head.jsp" width=100% height=128 frameborder=0></iframe >
</div>
<form action="org.do" method="post" name="myform" id="myform">
  <input type="hidden" name="ope" id="ope" value="toOrg"/>
  <input type="hidden" name="sourceId" id="sourceId" value="${oid }"/>
  <input type="hidden" name="oid" id="oid" value=""/>
  <input type="hidden" name="ids" id="ids" value="${ids }"/>
</form>
<div id="content">
  <div id="center">
   <div class="top" style="position:relative; display:block;">
    <table id="tb" width="90%" border="0" cellspacing="0" cellpadding="0" style="position:relative;" >
      <tr id="tb1">
        <td width="1145" colspan="7"><span style="text-align:left; float:left; padding-left:8px; margin-left:4px; color:#141414;"><img src=images/icon.gif width="9" height="10" /> 当前位置：全国区域图</span>
        <span id="errorTip" style="color: red;display: none" >获取报警数据失败 ...</span>
        <a href="#"><%-- <img src="images/index/back.gif" width="58" height="21" class="pho"/>--%></a></td>
      </tr>
    </table>
    </div>
    <table id="tb" width="90%" border="0" cellspacing="0" cellpadding="0">
      <tr bgcolor="#f1f4f8">
        <td colspan="7" bgcolor="#FFFFFF">
      <img src="images/index/map_zs.gif" width="980" height="668" border="0" usemap="#Map" />
          <map name="Map" id="Map">
            <area shape="poly" coords="676,168,674,164,682,183" 
            	href="org.do?ope=toOrg&oid=3" />
            <area shape="poly" coords="566,250,569,246,575,244,574,237,570,232,564,232,561,236,557,237,557,242,553,248,551,252,556,254,563,251,559,252"
             	href="org.do?ope=toOrg&oid=3" target="_self" onfocus="this.blur()"/>
            <area shape="poly" coords="683,367,693,375,695,388,692,397,671,407,652,401,643,391,643,382,653,372,676,370,681,367" 
            	href="org.do?ope=toOrg&oid=4" target="_self" onfocus="this.blur()"/>
            <area shape="poly" coords="501,369,493,369,499,369,495,369,487,369,488,372,490,377,485,382,481,385,483,390,485,393,488,396,492,399,494,404,491,408,485,411,478,414,471,414,470,417,470,422,470,426,473,429,478,434,481,434,483,430,486,425,491,424,496,425,494,421,496,417,500,416,503,417,508,418,510,418,514,420,516,422,520,425,524,426,529,424,533,422,538,425,541,429,546,430,550,431,554,429,558,426,559,424,563,422,567,420,570,419,575,418,574,414,572,409,568,404,567,400,570,400,566,397,563,394,561,391,562,388,564,386,569,384,568,381,568,377,562,377,559,373,558,370,552,375,549,379,547,383,545,386,538,386,530,386,528,386,520,382,516,380,514,380,506,378,501,372,503,372" 
            	href="org.do?ope=toOrg&oid=5" target="_self" onfocus="this.blur()"/>
            <area shape="poly" coords="326,228,327,222,327,218,325,215" 
            	href="org.do?ope=toOrg&oid=7" onfocus="this.blur()"/>
            <area shape="poly" coords="327,222,327,230,330,236,332,238,332,240,335,244,337,247,341,244,345,243,351,242,354,243,356,243,353,247,350,252,348,255,348,258,350,258,352,260,356,263,360,266,364,273,361,269,368,274,370,277,375,278,378,279,378,274,378,271,383,270,386,270,389,272,392,273,397,270,402,268,405,268,403,271,400,275,398,279,396,283,396,288,398,292,402,297,407,300,409,301,411,301,414,304,415,308,418,309,420,312,420,317,421,323,424,329,426,330,428,334,431,334,439,332,443,328,445,323,444,319,439,315,439,311,442,309,442,304,447,301,447,306,448,308,452,310,458,314,464,317,466,319,463,331,454,333,451,337,447,338,439,337,434,342,435,349,437,355,437,361,431,362,428,364,430,369,431,374,420,376,425,377,420,377,416,377,413,373,408,368,403,360,397,356,391,352,385,351,383,355,381,360,381,366,375,364,372,363,366,356,368,361,364,356,362,352,363,350,367,350,368,353,371,354,376,355,379,354,382,351,381,348,377,344,375,343,382,338,387,333,391,329,392,323,390,318,387,310,386,305,385,301,385,296,383,293,380,293,377,292,374,292,371,291,367,288,363,284,360,280,357,278,357,276,349,276,348,278,345,275,343,273,340,270,337,267,333,266,332,268,329,266,326,264,323,262,319,262,317,262,312,264,312,267,313,269,313,275,309,276,304,271,301,267,296,263,292,262,286,258,282,258,276,258,269,258,266,258,264,254,263,248,264,246,265,244,267,242,272,241,277,240,281,235,283,228,285,226,290,223,295,224,304,223,306,222,308,219,307,214,308,209,310,207,313,207,320,208,324,210,326,214,326,218,327,219" 
            	href="org.do?ope=toOrg&oid=6" target="_self" onfocus="this.blur()"/>
            <area shape="poly" coords="652,144,644,146,639,141,634,135,628,137,623,141,622,142,616,141,612,138,610,141,612,144,615,147,619,153,620,160,622,166,630,161,632,164,640,175,643,178,647,179,650,182,655,185,659,182,661,186,666,182,673,173,678,163,682,157,681,154,675,149,670,147,664,149,662,150,660,146,659,141,653,142" 
            	href="org.do?ope=toOrg&oid=7" target="_self" onfocus="this.blur()" />
            <area shape="poly" coords="683,207,688,199,696,201,702,202,702,196,700,192,702,187,708,187,709,182,713,177,716,170,720,171,720,175,721,179,728,180,730,175,730,166,726,162,721,157,716,155,710,159,706,165,701,166,697,161,692,154,690,157,686,157,680,158,678,167,672,177,664,183,662,187,665,192,666,199,668,204,671,208,675,213,678,215,680,215,682,208,685,203" 
            	href="org.do?ope=toOrg&oid=8" target="_self" onfocus="this.blur()" />
            <area shape="poly" coords="378,369,372,365,368,363,370,369,370,375,366,377,359,380,354,382,349,379,347,377,342,380,340,376,333,375,334,372,330,366,330,360,324,357,319,357,313,357,317,360,318,366,316,369,316,373,316,375,316,379,319,383,324,385,329,396,332,405,334,415,334,428,332,435,331,441,334,449,338,442,344,440,343,445,345,450,347,455,353,457,358,459,361,468,366,476,365,480,366,485,372,491,376,493,382,367,380,367"
            	href="org.do?ope=toOrg&oid=9" target="_self" onfocus="this.blur()" />
            <area shape="poly" coords="381,492,388,487,389,481,389,478,385,474,387,466,391,465,393,463,395,460,394,455,395,449,399,443,405,444,407,449,408,452,411,454,415,457,422,453,427,459,433,462,437,461,434,456,426,453,425,448,429,442,433,439,427,431,424,425,426,417,425,409,425,405,431,408,437,412,440,411,443,410,445,406,453,401,455,389,452,381,442,377,431,379,423,379,413,380,409,374,406,368,401,363,397,359,392,354,385,353,383,358,377,491,394,424" 
            	href="org.do?ope=toOrg&oid=10" target="_self" onfocus="this.blur()" />
          </map>
 
        <div id="4_light" style="display: none;LEFT: 881px; POSITION: absolute; TOP: 571px; height:*;background-image: url(images/index/alarm01.gif); background-repeat: no-repeat; background-position: 0px 0px; color:red; width: 26px; height: 25px;"> </div>
        <div id="8_light" style="display: none;LEFT: 890px; POSITION: absolute; TOP: 335px; height:*;background-image: url(images/index/alarm01.gif); background-repeat: no-repeat; background-position: 0px 0px; color:red; width: 26px; height: 25px;"> </div>
        <div id="7_light" style="display: none;LEFT: 845px; POSITION: absolute; TOP: 319px; height:*;background-image: url(images/index/alarm01.gif); background-repeat: no-repeat; background-position: 0px 0px; color:red; width: 26px; height: 25px;"> </div>
        <div id="3_light" style="display: none;LEFT: 778px; POSITION: absolute; TOP: 407px; height:*;background-image: url(images/index/alarm01.gif); background-repeat: no-repeat; background-position: 0px 0px; color:red; width: 26px; height: 25px;"> </div>
        <div id="6_light" style="display: none;LEFT: 635px; POSITION: absolute; TOP: 508px; height:*;background-image: url(images/index/alarm01.gif); background-repeat: no-repeat; background-position: 0px 0px; color:red; width: 26px; height: 25px;"> </div>
        <div id="5_light" style="display: none;LEFT: 715px; POSITION: absolute; TOP: 580px; height:*;background-image: url(images/index/alarm01.gif); background-repeat: no-repeat; background-position: 0px 0px; color:red; width: 26px; height: 25px;"> </div>
        <div id="10_light" style="display: none;LEFT: 598px; POSITION: absolute; TOP: 589px; height:*;background-image: url(images/index/alarm01.gif); background-repeat: no-repeat; background-position: 0px 0px; color:red; width: 26px; height: 25px;"> </div>
        <div id="9_light" style="display: none;LEFT: 545px; POSITION: absolute; TOP: 577px; height:*;background-image: url(images/index/alarm01.gif); background-repeat: no-repeat; background-position: 0px 0px; color:red; width: 26px; height: 25px;"> </div>
        
        
        
        
        </td>
      </tr>
      <tr>
        <td colspan="7">&nbsp;</td>
      </tr>
    </table>
  </div>
</div>
<div>
  <iframe scrolling="no" src="footer.jsp" width=100% height=22 frameborder=0></iframe >
</div>
</html>
