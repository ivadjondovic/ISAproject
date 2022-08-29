package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Client;
import com.isa.project.model.FishingLessonReservation;

@Repository
public interface FishingLessonReservationRepository  extends JpaRepository<FishingLessonReservation, Long>  {
	
	public List<FishingLessonReservation> findByClientAndAccepted(Client client, Boolean accepted);

}
