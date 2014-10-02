package com.ebook.actionbeans;

import com.ebook.beans.ClientBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import com.ebook.entities.Clients;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

@Named
@SessionScoped
public class ClientActionBean implements Serializable {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ClientBean clientBean;

    /**
     * get all client list
     *
     * @return
     * @throws SQLException
     */
    public List<Clients> getClient() throws SQLException {
        TypedQuery<Clients> query = entityManager.createQuery("SELECT c FROM Clients c", Clients.class);
        List<Clients> client = query.getResultList();
        return client;
    }

    /**
     * get client from user name and password
     *
     * @return
     */
    public String getClientByUserNamePW() {
        TypedQuery<Clients> query;
        if ((clientBean.getUserName() != null) && (clientBean.getPassword() != null)) {
            query = entityManager.createQuery("SELECT c FROM Clients c where c.username=?1 and c.password = ?2", Clients.class);
            query.setParameter(1, clientBean.getUserName());
            query.setParameter(2, clientBean.getPassword());
        } else {
            query = null;
        }

        if (!query.getResultList().isEmpty() && query.getSingleResult() != null) {

            Clients client = query.getSingleResult();

            clientBean.login();
            clientBean.setClientID(client.getClientID());
            clientBean.setCompanyName(client.getCompanyName());
            clientBean.setFirstName(client.getFirstName());
            clientBean.setLastName(client.getLastName());
            clientBean.setAddress1(client.getAddress1());
            clientBean.setAddress2(client.getAddress2());
            clientBean.setCellPhone(client.getCellPhone());
            clientBean.setCity(client.getCity());
            clientBean.setCountry(client.getCountry());
            clientBean.setEmail(client.getEmail());
            clientBean.setProvince(client.getProvince());
            clientBean.setTitle(client.getTitle());
            clientBean.setPostalCode(client.getPostalCode());
            clientBean.setHomePhone(client.getHomePhone());
            clientBean.setPrivilegeID(client.getPrivilegeID());
            clientBean.setInvalidLogin("");
            
            if (clientBean.getPrivilegeID() == 0) {
                return "reportHeader.xhtml?reportID=4&faces-redirect=true";
            }

            if (!clientBean.getReferencePage().isEmpty()) {
                return clientBean.getReferencePage();
            } else {
                return "orderHistory";
            }

        } else {
            clientBean.setInvalidLogin("Invalid Login");
            return "login";
        }

    }

    /**
     * save registration form to db
     */
    public void saveRegistrationForm() {
        boolean success = true;

        try {
            userTransaction.begin();
            Clients cb = entityManager.find(Clients.class, clientBean.getClientID());
            cb.setCompanyName(clientBean.getCompanyName());
            cb.setTitle(clientBean.getTitle());
            cb.setFirstName(clientBean.getFirstName());
            cb.setLastName(clientBean.getLastName());
            cb.setAddress1(clientBean.getAddress1());
            cb.setAddress2(clientBean.getAddress2());
            cb.setCity(clientBean.getCity());
            cb.setProvince(clientBean.getProvince());
            cb.setPostalCode(clientBean.getPostalCode());
            cb.setCountry(clientBean.getCountry());
            cb.setEmail(clientBean.getEmail());
            cb.setHomePhone(clientBean.getHomePhone());
            cb.setCellPhone(clientBean.getCellPhone());
            cb.setUsername(clientBean.getUserName());
            cb.setPassword(clientBean.getPassword());
            cb.setPrivilegeID(1);

            userTransaction.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            Logger.getLogger(ClientActionBean.class.getName()).log(Level.SEVERE, null, ex);
            try {
                userTransaction.rollback();
                success = false;

            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(ClientActionBean.class.getName()).log(Level.SEVERE, null, ex1);
                success = false;
            }

        }
    }

    /**
     * insert registration form to db
     * @return 
     */
    public String insertRegistrationForm() {
        boolean success = true;

        try {
            userTransaction.begin();
            Clients cb = new Clients();
            cb.setCompanyName(clientBean.getCompanyName());
            cb.setTitle(clientBean.getTitle());
            cb.setFirstName(clientBean.getFirstName());
            cb.setLastName(clientBean.getLastName());
            cb.setAddress1(clientBean.getAddress1());
            cb.setAddress2(clientBean.getAddress2());
            cb.setCity(clientBean.getCity());
            cb.setProvince(clientBean.getProvince());
            cb.setPostalCode(clientBean.getPostalCode());
            cb.setCountry(clientBean.getCountry());
            cb.setEmail(clientBean.getEmail());
            cb.setHomePhone(clientBean.getHomePhone());
            cb.setCellPhone(clientBean.getCellPhone());
            cb.setUsername(clientBean.getUserName());
            cb.setPassword(clientBean.getPassword());

            entityManager.persist(cb);
            userTransaction.commit();

            clientBean.login();
            clientBean.setClientID(cb.getClientID());
            clientBean.setCompanyName(clientBean.getCompanyName());
            clientBean.setFirstName(clientBean.getFirstName());
            clientBean.setLastName(clientBean.getLastName());
            clientBean.setAddress1(clientBean.getAddress1());
            clientBean.setAddress2(clientBean.getAddress2());
            clientBean.setCellPhone(clientBean.getCellPhone());
            clientBean.setCity(clientBean.getCity());
            clientBean.setCountry(clientBean.getCountry());
            clientBean.setEmail(clientBean.getEmail());
            clientBean.setProvince(clientBean.getProvince());
            clientBean.setTitle(clientBean.getTitle());
            clientBean.setPostalCode(clientBean.getPostalCode());
            clientBean.setHomePhone(clientBean.getHomePhone());

        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            Logger.getLogger(ClientActionBean.class.getName()).log(Level.SEVERE, null, ex);
            try {
                userTransaction.rollback();
                success = false;

            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Logger.getLogger(ClientActionBean.class.getName()).log(Level.SEVERE, null, ex1);
                success = false;
            }

        }

        return "registrationForm";
    }

}
