package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.configuration.RegistrationUserDetails;
import com.example.demo.model.Registration;

@Component
public class RegistrationUserDetailsService implements UserDetailsService {
	
	@Autowired
	private RegistrationService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Registration> registration=service.fetchByUsername(username);
		return registration.map(RegistrationUserDetails::new)
		.orElseThrow(()->new UsernameNotFoundException("user not found "+username));
	}

}
