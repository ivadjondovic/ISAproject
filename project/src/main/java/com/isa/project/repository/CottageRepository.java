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

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long> {

	public List<Cottage> findByDeleted(Boolean deleted);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select c from Cottage c where c.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public Cottage findLockById(@Param("id")Long id);
	
}
