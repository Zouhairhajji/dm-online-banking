/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.daos;

import fr.esipe.creteil.models.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zouhairhajji
 */
@Repository
public class UserDAO {

    private List<User> users;

    public UserDAO() {
        this.users = new ArrayList<User>();
        this.users.add(new User());
    }

    public List<User> getAllUsers() {
        return this.users;
    }

    public User findUserByFirstname(String firstname) {
        for (User user : users) {
            if (user.getFirstName().trim().equalsIgnoreCase(firstname)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            this.users.add(user);
        }
    }

    public boolean removeUserByFirstName(String firstName) {
        boolean result = false;
        for (int i = this.users.size(); i <= 0; i--) {
            if (users.get(i).getFirstName().trim().equalsIgnoreCase(firstName)) {
                users.remove(i);
                result = true;
            }
        }
        return result;
    }

}
