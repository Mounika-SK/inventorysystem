package com.inventorysystem.inventorysystem.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.inventorysystem.inventorysystem.model.Category;
import com.inventorysystem.inventorysystem.model.Product;
import com.inventorysystem.inventorysystem.repository.CategoryRepository;
import com.inventorysystem.inventorysystem.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = ("classpath:application-test.properties"))
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@DisplayName("Test for Save Method")
	public void testForSave() {
		Product product = new Product();
		product.setProductName("Mobile");
		product.setPrice(2000);
		product.setDescriprion("SecondHanded Phone");
		productRepository.save(product);
		
		Product productOne = productRepository.findById(product.getProductId()).orElse(null);
		
		assertThat(product).isNotNull();
		
		
	}
    
	@Test
	@DisplayName("Test for FindAll Method")
	public void testforfindallmethod() {
		Product product = new Product();
		product.setProductName("Mobile");
		product.setPrice(2000);
		product.setDescriprion("SecondHanded Phone");
		productRepository.save(product);
		
		Product productOne = new Product();
		productOne.setProductName("Fan");
		productOne.setPrice(2000);
		productOne.setDescriprion("Bajaj");
		productRepository.save(productOne);
		
		List<Product> productList = productRepository.findAll();
		
		assertThat(productList).isNotNull();
		assertThat(productList.size()).isGreaterThanOrEqualTo(2);
		 
	}
	
	
	@Test
	@DisplayName("Test for FindById Method")
	public void testforfindbyid() {
		Product product = new Product();
		product.setProductName("Mobile");
		product.setPrice(2000);
		product.setDescriprion("SecondHanded Phone");
		productRepository.save(product);
		
		Product productOne = productRepository.findById(product.getProductId()).orElse(null);
		
		
		assertThat(productOne).isEqualTo(product);
	}
	
	
	@Test
	@DisplayName("Test for Delete Method")
	public void testfordelete() {
		Product product = new Product();
		product.setProductName("Mobile");
		product.setPrice(2000);
		product.setDescriprion("SecondHanded Phone");
		productRepository.save(product);
		
		
		productRepository.delete(product);
		Product productOne = productRepository.findById(product.getProductId()).orElse(null);
		
		assertThat(productOne).isNull();
		
		
		
	}

}
