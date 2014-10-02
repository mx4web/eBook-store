package com.ebook.actionbeans;

import com.ebook.beans.BookBean;
import com.ebook.beans.GenreBean;
import com.ebook.beans.SearchBean;
import com.ebook.beans.SiteBean;
import com.ebook.entities.Genre;
import com.ebook.entities.Inventory;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static com.ebook.constants.EBookConstants.RECENT_BOOKS_QTY;
import static com.ebook.constants.EBookConstants.TOP_SELLERS_QTY;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.TemporalType;

/**
 * action bean for page queries
 *
 */
@Named
@SessionScoped
public class EBookActionBean implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private GenreBean genreBean;

    @Inject
    private SearchBean searchBean;

    @Inject
    private BookBean bookBean;

    @Inject
    private SiteBean siteBean;

    @Inject
    private PreRenderViewBean preRenderViewBean;

    /**
     * get all genres to a list
     *
     * @return
     */
    public List<Genre> getGenre() {

        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g", Genre.class);

        List<Genre> genres = query.getResultList();

        return genres;
    }

    /**
     * get all books from selected genre, or get all books from inventory if
     * there is no genre provided
     *
     * @return
     */
    public List<Inventory> getBooksByGenreID() {

        TypedQuery<Inventory> query;

        if (genreBean.getGenreID() != 0) {
            query = entityManager.createQuery("SELECT i FROM Inventory i, Genre g where i.genreID.genreID = g.genreID and i.status = 1 and g.genreID = ?1", Inventory.class);
            query.setParameter(1, genreBean.getGenreID());
        } else {
            query = entityManager.createQuery("SELECT i FROM Inventory i where i.status = 1", Inventory.class);
        }

        List<Inventory> books = query.getResultList();

        return books;
    }

    /**
     * get recent books from selected genre, or get recent books from inventory
     * if there is no genre provided
     *
     * @return
     */
    public List<Inventory> getRecentBooks() {

        TypedQuery<Inventory> query;

        if (genreBean.getGenreID() != 0) {
            query = entityManager.createQuery("SELECT i FROM Inventory i, Genre g where i.genreID.genreID = g.genreID and i.status = 1 and g.genreID = ?1 order by i.createdDate desc", Inventory.class);
            query.setParameter(1, genreBean.getGenreID());
        } else {
            query = entityManager.createQuery("SELECT i FROM Inventory i where i.status = 1 order by i.createdDate desc", Inventory.class);
        }

        query.setMaxResults(RECENT_BOOKS_QTY);

        List<Inventory> books = query.getResultList();

        return books;
    }

    /**
     * get top sellers from selected genre, or get top sellers from inventory if
     * there is no genre provided
     *
     * @return
     */
    public List<Inventory> getTopSellers() {

        TypedQuery<Inventory> query;

        if (genreBean.getGenreID() != 0) {
            query = entityManager.createQuery("SELECT i FROM Inventory i, Genre g where i.genreID.genreID = g.genreID and i.status = 1 and g.genreID = ?1 order by i.soldQty desc", Inventory.class);
            query.setParameter(1, genreBean.getGenreID());
        } else {
            query = entityManager.createQuery("SELECT i FROM Inventory i where i.status = 1 order by i.soldQty desc", Inventory.class);
        }

        query.setMaxResults(TOP_SELLERS_QTY);

        List<Inventory> books = query.getResultList();

        return books;
    }

    /**
     * get last viewed books
     *
     * @return
     */
    public List<Inventory> getLastViewedBooks() {
        List<Inventory> books = null;

        // retrieve last viewed books from cookie
        preRenderViewBean.retrieveCookies();

        if (siteBean.getLastViewedBooks() != null && siteBean.getLastViewedBooks().length() > 0) {

            TypedQuery<Inventory> query;

            query = entityManager.createQuery("SELECT i FROM Inventory i where i.bookID in :books and i.status = 1", Inventory.class);

            List<String> lvBooks = Arrays.asList(siteBean.getLastViewedBooks().split("[\\s,]+"));

            query.setParameter("books", lvBooks);

            books = query.getResultList();

            //re-order books list as the order of last viewed books cookie list
            List<Inventory> orderedBooks = new ArrayList<>();
            for (int i = 0; i < lvBooks.size(); i++) {
                for (Inventory book : books) {
                    if (book.getBookID().intValue() == Integer.valueOf(lvBooks.get(i)).intValue()) {
                        orderedBooks.add(book);
                        break;
                    }
                }
            }

            return orderedBooks;
        }

        return books;

    }

    public List<Inventory> getRecommendBooks() {
        List<Inventory> books = null;

        if (siteBean.getLastViewedBooks() != null && siteBean.getLastViewedBooks().length() > 0) {

            TypedQuery<Inventory> query;

            query = entityManager.createQuery("SELECT i FROM Inventory i where  i.status = 1 and i.genreID = ( SELECT ii.genreID FROM Inventory ii where ii.bookID = :bookID)", Inventory.class);

            String[] sBooks = siteBean.getLastViewedBooks().split("[\\s,]+");

            query.setParameter("bookID", Integer.valueOf(sBooks[0]));

            books = query.getResultList();
        }

        return books;

    }

    /**
     * basic search function
     *
     * @return
     */
    public List<Inventory> searchBooks() {

        TypedQuery<Inventory> query;

        if (genreBean.getGenreID() != 0) {
            query = entityManager.createQuery("SELECT i FROM Inventory i, Genre g where i.genreID.genreID = g.genreID and i.status = 1 and g.genreID = :genreID and i.title like :title", Inventory.class);
            query.setParameter("genreID", genreBean.getGenreID());
            query.setParameter("title", "%" + searchBean.getTitle() + "%");
        } else {
            query = entityManager.createQuery("SELECT i FROM Inventory i where i.title like :title", Inventory.class);
            query.setParameter("title", "%" + searchBean.getTitle() + "%");
        }

        List<Inventory> books = query.getResultList();

        return books;
    }

    /**
     * advance server function, based on title, author & isbn
     *
     * @return
     */
    public List<Inventory> searchBooksAdvance() {

        TypedQuery<Inventory> query;

        query = entityManager.createQuery("SELECT i FROM Inventory i where i.title like :title and i.status = 1 and i.author like :author and i.isbn like :isbn", Inventory.class);
        query.setParameter("title", "%" + searchBean.getTitle() + "%");
        query.setParameter("author", "%" + searchBean.getAuthor() + "%");
        query.setParameter("isbn", "%" + searchBean.getISBN() + "%");

        List<Inventory> books = query.getResultList();

        return books;
    }

    /**
     * get book detail by book id
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void getBookDetails() throws IllegalAccessException, InvocationTargetException {

        TypedQuery<Inventory> query;

        query = entityManager.createQuery("SELECT i FROM Inventory i where i.bookID = :bookID", Inventory.class);
        query.setParameter("bookID", bookBean.getBookID());
        query.setMaxResults(1);

        List<Inventory> book = query.getResultList();

        if (book.size() > 0) {
            bookBean.setBookID(book.get(0).getBookID());
            bookBean.setAuthor(book.get(0).getAuthor());
            bookBean.setCreatedDate(book.get(0).getCreatedDate());
            bookBean.setDescription(book.get(0).getDescription());
            bookBean.setImage(book.get(0).getImage());
            bookBean.setIsbn(book.get(0).getIsbn());
            bookBean.setListPrice(book.get(0).getListPrice());
            bookBean.setWholesalePrice(book.get(0).getWholesalePrice());
            bookBean.setNumberOfPages(book.get(0).getNumberOfPages());
            bookBean.setPublisher(book.get(0).getPublisher());
            bookBean.setStatus(book.get(0).getStatus());
            bookBean.setTitle(book.get(0).getTitle());
        } else {
            bookBean.setBookID(0);
            bookBean.setAuthor("");
            bookBean.setDescription("");
            bookBean.setImage("");
            bookBean.setIsbn("");
            bookBean.setStatus((short)0);
            bookBean.setTitle("");
        }
        //BeanUtils.copyProperties(book, bookBean);
    }

    /**
     * get submitted order count
     *
     * @return
     * @throws java.text.ParseException
     */
    public long getSubmittedOrders() throws ParseException {
        TypedQuery<Long> query;

        query = entityManager.createQuery("SELECT count(1) as orderCount FROM Invoice i where i.invoiceDate >= :today", Long.class);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date today = dateFormat.parse(dateFormat.format(new Date()));

        query.setParameter("today", today, TemporalType.DATE);

        long orderCount = query.getSingleResult();

        return orderCount;
    }
    
    /**
     * get special books
     *
     * @return
     */
    public List<Object[]> getSpecialBooks(){
        TypedQuery<Object[]> query;

        query = entityManager.createQuery("SELECT i.bookID, i.image, i.title, i.listPrice, i.wholesalePrice, (i.wholesalePrice / i.listPrice) as price FROM Inventory i order by price", Object[].class);

        List<Object[]> books = query.getResultList();

        return books;
    }

}
