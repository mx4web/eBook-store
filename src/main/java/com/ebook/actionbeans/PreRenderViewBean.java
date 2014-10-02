package com.ebook.actionbeans;

import com.ebook.beans.BookBean;
import com.ebook.beans.SiteBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;

@Named
@RequestScoped
public class PreRenderViewBean {

    @Inject
    SiteBean siteBean;

    @Inject
    BookBean bookBean;

    /**
     * retrieve last viewed books cookie
     *
     */
    public void retrieveCookies() {
        FacesContext context = FacesContext.getCurrentInstance();

        // Retrieve a specific cookie
        Object lastViewedBooksCookie = context.getExternalContext().getRequestCookieMap().get("LastViewedBooksCookie");
        if (lastViewedBooksCookie != null) {
            siteBean.setLastViewedBooks(((Cookie) lastViewedBooksCookie).getValue());
        } else {
            siteBean.setLastViewedBooks("");
        }
    }

    /**
     * update last viewed books cookie with current book id
     *
     */
    public void writeCookie() {

        retrieveCookies();

        FacesContext context = FacesContext.getCurrentInstance();
        String cookieValue;

        // \s matches any white space
        // convert string " item1 , item2 , item3 , " to array ["item1", "item2", "item3"]
        String[] booklist = siteBean.getLastViewedBooks().split("[\\s,]+");

        // remove current book id from list
        List<String> books = new ArrayList<>();
        for (String book : Arrays.asList(booklist)) {
            if (!book.equals(bookBean.getBookID().toString())) {
                books.add(book);
            }
        }

        if (books.size() > 1) {
            cookieValue = bookBean.getBookID() + "," + books.get(0) + "," + books.get(1);
        } else if (books.size() == 1) {
            cookieValue = bookBean.getBookID() + "," + books.get(0);
        } else {
            cookieValue = bookBean.getBookID().toString();
        }

        siteBean.setLastViewedBooks(cookieValue);

        context.getExternalContext().addResponseCookie("LastViewedBooksCookie", cookieValue, null);
    }

}
