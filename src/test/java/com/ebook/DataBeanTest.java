/*
 * 
 */
package com.ebook;

import com.ebook.beans.AdminPageBean;
import com.ebook.beans.BookBean;
import com.ebook.beans.CartBean;
import com.ebook.beans.GenreBean;
import com.ebook.beans.InventoryBean;
import com.ebook.beans.InvoiceBean;
import com.ebook.beans.ReportBean;
import com.ebook.beans.SearchBean;
import com.ebook.beans.SearchReviewBean;
import com.ebook.beans.SiteBean;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 */
@RunWith(Arquillian.class)
public class DataBeanTest {

    @Inject
    BookBean bookBean;

    @Inject
    AdminPageBean adminPageBean;

    @Inject
    CartBean cartBean;

    @Inject
    GenreBean genreBean;

    @Inject
    InventoryBean inventoryBean;

    @Inject
    InvoiceBean invoiceBean;

    @Inject
    ReportBean reportBean;

    @Inject
    SearchBean searchBean;

    @Inject
    SearchReviewBean searchReviewBean;

    @Inject
    SiteBean siteBean;

    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class).
                addClasses(BookBean.class).
                addClasses(AdminPageBean.class).
                addClasses(CartBean.class).
                addClasses(GenreBean.class).
                addClasses(InventoryBean.class).
                addClasses(InvoiceBean.class).
                addClasses(ReportBean.class).
                addClasses(SearchBean.class).
                addClasses(SearchReviewBean.class).
                addClasses(SiteBean.class).
                addAsWebInfResource(EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
    }

    @Test
    public void injection() {
        assertNotNull(bookBean);
        assertNotNull(adminPageBean);
        assertNotNull(cartBean);
        assertNotNull(genreBean);
        assertNotNull(inventoryBean);
        assertNotNull(invoiceBean);
        assertNotNull(reportBean);
        assertNotNull(searchBean);
        assertNotNull(searchReviewBean);
        assertNotNull(siteBean);

    }
}
