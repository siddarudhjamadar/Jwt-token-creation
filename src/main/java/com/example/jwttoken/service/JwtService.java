package com.example.jwttoken.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.jwttoken.entity.User;

public interface JwtService {

	
	public String extractUserName(String token);
	String generateToken(UserDetails userDetails);
	boolean isTokenValid(String token,UserDetails userDetails);
	public String generateRefreshToken(HashMap<String,Object> extraClaims,UserDetails userDetails);	
}
