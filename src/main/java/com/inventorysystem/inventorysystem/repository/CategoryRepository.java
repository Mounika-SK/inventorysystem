package com.inventorysystem.inventorysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventorysystem.inventorysystem.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
