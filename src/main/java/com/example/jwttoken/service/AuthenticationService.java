package com.example.jwttoken.service;

import com.example.jwttoken.dto.JwtAuthenticationResponse;
import com.example.jwttoken.dto.RefreshTokenRequest;
import com.example.jwttoken.dto.SignInRequest;
import com.example.jwttoken.dto.SignUpRequest;
import com.example.jwttoken.entity.User;

public interface AuthenticationService {
	public User signUp(SignUpRequest upRequest);
	public JwtAuthenticationResponse signIn(SignInRequest request);
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
