package com.example.jwttoken.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwttoken.dto.JwtAuthenticationResponse;
import com.example.jwttoken.dto.RefreshTokenRequest;
import com.example.jwttoken.dto.SignInRequest;
import com.example.jwttoken.dto.SignUpRequest;
import com.example.jwttoken.entity.Role;
import com.example.jwttoken.entity.User;
import com.example.jwttoken.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private UserRepository repository ;
	@Autowired
	private  PasswordEncoder encoder ;
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtService jwtService;
	
	public User signUp(SignUpRequest upRequest) {
		User user = new User();
		user.setEmail(upRequest.getEmail());
	user.setFirstname(upRequest.getFirstName());
	user.setSecondname(upRequest.getLastName());
	user.setRole(Role.USER);
	user.setPassword(new BCryptPasswordEncoder().encode("admin"));
	return repository.save(user);
	
	}
	
	public JwtAuthenticationResponse signIn(SignInRequest request) {
		manager.authenticate(new UsernamePasswordAuthenticationToken
				(request.getEmail(), request.getPassword()));
		var user = new User();  // Assuming User is a class or type that you've defined
	  user = repository.findByEmail(request.getEmail())
			.orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
	var jwt=jwtService.generateToken(user);
	var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);
	JwtAuthenticationResponse response = new JwtAuthenticationResponse();
	response.setToken(jwt);
	response.setRefreshToken(refreshToken);
	return response;
	}
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
	String userEmail=jwtService.extractUserName(refreshTokenRequest.getToken());
	User user =repository.findByEmail(userEmail).orElseThrow();
	if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
		var jwt=jwtService.generateToken(user);
		
		JwtAuthenticationResponse response = new JwtAuthenticationResponse();
		response.setToken(jwt);
		response.setRefreshToken(refreshTokenRequest.getToken());
		return response;
	}
	return null;
	}
	
}
