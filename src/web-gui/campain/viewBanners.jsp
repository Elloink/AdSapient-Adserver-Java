<%@ page import="com.adsapient.gui.forms.BannerUploadActionForm"%>
<%@ page import="com.adsapient.shared.mappable.BannerRepresentation"%>
<%@ page import="com.adsapient.shared.service.I18nService"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.adsapient.gui.ContextAwareGuiBean"%>
<%@ page import="com.adsapient.shared.service.TotalsReportService"%>
<%@ page import="com.adsapient.shared.AdsapientConstants"%>
<%@ page import="com.adsapient.shared.mappable.SiteImpl"%>
<%@ page import="com.adsapient.shared.mappable.BannerImpl"%>
<%@ page import="com.adsapient.shared.service.LinkHelperService"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<% ApplicationContext ctx = ContextAwareGuiBean.getContext();%>
<% LinkHelperService lhs = (LinkHelperService) ctx.getBean("linkHelperService");%>
<% I18nService i18nService = (I18nService) ctx.getBean("i18nService");%>
<% TotalsReportService trs = (TotalsReportService) ctx.getBean("totalsReportsService");%>
<%BannerUploadActionForm form=(BannerUploadActionForm) request.getAttribute("bannerUploadActionForm");%>

<html:messages id="msg" message="true" header="messages.header" footer="messages.footer" >
 <bean:write name="msg" />
</html:messages>

<html:form action="upload.do" enctype="multipart/form-data">
<html:hidden property="action"/>
<html:hidden property="campainId"/>
<tr><td>
	<table width="100%"cellspacing="0" cellpadding="0"><tr>
	<td><img src="images/table1.gif"></td>
	<td width="100%" class="tableheader">
	<%if(form.getCampainId()==null){%> <bean:message key="banners.list.title"/> <%}else{%>
						<bean:message key="campaignbanners.list.title"/><%=form.getCampainId()%><%}%>
	</td>
	<td><img src="images/table2.gif"></td>
	</tr></table>
</td></tr>
<tr><td>
	<table width="100%" cellspacing="0" cellpadding="0">
	
	<tr>
	<td width="5" heigh="10" style="background-image: url(images/table4.gif);"></td>
		<td><table border="0" style="background-color:#ffffff;" cellspacing="1" width="100%"><tr>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message key="id"/></nobr></td>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message key="campaign.id"/></nobr></td>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message key="campaign.name"/></nobr></td>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message key="banner.name"/></nobr></td>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message key="banner.size"/></nobr></td>
	<td height="10"  width="10%" class="tabletableheader" ><nobr><bean:message key="cpm.cpc"/></nobr></td>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message	key="cpl.cps" /></nobr></td>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message key="status"/></nobr></td>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message key="views_clicks"/></nobr></td>
	<td height="10"  width="10%" class="tabletableheader"><nobr><bean:message key="leads.sales"/></nobr></td>
	<td width="5%" class="tabletableheader"><nobr><bean:message	key="spendings" /></nobr></td>
	<td height="10"  width="15%" class="tabletableheader" colspan="5"><bean:message key="actions"/></td>
		</tr>
		
 <logic:iterate  id="userBanner"  name="bannerUploadActionForm" property="userBanners">
<%BannerRepresentation banner= (BannerRepresentation) userBanner;%>
		<tr>
	<td height="10" class="tabledata-c"><nobr><%=banner.getBannerId()%></nobr></td>
	<td height="10" class="tabledata-c"><nobr><%=banner.getCampainId()%></nobr></td>
	<td height="10" class="tabledata-c"><%=banner.getCampainName()%></td><%--() taken out <nobr> tag--%>
	<td height="10" class="tabledata-c"><nobr><%=banner.getName()%></nobr></td>
	<td height="10" class="tabledata-c"><nobr><%=banner.getBannerSize()%></nobr></td>
	<td height="10" class="tabledata-c"><nobr><%=lhs.generateRates(session,banner,response)%></nobr></td>
	<td height="10" class="tabledata-c"><nobr><%=lhs.generateCPLRates(session,banner,response)%></nobr></td>
	<td height="10" class="tabledata-c"><nobr><%=banner.getStatusText(request)%></nobr></td>
	<td height="10" class="tabledata-c"><%=trs.getTotalUnitsByEntity(BannerImpl.class,banner.getBannerId(), AdsapientConstants.ADVIEW)%> | <%=trs.getTotalUnitsByEntity(BannerImpl.class,banner.getBannerId(), AdsapientConstants.CLICK)%></nobr></td>
	<td height="10" class="tabledata-c"><%=trs.getTotalUnitsByEntity(BannerImpl.class,banner.getBannerId(), AdsapientConstants.LEAD)%> | <%=trs.getTotalUnitsByEntity(BannerImpl.class,banner.getBannerId(), AdsapientConstants.SALE) %></nobr></td>
	<td height="10" class="tabledata-c"><nobr><%=trs.getTotalUnitsByEntity(BannerImpl.class,banner.getBannerId(), AdsapientConstants.EARNEDSPENT)%></nobr></td>
	<%--<td height="10" class="tabledata-c"><nobr><a class="tabledata" href="<%=response.encodeURL(banner.getReportsHref())%>"><img src="images/icons/reports.png" border="0" title=<bean:message key="reports"/>></a></nobr></td>
	<td height="10" class="tabledata-c"><nobr><%if (JSPHelper.checkResetAccess(request)){%><a class="tabledata" href="<%=response.encodeURL(banner.getResetStatisticHref())%>" onClick="return window.confirm('<%=Msg.fetch("reset.confirm",session)%>');">
	                                               <%}%><img src="images/icons/resetstats.gif" border="0" title=<bean:message key="reset.statistic"/>></a></nobr></td> --%>
	<td height="10" class="tabledata-c"><nobr><a class="tabledata" href="<%=response.encodeURL("filter.do?bannerId="+banner.getBannerId()+"&campainId="+banner.getCampainId()+"&userId="+banner.getUserId())%>"><img src="images/icons/targeting.png" border="0" title=<bean:message key="targeting"/>></a></td>
	<td height="10" class="tabledata-c"><nobr><a class="tabledata" href="<%=response.encodeURL(banner.getSettingsHref())%>"><img src="images/icons/edit.png" border="0" title=<bean:message key="edit"/>></a></nobr></td>
	 <td height="10" class="tabledata-c"><nobr><a class="tabledata" href="<%=response.encodeURL("totals.do?bannerId="+banner.getId())%>" onClick="return window.confirm('<%=(i18nService.fetch("reset.confirm",session))%>');"><img src="images/icons/resetstats.gif" border="0" title='<bean:message key="reset"/>'/></a></nobr></td>
     <td height="10" class="tabledata-c"><nobr><%if (banner.getDeleteHref()!=null){%><a class="tabledata" href="<%=response.encodeURL(banner.getDeleteHref())%>" onClick="return window.confirm('<%=(i18nService.fetch("delete.confirm",session)+" "+banner.getName())%>');"><img src="images/icons/remove.png" border="0" title=<bean:message key="remove"/>></a><%}%></nobr></td>

		</tr>
	</logic:iterate>
	
		<tr>
	<td height="10"  colspan="16"  class="tabledata"><table border="0" width="100%" cellspacing="7">
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
