package com.company.Ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Ecommerce.dto.cart.AddToCartDto;
import com.company.Ecommerce.dto.cart.CartDto;
import com.company.Ecommerce.dto.cart.CartItemsDto;
import com.company.Ecommerce.exceptions.CustomException;
import com.company.Ecommerce.model.Cart;
import com.company.Ecommerce.model.Product;
import com.company.Ecommerce.model.User;
import com.company.Ecommerce.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	ProductService productService;
	
	@Autowired
	CartRepository cartRepository;
	
	public void addToCart(AddToCartDto addToCartDto, User user) {
		
		//validate product id
		System.out.println(addToCartDto.toString());
		Product product = productService.findById(addToCartDto.getProductId());
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(addToCartDto.getQuantity());
		cart.setCreatedDate(new Date());
		
		//save the cart
		cartRepository.save(cart);
	}


	public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
		Cart cart = new Cart(product, user, addToCartDto.getQuantity());
        cartRepository.save(cart);
	}


	public CartDto listCartItems(User user) {
		double totalPrice = 0;
		List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemsDto> cartItemDtoList = new ArrayList<>();
		for( Cart cart:cartList) {
			CartItemsDto cartItemsDto = new CartItemsDto(cart);
			cartItemDtoList.add(cartItemsDto);
			totalPrice += cart.getQuantity() * cart.getProduct().getProductPrice();
		}
		CartDto cartDto = new CartDto();
		cartDto.setTotalCost(totalPrice);
		cartDto.setCartItems(cartItemDtoList);
		return cartDto;
	}


	public void deleteCartItem(int itemId, User user) {
		//check item id belongs to user
		Optional<Cart> cartItem = cartRepository.findById(itemId);
		if(!cartItem.isPresent()) {
			throw new CustomException("item id ["+itemId+"] doesn't exist");
		}
		Cart cart = cartItem.get();
		if(cart.getUser() != user) {
			throw new CustomException("cart item "+cart.toString()+" doesn't belong to user");
		}
		cartRepository.delete(cart);
		
		//
	}

}
