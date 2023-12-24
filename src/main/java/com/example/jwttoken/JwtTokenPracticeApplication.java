package com.example.jwttoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.jwttoken.entity.Role;
import com.example.jwttoken.entity.User;
import com.example.jwttoken.repository.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.jwttoken")
public class JwtTokenPracticeApplication implements CommandLineRunner{

	@Autowired
	private UserRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(JwtTokenPracticeApplication.class, args);
	}

	public void run(String...args) {
		User adminAccount=repository.findByRole(Role.ADMIN);
		if(null==adminAccount) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setSecondname("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			repository.save(user);

		}


	}
}
