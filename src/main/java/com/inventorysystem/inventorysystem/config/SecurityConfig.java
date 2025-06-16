package com.inventorysystem.inventorysystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeRequests()
		.requestMatchers("/category/**").permitAll()
		.requestMatchers("/supplier/**").hasAnyRole("SUPPLIER")
		.anyRequest().authenticated()
		.and().httpBasic();
		
		return http.build();
	}
    
	@Bean
	public UserDetailsService userDetailsServcie() {
		UserDetails supplier = User.builder().username("supplier").password(passwordEncoder().encode("password")).roles("SUPPLIER")
				.build();
		
		
		
		return new InMemoryUserDetailsManager(supplier);
	}
	
	@Bean
	public  PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
