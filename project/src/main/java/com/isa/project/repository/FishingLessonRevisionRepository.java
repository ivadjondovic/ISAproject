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

import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonRevision;

@Repository
public interface FishingLessonRevisionRepository extends JpaRepository<FishingLessonRevision, Long>  {
	
	public List<FishingLessonRevision> findByFishingLessonAndStatus(FishingLesson fishingLesson, String status);
	public List<FishingLessonRevision> findByStatus(String status);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select r from FishingLessonRevision r where r.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public FishingLessonRevision findLockById(@Param("id")Long id);

}
