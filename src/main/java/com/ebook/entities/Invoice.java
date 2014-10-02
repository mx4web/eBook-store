/*
 * 
 */

package com.ebook.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "invoice", catalog = "ebookstore_jianyu", schema = "")
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoiceID", query = "SELECT i FROM Invoice i WHERE i.invoiceID = :invoiceID"),
    @NamedQuery(name = "Invoice.findByInvoiceDate", query = "SELECT i FROM Invoice i WHERE i.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "Invoice.findBySubTotal", query = "SELECT i FROM Invoice i WHERE i.subTotal = :subTotal"),
    @NamedQuery(name = "Invoice.findByGst", query = "SELECT i FROM Invoice i WHERE i.gst = :gst"),
    @NamedQuery(name = "Invoice.findByDiscount", query = "SELECT i FROM Invoice i WHERE i.discount = :discount"),
    @NamedQuery(name = "Invoice.findByInvoiceTotal", query = "SELECT i FROM Invoice i WHERE i.invoiceTotal = :invoiceTotal")})
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InvoiceID")
    private Integer invoiceID;
    @Column(name = "InvoiceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SubTotal")
    private BigDecimal subTotal;
    @Column(name = "GST")
    private BigDecimal gst;
    @Column(name = "Discount")
    private BigDecimal discount;
    @Column(name = "InvoiceTotal")
    private BigDecimal invoiceTotal;
    @JoinColumn(name = "ClientID", referencedColumnName = "ClientID")
    @ManyToOne
    private Clients clientID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceID")
    private List<Invoicedetails> invoicedetailsList;

    public Invoice() {
    }

    public Invoice(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getGst() {
        return gst;
    }

    public void setGst(BigDecimal gst) {
        this.gst = gst;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public Clients getClientID() {
        return clientID;
    }

    public void setClientID(Clients clientID) {
        this.clientID = clientID;
    }

    public List<Invoicedetails> getInvoicedetailsList() {
        return invoicedetailsList;
    }

    public void setInvoicedetailsList(List<Invoicedetails> invoicedetailsList) {
        this.invoicedetailsList = invoicedetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceID != null ? invoiceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoiceID == null && other.invoiceID != null) || (this.invoiceID != null && !this.invoiceID.equals(other.invoiceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cejv539.ebook.entities.Invoice[ invoiceID=" + invoiceID + " ]";
    }

}
