package com.uxpsystems.assignment.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.dao.UserRepository;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		String userName = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user = userRepository.findByUserName(userName);
		if(password.equals(user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
