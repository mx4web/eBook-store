package com.ebook.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 */
@Named(value = "adminPageBean")
@SessionScoped
public class AdminPageBean implements Serializable{
    
    private String buttonAction;

    public String getButtonAction() {
        return buttonAction;
    }

    public void setButtonAction(String buttonAction) {
        this.buttonAction = buttonAction;
    }
    
    
    
    
    
}
