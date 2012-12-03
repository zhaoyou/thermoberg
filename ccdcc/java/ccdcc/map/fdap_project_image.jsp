<%@ page language="java"  pageEncoding="gbk" import="java.util.*"%>
<%@page import="org.tbcc.util.MySpringFactory"%>
<%@page import="org.tbcc.biz.ProjectBiz"%>
<%@page import="org.tbcc.biz.IGetImageEachControlAllDatas" %>
<%@page import="org.tbcc.entity.TbccPrjType"%>
<%@page import="org.tbcc.entity.TbccProjectImages" %>
<%@page import="org.tbcc.biz.IGetImageEachControlAllDatas"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>ok</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <%-- show project image for fdap  --%>
  <body oncontextmenu="window.event.returnValue=false" >
    <%
    	 String projectId = request.getParameter("projectId");
    	 if (projectId == null || "".equals(projectId)) {
    	 		out.println("bad request");
    	 		response.setStatus(400);
    	 		return;
    	 }
    	 
    	 ProjectBiz projectBiz = (ProjectBiz)MySpringFactory.getInstance().getBean("projectBiz");
    	 IGetImageEachControlAllDatas imageBiz =
    	 		(IGetImageEachControlAllDatas)MySpringFactory.getInstance().getBean("getImageEachControlAllDatasImpl");
 
				TbccPrjType project = projectBiz.getByProId(projectId);
				
				request.setAttribute("projList", Arrays.asList(project));

				List<TbccProjectImages> images = imageBiz.getImagesByPid(projectId);	//根据项目名获取所有的图层信息
				
				
				request.setAttribute("imageList", images);					//获取所有的图层信息
				
				if(images!=null && images.size()>0){		//如果存在图层
					TbccProjectImages image =  images.get(0) ;								//默认获取第一个图层
					
					String info = imageBiz.getEachControlAllDates(image.getId(),projectId);			//获取第一个信息 
					request.setAttribute("info", info);										//把数据存放到request
				}
							
				request.setAttribute("projectId", projectId);
				request.getRequestDispatcher("fdap_project_image_real.jsp").forward(request,response);
				
				
 
     %>
  </body>
</html>
