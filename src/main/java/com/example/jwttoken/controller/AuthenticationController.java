package com.example.jwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwttoken.dto.JwtAuthenticationResponse;
import com.example.jwttoken.dto.RefreshTokenRequest;
import com.example.jwttoken.dto.SignInRequest;
import com.example.jwttoken.dto.SignUpRequest;
import com.example.jwttoken.entity.User;
import com.example.jwttoken.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignUpRequest request){
		return  ResponseEntity.ok(service.signUp(request));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request){
		return  ResponseEntity.ok(service.signIn(request));
	}
	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request){
		return  ResponseEntity.ok(service.refreshToken(request));
	}
}
