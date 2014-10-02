package com.ebook.actionbeans;

import com.ebook.beans.AdminPageBean;
import com.ebook.beans.GenreBean;
import com.ebook.beans.InventoryBean;
import com.ebook.beans.SearchReviewBean;
import com.ebook.entities.Genre;
import com.ebook.entities.Inventory;
import java.io.Serializable;
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
 *
 */
@Named
@SessionScoped
public class InventoryActionBean implements Serializable {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private InventoryBean inventoryBean;

    @Inject
    private GenreBean genreBean;

    @Inject
    private AdminPageBean adminPageBean;
    
    @Inject 
    private SearchReviewBean searchReviewBean;

    /**
     * get list of all inventory
     *
     * @return
     */
    public List<Object[]> getInventory() {

        TypedQuery<Object[]> query;
        query = entityManager.createQuery("SELECT i.bookID,i.title,g.name,i.isbn,i.listPrice,i.publisher FROM Inventory i inner join i.genreID g where i.status=1", Object[].class);

        List<Object[]> inventory = query.getResultList();
        return inventory;

    }

    /**
     * get inventory by id
     *
     * @return
     */
    public List<Object[]> getInventoryByID() {

        TypedQuery<Object[]> query;
        query = entityManager.createQuery("SELECT i.bookID,i.title,g.name,i.isbn,i.listPrice,i.publisher FROM Inventory i inner join i.genreID g", Object[].class);

        List<Object[]> inventory = query.getResultList();
        return inventory;

    }

    /**
     * delete inventory by id, update the status to 0 in db
     *
     * @param bookID
     * @return
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     */
    public String deleteInventory(int bookID) throws SystemException, RollbackException {

        try {
            userTransaction.begin();

            TypedQuery<Inventory> query;

            query = entityManager.createQuery("update Inventory i set i.status = 0 where i.bookID = :bookID", Inventory.class);

            query.setParameter("bookID", bookID);

            query.executeUpdate();

            userTransaction.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException e) {
            Logger.getLogger(InventoryActionBean.class.getName()).log(Level.SEVERE, null, e);
            return "inventoryManagement";
        }
        return "inventoryManagement";

    }

    /**
     * get inventory by id
     *
     * @return
     */
    public String getInventoryByID(int bookID) {

        TypedQuery<Inventory> query;
        query = entityManager.createQuery("SELECT i FROM Inventory i WHERE i.bookID=?1", Inventory.class);
        query.setParameter(1, bookID);

        List<Inventory> inventory = query.getResultList();

        inventoryBean.setBookID(inventory.get(0).getBookID());
        inventoryBean.setIsbn(inventory.get(0).getIsbn());
        inventoryBean.setDescription(inventory.get(0).getDescription());
        inventoryBean.setGenreID(inventory.get(0).getGenreID());
        inventoryBean.setImage(inventory.get(0).getImage());
        inventoryBean.setListPrice(inventory.get(0).getListPrice());
        inventoryBean.setNumberOfPages(inventory.get(0).getNumberOfPages());
        inventoryBean.setPublisher(inventory.get(0).getPublisher());
        inventoryBean.setSoldQty(inventory.get(0).getSoldQty());
        inventoryBean.setAuthor(inventory.get(0).getAuthor());
        inventoryBean.setStatus(inventory.get(0).getStatus());
        inventoryBean.setTitle(inventory.get(0).getTitle());
        inventoryBean.setWholesalePrice(inventory.get(0).getWholesalePrice());

        adminPageBean.setButtonAction("1");

        return "productForm";

    }

    /**
     * insert inventory to db
     */
    public String insertInventoryForm() {
        boolean success = true;

        try {
            userTransaction.begin();

            Genre genre = new Genre();
            genre.setGenreID(searchReviewBean.getGenre());
            
            Inventory i = new Inventory();
            i.setIsbn(inventoryBean.getIsbn());
            i.setDescription(inventoryBean.getDescription());
            i.setGenreID(genre);
            i.setImage(inventoryBean.getImage());
            i.setListPrice(inventoryBean.getListPrice());
            i.setNumberOfPages(inventoryBean.getNumberOfPages());
            i.setPublisher(inventoryBean.getPublisher());
            i.setSoldQty(inventoryBean.getSoldQty());
            i.setAuthor(inventoryBean.getAuthor());
            i.setStatus(inventoryBean.getStatus());
            i.setTitle(inventoryBean.getTitle());
            i.setWholesalePrice(inventoryBean.getWholesalePrice());

            
            
            
            entityManager.persist(i);
            userTransaction.commit();

        } catch (Exception ex) {
            Logger.getLogger(InventoryBean.class.getName()).log(Level.SEVERE, null, ex);
            try {
                userTransaction.rollback();
                success = false;

            } catch (Exception ex1) {
                Logger.getLogger(InventoryActionBean.class.getName()).log(Level.SEVERE, null, ex1);
                success = false;
            }

        }

        return "inventoryManagement";
    }

    /**
     * save registration form to db
     * @return 
     */
    public String updateInventoryForm() {
        boolean success = true;
     
        try {
            userTransaction.begin();
            Genre genre = new Genre();
            genre.setGenreID(searchReviewBean.getGenre());
            
            System.out.println("Genre "+genre);
            
            Inventory i = entityManager.find(Inventory.class, inventoryBean.getBookID());
            i.setIsbn(inventoryBean.getIsbn());
            i.setDescription(inventoryBean.getDescription());
            i.setGenreID(genre);
            i.setImage(inventoryBean.getImage());
            i.setListPrice(inventoryBean.getListPrice());
            i.setNumberOfPages(inventoryBean.getNumberOfPages());
            i.setPublisher(inventoryBean.getPublisher());
            i.setSoldQty(inventoryBean.getSoldQty());
            i.setAuthor(inventoryBean.getAuthor());
            i.setStatus(inventoryBean.getStatus());
            i.setTitle(inventoryBean.getTitle());
            i.setWholesalePrice(inventoryBean.getWholesalePrice());

            userTransaction.commit();
        } catch (Exception ex) {
            Logger.getLogger(InventoryActionBean.class.getName()).log(Level.SEVERE, null, ex);
            try {
                userTransaction.rollback();
                success = false;

            } catch (Exception ex1) {
                Logger.getLogger(ClientActionBean.class.getName()).log(Level.SEVERE, null, ex1);
                success = false;
            }

        }
        return "inventoryManagement";

    }

}
