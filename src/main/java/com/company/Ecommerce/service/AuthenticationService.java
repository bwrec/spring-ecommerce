package com.company.Ecommerce.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Ecommerce.exceptions.AuthenticationFailedException;
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
	
	public User getUser(String token) {
		AuthenticationToken t = authenticationTokenRepository.findByToken(token);
		if(Objects.isNull(t)) {
			return null;
		}
		return t.getUser();
	}
	
	public void authenticateToken(String token) {
		if(Objects.isNull(token)) {
			throw new AuthenticationFailedException("Token not present");
		}
		if(Objects.isNull(getUser(token))) {
			throw new AuthenticationFailedException("Token not valid");
		}
	}

}
