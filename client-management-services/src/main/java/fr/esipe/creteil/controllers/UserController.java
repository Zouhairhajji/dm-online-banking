package fr.esipe.creteil.controllers;


import fr.esipe.creteil.services.IUserService;
import fr.esipe.creteil.users.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HAJJI Zouhair
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private IUserService userService;
    
    @RequestMapping(method = RequestMethod.POST)
    public UserDTO createUser(@RequestBody(required = true) UserDTO user){
        UserDTO userDTO = this.userService.createUser(user);
        return userDTO;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public UserDTO createUser(@RequestParam(value = "idUser", required = true) String idUser,
            @RequestParam(value = "address", required = true) String address ,
            @RequestParam(value = "numberPhone", required = true) String numberPhone){
        UserDTO userDTO = this.userService.updateUser(idUser, address, numberPhone);
        return userDTO;
    }
    
}
