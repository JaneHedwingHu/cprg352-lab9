/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Role;

/**
 *
 * @author jinwe
 */
public class RoleDB {
    public static Role getRoleById(long roleId) {
        EntityManager em = DBUtil.getFactory().createEntityManager();
        try {
            Role role = em.find(Role.class, roleId);
            return role;
        } finally {
            em.close();
        }
    }
    
    public static List<Role> getAll() {
                EntityManager em = DBUtil.getFactory().createEntityManager();
        try {
            return em.createNamedQuery("Role.findAll").getResultList();
        } finally {
            em.close();
        }
    }
    
    public static Role getRoleByName(String roleName) {
        EntityManager em = DBUtil.getFactory().createEntityManager();
        String qString = "Select r FROM Role r WHERE r.roleName = :roleName";
        TypedQuery<Role> q = em.createQuery(qString, Role.class);
        q.setParameter("roleName", roleName);
        
        try {
            Role role = q.getSingleResult();
            return role;
        } finally {
            em.close();
        }
    }
}
