package com.Springboot.restApi.Security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Springboot.restApi.entity.User;
import com.Springboot.restApi.repository.UserRepository;

@Service
public class customUserDetailService implements UserDetailsService {
	
	private UserRepository userRepository;
	public customUserDetailService(UserRepository userRepository) {
 		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		 
		User user= userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
		.orElseThrow(()-> new UsernameNotFoundException("user not found in email or user name"+ usernameOrEmail));
		
		Set<GrantedAuthority> authority=user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
		 
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(),
				authority);
	}

}
