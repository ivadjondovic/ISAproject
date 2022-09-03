package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Cottage;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long> {

	public List<Cottage> findByDeleted(Boolean deleted);
	
}
