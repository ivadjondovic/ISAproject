package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.FishingLessonReservation;

@Repository
public interface FishingLessonReservationRepository  extends JpaRepository<FishingLessonReservation, Long>  {

}
