/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.accounts.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author zouhairhajji
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailDTO {
    
    private AccountDTO account;
    private List<HistoryDTO> histories;
    
    
}
