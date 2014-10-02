/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebook.beans;

import com.ebook.actionbeans.EBookActionBean;
import com.ebook.entities.Genre;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 */
@Named(value = "inventoryBean")
@SessionScoped
public class InventoryBean implements Serializable {

    private Integer bookID;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer numberOfPages;
    private String image;
    private BigDecimal wholesalePrice;
    private BigDecimal listPrice;
    private Date createdDate;
    private Short status;
    private String description;
    private Integer soldQty;
    private Genre genreID;

    public InventoryBean() {
        bookID = 0;
        isbn = "";
        title = "";
        author = "";
        publisher = "";
        numberOfPages = 0;
        image = "";
        wholesalePrice = new BigDecimal("0");
        listPrice = new BigDecimal("0");
        status = 0;
        description = "";
        soldQty = 0;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(Integer soldQty) {
        this.soldQty = soldQty;
    }

    public Genre getGenreID() {
        return genreID;
    }

    public void setGenreID(Genre genreID) {
        this.genreID = genreID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.bookID);
        hash = 61 * hash + Objects.hashCode(this.isbn);
        hash = 61 * hash + Objects.hashCode(this.title);
        hash = 61 * hash + Objects.hashCode(this.author);
        hash = 61 * hash + Objects.hashCode(this.publisher);
        hash = 61 * hash + Objects.hashCode(this.numberOfPages);
        hash = 61 * hash + Objects.hashCode(this.image);
        hash = 61 * hash + Objects.hashCode(this.wholesalePrice);
        hash = 61 * hash + Objects.hashCode(this.listPrice);
        hash = 61 * hash + Objects.hashCode(this.createdDate);
        hash = 61 * hash + Objects.hashCode(this.status);
        hash = 61 * hash + Objects.hashCode(this.description);
        hash = 61 * hash + Objects.hashCode(this.soldQty);
        hash = 61 * hash + Objects.hashCode(this.genreID);
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
        final InventoryBean other = (InventoryBean) obj;
        if (!Objects.equals(this.bookID, other.bookID)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.numberOfPages, other.numberOfPages)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.wholesalePrice, other.wholesalePrice)) {
            return false;
        }
        if (!Objects.equals(this.listPrice, other.listPrice)) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.soldQty, other.soldQty)) {
            return false;
        }
        if (!Objects.equals(this.genreID, other.genreID)) {
            return false;
        }
        return true;
    }

}
