/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.controllers;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.TransactionDetailDTO;
import fr.esipe.creteil.exceptions.AccountException;
import fr.esipe.creteil.iservices.IAccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author zouhairhajji
 */
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;
    
    
    @RequestMapping(path = "/{idUser}", method = RequestMethod.GET)
    public ResponseEntity<?> update(@PathVariable String idUser) {
        try {
            List<TransactionDetailDTO> allAccountHistory = this.accountService.getAllAccountHistory(idUser);
            return new ResponseEntity<>(allAccountHistory, HttpStatus.OK);
        } catch (AccountException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }catch (RestClientException ex){
            return new ResponseEntity<>("cannot connect to data-service-access", HttpStatus.EXPECTATION_FAILED);
        } 
    }
    
    @RequestMapping(path = "/withdraw/{idAccount}", method = RequestMethod.GET)
    public ResponseEntity<?> withdrawMoney(@PathVariable String idAccount,
            @RequestParam(value = "amount", required = true) Long amount) {
        
        try {
            AccountDTO historyDTO = this.accountService.withDrawMoney(idAccount, amount);
            return new ResponseEntity<>(historyDTO, HttpStatus.OK);
        } catch (AccountException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }catch (RestClientException ex){
            return new ResponseEntity<>("cannot connect to data-service-access", HttpStatus.EXPECTATION_FAILED);
        } 
    }
    
    @RequestMapping(path = "/deposit/{idAccount}", method = RequestMethod.GET)
    public ResponseEntity<?> depositMoney(@PathVariable String idAccount,
            @RequestParam(value = "amount", required = true) Long amount) {
        
        try {
            AccountDTO historyDTO = this.accountService.depositMoney(idAccount, amount);
            return new ResponseEntity<>(historyDTO, HttpStatus.OK);
        } catch (AccountException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }catch (RestClientException ex){
            return new ResponseEntity<>("cannot connect to data-service-access", HttpStatus.EXPECTATION_FAILED);
        } 
    }

}
