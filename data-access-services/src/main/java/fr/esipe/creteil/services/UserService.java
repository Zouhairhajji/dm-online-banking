package fr.esipe.creteil.services;


import fr.esipe.creteil.entities.UserEntity;
import fr.esipe.creteil.exceptions.UserException;
import fr.esipe.creteil.repositories.UserRepository;
import fr.esipe.creteil.users.dtos.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public UserDTO create(UserDTO userDto) {
        userDto.setId(null);
        UserEntity userEntity = this.mapper.map(userDto, UserEntity.class);
        this.userRepository.save(userEntity);
        return this.mapper.map(userEntity, UserDTO.class);
    }

    

    @Override
    public void update(String idUser, String address, String numberPhone) throws UserException{
        UserEntity userEntity = this.userRepository.findOne(Long.parseLong(idUser));
        if(userEntity == null){
            throw new UserException(String.format("The user %s does not exists", idUser));
        }
        
        if(numberPhone != null){
            userEntity.setNumberPhone(numberPhone);
        }
        if(numberPhone != null){
            userEntity.setAddress(numberPhone);
        }
        this.userRepository.save(userEntity);
    }

    @Override
    public UserDTO getUserById(String idUser) throws UserException {
        UserEntity userEntity = userRepository.findOne(Long.parseLong(idUser));
        if(userEntity == null){
            throw new UserException(String.format("The user %s does not exists", idUser));
        }
        return this.mapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserDTO getUserByLastName(String lastName) throws UserException {
        UserEntity userEntity = userRepository.getUserByLastName(lastName);
        if(userEntity == null){
            throw new UserException(String.format("The user %s does not exists", lastName));
        }
        return this.mapper.map(userEntity, UserDTO.class);
    }

    
}
