/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author WebChaiQuan
 */
public class RoleService {
    
    private RoleDB roleDB=new RoleDB();
    public List<Role> getAll() throws Exception{
    
    return roleDB.getAll();
    }
    
}
