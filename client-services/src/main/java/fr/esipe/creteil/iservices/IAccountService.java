/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.iservices;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.TransactionDetailDTO;
import fr.esipe.creteil.exceptions.AccountException;
import java.util.List;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author zouhairhajji
 */
public interface IAccountService {
    
    public AccountDTO withDrawMoney(String accountID, Long amount) throws AccountException, RestClientException;
    
    public AccountDTO depositMoney(String accountID, Long amount) throws AccountException, RestClientException;
    
    public List<TransactionDetailDTO> getAllAccountHistory(String userID) throws AccountException, RestClientException;
}
