package com.company.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Ecommerce.model.AuthenticationToken;
import com.company.Ecommerce.model.User;
import com.company.Ecommerce.repository.AuthenticationTokenRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	AuthenticationTokenRepository authenticationTokenRepository;

	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		authenticationTokenRepository.save(authenticationToken);
		
	}

	public AuthenticationToken getToken(User user) {
		return authenticationTokenRepository.findByUser(user);
	}

}
