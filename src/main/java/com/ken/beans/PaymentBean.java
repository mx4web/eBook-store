package com.ken.beans;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

@Named("payment")
@RequestScoped
public class PaymentBean implements Serializable {

    private static final long serialVersionUID = 7775666984664728089L;
    
    private double amount;
    private String name;
    private CreditCard card = new CreditCard("");
    private Date date = new Date();

    public void setAmount(double newValue) {
        amount = newValue;
    }

    public double getAmount() {
        return amount;
    }

    public void setCard(CreditCard newValue) {
        card = newValue;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setDate(Date newValue) {
        date = newValue;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
