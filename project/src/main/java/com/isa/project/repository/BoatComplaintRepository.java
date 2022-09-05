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

import com.isa.project.model.BoatComplaint;

@Repository
public interface BoatComplaintRepository extends JpaRepository<BoatComplaint, Long> {
	
	public List<BoatComplaint> findByAnswered(Boolean answered);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select c from BoatComplaint c where c.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public BoatComplaint findLockById(@Param("id")Long id);

}
