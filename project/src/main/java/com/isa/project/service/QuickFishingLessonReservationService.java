package com.isa.project.service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickFishingLessonReservation;

public interface QuickFishingLessonReservationService {

	public Client clientReservation(QuickClientReservationDTO dto);
	public QuickFishingLessonReservation accept(Long id);
	public void deleteReservation(Long id); 
}
