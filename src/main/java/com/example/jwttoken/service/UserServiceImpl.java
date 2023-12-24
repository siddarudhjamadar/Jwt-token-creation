package com.example.jwttoken.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwttoken.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	@Autowired
	private  UserRepository repository;
	
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return repository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
			}
		};
	}
}
