/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author WebChaiQuan
 */
public class UserDB {
 
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user;";
        
        try {
            ps = con.prepareStatement(sql);
            //ps.setString(1, owner);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                String email = rs.getString(1);
                boolean status = rs.getBoolean(2);
                String firstName=rs.getString(3);
                String lastName=rs.getString(4);
                String password=rs.getString(5);
                int role=rs.getInt(6);
                User user = new User(email,firstName,lastName,password,role,status);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }

    public User get(String email) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String emails = rs.getString(1);
                boolean status = rs.getBoolean(2);
                String firstName=rs.getString(3);
                String lastName=rs.getString(4);
                String password=rs.getString(5);
                int role=rs.getInt(6);
                user = new User(emails,firstName,lastName,password,role,status);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return user;
    }

public void update(String previousEmail, String email, String firstName,String lastName,boolean status,int roleN,String password) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update `user` set email=?, first_name=?, last_name=?,password=?,role=?, active=? where email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
               ps.setString(2, firstName);
                  ps.setString(3, lastName);
                     ps.setString(4, password);
                        ps.setInt(5, roleN);
                           ps.setBoolean(6, status);
                              ps.setString(7, previousEmail);
               System.out.println("userDB "+ps);
                              ps.executeUpdate();
           
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
   
    }



public void delete(String email) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from `user` where email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
           
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
   
    }




public void insert( String email, String firstName,String lastName,boolean status,int roleN,String password) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO `user` (`email`,`active`,`first_name`,`last_name`,`password`,`role`)\n" +
"	VALUES (?, ?, ?,?, ?, ?);";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
               ps.setBoolean(2, status);
                  ps.setString(3, firstName);
                     ps.setString(4, lastName);
                      ps.setString(5, password);
                        ps.setInt(6, roleN);
                          
              System.out.println("insert sql "+ps);          
              ps.executeUpdate();
           
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
   
    }


}