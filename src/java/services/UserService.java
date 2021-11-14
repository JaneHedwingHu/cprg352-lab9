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

    private UserDB userDB = new UserDB();
    private RoleService roleService = new RoleService();

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public User get(String email) throws Exception {
        return userDB.get(email);
    }

    public void update(String previousEmail, User user) throws Exception {
        userDB.update(previousEmail, user.getEmail(), user.getFirstName(), user.getLastName(), user.isStatus(), user.getRole(), user.getPassword());
    }

    public void insert(User user) throws Exception {    
        userDB.insert(user.getEmail(), user.getFirstName(), user.getLastName(), user.isStatus(), user.getRole(), user.getPassword());  
    }

    public void delete(String email) throws Exception {
      
        userDB.delete(email);
    }

    public String getRoleString(User user) throws Exception {
        List<Role> roles = roleService.getAll();
        String roleS = "Not defined in database";
        for (int i = 0; i < roles.size(); i++) {
            if (user.getRole() == roles.get(i).getRoleN()) {
                return roles.get(i).getRole();
            }
        }
        return roleS;
    }
    
       public int getRoleNumber(String role) throws Exception {
        List<Role> roles = roleService.getAll();
        int roleN = 0;
        for (int i = 0; i < roles.size(); i++) {
            if (role.equals(roles.get(i).getRole())) {
                return roles.get(i).getRoleN();
            }
        }
        return roleN;
    }
          public boolean checkExist(String email) throws Exception {
        List<User> users = userDB.getAll();
      
        for (int i = 0; i < users.size(); i++) {
            if(email.equals(users.get(i).getEmail())){
                return true;            
            }
        }
        return false;
    }
}
