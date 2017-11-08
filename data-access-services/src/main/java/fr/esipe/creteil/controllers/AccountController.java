/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.controllers;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.HistoryDTO;
import fr.esipe.creteil.accounts.enums.AccountType;
import fr.esipe.creteil.exceptions.UserException;
import fr.esipe.creteil.services.IAccountService;
import fr.esipe.creteil.services.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zouhairhajji
 */
@RestController
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserService userService;
    
    
    @RequestMapping(value = "/{idUser}", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@PathVariable(required = true) String idUser,
            @RequestParam(value = "accountType", required = true) AccountType accountType) {
        try {
            AccountDTO accountDTO = this.accountService.create(idUser, accountType);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "/deposit/{idAccount}", method = RequestMethod.PUT)
    public ResponseEntity<?> depositMoney(@PathVariable(required = true) String idAccount,
            @RequestParam(value = "amount", required = true) Long amount){
        try {
            AccountDTO accountDTO = this.accountService.depositMoney(idAccount, amount);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "/withdraw/{idAccount}", method = RequestMethod.PUT)
    public ResponseEntity<?> withDrawMoney(@PathVariable(required = true) String idAccount,
            @RequestParam(value = "amount", required = true) Long amount){
        try {
            AccountDTO accountDTO = this.accountService.withDrawMoney(idAccount, amount);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "/{idUser}", method = RequestMethod.GET)
    private ResponseEntity<?> getHistories(@PathVariable(required = true) String idUser){
        try {
            List<HistoryDTO> historyDTOs = this.accountService.getHisoties(idUser);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
