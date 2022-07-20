package com.company.Ecommerce.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.company.Ecommerce.model.Category;
import com.company.Ecommerce.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * DTO IS DATA TRANSFER OBJECT.
 * WE CANNOT DIRECTLY USE OUR PRODUCT MODEL AS IT ALSO NEEDS CATEGORY.
 * WE SHALL USE DTO.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

	private int prodId;
	private String productName;
	private String imageURL;
	private double productPrice;
	private String productDescription;
	private Integer categoryId;
}
