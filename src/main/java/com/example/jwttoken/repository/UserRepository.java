package com.example.jwttoken.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwttoken.entity.Role;
import com.example.jwttoken.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

Optional<User>	findByEmail(String email);
 User findByRole(Role role);
}
