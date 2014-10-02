/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebook.beans;

import com.ebook.entities.Invoicedetails;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 */
@Named(value = "invoiceBean")
@SessionScoped
public class InvoiceBean implements Serializable {

    private int invoiceID;
    private Date invoiceDate;
    private int clientID;
    private BigDecimal subTotal;
    private double gst;
    private BigDecimal invoiceTotal;
    private List<Object[]> invoiceDetailsList;
    
    /**
     * Creates a new instance of InvoiceBean
     */
    public InvoiceBean() {
        subTotal = new BigDecimal("0");
        invoiceTotal = new BigDecimal("0");

    }

    
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public BigDecimal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public List<Object[]> getInvoiceDetailsList() {
        return invoiceDetailsList;
    }

    public void setInvoiceDetailsList(List<Object[]> invoiceDetailsList) {
        this.invoiceDetailsList = invoiceDetailsList;
    }

  
    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.invoiceID;
        hash = 97 * hash + Objects.hashCode(this.invoiceDate);
        hash = 97 * hash + this.clientID;
        hash = 97 * hash + Objects.hashCode(this.subTotal);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.gst) ^ (Double.doubleToLongBits(this.gst) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.invoiceTotal);
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
        final InvoiceBean other = (InvoiceBean) obj;
        if (this.invoiceID != other.invoiceID) {
            return false;
        }
        if (!Objects.equals(this.invoiceDate, other.invoiceDate)) {
            return false;
        }
        if (this.clientID != other.clientID) {
            return false;
        }
        if (!Objects.equals(this.subTotal, other.subTotal)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gst) != Double.doubleToLongBits(other.gst)) {
            return false;
        }
        if (!Objects.equals(this.invoiceTotal, other.invoiceTotal)) {
            return false;
        }
        return true;
    }

}
