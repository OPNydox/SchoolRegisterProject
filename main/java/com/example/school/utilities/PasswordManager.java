package com.example.school.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordManager {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
