package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.project.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	public Category findByCategory(String category);
	
}
