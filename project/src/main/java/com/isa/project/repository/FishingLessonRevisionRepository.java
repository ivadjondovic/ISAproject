package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonRevision;

@Repository
public interface FishingLessonRevisionRepository extends JpaRepository<FishingLessonRevision, Long>  {
	
	public List<FishingLessonRevision> findByFishingLesson(FishingLesson fishingLesson);
	public List<FishingLessonRevision> findByStatus(String status);

}
