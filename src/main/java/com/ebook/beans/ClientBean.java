package com.ebook.beans;

import com.ebook.actionbeans.CartActionBean;
import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 */

/*
 Client bean
 */
@Named(value = "clientBean")
@SessionScoped
public class ClientBean implements Serializable {

    private Integer clientID;
    private String title;
    private String lastName;
    private String firstName;
    private String companyName;
    private String address1;
    private String address2;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String homePhone;
    private String cellPhone;
    private String email;
    private String password;
    private String userName;
    private int privilegeID=1; //0 administrator, 1 customer

    private boolean loggedIn;
    private String invalidLogin = "";
    private String referencePage = "";
    
    @Inject
    CartActionBean cartActionBean;


    /**
     * Creates a new instance of ClientBean
     */
    public ClientBean() {
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }
    
    /**
     * check if logged in as admin user
     * 
     * @return 
     */
    public boolean isLoggedInAsAdmin() {
        return (this.loggedIn && this.privilegeID == 0);
    }

    /**/
    public void login() {
        this.loggedIn = true;

    }

    /**
     * logout and reset client bean
     * 
     * @return 
     */
    public String logout() {
        this.loggedIn = false;
        this.clientID = 0;
        this.title = "";
        this.lastName = "";
        this.firstName = "";
        this.companyName = "";
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.province = "";
        this.country = "";
        this.postalCode = "";
        this.homePhone = "";
        this.cellPhone = "";
        this.email = "";
        this.password = "";
        this.userName = "";
        this.privilegeID = 1;
        this.referencePage = "";
        
        //clear shopping cart after logout
        cartActionBean.clearCart();

        return "login";
    }

    public void checkLogin(ComponentSystemEvent event) {
        if (!loggedIn) {
            FacesContext context = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
            handler.performNavigation("login");
        }
    }

    public String getInvalidLogin() {
        return invalidLogin;
    }

    public void setInvalidLogin(String invalidLogin) {
        this.invalidLogin = invalidLogin;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPrivilegeID() {
        return privilegeID;
    }

    public void setPrivilegeID(int privilegeID) {
        this.privilegeID = privilegeID;
    }

    public String getReferencePage() {
        return referencePage;
    }

    public void setReferencePage(String referencePage) {
        this.referencePage = referencePage;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.clientID);
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + Objects.hashCode(this.lastName);
        hash = 41 * hash + Objects.hashCode(this.firstName);
        hash = 41 * hash + Objects.hashCode(this.companyName);
        hash = 41 * hash + Objects.hashCode(this.address1);
        hash = 41 * hash + Objects.hashCode(this.address2);
        hash = 41 * hash + Objects.hashCode(this.city);
        hash = 41 * hash + Objects.hashCode(this.province);
        hash = 41 * hash + Objects.hashCode(this.country);
        hash = 41 * hash + Objects.hashCode(this.postalCode);
        hash = 41 * hash + Objects.hashCode(this.homePhone);
        hash = 41 * hash + Objects.hashCode(this.cellPhone);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.password);
        hash = 41 * hash + Objects.hashCode(this.userName);
        hash = 41 * hash + this.privilegeID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientBean other = (ClientBean) obj;
        if (!Objects.equals(this.clientID, other.clientID)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.address1, other.address1)) {
            return false;
        }
        if (!Objects.equals(this.address2, other.address2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        if (!Objects.equals(this.homePhone, other.homePhone)) {
            return false;
        }
        if (!Objects.equals(this.cellPhone, other.cellPhone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (this.privilegeID != other.privilegeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientBean{" + "clientID=" + clientID + ", title=" + title + ", lastName=" + lastName + ", firstName=" + firstName + ", companyName=" + companyName + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", province=" + province + ", country=" + country + ", postalCode=" + postalCode + ", homePhone=" + homePhone + ", cellPhone=" + cellPhone + ", email=" + email + ", password=" + password + ", userName=" + userName + ", privilegeID=" + privilegeID + '}';
    }

}
