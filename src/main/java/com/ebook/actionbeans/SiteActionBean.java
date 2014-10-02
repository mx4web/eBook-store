/*
 * 
 */
package com.ebook.actionbeans;

import com.ebook.beans.CartBean;
import com.ebook.beans.ClientBean;
import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 */
@Named(value = "siteActionBean")
@SessionScoped
public class SiteActionBean implements Serializable {

    @Inject
    ClientBean clientBean;

    @Inject
    CartBean cartBean;
    
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    /**
     * check if user has logged in
     *
     * @param event
     */
    public void isLoggedIn(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (!clientBean.isLoggedIn()) {

            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

            nav.performNavigation("login");
        }
    }

    /**
     * check if user has logged in as admin
     *
     * @param event
     */
    public void isLoggedInAsAdmin(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (!clientBean.isLoggedIn() || clientBean.getPrivilegeID() == 1) {

            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

            nav.performNavigation("page404");
        }
    }

    /**
     * check if can view checkout page
     *
     * @param event
     */
    public void canCheckout(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

        if (!clientBean.isLoggedIn()) {
            nav.performNavigation("login");
        } else if (cartBean.getCartDetails().isEmpty()) {
            nav.performNavigation("shoppingcart");
        }

    }

    public String frenchAction() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(Locale.CANADA_FRENCH);
        return null;
    }
    
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language, "CA");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
