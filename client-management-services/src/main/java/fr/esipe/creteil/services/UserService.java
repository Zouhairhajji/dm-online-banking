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
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author zouhairhajji
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${data-access-service}")
    private String dataAccessService;
    
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        String url = this.dataAccessService + "/users";
        ResponseEntity<UserDTO> postForEntity = this.restTemplate.postForEntity(url, userDTO, UserDTO.class);
        return postForEntity.getBody();
    }

    @Override
    public UserDTO updateUser(String userID, String address, String numberPhone) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(String.format("%s/users/%s", this.dataAccessService, userID));
        builder.queryParam("address", address);
        builder.queryParam("numberPhone", numberPhone);
        ResponseEntity<UserDTO> responseEntity = this.restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, HttpEntity.EMPTY, UserDTO.class);
        return responseEntity.getBody();
    }

    @Override
    public UserDTO getUser(String userID) {
        return null;
    }

    @Override
    public UserDTO getUserByLastName(String lastname){
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(String.format("%s/users", this.dataAccessService));
        builder.queryParam("lastName", lastname);
        ResponseEntity<UserDTO> responseEntity = this.restTemplate.exchange(builder.toUriString(), HttpMethod.GET, HttpEntity.EMPTY, UserDTO.class);
        return responseEntity.getBody();
    }

    @Override
    public AccountDTO createAccount(String userID, AccountType accountType) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(String.format("%s/account/%s", this.dataAccessService, userID));
        builder.queryParam("accountType", accountType);
        ResponseEntity<AccountDTO> responseEntity = this.restTemplate.exchange(builder.toUriString(), HttpMethod.POST, HttpEntity.EMPTY, AccountDTO.class);
        return responseEntity.getBody();
    }

    @Override
    public List<TransactionDetailDTO> getAllTransactions(String userID) throws UserException {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(String.format("%s/account/%s", this.dataAccessService, userID));
        ResponseEntity<TransactionDetailDTO[]> responseEntity = this.restTemplate.exchange(builder.toUriString(), HttpMethod.GET, HttpEntity.EMPTY, TransactionDetailDTO[].class);
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return Arrays.asList(responseEntity.getBody());
        }else {
            throw new UserException("error");
        }
    }
    
    

}
