/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

/**
 *
 * @author WebChaiQuan
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;
    
    @Column(unique=true)
    private String roleName;
    
    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL
    )
    private List<User> users;

    public Role() {}
    
    public Role(int roleId, String roleName) {
        this.roleId=roleId;
        this.roleName=roleName;
    }
    
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleN) {
        this.roleId = roleN;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
