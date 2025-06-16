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
import com.inventorysystem.inventorysystem.model.Supplier;
import com.inventorysystem.inventorysystem.repository.ProductRepository;
import com.inventorysystem.inventorysystem.repository.SupplierRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = ("classpath:application-test.properties"))
public class SupplierRepositoryTest {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Test
	@DisplayName("Test for Save Method")
	public void testForSave() {
		Supplier supplier = new Supplier();
		supplier.setName("Mouni");
		supplier.setAddress("Abcd street , sivangiri");
		supplierRepository.save(supplier);
		
		Supplier supplierOne = supplierRepository.findById(supplier.getSupplierId()).orElse(null);
		
		assertThat(supplierOne).isNotNull();
		
		
	}
    
	@Test
	@DisplayName("Test for FindAll Method")
	public void testforfindallmethod() {
		Supplier supplier = new Supplier();
		supplier.setName("Mouni");
		supplier.setAddress("Abcd street , sivangiri");
		supplierRepository.save(supplier);
		
		Supplier supplierOne = new Supplier();
		supplierOne.setName("Mohan");
		supplierOne.setAddress("sabd street , sivangiri");
		supplierRepository.save(supplierOne);
		
		List<Supplier> supplierList = supplierRepository.findAll();
		
		assertThat(supplierList).isNotNull();
		assertThat(supplierList.size()).isGreaterThanOrEqualTo(2);
		 
	}
	
	
	@Test
	@DisplayName("Test for FindById Method")
	public void testforfindbyid() {
		Supplier supplier = new Supplier();
		supplier.setName("Mouni");
		supplier.setAddress("Abcd street , sivangiri");
		supplierRepository.save(supplier);
		
		Supplier supplierOne = supplierRepository.findById(supplier.getSupplierId()).orElse(null);
		
		assertThat(supplierOne).isEqualTo(supplier);
	}
	
	
	@Test
	@DisplayName("Test for Delete Method")
	public void testfordelete() {
		Supplier supplier = new Supplier();
		supplier.setName("Mouni");
		supplier.setAddress("Abcd street , sivangiri");
		supplierRepository.save(supplier);
		
		supplierRepository.delete(supplier);
		Supplier supplierOne = supplierRepository.findById(supplier.getSupplierId()).orElse(null);
		
		assertThat(supplierOne).isNull();
		
		
		
	}

}
