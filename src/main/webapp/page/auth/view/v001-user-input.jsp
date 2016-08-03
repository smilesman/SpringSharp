<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<jsp:include page="/page/common/ShowMessage.jsp"/>
<jsp:useBean id="sessionMng" scope="session" class="lab.sharp.core.util.SessionMng"></jsp:useBean>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
  
<form name = "form" id = "form" action ="<%=basePath%>/page/auth/control/c001.jsp"   method = "post">
	<TABLE >
		<TBODY>
			<tr>
				<TD>ตวยผร๛</TD>
				<TD><input name = "sLoginName"></TD>
			</tr>
			<tr>
				<TD>ตวยผรÜย๋</TD>
				<TD><input name = "sLoginPassword"></TD>
			</tr>
		</TBODY>
	</TABLE>
 </form>
