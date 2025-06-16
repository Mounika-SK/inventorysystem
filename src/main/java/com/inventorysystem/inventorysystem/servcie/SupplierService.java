package com.inventorysystem.inventorysystem.servcie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventorysystem.inventorysystem.model.Product;
import com.inventorysystem.inventorysystem.model.Supplier;
import com.inventorysystem.inventorysystem.repository.ProductRepository;
import com.inventorysystem.inventorysystem.repository.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
		
	}
	
	public List<Supplier> listOfSupplier(){
		return supplierRepository.findAll();
	}
	
	public Supplier findSupplierById(Long id) {
		return supplierRepository.findById(id).orElse(null);
	}
	
	public void deleteProduct(Long id) {
		Supplier supplier = supplierRepository.findById(id).get();
		supplierRepository.delete(supplier);
	}

}
