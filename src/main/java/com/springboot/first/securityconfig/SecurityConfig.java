package com.springboot.first.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.first.Security.JwtAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		http.csrf().disable()
        .authorizeRequests()
        .requestMatchers("/api/login/**").permitAll()
        .requestMatchers("/api/register/**").permitAll()
        .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
        .requestMatchers("/api/users/**").hasAuthority("ADMIN")
        .requestMatchers("/api/books/**").hasAuthority("ADMIN")
        .requestMatchers("/api/bookhistory/issueBook").hasAuthority("ADMIN")
        .requestMatchers("/api/bookhistory/returnBook").hasAuthority("USER")
        .requestMatchers("/api/bookhistory/historyByBookId/**").hasAuthority("ADMIN")
        .requestMatchers("/api/bookhistory/findUserTakenBooks/**").hasAuthority("ADMIN")
        .requestMatchers("/api/fines/**").hasAuthority("ADMIN")
        .anyRequest().authenticated();
		
    
		http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
	    return http.build();
	}
	
}
