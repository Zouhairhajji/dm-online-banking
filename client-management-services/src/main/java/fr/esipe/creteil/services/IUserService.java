/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.services;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.TransactionDetailDTO;
import fr.esipe.creteil.accounts.enums.AccountType;
import fr.esipe.creteil.exceptions.UserException;
import fr.esipe.creteil.users.dtos.UserDTO;
import java.util.List;

/**
 *
 * @author zouhairhajji
 */
public interface IUserService {
    
    public UserDTO createUser(UserDTO userDTO);
    
    public UserDTO updateUser(String  userID, String address, String numberPhone);
    
    public UserDTO getUser(String userID)  throws UserException;
    
    public UserDTO getUserByLastName(String lastname)  throws UserException;
    
    public AccountDTO createAccount(String userID, AccountType accountType);
 
    
    public List<TransactionDetailDTO> getAllTransactions(String userID)  throws UserException;
}
