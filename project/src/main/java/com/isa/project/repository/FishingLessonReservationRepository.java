package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonReservation;

@Repository
public interface FishingLessonReservationRepository  extends JpaRepository<FishingLessonReservation, Long>  {
	
	public List<FishingLessonReservation> findByClientAndAcceptedAndCanceled(Client client, Boolean accepted, Boolean canceled);
	public List<FishingLessonReservation> findByAcceptedAndCanceledAndCalculated(Boolean accepted, Boolean canceled, Boolean calculated);
	public List<FishingLessonReservation> findByClient(Client client);
	public List<FishingLessonReservation> findByFishingLessonAndCanceled(FishingLesson fishingLesson, Boolean canceled);
	
}
