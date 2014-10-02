package com.ebook.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 */
@Named(value = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

    private List<CartDetailBean> cartDetails;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal gst;
    private BigDecimal total;
    private Boolean showPopup;

    public CartBean() {
        cartDetails = new ArrayList<>();
        subtotal = new BigDecimal("0");
        discount = new BigDecimal("0");
    }

    public List<CartDetailBean> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetailBean> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getGst() {
        return gst;
    }

    public void setGst(BigDecimal gst) {
        this.gst = gst;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(Boolean showPopup) {
        this.showPopup = showPopup;
    }

}
