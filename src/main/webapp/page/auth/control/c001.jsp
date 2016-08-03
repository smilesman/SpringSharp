<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="lab.sharp.module.auth.web.UserController" %>
<%@ page import="lab.sharp.module.auth.entity.User" %>
<jsp:useBean id="sessionMng" scope="session" class="lab.sharp.core.util.SessionMng"></jsp:useBean>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
<%
	long lId = -1;
	UserController controller = new UserController();
	User user = new User();
	user.convertRequestToDataEntity(request);
	try{
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(lId>0){
			sessionMng.getActionMessages().addMessage("保存成功");
		}else{
			sessionMng.getActionMessages().addMessage("保存失败");
		}
	}
	
	String sNextPageURL = "../view/v001-user-input.jsp";
RequestDispatcher rd = request.getRequestDispatcher(sNextPageURL);
rd.forward( request,response );
%>




</html>
