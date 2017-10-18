/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author zouhairhajji
 */
@Configuration
public class InjectionConfig {
    
    @Bean
    public ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
    
}
