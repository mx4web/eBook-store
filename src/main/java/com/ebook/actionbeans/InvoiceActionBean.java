package com.ebook.actionbeans;

import com.ebook.beans.ClientBean;
import com.ebook.beans.InvoiceBean;
import com.ebook.entities.Invoice;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 */
@Named
@SessionScoped
public class InvoiceActionBean implements Serializable {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ClientBean clientBean;

    @Inject
    private InvoiceBean invoiceBean;

    /**
     * get order history of the user/client
     *
     * @return
     */
    public List<Invoice> getOrdersByClient() {

        TypedQuery<Invoice> query;

        query = entityManager.createQuery("SELECT i FROM Invoice i join i.clientID c where c.clientID=?1", Invoice.class);
        query.setParameter(1, clientBean.getClientID());

        List<Invoice> invoice = query.getResultList();
        return invoice;

    }

    /**
     * get order details by Invoice id
     *
     * @param invoiceID
     * @return
     */
    public List<Object[]> getOrdersDetailsByID(int invoiceID) {

        TypedQuery<Object[]> query;

        query = entityManager.createQuery("SELECT id.invoiceID, id.price, b.bookID,b.title,b.isbn "
                + "FROM Invoicedetails id inner join id.invoiceID i inner join id.bookID b "
                + "WHERE i.invoiceID=:invoiceID", Object[].class);
        query.setParameter("invoiceID", invoiceID);

        List<Object[]> invoiceDetails = query.getResultList();

        return invoiceDetails;

    }


    /**
     * get order history of the user/client
     *
     * @param invoiceID
     * @return
     */
    public List<Invoice> getOrdersByID(int invoiceID) {

        TypedQuery<Invoice> query;

        query = entityManager.createQuery("SELECT i.gst,i.discount,i.subTotal,i.invoiceTotal FROM Invoice i WHERE i.invoiceID=?1", Invoice.class);
        query.setParameter(1, invoiceID);

        List<Invoice> invoice = query.getResultList();
        return invoice;

    }
    
    /**
     * get all purchased ebooks of the user/client
     *
     * @return
     */
    public List<Object[]> getEBooksByClient() {

        TypedQuery<Object[]> query;

        query = entityManager.createQuery("SELECT distinct b.title FROM Invoicedetails id inner join id.invoiceID i " +
                " inner join id.bookID b inner join i.clientID c where c.clientID= ?1 order by b.title", Object[].class);
        query.setParameter(1, clientBean.getClientID());

        List<Object[]> invoice = query.getResultList();
        return invoice;

    }

}
