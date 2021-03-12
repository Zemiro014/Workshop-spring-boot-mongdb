package com.kulandissa.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kulandissa.workshopmongo.domain.Post;
import com.kulandissa.workshopmongo.domain.User;
import com.kulandissa.workshopmongo.dto.AuthorDTO;
import com.kulandissa.workshopmongo.repositories.PostRepository;
import com.kulandissa.workshopmongo.repositories.UserRepository;

/*
 * Se precisa executar algum código antes de iniciar um aplicativo, então pode utilizar as interfaces
 * "ApplicationRunner" ou "CommandLineRunner"
 * */

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll(); // Limpar a coleção no mongoDB
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para Angola, partiu!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));	
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
