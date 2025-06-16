package com.inventorysystem.inventorysystem.servcie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventorysystem.inventorysystem.model.Category;
import com.inventorysystem.inventorysystem.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
		
	}
	
	public List<Category> listOfCategory(){
		return categoryRepository.findAll();
	}
	
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}
	
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id).get();
		categoryRepository.delete(category);
	}

}
