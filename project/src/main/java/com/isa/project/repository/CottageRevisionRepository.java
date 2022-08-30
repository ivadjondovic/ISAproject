package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Cottage;
import com.isa.project.model.CottageRevision;

@Repository
public interface CottageRevisionRepository extends JpaRepository<CottageRevision, Long> {
	
	public List<CottageRevision> findByCottage(Cottage cottage);

}
