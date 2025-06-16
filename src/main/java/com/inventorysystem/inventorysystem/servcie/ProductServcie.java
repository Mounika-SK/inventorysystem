package com.inventorysystem.inventorysystem.servcie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventorysystem.inventorysystem.model.Category;
import com.inventorysystem.inventorysystem.model.Product;
import com.inventorysystem.inventorysystem.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServcie {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
		
	}
	
	public List<Product> listOfProduct(){
		return productRepository.findAll();
	}
	
	public Product findProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id).get();
		productRepository.delete(product);
	}
	

}
