/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author WebChaiQuan
 */
public class UserService {
    public static List<User> getAll() throws Exception {
        return UserDB.getAll();
    }

    public static User get(String email) throws Exception {
        return UserDB.getUserByEmail(email);
    }

    public static void update(String previousEmail, User user) throws Exception {
        UserDB.update(previousEmail, user.getEmail(), user.getFirstName(), user.getLastName(), user.isStatus(), user.getRole(), user.getPassword());
    }

    public static void insert(User user) throws Exception {    
        UserDB.insert(user.getEmail(), user.getFirstName(), user.getLastName(), user.isStatus(), user.getRole(), user.getPassword());  
    }

    public static void delete(String email) throws Exception {
        UserDB.delete(email);
    }
}
