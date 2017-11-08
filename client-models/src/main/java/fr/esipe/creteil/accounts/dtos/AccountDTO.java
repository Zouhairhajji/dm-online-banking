/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.accounts.dtos;

import fr.esipe.creteil.accounts.enums.AccountType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zouhairhajji
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    
    @Pattern(regexp = "[0-9]{1,}")
    private String id;
    
    @NotNull
    private AccountType accountType;
    
    @NotNull
    private Long balance;
    
    
    
}
