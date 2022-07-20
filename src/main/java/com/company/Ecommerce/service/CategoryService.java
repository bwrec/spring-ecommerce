package com.company.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Ecommerce.model.Category;
import com.company.Ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public void createCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> getCategory(){
		return categoryRepository.findAll();
	}
	
	public void updateCategory(int id, Category category) {
		Category cat = categoryRepository.getById(id);
		cat.setCategoryName(category.getCategoryName());
		cat.setDescription(category.getDescription());
		cat.setImageURL(category.getImageURL());
		categoryRepository.save(cat);
	}

	public boolean findById(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).isPresent();
	}

}
