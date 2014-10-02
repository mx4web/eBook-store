package com.ebook.beans;

import com.ebook.entities.Clients;
import com.ebook.entities.Inventory;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 */
@Named(value = "reviewBean")
@RequestScoped
public class ReviewBean implements Serializable {

    private Integer reviewID;
    private Date reviewDate;
    private Short rating;
    private String reviewText;
    private Short status;
    private Inventory book;
    private Clients client;

    /**
     * Creates a new instance of ReviewBean
     */
    public ReviewBean() {
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Inventory getBook() {
        return book;
    }

    public void setBook(Inventory book) {
        this.book = book;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
    

}
