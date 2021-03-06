/*
 * AdSapient - Open Source Ad Server
 * http://www.sourceforge.net/projects/adsapient
 * http://www.adsapient.com
 *
 * Copyright (C) 2001-06 Vitaly Sazanovich
 * Vitaly.Sazanovich@gmail.com
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Library General Public License  as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */
package com.adsapient.gui.forms;

import com.adsapient.shared.mappable.AdminOptions;
import com.adsapient.shared.service.CookieManagementService;
import com.adsapient.shared.dao.HibernateEntityDao;
import com.adsapient.gui.ContextAwareGuiBean;

import org.apache.struts.action.ActionForm;


public class AdminOptionsActionForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String action = "init";
    private String mailServer;
    private String mailServerLogin;
    private String mailServerPassword;
    private String defaultTarget;
    private String defaultLoadingType;
    private String databaseLogin;
    private String databasePassword;
    private String databaseURL;
    private String databaseClass;
    private String type;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    public String getMailServerLogin() {
        return mailServerLogin;
    }

    public void setMailServerLogin(String mailServerLogin) {
        this.mailServerLogin = mailServerLogin;
    }

    public String getMailServerPassword() {
        return mailServerPassword;
    }

    public void setMailServerPassword(String mailServerPassword) {
        this.mailServerPassword = mailServerPassword;
    }

    public void initConnections() {
        HibernateEntityDao hibernateEntityDao =  (HibernateEntityDao) ContextAwareGuiBean.getContext().getBean("hibernateEntityDao");
        this.mailServer = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.MAIL_SERVER)).getItemValue();

        this.mailServerLogin = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.MAIL_SERVER_LOGIN)).getItemValue();

        this.mailServerPassword = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.MAIL_SERVER_PASSWORD)).getItemValue();

        this.databaseURL = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.DATABASE_URL)).getItemValue();
        this.databaseLogin = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.DATABASE_LOGIN)).getItemValue();
        this.databasePassword = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.DATABASE_PASSWORD)).getItemValue();
        this.databaseClass = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.DATABASE_CLASS)).getItemValue();
    }

    public void initDefaultOptions() {
        HibernateEntityDao hibernateEntityDao =  (HibernateEntityDao) ContextAwareGuiBean.getContext().getBean("hibernateEntityDao");
        this.defaultLoadingType = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.LOADING_TYPE)).getItemValue();
        this.defaultTarget = ((AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.TARGET_WINDOW)).getItemValue();
    }

    public void updateAdminOptions() {
        HibernateEntityDao hibernateEntityDao =  (HibernateEntityDao) ContextAwareGuiBean.getContext().getBean("hibernateEntityDao");
        if ("mail".equalsIgnoreCase(this.getType())) {
            AdminOptions mailServer = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                    AdminOptions.MAIL_SERVER);
            mailServer.setItemValue(this.mailServer);

            hibernateEntityDao.updateObject(mailServer);

            AdminOptions mailServerLogin = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                    AdminOptions.MAIL_SERVER_LOGIN);
            mailServerLogin.setItemValue(this.mailServerLogin);

            hibernateEntityDao.updateObject(mailServerLogin);

            AdminOptions mailServerPass = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                    AdminOptions.MAIL_SERVER_PASSWORD);
            mailServerPass.setItemValue(this.mailServerPassword);

            hibernateEntityDao.updateObject(mailServerPass);
        }

        if ("database".equalsIgnoreCase(this.type)) {
            AdminOptions dbUrl = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                    AdminOptions.DATABASE_URL);
            dbUrl.setItemValue(this.databaseURL);

            hibernateEntityDao.updateObject(dbUrl);

            AdminOptions dbLogin = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                    AdminOptions.DATABASE_LOGIN);
            dbLogin.setItemValue(this.databaseLogin);

            hibernateEntityDao.updateObject(dbLogin);

            AdminOptions dbPass = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                    AdminOptions.DATABASE_PASSWORD);
            dbPass.setItemValue(this.databasePassword);

            hibernateEntityDao.updateObject(dbPass);

            AdminOptions dbClass = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                    AdminOptions.DATABASE_CLASS);
            dbClass.setItemValue(this.databaseClass);

            hibernateEntityDao.updateObject(dbClass);
        }
    }

    public void updateSysSettings() {
        HibernateEntityDao hibernateEntityDao =  (HibernateEntityDao) ContextAwareGuiBean.getContext().getBean("hibernateEntityDao");
        AdminOptions targetWind = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.TARGET_WINDOW);
        targetWind.setItemValue(this.defaultTarget);
        hibernateEntityDao.updateObject(targetWind);

        AdminOptions defLoadType = (AdminOptions) hibernateEntityDao.loadObject(AdminOptions.class,
                AdminOptions.LOADING_TYPE);
        defLoadType.setItemValue(this.defaultLoadingType);
        hibernateEntityDao.updateObject(defLoadType);
    }

    public String getDefaultLoadingType() {
        return defaultLoadingType;
    }

    public void setDefaultLoadingType(String defaultLoadingType) {
        this.defaultLoadingType = defaultLoadingType;
    }

    public String getDefaultTarget() {
        return defaultTarget;
    }

    public void setDefaultTarget(String defaultTarget) {
        this.defaultTarget = defaultTarget;
    }

    public String getDatabaseLogin() {
        return databaseLogin;
    }

    public void setDatabaseLogin(String databaseLogin) {
        this.databaseLogin = databaseLogin;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseClass() {
        return databaseClass;
    }

    public void setDatabaseClass(String databaseClass) {
        this.databaseClass = databaseClass;
    }

    public String getDatabaseURL() {
        return databaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
