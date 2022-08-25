package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.QuickFishingLessonReservation;

@Repository
public interface QuickFishingLessonReservationRepository extends JpaRepository<QuickFishingLessonReservation, Long> {

}
