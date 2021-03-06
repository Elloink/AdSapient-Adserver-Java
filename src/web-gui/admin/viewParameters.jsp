<%@ page import="com.adsapient.shared.mappable.ParameterImpl"%>
<%@ page import="com.adsapient.shared.service.I18nService"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.adsapient.gui.ContextAwareGuiBean"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html:messages id="msg" message="true" header="messages.header" footer="messages.footer" >
 <bean:write name="msg" />
</html:messages>
<% ApplicationContext ctx = ContextAwareGuiBean.getContext();%>
<% I18nService i18nService = (I18nService) ctx.getBean("i18nService");%>
<html:form action="parameter.do" enctype="multipart/form-data"> 
<html:hidden property="action"/>
<tr><td>
	<table width="100%"cellspacing="0" cellpadding="0"><tr>
	<td><img src="images/table1.gif"></td>
	<td width="100%" class="tableheader"><bean:message key="parameter.list.title"/></td>
	<td><img src="images/table2.gif"></td>
	</tr></table>
</td></tr>
<tr><td>
	<table width="100%" cellspacing="0" cellpadding="0">
	
	<tr>
	<td width="5" heigh="10" style="background-image: url(images/table4.gif);"></td>
		<td><table border="0" style="background-color:#ffffff;" cellspacing="1" width="100%"><tr>
		<td height="10"  width="1%" class="tabletableheader"><bean:message key="id"/></td>
		<td height="10"  width="40%" class="tabletableheader"><bean:message key="parameter.name"/></td>
		<td height="10"  width="5%" class="tabletableheader" colspan="2"><bean:message key="actions"/></td> 
	</tr>
	
	 <logic:iterate  id="parameter"  name="parameterForm" property="parametersCollection">
	 <%ParameterImpl param = (ParameterImpl) parameter;%>
	<tr>
		<td height="10" class="tabledata"><nobr><%=param.getParameterId()%></nobr></td>
		<td height="10" class="tabledata"><nobr><%=param.getLabel()%></nobr></td>
		<td height="10" class="tabledata-c"><nobr><a class="tabledata" href="<%=response.encodeURL("parameter.do?action=view&parameterId="+param.getParameterId())%>"><img src="images/icons/edit.png" border="0" title=<bean:message key="edit"/>></a></nobr></td> 
		<td height="10" class="tabledata-c"><nobr><a class="tabledata" href="<%=response.encodeURL("parameter.do?action=removeParameter&parameterId="+param.getParameterId())%>" onClick="return window.confirm('<%=(i18nService.fetch("delete.confirm",session)+" "+param.getParameterId())%>');"><img src="images/icons/remove.png" border="0" title=<bean:message key="remove"/>></a></nobr></td>
	</tr>
	</logic:iterate>	
	
		<tr>
	<td height="10"  colspan="12"  class="tabledata"><table border="0" width="100%" cellspacing="7">
		<tr><td class="tabledata"><input type="submit" value='<bean:message key="add"/>'></td>
		</tr></table></td>
		</tr>		
		</table></td>
	<td width="6" heigh="10" style="background-image: url(images/table5.gif);"></td>
	</tr>
	</table>
</td></tr>
</html:form>

<tr><td>
	<table width="100%" cellspacing="0" cellpadding="0"><tr>
	<td><img src="images/table6.gif"></td>
	<td width="100%" heigh="10" style="background-image: url(images/table8.gif);"></td>
	<td><img src="images/table7.gif"></td>
	</tr></table>
</td></tr>
