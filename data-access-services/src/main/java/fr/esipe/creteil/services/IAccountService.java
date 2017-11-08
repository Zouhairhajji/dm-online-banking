/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.services;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.HistoryDTO;
import fr.esipe.creteil.accounts.enums.AccountType;
import static fr.esipe.creteil.accounts.enums.AccountType.*;
import fr.esipe.creteil.exceptions.UserException;
import java.util.List;

/**
 *
 * @author zouhairhajji
 */
public interface IAccountService {

    public final static Integer AGE_CLIENT_ADULTE = 18;
    public final static Long MAX_WEEKLY_WITHDRAW = 800L;
    public final static AccountType[] FORBIDEN_DIRECT_ACCESS_ACCOUNT = {PLAN_EL, LIVRET_EE, COMPTE_EL};
    
    public AccountDTO create(String userID, AccountType accountType)  throws UserException;
    public AccountDTO depositMoney(String idAccount, Long amount)  throws UserException;

    public List<HistoryDTO> getHisoties(String idUser) throws UserException;

    public AccountDTO withDrawMoney(String idAccount, Long amount) throws UserException;

}
