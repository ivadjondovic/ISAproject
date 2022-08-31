package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Boat;
import com.isa.project.model.BoatRevision;

@Repository
public interface BoatRevisionRepository extends JpaRepository<BoatRevision, Long>  {
	
	public List<BoatRevision> findByBoat(Boat boat);

}
