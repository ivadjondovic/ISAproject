package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.AvailableFishingLessonPeriod;

@Repository
public interface AvailableFishingLessonPeriodRepository extends JpaRepository<AvailableFishingLessonPeriod, Long>  {

}
