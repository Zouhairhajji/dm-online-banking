/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * //Gokan TODO: Inutile de dupliquer avec celui de rest-client-utils !
 * //Gokan TODO: En temps normal cela devrait meme lancer une exception mais visiblement tu n'as
 * //Gokan TODO: pas importé cette dépendance dans ce module
 * @author zouhairhajji
 */
@Configuration
public class InectionConfig {
    
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
    
}
