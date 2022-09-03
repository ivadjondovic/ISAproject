package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Boat;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {

	public List<Boat> findByDeleted(Boolean deleted);
	
}
