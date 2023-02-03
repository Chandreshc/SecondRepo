package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.RegistrationUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	//authentication
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//		UserDetails admin=User.withUsername("Chandresh")
//				.password(encoder.encode("Pass"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user=User.withUsername("Loli")
//				.password(encoder.encode("Lalu"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
		
		return new RegistrationUserDetailsService();
	}
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/login","/register").permitAll()
		.and()
		.authorizeHttpRequests().requestMatchers("/*/tasks").authenticated()
		.formLogin().and().build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
