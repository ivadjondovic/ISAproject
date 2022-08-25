package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.AdditionalFishingLessonService;

@Repository
public interface AdditionalFishingLessonServiceRepository extends JpaRepository<AdditionalFishingLessonService, Long> {

}
