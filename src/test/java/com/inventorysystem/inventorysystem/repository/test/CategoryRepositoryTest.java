package com.inventorysystem.inventorysystem.repository.test;

import com.inventorysystem.inventorysystem.model.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.inventorysystem.inventorysystem.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = ("classpath:application-test.properties"))

public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	@DisplayName("Test for Save Method")
	public void testForSave() {
		Category category = new Category();
		category.setCategoryName("Electronics");
		categoryRepository.save(category);
		
		Category categoryOne = categoryRepository.findById(category.getCategoryId()).orElse(null);
		
		assertThat(categoryOne).isNotNull();
		
		
	}
    
	@Test
	@DisplayName("Test for FindAll Method")
	public void testforfindallmethod() {
		Category category = new Category();
		category.setCategoryName("Electronics");
		categoryRepository.save(category);
		
		Category categoryOne = new Category();
		categoryOne.setCategoryName("Tools");
		categoryRepository.save(categoryOne);
		
		List<Category> categoryList = categoryRepository.findAll();
		
		assertThat(categoryList).isNotNull();
		assertThat(categoryList.size()).isGreaterThanOrEqualTo(2);
		 
	}
	
	
	@Test
	@DisplayName("Test for FindById Method")
	public void testforfindbyid() {
		Category category = new Category();
		category.setCategoryName("Electronics");
		categoryRepository.save(category);
		
		Category categoryOne = categoryRepository.findById(category.getCategoryId()).orElse(null);
		
		
		assertThat(categoryOne).isEqualTo(category);
	}
	
	
	@Test
	@DisplayName("Test for Delete Method")
	public void testfordelete() {
		Category category = new Category();
		category.setCategoryName("Electronics");
		categoryRepository.save(category);
		
		categoryRepository.delete(category);
		
		Category categoryOne = categoryRepository.findById(category.getCategoryId()).orElse(null);
		assertThat(categoryOne).isNull();
		
		
		
	}
	
}
