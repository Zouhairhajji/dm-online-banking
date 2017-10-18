package fr.esipe.creteil.controllers;

import fr.esipe.creteil.models.User;
import fr.esipe.creteil.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author zouhairhajji
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * This method allow users to render all users 
     */
    @GetMapping("list")
    public List<User> listUsers() throws InterruptedException {
        return this.userService.getAllUsers();
    }

    
    /**
     * This method allow users to render specific user depending on his firstname
     */
    @GetMapping("getuser")
    public User getUserByFirstName(@RequestParam(value = "firstname", required = true) String firstname) {
        return this.userService.findUserByFirstname(firstname);
    }

    /**
     * This method allow users to add user in the database
     * using POST verb
     */
    @PostMapping("adduser")
    public User addUser(@RequestParam(value = "firstname", required = true) String firstname,
            @RequestParam(value = "lastname", required = true) String lastname) {
        
        this.userService.addUser(new User(firstname, lastname));
        return this.userService.findUserByFirstname(firstname);
    }
    
    /**
     * This method allow users delete a user from using his firstname
     * using POST verb
     */
    @PostMapping("deleteuser")
    public boolean deleteUserByFirstname(@RequestParam(value = "firstname", required = true) String firstname){
        return this.userService.removeUserByFirstName(firstname);
    }

}
