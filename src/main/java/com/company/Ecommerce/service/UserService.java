package com.company.Ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Ecommerce.dto.ResponseDto;
import com.company.Ecommerce.dto.user.SigninDto;
import com.company.Ecommerce.dto.user.SigninResponseDto;
import com.company.Ecommerce.dto.user.SignupDto;
import com.company.Ecommerce.exceptions.AuthenticationFailedException;
import com.company.Ecommerce.exceptions.CustomException;
import com.company.Ecommerce.model.AuthenticationToken;
import com.company.Ecommerce.model.User;
import com.company.Ecommerce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Transactional
	public ResponseDto signup(SignupDto signupDto) {
		//check if already present
		if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
			throw new CustomException("User already exists!");
		}
		
		//encrypt password
		String encryptedPassword = signupDto.getPassword();
		try {
			encryptedPassword = hashPassword(signupDto.getPassword());
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//save the user
		User user = new User();
		user.setEmail(signupDto.getEmail());
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastName());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		
		
		//create a token
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		authenticationService.saveConfirmationToken(authenticationToken);
		
		ResponseDto responseDto = new ResponseDto("success", "created");
		return responseDto;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter
						.printHexBinary(digest).toUpperCase();
		return hash;
	}

	public SigninResponseDto signIn(SigninDto signinDto) {
		//FIND USER BY EMAIL
		User user = userRepository.findByEmail(signinDto.getEmail());
		if(Objects.isNull(user)) {
			throw new AuthenticationFailedException("username or password is not valid");
		}
		
		//hash the password
		//compare the passwords
		try {
			if(!user.getPassword().equals(hashPassword(signinDto.getPassword()))) {
				throw new AuthenticationFailedException("username or password is not valid");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		//if match, retreive token and return response
		AuthenticationToken token = authenticationService.getToken(user);
		if(Objects.isNull(token)) {
			throw new CustomException("token not present");
		}
		return new SigninResponseDto("success", token.getToken());
	}

}
