package com.inventorysystem.inventorysystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inventorysystem.inventorysystem.exception.*;
import com.inventorysystem.inventorysystem.model.*;
import com.inventorysystem.inventorysystem.servcie.*;

@Controller
public class InventoryController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductServcie  productService;

	@Autowired
	private SupplierService supplierService;
	
	@PostMapping("/category/save")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category){
		Category categoryOne = categoryService.saveCategory(category);
		return new ResponseEntity<Category>(categoryOne,HttpStatus.OK);
	}
	
	@GetMapping("/category/all")
	public ResponseEntity<List<Category>> allCategory(){
		List<Category> categoryList = categoryService.listOfCategory();
		return new ResponseEntity<List<Category>>(categoryList,HttpStatus.OK);
		
	}
	
	@PostMapping("/product/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		Category category = categoryService.findCategoryById(product.getCategory().getCategoryId());
		category.getProductList().add(product);
		Product productOne = productService.saveProduct(product);
		return new ResponseEntity<Product>(productOne,HttpStatus.OK);
		
	}
	
	@GetMapping("/product/all")
	public ResponseEntity<List<Product>> allProduct(){
		List<Product> productList = productService.listOfProduct();
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
		
	}
	
	@GetMapping("/product/get/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id){
		Product product = productService.findProductById(id);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@PostMapping("/product/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id ,@RequestBody Product product){
		Product productOne = productService.findProductById(id);
		productOne.setProductName(product.getProductName());
		productOne.setPrice(product.getPrice());
		productOne.setDescriprion(product.getDescriprion());
		productOne.setCategory(product.getCategory());
		Category category = categoryService.findCategoryById(product.getCategory().getCategoryId());
		category.getProductList().add(productOne);
		for(Supplier s :productOne.getSupplierList()) {
			s.setProduct(product);
		}
		productService.saveProduct(productOne);
		return new ResponseEntity<Product>(productOne,HttpStatus.OK);
	}
	
	@GetMapping("product/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		productService.deleteProduct(id);
		return new ResponseEntity<String>("The Product of given id is "+id+" ,It is Deleted",HttpStatus.OK);
	}
	 
	@GetMapping("/product/supplier/{id}")
	public ResponseEntity<Product> getProductBySupplier(@PathVariable Long id) throws SupplierNotFoundException{
		Supplier supplier = supplierService.findSupplierById(id);
		if(supplier==null) {
			throw new SupplierNotFoundException("Supplier is not Found");
		}
		Product product = supplier.getProduct();
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@GetMapping("/product/category/{id}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long id)throws NullException{
	    Category category = categoryService.findCategoryById(id);
	    if(category.getProductList().isEmpty()) {
	    	throw new  NullException("Category has No Product yet");
	    }
		List<Product> productList = category.getProductList() ;
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/supplier/save")
	public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier){
		Category category = categoryService.findCategoryById(supplier.getCategory().getCategoryId());
		category.getSupplierList().add(supplier);
		Product product = productService.findProductById(supplier.getProduct().getProductId());
		product.getSupplierList().add(supplier);
		Supplier supplierOne = supplierService.saveSupplier(supplier);
		return new ResponseEntity<Supplier>(supplierOne,HttpStatus.OK);
	}
	
	@GetMapping("/supplier/all")
	public ResponseEntity<List<Supplier>> allSupplier(){
		List<Supplier> supplierList = supplierService.listOfSupplier();
		return new ResponseEntity<List<Supplier>>(supplierList,HttpStatus.OK);
		
	}
	
	
	
	
	

}
