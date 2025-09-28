package com.Springboot.restApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Springboot.restApi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameOrEmail(String username,String name);
	
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String Email);
	
	

}
