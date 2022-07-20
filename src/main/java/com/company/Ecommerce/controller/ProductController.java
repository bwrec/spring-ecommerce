package com.company.Ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.Ecommerce.commons.APIResponse;
import com.company.Ecommerce.dto.ProductDto;
import com.company.Ecommerce.model.Category;
import com.company.Ecommerce.repository.CategoryRepository;
import com.company.Ecommerce.service.ProductService;

@RestController
@RequestMapping("v1/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@PostMapping("/postProduct")
	public ResponseEntity<APIResponse> createProduct(@RequestBody ProductDto productDto){
		Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<>(new APIResponse(false, "category doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		productService.createProduct(productDto, optionalCategory.get());
		return new ResponseEntity<>(new APIResponse(true, "product has been added"), HttpStatus.CREATED);
	}
	
	@GetMapping("/getProduct")
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> allProductsDto = productService.getAllProducts();
		return new ResponseEntity<>(allProductsDto, HttpStatus.OK);
	}
	
	@PostMapping("/updateProduct/{prodId}")
	public ResponseEntity<APIResponse> updateProduct(@PathVariable("prodId") Integer prodId, @RequestBody ProductDto productDto){
		if(!(productService.findById(prodId))) {
			return new ResponseEntity<>(new APIResponse(false, "product doesn't exists"),HttpStatus.NOT_FOUND);
		}
		productService.updateProduct(prodId, productDto);
		return new ResponseEntity<>(new APIResponse(true, "product has been updated"), HttpStatus.CREATED);
	}
}
