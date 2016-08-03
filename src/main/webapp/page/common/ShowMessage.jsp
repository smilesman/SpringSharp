<%--<%@ page contentType="text/html;charset=GBK"%> 
<jsp:useBean id="sessionMng" scope="session" class="com.iss.itreasury.util.SessionMng"></jsp:useBean>
<%@page import="com.iss.itreasury.util.*,
                com.iss.itreasury.settlement.bizlogic.bankpay.*,
    			com.iss.itreasury.settlement.remoteresult.*,
                java.sql.*,
				java.util.*"
%>
<%
	CPFHTML.showMessage(out,request.getParameter("strMessage"), sessionMng, request.getParameter("strTitle"));
%>
--%>

<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sharp.core.action.ActionMessages" %>
<jsp:useBean id="sessionMng" scope="session" class="com.sharp.core.utils.SessionMng"></jsp:useBean>
<%
ActionMessages messages = null;
messages = sessionMng.getActionMessages();
if(messages != null && !messages.isEmpty())
{
    Iterator<String> itTemp = messages.getMessages().iterator();

	StringBuffer sbTemp = new StringBuffer(128);

    while(itTemp.hasNext())
    {
        String strTemp = (String)itTemp.next();

		//去除回车换行符
		StringBuffer sbTempForConvert = new StringBuffer(strTemp);

		boolean bFlag = true;
		while (bFlag)
		{
			bFlag = false;

			int nPoint = sbTempForConvert.toString().indexOf("\r\n");
			if (nPoint > -1)
			{
				sbTempForConvert.replace(nPoint, nPoint + 2, " ");

				bFlag = true;
				
				continue;
			}

			nPoint = sbTempForConvert.toString().indexOf("\r");
			if (nPoint > -1)
			{
				sbTempForConvert.replace(nPoint, nPoint + 1, " ");

				bFlag = true;
				
				continue;
			}

			nPoint = sbTempForConvert.toString().indexOf("\n");
			if (nPoint > -1)
			{
				sbTempForConvert.replace(nPoint, nPoint + 1, " ");

				bFlag = true;
				
				continue;
			}
		}

		int nPoint = 0;

		while (nPoint >= 0)
		{
			nPoint = sbTempForConvert.toString().indexOf("\"", nPoint+1);

			if (nPoint > -1)
			{
				if (!sbTempForConvert.substring(nPoint - 1, nPoint).equalsIgnoreCase("\\"))
				{
					sbTempForConvert.replace(nPoint, nPoint + 1, "\\\"");
				}
			}
		}

		sbTemp.append(sbTempForConvert);
        sbTemp.append("\\n");
    }
%>
	<script language="JavaScript">
	alert("<%=sbTemp.toString()%>");
	</script>
<%
    //使用后立即清除
    messages.clear();
}
%>

