package fr.esipe.creteil.controllers;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.TransactionDetailDTO;
import fr.esipe.creteil.accounts.enums.AccountType;
import fr.esipe.creteil.exceptions.UserException;
import fr.esipe.creteil.services.IUserService;
import fr.esipe.creteil.users.dtos.UserDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UserDTO createUser(@RequestBody(required = true) UserDTO user) {
        UserDTO userDTO = this.userService.createUser(user);
        return userDTO;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestParam(value = "idUser", required = true) String idUser,
            @RequestParam(value = "address", required = true) String address,
            @RequestParam(value = "numberPhone", required = true) String numberPhone) {

        UserDTO userDTO = this.userService.updateUser(idUser, address, numberPhone);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUserByLastName(@RequestParam(value = "lastname", required = true) String lastname) {
        try {
            UserDTO userDTO = this.userService.getUserByLastName(lastname);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/{idUser}", method = RequestMethod.POST)
    public ResponseEntity<?> createAccountForUser(@PathVariable(required = true) String idUser,
            @RequestParam(value = "accountType", required = true) AccountType accountType) {

        try {
            AccountDTO accountDTO = this.userService.createAccount(idUser, accountType);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "/{idUser}/history", method = RequestMethod.GET)
    public ResponseEntity<?> getHistoryTransactions(@PathVariable(required = true) String idUser) {
        try {
            List<TransactionDetailDTO> detailDTOs = this.userService.getAllTransactions(idUser);
            return new ResponseEntity<>(detailDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
