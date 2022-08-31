package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.FishingLessonComplaint;

@Repository
public interface FishingLessonComplaintRepository extends JpaRepository<FishingLessonComplaint, Long>  {
	
	public List<FishingLessonComplaint> findByAnswered(Boolean answered);

}
