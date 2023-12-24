package com.example.jwttoken.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String token;
	private Object refreshToken;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Object getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(Object refreshToken) {
		this.refreshToken = refreshToken;
	}
	

}
