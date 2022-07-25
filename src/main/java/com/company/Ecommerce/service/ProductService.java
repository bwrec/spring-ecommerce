package com.company.Ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Ecommerce.dto.ProductDto;
import com.company.Ecommerce.exceptions.ProductNotExistsException;
import com.company.Ecommerce.model.Category;
import com.company.Ecommerce.model.Product;
import com.company.Ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public void createProduct(ProductDto productDto, Category category) {
		Product product = new Product();
		product.setImageURL(productDto.getImageURL());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductName(productDto.getProductName());
		product.setCategory(category);
		productRepository.save(product);
		
	}

	public List<ProductDto> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		List <ProductDto> allProductsDto = new ArrayList<>();
		for(Product p:allProducts) {
			allProductsDto.add(productToDto(p));
		}
		return allProductsDto;
	}
	
	public ProductDto productToDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setProdId(product.getProdId());
		productDto.setImageURL(product.getImageURL());
		productDto.setProductPrice(product.getProductPrice());
		productDto.setProductDescription(product.getProductDescription());
		productDto.setProductName(product.getProductName());
		productDto.setCategoryId(product.getCategory().getId());
		return productDto;
	}

	public boolean findById(Integer prodId) {
		return productRepository.findById(prodId).isPresent();
	}
	
	public Product findById(int productId) throws ProductNotExistsException{
		Optional<Product> optionalProduct = productRepository.findById(productId);
		System.out.println("[ "+productId+" ]");
		if(!optionalProduct.isPresent()) {
			throw new ProductNotExistsException("Product ID "+productId+" is invalid");
		}
		return optionalProduct.get();
	}

	public void updateProduct(Integer prodId, ProductDto productDto) {
		Product product = productRepository.getById(prodId);
		productDto.setProdId(product.getProdId());
		product.setImageURL(productDto.getImageURL());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductName(productDto.getProductName());
		productRepository.save(product);
		
	}
	
}
