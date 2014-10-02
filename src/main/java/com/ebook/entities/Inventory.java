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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 */
@Entity
@Table(name = "inventory", catalog = "ebookstore_jianyu", schema = "")
@NamedQueries({
    @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i"),
    @NamedQuery(name = "Inventory.findByBookID", query = "SELECT i FROM Inventory i WHERE i.bookID = :bookID"),
    @NamedQuery(name = "Inventory.findByIsbn", query = "SELECT i FROM Inventory i WHERE i.isbn = :isbn"),
    @NamedQuery(name = "Inventory.findByTitle", query = "SELECT i FROM Inventory i WHERE i.title = :title"),
    @NamedQuery(name = "Inventory.findByAuthor", query = "SELECT i FROM Inventory i WHERE i.author = :author"),
    @NamedQuery(name = "Inventory.findByPublisher", query = "SELECT i FROM Inventory i WHERE i.publisher = :publisher"),
    @NamedQuery(name = "Inventory.findByNumberOfPages", query = "SELECT i FROM Inventory i WHERE i.numberOfPages = :numberOfPages"),
    @NamedQuery(name = "Inventory.findByImage", query = "SELECT i FROM Inventory i WHERE i.image = :image"),
    @NamedQuery(name = "Inventory.findByWholesalePrice", query = "SELECT i FROM Inventory i WHERE i.wholesalePrice = :wholesalePrice"),
    @NamedQuery(name = "Inventory.findByListPrice", query = "SELECT i FROM Inventory i WHERE i.listPrice = :listPrice"),
    @NamedQuery(name = "Inventory.findByCreatedDate", query = "SELECT i FROM Inventory i WHERE i.createdDate = :createdDate"),
    @NamedQuery(name = "Inventory.findByStatus", query = "SELECT i FROM Inventory i WHERE i.status = :status"),
    @NamedQuery(name = "Inventory.findBySoldQty", query = "SELECT i FROM Inventory i WHERE i.soldQty = :soldQty")})
public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BookID")
    private Integer bookID;
    @Size(max = 45)
    @Column(name = "ISBN")
    private String isbn;
    @Size(max = 255)
    @Column(name = "Title")
    private String title;
    @Size(max = 255)
    @Column(name = "Author")
    private String author;
    @Size(max = 255)
    @Column(name = "Publisher")
    private String publisher;
    @Column(name = "NumberOfPages")
    private Integer numberOfPages;
    @Size(max = 255)
    @Column(name = "Image")
    private String image;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WholesalePrice")
    private BigDecimal wholesalePrice;
    @Column(name = "ListPrice")
    private BigDecimal listPrice;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "Status")
    private Short status;
    @Lob
    @Size(max = 65535)
    @Column(name = "Description")
    private String description;
    @Column(name = "SoldQty")
    private Integer soldQty;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookID")
    private List<Reviews> reviewsList;
    @JoinColumn(name = "GenreID", referencedColumnName = "GenreID")
    @ManyToOne(optional = false)
    private Genre genreID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookID")
    private List<Invoicedetails> invoicedetailsList;

    public Inventory() {
    }

    public Inventory(Integer bookID) {
        this.bookID = bookID;
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

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public Genre getGenreID() {
        return genreID;
    }

    public void setGenreID(Genre genreID) {
        this.genreID = genreID;
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
        hash += (bookID != null ? bookID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;
        if ((this.bookID == null && other.bookID != null) || (this.bookID != null && !this.bookID.equals(other.bookID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cejv539.ebook.entities.Inventory[ bookID=" + bookID + " ]";
    }

}
