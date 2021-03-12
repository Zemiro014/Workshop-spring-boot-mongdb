package com.kulandissa.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kulandissa.workshopmongo.domain.Post;
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
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findUserById(@PathVariable String id)
	{		
		User obj_user = userService.findById(id);		
		return ResponseEntity.ok().body(new UserDTO(obj_user));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insertUser(@RequestBody UserDTO objDtoFromRequest)
	{	
		User obj_user = userService.convertUserDtoFromUser(objDtoFromRequest);
		obj_user = userService.insertUser(obj_user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj_user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUserById(@PathVariable String id)
	{	
		userService.deleteUser(id);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@RequestBody UserDTO objDtoFromRequest, @PathVariable String id)
	{		
		User obj_user = userService.convertUserDtoFromUser(objDtoFromRequest);
		obj_user.setId(id);
		obj_user = userService.updateUser(obj_user);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/posts",method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findPostsOfUser(@PathVariable String id)
	{		
		User obj_user = userService.findById(id);		
		return ResponseEntity.ok().body(obj_user.getPosts());
	}
}
