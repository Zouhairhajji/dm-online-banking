/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.users.dtos;

import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author zouhairhajji
 */
@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
    
    @Pattern(regexp = "[0-9]{1,}")
    private String id;
    
    @NotEmpty
    private String lastName;
    
    @NotEmpty
    private String firstName;
    
    @NotEmpty
    private String address;
    
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String phoneNumber;
    
    private Integer age;
    
    
    
}
