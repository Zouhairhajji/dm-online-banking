package fr.esipe.creteil.controllers;


import fr.esipe.creteil.exceptions.UserException;
import fr.esipe.creteil.services.UserService;
import fr.esipe.creteil.users.dtos.UserDTO;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author hajjizouhair
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") @Valid @Pattern(regexp = "[0-9]{1,}") String idUser) {
        try {
            final UserDTO userDto = userService.getUserById(idUser);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
            
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUserByLastName(@RequestParam(value = "lastName", required = true) String lastName) {
        try {
            final UserDTO userDto = userService.getUserByLastName(lastName);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
            
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    
    /**
     *
     * This method allow to create new User
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
    }

    /**
     *
     * this method allow to update user
     * 
     * @param address
     * @param numberPhone
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(
            @RequestParam(value = "address") String address,
            @RequestParam(value = "numberPhone") String numberPhone,
            @PathVariable String id) {
        try {
            this.userService.update(id, address, numberPhone);
            UserDTO userDto = this.userService.getUserById(id);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
            
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    
    
}
