package com.example.jwttoken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AdminController {

	@GetMapping
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Hi Admin");
	}
	
	
}
