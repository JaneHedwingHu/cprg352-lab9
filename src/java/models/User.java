/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import services.RoleService;

/**
 *
 * @author WebChaiQuan
 */
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int role;
    private boolean status;
    private String roleS;


   



    
    public User(String email,String firstName,String lastName,String password,int role, boolean status) throws Exception{
    this.email=email;
    this.firstName=firstName;
    this.lastName=lastName;
    this.password=password;
    this.role=role;
    this.status=status;
    setRoleS();
    
    
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
        public String getRoleS() {
        return roleS;
    }

 

    public void setRoleS() throws Exception{
        RoleService roleService=new RoleService();
        List<Role> roles = roleService.getAll();
        String roleS = "Not defined in database";
        for (int i = 0; i < roles.size(); i++) {
            if (this.getRole() == roles.get(i).getRoleN()) {
                roleS= roles.get(i).getRole();
            }
        }
        this.roleS=roleS;
    
}
}