package com.kulandissa.workshopmongo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kulandissa.workshopmongo.domain.User;
import com.kulandissa.workshopmongo.dto.UserDTO;
import com.kulandissa.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	// @RequestMapping(method=RequestMethod.GET) ou
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll()
	{
		/*
		 * ResponseEntity é usado de modo a facilitar o envio de dados para HTTP com todas as informações 
		 * necessários no cabeçalho (o retorno até mesmo os possíveis erros
		 * */		
		List<User> list = userService.findAll();
		
		/* Convertendo List<User> para List<UserDTO>
		 * List<UserDTO> listDto = new ArrayList<>();
			for(User obj_User : list) {
				UserDTO obj_Dto = new UserDTO(obj_User);
				listDto.add(obj_Dto);
			}			
			
			Ou de uma forma simplificada como está escerito na alínea 43
		*/
		
		List<UserDTO> listDto = list.stream().map(x-> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
}
