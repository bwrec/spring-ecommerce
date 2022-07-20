package com.company.Ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodId;
	
	@Column(name = "product_name")
	@NotBlank
	private String productName;
	
	@Column(name = "prod_image_url")
	@NotBlank
	private String imageURL;
	
	@Column(name = "product_price")
	@NotBlank
	private double productPrice;
	
	@Column(name = "product_description")
	@NotBlank
	private String productDescription;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonIgnore
	Category category;
}
