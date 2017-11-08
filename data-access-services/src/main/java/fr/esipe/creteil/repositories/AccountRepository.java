/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.repositories;

import fr.esipe.creteil.entities.AccountEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author zouhairhajji
 */
public interface AccountRepository extends CrudRepository<AccountEntity, Long>{

    @Override
    List<AccountEntity> findAll();
    
    
    
}
