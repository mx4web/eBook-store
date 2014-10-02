package com.ebook.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

/**
 * 
 */
@Named(value = "cartDetailBean")
@RequestScoped
public class CartDetailBean implements Serializable{
    private int bookID;
    private String title;
    private BigDecimal price;

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

   
   
}
