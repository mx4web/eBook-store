package com.ebook.actionbeans;

import com.ebook.beans.BookBean;
import com.ebook.beans.ClientBean;
import com.ebook.beans.ReviewBean;
import com.ebook.beans.SearchReviewBean;
import com.ebook.entities.Clients;
import com.ebook.entities.Genre;
import com.ebook.entities.Inventory;
import com.ebook.entities.Reviews;
import java.io.Serializable;
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
 *
 */
@Named
@SessionScoped
public class ReviewActionBean implements Serializable {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private SearchReviewBean searchReviewBean;

    @Inject
    private ClientBean clientBean;

    @Inject
    private ReviewBean reviewBean;

    @Inject
    private BookBean bookBean;

    @Inject
    private EBookActionBean eBookActionBean;

    List<Integer> statusList = new ArrayList<>();
    
    Genre genre = new Genre();

    /**
     * search reviews by book title, genre and review status
     *
     * @return
     */
    public List<Object[]> searchReviews() {
        TypedQuery<Object[]> query;

        query = entityManager.createQuery("SELECT c.firstName, c.lastName, r.reviewDate, r.rating, r.reviewText, r.status, i.title, r.reviewID "
                + "FROM Reviews r inner join r.bookID i inner join r.clientID c "
                + "where i.title like :title and r.status in :status and i.genreID in :genre "
                + "order by i.title", Object[].class);

        statusList.clear();

        if (searchReviewBean.getReviewStatus().equalsIgnoreCase("approved")) {
            statusList.add(1);
        } else if (searchReviewBean.getReviewStatus().equalsIgnoreCase("unapproved")) {
            statusList.add(0);
        } else {
            statusList.add(0);
            statusList.add(1);
        }

        List<Genre> genreList = new ArrayList<>();

        if (searchReviewBean.getGenre() != 0) {
            genre.setGenreID(searchReviewBean.getGenre());
            genreList.add(genre);
            query.setParameter("genre", genreList);
        } else {
            genreList = eBookActionBean.getGenre();
        }

        query.setParameter("title", "%" + searchReviewBean.getTitle() + "%");
        query.setParameter("status", statusList);
        query.setParameter("genre", genreList);

        List<Object[]> reviews = query.getResultList();

        return reviews;
    }

    /**
     * delete review by review id
     *
     * @param reviewID
     */
    public void deleteReviews(int reviewID) {

        try {
            userTransaction.begin();

            TypedQuery<Reviews> query;

            query = entityManager.createQuery("delete from Reviews r where r.reviewID = :reviewID", Reviews.class);

            query.setParameter("reviewID", reviewID);

            query.executeUpdate();

            userTransaction.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException e) {
            Logger.getLogger(CartActionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * update review status, status 0: unapproved, status 1: approved
     *
     * @param reviewID
     * @param statusID
     */
    public void updateReviews(int reviewID, int statusID) {

        try {
            userTransaction.begin();

            TypedQuery<Reviews> query;

            query = entityManager.createQuery("update Reviews r set r.status = :statusID where r.reviewID = :reviewID", Reviews.class);

            query.setParameter("reviewID", reviewID);
            query.setParameter("statusID", statusID);

            query.executeUpdate();

            userTransaction.commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException e) {
            Logger.getLogger(CartActionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * get unapproved reviews count
     *
     * @return
     */
    public long getUnapprovedReviews() {
        TypedQuery<Long> query;

        query = entityManager.createQuery("SELECT count(r.reviewID) as reviewCount FROM Reviews r where r.status = 0", Long.class);

        long reviewCount = query.getSingleResult();

        return reviewCount;
    }

    /**
     * get reviews by book id
     *
     * @param bookID
     * @return
     */
    public List<Object[]> getReviewsByBook(int bookID) {
        TypedQuery<Object[]> query;

        query = entityManager.createQuery("SELECT c.firstName, c.lastName, r.reviewDate, r.rating, r.reviewText "
                + "FROM Reviews r inner join r.bookID i inner join r.clientID c where i.bookID = :bookID and r.status = 1", Object[].class);
        query.setParameter(
                "bookID", bookID);

        List<Object[]> reviews = query.getResultList();

        return reviews;
    }

    /**
     * save review to db
     */
    public void reviewBook() {
        try {
            userTransaction.begin();

            Clients clients = new Clients();
            clients.setClientID(clientBean.getClientID());

            Inventory book = new Inventory();
            book.setBookID(bookBean.getBookID());

            Reviews reviews = new Reviews();

            reviews.setBookID(book);
            reviews.setClientID(clients);
            reviews.setRating(reviewBean.getRating());
            reviews.setReviewDate(new Date());
            reviews.setReviewText(reviewBean.getReviewText());
            reviews.setStatus((short) 0);

            entityManager.persist(reviews);

            userTransaction.commit();

            reviewBean.setRating((short) 0);
            reviewBean.setReviewText("");

        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | IllegalStateException | RollbackException | SecurityException ex) {
            Logger.getLogger(CartActionBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
