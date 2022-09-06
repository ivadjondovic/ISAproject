package com.isa.project.repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.isa.project.model.LoyaltyProgram;


public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long>{

	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from LoyaltyProgram p where p.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public LoyaltyProgram findLockById(@Param("id")Long id);
	
	
}
