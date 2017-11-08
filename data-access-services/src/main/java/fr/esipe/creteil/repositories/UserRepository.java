package fr.esipe.creteil.repositories;

import fr.esipe.creteil.entities.UserEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    
        @Override
	public List<UserEntity> findAll();
        
        UserEntity getUserByLastName(String lastName);
        
}
