/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import models.Role;
import models.User;

/**
 *
 * @author jinwe
 */
public class UserDB {    
    public static User getUserByEmail(String email) {
        EntityManager em = DBUtil.getFactory().createEntityManager();
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public static List<User> getAll() {
        EntityManager em = DBUtil.getFactory().createEntityManager();
        String qString = "SELECT u FROM User u";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        try {
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static void delete(String email) {
        EntityManager em = DBUtil.getFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User user = em.find(User.class, email);
            em.remove(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void insert(String email, String firstName, String lastName, boolean status, Role role, String password) {
        EntityManager em = DBUtil.getFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            User user = new User(email, firstName, lastName, password, role, status);
            em.persist(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(String previousEmail, String email, String firstName, String lastName, boolean status, Role role, String password) {
        EntityManager em = DBUtil.getFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            User user = em.find(User.class, previousEmail);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setStatus(status);
            user.setRole(role);
            user.setPassword(password);
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
