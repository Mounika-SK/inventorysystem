package com.inventorysystem.inventorysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorysystem.inventorysystem.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{

}
