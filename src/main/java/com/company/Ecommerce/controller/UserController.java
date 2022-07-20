package com.company.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.Ecommerce.dto.ResponseDto;
import com.company.Ecommerce.dto.user.SigninDto;
import com.company.Ecommerce.dto.user.SigninResponseDto;
import com.company.Ecommerce.dto.user.SignupDto;
import com.company.Ecommerce.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {

	@Autowired
	UserService userService;
	
	//sign up
	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody SignupDto signupDto) {
		return userService.signup(signupDto);
	}
	
	//sign-in
	@PostMapping("/sign-in")
	public SigninResponseDto signIn(@RequestBody SigninDto signinDto) {
		return userService.signIn(signinDto);
	}
}
