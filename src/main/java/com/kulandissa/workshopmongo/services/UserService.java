package com.kulandissa.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kulandissa.workshopmongo.domain.User;
import com.kulandissa.workshopmongo.dto.UserDTO;
import com.kulandissa.workshopmongo.repositories.UserRepository;
import com.kulandissa.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insertUser(User obj_User) {
		return userRepository.insert(obj_User);
	}
	
	public void deleteUser(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User updateUser(User obj_UserReceivedFromRequest) 
	{
		User obj_toBeUpdated = findById(obj_UserReceivedFromRequest.getId());
		updatingExistentUserDate(obj_toBeUpdated, obj_UserReceivedFromRequest);
		return userRepository.save(obj_toBeUpdated);
	}
	
	private void updatingExistentUserDate(User obj_toBeUpdated, User updater_Obj) {
		obj_toBeUpdated.setName(updater_Obj.getName());
		obj_toBeUpdated.setEmail(updater_Obj.getEmail());		
	}

	public User convertUserDtoFromUser(UserDTO obj_Dto) {
		return new User(obj_Dto.getId(), obj_Dto.getName(), obj_Dto.getEmail());
	}
}
