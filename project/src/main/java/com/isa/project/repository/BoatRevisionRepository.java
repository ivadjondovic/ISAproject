package com.isa.project.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Boat;
import com.isa.project.model.BoatRevision;

@Repository
public interface BoatRevisionRepository extends JpaRepository<BoatRevision, Long>  {
	
	public List<BoatRevision> findByBoatAndStatus(Boat boat, String status);
	public List<BoatRevision> findByStatus(String status);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select r from BoatRevision r where r.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public BoatRevision findLockById(@Param("id")Long id);

}
