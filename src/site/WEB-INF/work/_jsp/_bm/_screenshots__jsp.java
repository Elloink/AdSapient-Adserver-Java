/*
 * JSP generated by Resin-3.0.19 (built Mon, 15 May 2006 04:50:47 PDT)
 */

package _jsp._bm;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class _screenshots__jsp extends com.caucho.jsp.JavaPage{
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.Application _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = com.caucho.jsp.QJspFactory.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html");
    try {
      out.write(_jsp_string0, 0, _jsp_string0.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      com.caucho.jsp.QJspFactory.freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.make.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.util.CauchoSystem.getVersionId() != 7932908523769947432L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.make.Dependency depend;
      depend = (com.caucho.make.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("bm/screenshots.jsp"), 5377018934092260888L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("bm/links.jsp"), 707448061384083527L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("copyrights.jsp"), -8144456906676724675L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  private final static char []_jsp_string0;
  static {
    _jsp_string0 = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n\r\n<html>\r\n<head>\r\n	<title>AdSapient</title>\r\n	<link rel=\"STYLESHEET\" type=\"text/css\" href=\"..\\adsapient.css\">\r\n</head>\r\n\r\n<body>\r\n<table class=\"main_table\">\r\n	<tr>\r\n		<td class=\"main_table_top\">\r\n			<table class=\"table_center\" align=\"center\">\r\n				<tr>\r\n					<td></td>\r\n				</tr>\r\n			</table>\r\n		</td>\r\n	</tr>\r\n	<tr>\r\n		<td class=\"main_table_top_links\">\r\n			<table class=\"table_center_links\" align=\"center\">\r\n				<tr>\r\n					<td><a href=\"../index.jsp\" class=\"links_text\">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n<a href=\"../products.jsp\" class=\"links_text\">Products</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n<a href=\"../support.jsp\" class=\"links_text\">Support</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n<a href=\"../contact.jsp\" class=\"links_text\">Contact</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n				</tr>\r\n			</table>\r\n		</td>\r\n	</tr>\r\n	<tr>\r\n		<td class=\"main_table_screen\">\r\n			<table class=\"table_center_screen\" align=\"center\">\r\n				<tr>\r\n					<td width=\"780\" height=\"100%\" valign=\"top\">\r\n					<!--Page Start-->\r\n					\r\n					<table width=\"780\" height=\"100%\" cellpadding=\"0\">\r\n							<tr>\r\n								<td width=\"10\"></td>\r\n								<td width=\"205\" valign=\"top\" align=\"left\">\r\n									<!-- Menu -->\r\n									<table width=\"215\" cellpadding=\"0\" cellspacing=\"0\">\r\n										<tr>\r\n											<td class=\"menu_top\"></td>\r\n										</tr>\r\n										<tr>\r\n											<td class=\"menu_middle\"><img src=\"../images/bm.gif\" align=\"left\" border=\"0\"><br>\r\n											<br><br><br>\r\n											<a href=\"../bm/index.jsp\" class=\"links_text2\">About AdSapient BM</a><br><br>\r\n											<a href=\"../bm/features.jsp\" class=\"links_text2\">Features</a><br><br>\r\n   											<a href=\"../bm/screenshots.jsp\" class=\"links_text2\">Screenshots</a><br><br>\r\n											<a href=\"../bm/install.jsp\" class=\"links_text2\">Sys.Req. and Installation</a><br><br>\r\n											<img src=\"../images/an.gif\" align=\"left\" border=\"0\"><br>\r\n											<br><br><br>\r\n											<a href=\"../an/index.jsp\" class=\"links_text2\">About AdSapient AN</a><br><br>\r\n											<a href=\"../an/features.jsp\" class=\"links_text2\">Features</a><br><br>\r\n   											<a href=\"../an/screenshots.jsp\" class=\"links_text2\">Screenshots</a><br><br>\r\n											<a href=\"../an/install.jsp\" class=\"links_text2\">Sys.Req. and Installation</a><br><br>\r\n											<br>\r\n											<br>\r\n											<a href=\"../products.jsp\" class=\"links_text\">Products Overview</a><br><br>\r\n   											<a href=\"../faq.jsp\" class=\"links_text\">FAQ</a><br><br>\r\n											<a href=\"../behavioral_targeting.jsp\" class=\"links_text\">Behavioral Targeting</a><br><br><br><br>\r\n											</td>\r\n										</tr>\r\n										<tr>\r\n											<td class=\"menu_bottom\"></td>\r\n										</tr>\r\n									</table>\r\n								</td>\r\n								<td width=\"30\"></td>\r\n								<td class=\"products_text\" align=\"left\" valign=\"top\">\r\n									<!-- BMScreens -->\r\n<strong>Screenshots</strong><br>\r\n<br>\r\n<table border=\"0\" width=\"100%\">\r\n<tr><td class=\"promotext\"><table border=\"0\" width=\"100%\" cellspacing=\"5\">\r\n\r\n<tr><td align=\"center\">\r\n<a class=\"links\" href=\"javascript: popupwindow_top=window.open('../images/ss1.gif','','toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,left='+(screen.availWidth/2-840/2)+',top='+(screen.availHeight/2-680/2)+',width=840,height=680'); window.popupwindow_top.focus();\"><img width=\"200\" border=\"1\" height=\"153\" src=\"../images/ss1_small.gif\"><br/>\r\nEdit Banner Settings Dialog</a>\r\n</td>\r\n<td align=\"center\">\r\n<a class=\"links\" href=\"javascript: popupwindow_top=window.open('../images/ss2.gif','','toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,left='+(screen.availWidth/2-840/2)+',top='+(screen.availHeight/2-680/2)+',width=840,height=680'); window.popupwindow_top.focus();\"><img width=\"200\" border=\"1\" height=\"153\" src=\"../images/ss2_small.gif\"><br/>\r\nEdit Campaign Settings Dialog</a>\r\n</td>\r\n</tr>\r\n\r\n<tr><td align=\"center\">\r\n<a class=\"links\" href=\"javascript: popupwindow_top=window.open('../images/ss3.gif','','toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,left='+(screen.availWidth/2-840/2)+',top='+(screen.availHeight/2-680/2)+',width=840,height=680'); window.popupwindow_top.focus();\"><img width=\"200\" border=\"1\" height=\"153\" src=\"../images/ss3_small.gif\"><br/>\r\nTargeting Options -> Content Filter Dialog</a>\r\n</td>\r\n<td align=\"center\">\r\n<a class=\"links\" href=\"javascript: popupwindow_top=window.open('../images/ss4.gif','','toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,left='+(screen.availWidth/2-840/2)+',top='+(screen.availHeight/2-680/2)+',width=840,height=680'); window.popupwindow_top.focus();\"><img width=\"200\" border=\"1\" height=\"153\" src=\"../images/ss4_small.gif\"><br/>\r\nTargeting Options -> Geo Filter Dialog</a>\r\n</td>\r\n</tr>\r\n\r\n<tr><td align=\"center\">\r\n<a class=\"links\" href=\"javascript: popupwindow_top=window.open('../images/ss5.gif','','toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,left='+(screen.availWidth/2-840/2)+',top='+(screen.availHeight/2-680/2)+',width=840,height=680'); window.popupwindow_top.focus();\"><img width=\"200\" border=\"1\" height=\"153\" src=\"../images/ss5_small.gif\"><br/>\r\nTargeting Options -> Traffic Filter Dialog</a>\r\n</td>\r\n<td align=\"center\">\r\n<a class=\"links\" href=\"javascript: popupwindow_top=window.open('../images/ss6.gif','','toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,left='+(screen.availWidth/2-840/2)+',top='+(screen.availHeight/2-680/2)+',width=840,height=680'); window.popupwindow_top.focus();\"><img width=\"200\" border=\"1\" height=\"153\" src=\"../images/ss6_small.gif\"><br/>\r\nPublishing Reports Dialog</a>\r\n</td>\r\n</tr>\r\n\r\n<tr><td align=\"center\">\r\n<a class=\"links\" href=\"javascript: popupwindow_top=window.open('../images/ss7.gif','','toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,left='+(screen.availWidth/2-840/2)+',top='+(screen.availHeight/2-680/2)+',width=840,height=680'); window.popupwindow_top.focus();\"><img width=\"200\" border=\"1\" height=\"153\" src=\"../images/ss7_small.gif\"><br/>\r\nPublishing Reports Dialog (continuation)</a>\r\n</td>\r\n<td align=\"center\">\r\n<a class=\"links\" href=\"javascript: popupwindow_top=window.open('../images/ss8.gif','','toolbar=0,location=0,status=0,menubar=0,scrollbars=1,resizable=1,left='+(screen.availWidth/2-840/2)+',top='+(screen.availHeight/2-680/2)+',width=840,height=680'); window.popupwindow_top.focus();\"><img width=\"200\" border=\"1\" height=\"153\" src=\"../images/ss8_small.gif\"><br/>\r\nEdit Ad Place Settings Dialog</a>\r\n</td>\r\n</tr>\r\n\r\n</td></tr></table></td></tr>\r\n\r\n\r\n</table>\r\n\r\n								</td>\r\n							</tr>\r\n						</table>\r\n					\r\n					\r\n					\r\n					\r\n					\r\n					\r\n					<!--Page End-->\r\n					</td>\r\n				</tr>\r\n			</table>\r\n		</td>\r\n	</tr>\r\n	<tr>\r\n		<td class=\"main_table_bottom_links\">\r\n			<table class=\"table_center_links\" align=\"center\">\r\n				<tr>\r\n					<td><a href=\"../index.jsp\" class=\"links_text\">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n<a href=\"../products.jsp\" class=\"links_text\">Products</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n<a href=\"../support.jsp\" class=\"links_text\">Support</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n<a href=\"../contact.jsp\" class=\"links_text\">Contact</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n				</tr>\r\n			</table>\r\n		</td>\r\n	</tr>\r\n	<tr>\r\n		<td class=\"main_table_bottom\">\r\n			<table class=\"table_center_bottom\" align=\"center\">\r\n				<tr>\r\n					<td class=\"table_center_bottom_text\">\u00a9 2007 AdSapient. All Rights Reserved. <a href=\"terms.jsp\" class=\"bottom_text\">Terms</a> | <a href=\"privacy.jsp\" class=\"bottom_text\">Privacy</a></td>\r\n				</tr>\r\n			</table>\r\n		</td>\r\n	</tr>\r\n</table>\r\n</body>\r\n</html>\r\n".toCharArray();
  }
}
