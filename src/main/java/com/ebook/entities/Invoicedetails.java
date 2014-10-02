/*
 * 
 */

package com.ebook.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "invoicedetails", catalog = "ebookstore_jianyu", schema = "")
@NamedQueries({
    @NamedQuery(name = "Invoicedetails.findAll", query = "SELECT i FROM Invoicedetails i"),
    @NamedQuery(name = "Invoicedetails.findByInvoiceDetailID", query = "SELECT i FROM Invoicedetails i WHERE i.invoiceDetailID = :invoiceDetailID"),
    @NamedQuery(name = "Invoicedetails.findByPrice", query = "SELECT i FROM Invoicedetails i WHERE i.price = :price")})
public class Invoicedetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InvoiceDetailID")
    private Integer invoiceDetailID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @JoinColumn(name = "BookID", referencedColumnName = "BookID")
    @ManyToOne(optional = false)
    private Inventory bookID;
    @JoinColumn(name = "InvoiceID", referencedColumnName = "InvoiceID")
    @ManyToOne(optional = false)
    private Invoice invoiceID;

    public Invoicedetails() {
    }

    public Invoicedetails(Integer invoiceDetailID) {
        this.invoiceDetailID = invoiceDetailID;
    }

    public Integer getInvoiceDetailID() {
        return invoiceDetailID;
    }

    public void setInvoiceDetailID(Integer invoiceDetailID) {
        this.invoiceDetailID = invoiceDetailID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Inventory getBookID() {
        return bookID;
    }

    public void setBookID(Inventory bookID) {
        this.bookID = bookID;
    }

    public Invoice getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Invoice invoiceID) {
        this.invoiceID = invoiceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceDetailID != null ? invoiceDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoicedetails)) {
            return false;
        }
        Invoicedetails other = (Invoicedetails) object;
        if ((this.invoiceDetailID == null && other.invoiceDetailID != null) || (this.invoiceDetailID != null && !this.invoiceDetailID.equals(other.invoiceDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cejv539.ebook.entities.Invoicedetails[ invoiceDetailID=" + invoiceDetailID + " ]";
    }

}
