package fr.esipe.creteil.services;



import fr.esipe.creteil.exceptions.UserException;
import fr.esipe.creteil.users.dtos.UserDTO;

public interface IUserService {
    
        public UserDTO getUserById(String id) throws UserException;

	public  UserDTO create(UserDTO userDto);


	public void update(String idUser, String address, String numberPhone) throws UserException;
        
        public UserDTO getUserByLastName(String lastName) throws UserException ;
}
