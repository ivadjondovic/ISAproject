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

import com.isa.project.model.Cottage;
import com.isa.project.model.CottageRevision;

@Repository
public interface CottageRevisionRepository extends JpaRepository<CottageRevision, Long> {
	
	public List<CottageRevision> findByCottageAndStatus(Cottage cottage, String status);
	public List<CottageRevision> findByStatus(String status);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select r from CottageRevision r where r.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public CottageRevision findLockById(@Param("id")Long id);
	

}
