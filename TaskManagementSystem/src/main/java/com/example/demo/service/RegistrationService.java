package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RegistrationRepo;
import com.example.demo.model.Registration;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Registration saveRegistration(Registration reg) {
		reg.setPassword(passwordEncoder.encode(reg.getPassword()));
		return repo.save(reg);
	}
	
	public Registration fetchByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Optional<Registration> fetchByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public Registration fetchByEmailAndPassword(String email,String password) {
		return repo.findByEmailAndPassword(email,password);
	}
}
