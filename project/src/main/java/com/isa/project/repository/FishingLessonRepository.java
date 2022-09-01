package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.FishingLesson;
import com.isa.project.model.User;

@Repository
public interface FishingLessonRepository extends JpaRepository<FishingLesson, Long> {

	public List<FishingLesson> findByInstructor(User instructor);
}
