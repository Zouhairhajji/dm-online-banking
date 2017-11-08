/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.services;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.TransactionDetailDTO;
import fr.esipe.creteil.exceptions.AccountException;
import fr.esipe.creteil.iservices.IAccountService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author zouhairhajji
 */
@Service
public class AccountService implements IAccountService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${data-access-service}")
    private String dataAccessService;

    @Override
    public AccountDTO withDrawMoney(String accountId, Long amount) throws AccountException, RestClientException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s/account/withdraw/%s", this.dataAccessService, accountId));
        builder.queryParam("amount", amount);
        System.out.println("the uri : " + builder.toUriString());
        ResponseEntity<AccountDTO> responseEntity = this.restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, HttpEntity.EMPTY, AccountDTO.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new AccountException();
        }
    }
    
    @Override
    public AccountDTO depositMoney(String accountId, Long amount) throws AccountException, RestClientException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s/account/deposit/%s", this.dataAccessService, accountId));
        builder.queryParam("amount", amount);
        System.out.println("the uri : " + builder.toUriString());
        ResponseEntity<AccountDTO> responseEntity = this.restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, HttpEntity.EMPTY, AccountDTO.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new AccountException();
        }
    }


    @Override
    public List<TransactionDetailDTO> getAllAccountHistory(String userID) throws AccountException, RestClientException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s/account/%s", this.dataAccessService, userID));
        ResponseEntity<TransactionDetailDTO[]> responseEntity = this.restTemplate.exchange(builder.toUriString(), HttpMethod.GET, HttpEntity.EMPTY, TransactionDetailDTO[].class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(responseEntity.getBody());
        } else {
            throw new AccountException();
        }
    }

}
