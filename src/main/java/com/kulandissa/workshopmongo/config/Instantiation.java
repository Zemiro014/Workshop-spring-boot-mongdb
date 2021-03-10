package com.kulandissa.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kulandissa.workshopmongo.domain.User;
import com.kulandissa.workshopmongo.repositories.UserRepository;

/*
 * Se precisa executar algum código antes de iniciar um aplicativo, então pode utilizar as interfaces
 * "ApplicationRunner" ou "CommandLineRunner"
 * */

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll(); // Limpara coleção no mongoDB
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}

}
