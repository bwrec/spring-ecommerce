package com.company.Ecommerce.exceptions;


public class AuthenticationFailedException extends IllegalArgumentException{

	public AuthenticationFailedException(String message) {
		super(message);
	}
}
