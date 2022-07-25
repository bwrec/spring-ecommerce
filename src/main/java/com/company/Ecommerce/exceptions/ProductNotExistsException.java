package com.company.Ecommerce.exceptions;

public class ProductNotExistsException extends IllegalArgumentException {

	public ProductNotExistsException(String message) {
		super(message);
	}
}
