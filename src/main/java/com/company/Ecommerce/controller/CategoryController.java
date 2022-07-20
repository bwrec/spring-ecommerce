package com.company.Ecommerce.controller;

import java.util.List;

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
import com.company.Ecommerce.model.Category;
import com.company.Ecommerce.service.CategoryService;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/postCategory")
	public ResponseEntity<APIResponse> createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>(new APIResponse(true, "Created new category"), HttpStatus.CREATED);
	}
	
	@GetMapping("/getCategory")
	public ResponseEntity<List<Category>> getCategory() {
		List<Category> allCategories = categoryService.getCategory();
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}
	
	@PostMapping("/updateCategory/{id}")
	public ResponseEntity<APIResponse> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
		if(!(categoryService.findById(id))) {
			System.out.println(categoryService.findById(id));
			return new ResponseEntity<>(new APIResponse(false, "Category not found"), HttpStatus.NOT_FOUND);
		}
		System.out.println(id+" updated");
		categoryService.updateCategory(id, category);
		return new ResponseEntity<>(new APIResponse(true, "Updated category"), HttpStatus.OK);
	}
	

}
