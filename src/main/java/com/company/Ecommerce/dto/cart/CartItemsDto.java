package com.company.Ecommerce.dto.cart;

import com.company.Ecommerce.model.Cart;
import com.company.Ecommerce.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemsDto {

	private int id;
	private int quantity;
	private Product product;
	
	public CartItemsDto(Cart cart) {
		this.id = cart.getId();
		this.quantity = cart.getQuantity();
		this.product = cart.getProduct();
	}
}
