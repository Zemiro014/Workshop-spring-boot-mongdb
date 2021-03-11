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
	
	public User inserUser(User obj) {
		return userRepository.insert(obj);
	}
	
	public User convertUserDtoFromUser(UserDTO obj_Dto) {
		return new User(obj_Dto.getId(), obj_Dto.getName(), obj_Dto.getEmail());
	}
}
