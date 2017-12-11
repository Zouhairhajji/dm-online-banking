/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.users;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.HistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * //Gokan TODO: Une classe incomplète
 * @author zouhairhajji
 */
@Component
public class AccountAPI {
    
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${service.data-access-service}")
    private String dataAccessServiceUrl;
    
    public Boolean withDrawMoney(Long accountId, Long amount){
        return false;
    }
    
    public boolean depositMoney(Long accountId, Long amount){
        return false;
    }
 
    public List<AccountDTO> getAllAccounts(Long userId){
        return null;
    }
    
    public List<HistoryDTO> getAllAccountHistory(Long AccountId){
        return null;
    }
    
    
}
