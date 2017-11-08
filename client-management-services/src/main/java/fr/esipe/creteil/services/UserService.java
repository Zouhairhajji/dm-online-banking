/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.services;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.enums.AccountType;
import fr.esipe.creteil.users.dtos.UserDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    public List<UserDTO> getUser(String nom, String prenom) {
        return null;
    }

    @Override
    public AccountDTO createAccount(String userID, AccountType accountType) {
        return null;
    }

}
