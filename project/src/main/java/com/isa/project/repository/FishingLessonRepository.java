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
import com.isa.project.model.User;

@Repository
public interface FishingLessonRepository extends JpaRepository<FishingLesson, Long> {

	public List<FishingLesson> findByInstructor(User instructor);
	public List<FishingLesson> findByInstructorAndDeleted(User instructor, Boolean deleted);
	public List<FishingLesson> findByDeleted(Boolean deleted);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select f from FishingLesson f where f.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public FishingLesson findLockById(@Param("id")Long id);
}
