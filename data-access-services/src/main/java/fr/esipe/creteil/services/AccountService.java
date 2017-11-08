/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.services;

import fr.esipe.creteil.accounts.dtos.AccountDTO;
import fr.esipe.creteil.accounts.dtos.HistoryDTO;
import fr.esipe.creteil.accounts.dtos.TransactionDetailDTO;
import fr.esipe.creteil.accounts.enums.AccountType;
import static fr.esipe.creteil.accounts.enums.AccountType.LIVRET_JEUNE;
import fr.esipe.creteil.entities.AccountEntity;
import fr.esipe.creteil.entities.HistoryEntity;
import fr.esipe.creteil.entities.UserEntity;
import fr.esipe.creteil.exceptions.UserException;
import fr.esipe.creteil.repositories.AccountRepository;
import fr.esipe.creteil.repositories.HistoryRepository;
import fr.esipe.creteil.repositories.UserRepository;
import static java.lang.Math.abs;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zouhairhajji
 */
@Service("accountService")
public class AccountService implements IAccountService {
    
    @Autowired
    private  AccountRepository accountRepository;
    
    @Autowired
    private  UserRepository userRepository;
    
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ModelMapper mapper;
    
    @Override
    public AccountDTO create(String userID, AccountType accountType) throws UserException{
        UserEntity userEntity = this.userRepository.findOne(Long.parseLong(userID));
        if(userEntity == null){
            throw new UserException(String.format("The user %s does not exists", userID));
        }
        
        if(userEntity.getAge() > AGE_CLIENT_ADULTE && accountType == LIVRET_JEUNE){
            throw new UserException(String.format("The user %s cannot have %s because he had already %d year old", userID, accountType, AGE_CLIENT_ADULTE));
        }
        if(userEntity.getAccountList().stream().anyMatch(t -> t.getAccountType() == accountType)){
            throw new UserException(String.format("The user %s had already the account %s", userID, accountType));
        }
        AccountEntity accountEntity = AccountEntity.builder()
                .accountType(accountType)
                .balance(0L)
                .id(null)
                .user(userEntity)
                .build();
        accountEntity = this.accountRepository.save(accountEntity);
        return this.mapper.map(accountEntity, AccountDTO.class);
    }

    @Override
    public AccountDTO depositMoney(String idAccount, Long amount) throws UserException{
        AccountEntity accountEntity = this.accountRepository.findOne(Long.parseLong(idAccount));
        if(accountEntity == null){
            throw new UserException(String.format("The account %s does not exists", idAccount));
        }
        if(Arrays.asList(FORBIDEN_DIRECT_ACCESS_ACCOUNT).contains(accountEntity.getAccountType())){
            throw new UserException(String.format("You cannot update this account %d", accountEntity.getAccountType()));
        }
        
        this.historyRepository.save(HistoryEntity.builder()
                .account(accountEntity)
                .amount(abs(amount))
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .id(null)
                .build());
        
        accountEntity.setBalance(accountEntity.getBalance() + amount);
        this.accountRepository.save(accountEntity);
        return this.mapper.map(accountEntity, AccountDTO.class);
    }

    @Override
    public List<HistoryDTO> getHisoties(String idUser) throws UserException {
        UserEntity userEntity = this.userRepository.findOne(Long.parseLong(idUser));
        if(userEntity == null){
            throw new UserException(String.format("The user %s does not exists", idUser));
        }
        
        java.lang.reflect.Type targetListType = new TypeToken<List<TransactionDetailDTO>>() {}.getType();
        return this.mapper.map(userEntity.getAccountList(), targetListType);
    }

    @Override
    public AccountDTO withDrawMoney(String idAccount, Long amount) throws UserException {
        AccountEntity accountEntity = this.accountRepository.findOne(Long.parseLong(idAccount));
        if(accountEntity == null){
            throw new UserException(String.format("The account %s does not exists", idAccount));
        }
        if(Arrays.asList(FORBIDEN_DIRECT_ACCESS_ACCOUNT).contains(accountEntity.getAccountType())){
            throw new UserException(String.format("You cannot update this account %d", accountEntity.getAccountType()));
        }
        List<HistoryEntity> histories = accountEntity.getHistories();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Long weeklyAmount = histories.stream()
                .filter(s -> s.getDateCreation().after(calendar.getTime()))
                .mapToLong(s -> s.getAmount())
                .sum();
        
        if(weeklyAmount > MAX_WEEKLY_WITHDRAW){
            throw new UserException(String.format("You cannot update this account %d", accountEntity.getAccountType()));
        }
        
        this.historyRepository.save(HistoryEntity.builder()
                .account(accountEntity)
                .amount(-abs(amount))
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .id(null)
                .build());
        
        accountEntity.setBalance(accountEntity.getBalance() - amount);
        this.accountRepository.save(accountEntity);
        return this.mapper.map(accountEntity, AccountDTO.class);
    }

}
