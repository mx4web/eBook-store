package com.ebook.actionbeans;

import com.ebook.beans.CartDetailBean;
import com.ebook.beans.CartBean;
import com.ebook.beans.ClientBean;
import com.ebook.beans.InvoiceBean;
import static com.ebook.constants.EBookConstants.GST_RATE;
import com.ebook.entities.Clients;
import com.ebook.entities.Inventory;
import com.ebook.entities.Invoice;
import com.ebook.entities.Invoicedetails;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * action bean for shopping cart
 *
 */
@Named
@SessionScoped
public class CartActionBean implements Serializable {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private CartDetailBean cartDetailBean;

    @Inject
    private CartBean cartBean;
    
    @Inject
    private InvoiceBean invoiceBean;
    
    @Inject
    private ClientBean clientBean;

    BigDecimal subtotal = new BigDecimal("0");
    BigDecimal discount = new BigDecimal("0");
    BigDecimal gst = new BigDecimal("0");
    BigDecimal total = new BigDecimal("0");

    /**
     * add to shopping cart function by adding cartDetail bean to cart bean
     *
     * @return
     */
    public String addToCart() {

        List<CartDetailBean> cartDetails;

        CartDetailBean cb = new CartDetailBean();

        cb.setBookID(cartDetailBean.getBookID());
        cb.setTitle(cartDetailBean.getTitle());
        cb.setPrice(cartDetailBean.getPrice());

        cartDetails = cartBean.getCartDetails();

        for (int i = 0; i < cartDetails.size(); i++) {
            if (cartDetails.get(i).getBookID() == cb.getBookID()) {

                //if item already exists, do nothing for ebook style item
                //refresh cart subtotal
                refreshCart();

                return "shoppingcart";

            }
        }

        cartDetails.add(cb);

        cartBean.setCartDetails(cartDetails);

        //refresh cart subtotal
        refreshCart();

        return "shoppingcart";
    }

    /**
     * remove one cart line
     *
     * @param i
     * @return
     */
    public String removeCart(CartDetailBean i) {

        cartBean.getCartDetails().remove(i);

        //refresh cart subtotal
        refreshCart();

        return "shoppingcart";
    }

    /**
     * refresh current cart by calculate subtotal, apply promotion etc
     *
     * @return
     */
    public String refreshCart() {

        discount = new BigDecimal("0");
        subtotal = new BigDecimal("0");
        gst = new BigDecimal("0");
        total = new BigDecimal("0");

        //calculate subtotal
        for (int i = 0; i < cartBean.getCartDetails().size(); i++) {

            subtotal = subtotal.add(cartBean.getCartDetails().get(i).getPrice());

        }

        //apply promotion demo
        //25% discounts for purchase over $25
        if (subtotal.compareTo(new BigDecimal("25")) == 1) {
            discount = subtotal.multiply(new BigDecimal("0.25"), MathContext.DECIMAL128);
            subtotal = subtotal.subtract(discount);
        }

        gst = subtotal.multiply(GST_RATE, MathContext.DECIMAL128);
        total = subtotal.add(gst);

        cartBean.setDiscount(discount);
        cartBean.setSubtotal(subtotal);
        cartBean.setGst(gst);
        cartBean.setTotal(total);

        return "shoppingcart";
    }

    /**
     * save cart info to db, clear cart then redirect to thank you page
     *
     * @return
     */
    public String submitCart() {
        // to do
        // authorize cc payment here

        // save cart to db
        saveCart();

        // clear cart
        clearCart();

        return "thankyou";
    }

    /**
     * save shopping cart to db
     */
    public void saveCart() {
        try {
            userTransaction.begin();

            //save cart info
            Clients clients = new Clients();
            clients.setClientID(clientBean.getClientID());

            Invoice invoice = new Invoice();

            invoice.setClientID(clients);
            invoice.setGst(cartBean.getGst());
            invoice.setSubTotal(cartBean.getSubtotal());
            invoice.setDiscount(cartBean.getDiscount());
            invoice.setInvoiceTotal(cartBean.getTotal());
            invoice.setInvoiceDate(new Date());
            entityManager.persist(invoice);
            
            invoiceBean.setInvoiceID(invoice.getInvoiceID());

            //save cart details
            Invoicedetails invoicedetails;
            Inventory book;

            for (int i = 0; i < cartBean.getCartDetails().size(); i++) {
                invoicedetails = new Invoicedetails();

                TypedQuery<Inventory> query = entityManager.createNamedQuery("Inventory.findByBookID", Inventory.class);
                query.setParameter("bookID", cartBean.getCartDetails().get(i).getBookID());
                book = query.getSingleResult();

                invoicedetails.setInvoiceID(invoice);
                invoicedetails.setBookID(book);
                invoicedetails.setPrice(cartBean.getCartDetails().get(i).getPrice());

                entityManager.persist(invoicedetails);

                //update book sold quantity
                book.setSoldQty(book.getSoldQty() + 1);
                entityManager.persist(book);
            }

            userTransaction.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
            Logger.getLogger(CartActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * clear current shopping cart
     */
    public void clearCart() {
        cartBean.setDiscount(new BigDecimal("0"));
        cartBean.setSubtotal(new BigDecimal("0"));
        cartBean.setGst(new BigDecimal("0"));
        cartBean.setTotal(new BigDecimal("0"));
        List<CartDetailBean> cartDetails = new ArrayList<>();
        cartBean.setCartDetails(cartDetails);
    }
}
