/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author WebChaiQuan
 */
public class Role {
    private int roleN;
    private String role;



    public Role(int roleN, String role) {
this.roleN=roleN;
this.role=role;
    }
    public int getRoleN() {
        return roleN;
    }

    public void setRoleN(int roleN) {
        this.roleN = roleN;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
