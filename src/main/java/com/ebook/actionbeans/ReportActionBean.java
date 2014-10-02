package com.ebook.actionbeans;

import com.ebook.beans.ReportBean;
import com.ebook.entities.Invoice;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 */
@Named
@SessionScoped
public class ReportActionBean implements Serializable {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ReportBean reportBean;

    /**
     * get Top Sales Report Details
     *
     * @return
     */
    public String getSales() {

        TypedQuery<Object[]> query;
        query = entityManager.createQuery("SELECT count(i.bookID) as bookCount,i.bookID,b.title, sum(i.price) as cost "
                + "FROM Invoicedetails i inner join i.bookID b inner join i.invoiceID inv"
                + " WHERE inv.invoiceDate >= :startDate AND inv.invoiceDate <= :endDate"
                + " GROUP BY i.bookID,b.title", Object[].class);

        Calendar cal = Calendar.getInstance();
        cal.setTime(reportBean.getEndDate());
        cal.add(Calendar.DATE, 1);

        query.setParameter("startDate", reportBean.getStartDate(), TemporalType.DATE);
        query.setParameter("endDate", cal.getTime(), TemporalType.DATE);

        reportBean.setTopSalesReport(query.getResultList());

        getSalesTotal();

        return "topSalesReport";
    }

    /**
     * get Top Sales Total
     *
     */
    public void getSalesTotal() {

        TypedQuery<Object[]> query;
        query = entityManager.createQuery("SELECT  sum(i.invoiceTotal) as totalCost, sum(i.gst) as totalGST, "
                + " sum(i.subTotal) as totalSub, sum(i.discount) as totalDiscount FROM Invoice i"
                + "  WHERE i.invoiceDate >= :startDate AND i.invoiceDate <= :endDate ", Object[].class);
        Calendar cal = Calendar.getInstance();
        cal.setTime(reportBean.getEndDate());
        cal.add(Calendar.DATE, 1);

        query.setParameter("startDate", reportBean.getStartDate(), TemporalType.DATE);
        query.setParameter("endDate", cal.getTime(), TemporalType.DATE);

        Object[] result = query.getSingleResult();
        
        reportBean.setTotalSales((BigDecimal)result[0]);
        reportBean.setTotalGST((BigDecimal)result[1]);
        reportBean.setTotalSub((BigDecimal)result[2]);
        reportBean.setTotalDiscount((BigDecimal)result[3]);

    }

    /**
     * get Top Seller Report Details
     *
     * @return
     */
    public String getTopSeller() {

        TypedQuery<Object[]> query;
        query = entityManager.createQuery("SELECT count(i.bookID) as bookCount,i.bookID,b.title"
                + " FROM Invoicedetails i inner join i.bookID b inner join i.invoiceID inv"
                + " WHERE inv.invoiceDate >= :startDate AND inv.invoiceDate <= :endDate"
                + " GROUP BY i.bookID,b.title ORDER BY bookCount DESC ", Object[].class);

        Calendar cal = Calendar.getInstance();
        cal.setTime(reportBean.getEndDate());
        cal.add(Calendar.DATE, 1);

        query.setParameter("startDate", reportBean.getStartDate(), TemporalType.DATE);
        query.setParameter("endDate", cal.getTime(), TemporalType.DATE);

        reportBean.setTopSellerReport(query.getResultList());

        return "topSellerReport";

    }

    /**
     * get Top Client Report Details
     *
     * @return
     */
    public String getTopClients() {

        TypedQuery<Object[]> query;
        query = entityManager.createQuery("SELECT i.clientID,c.firstName,c.lastName"
                + ", sum(i.invoiceTotal) as cost FROM Invoice i inner join i.clientID c "
                + "  WHERE i.invoiceDate >= :startDate AND i.invoiceDate <= :endDate"
                + " GROUP BY i.clientID,c.firstName,c.lastName"
                + " ORDER BY cost DESC", Object[].class);

        Calendar cal = Calendar.getInstance();
        cal.setTime(reportBean.getEndDate());
        cal.add(Calendar.DATE, 1);

        query.setParameter("startDate", reportBean.getStartDate(), TemporalType.DATE);
        query.setParameter("endDate", cal.getTime(), TemporalType.DATE);

        reportBean.setTopClientsReport(query.getResultList());
        getSalesTotal();

        return "topClientsReport";
    }

    /**
     * get orders within the defined date range
     *
     * @return
     */
    public List<Invoice> getOrders() {

        TypedQuery<Invoice> query;
        query = entityManager.createQuery("select i from Invoice i where i.invoiceDate >= :startDate AND i.invoiceDate <= :endDate order by i.invoiceDate desc", Invoice.class);

        Calendar cal = Calendar.getInstance();
        cal.setTime(reportBean.getEndDate());
        cal.add(Calendar.DATE, 1);

        query.setParameter("startDate", reportBean.getStartDate(), TemporalType.DATE);
        query.setParameter("endDate", cal.getTime(), TemporalType.DATE);

        return query.getResultList();
    }

}
