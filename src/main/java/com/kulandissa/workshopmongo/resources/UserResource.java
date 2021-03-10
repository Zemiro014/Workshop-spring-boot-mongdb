package com.kulandissa.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kulandissa.workshopmongo.domain.User;
import com.kulandissa.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	// @RequestMapping(method=RequestMethod.GET) ou
	@GetMapping
	public ResponseEntity<List<User>> findAll()
	{
		/*
		 * ResponseEntity é usado de modo a facilitar o envio de dados para HTTP com todas as informações 
		 * necessários no cabeçalho (o retorno até mesmo os possíveis erros
		 * */
		
		List<User> list = userService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
}
