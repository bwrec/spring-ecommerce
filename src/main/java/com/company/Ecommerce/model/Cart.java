package com.company.Ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name="prod_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="cart_id")
	private User user;
	
	@Column(name="quantity")
	private int quantity;
	
	public Cart(Product product2, User user2, int quantity2) {
		this.product = product2;
		this.user = user2;
		this.quantity = quantity2;
		this.createdDate = new Date();
	}
	
}
