/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.services;

import fr.esipe.creteil.daos.UserDAO;
import fr.esipe.creteil.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zouhairhajji
 */
@Service("userService")
public class UserService {
    
    @Autowired
    private UserDAO userDAO;
    
    public List<User> getAllUsers(){
        return this.userDAO.getAllUsers();
    }
    
    public User findUserByFirstname(String firstname){
        return this.userDAO.findUserByFirstname(firstname);
    }
    
    public void addUser(User user){
        this.userDAO.addUser(user);
    }
    
    public boolean removeUserByFirstName(String firstName){
        return this.userDAO.removeUserByFirstName(firstName);
    }
}
