package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Client;
import com.isa.project.model.QuickFishingLessonReservation;

@Repository
public interface QuickFishingLessonReservationRepository extends JpaRepository<QuickFishingLessonReservation, Long> {

	public List<QuickFishingLessonReservation> findByClientAndAcceptedAndCanceled(Client client, Boolean accepted, Boolean canceled);
	public List<QuickFishingLessonReservation> findByAcceptedAndCanceledAndCalculated(Boolean accepted, Boolean canceled, Boolean calculated);
	public List<QuickFishingLessonReservation> findByClient(Client client);
}
