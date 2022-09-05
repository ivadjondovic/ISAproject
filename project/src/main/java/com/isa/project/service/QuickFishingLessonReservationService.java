package com.isa.project.service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickFishingLessonReservation;

public interface QuickFishingLessonReservationService {

	public Client clientReservation(QuickClientReservationDTO dto) throws Exception;
	public QuickFishingLessonReservation accept(Long id);
	public void deleteReservation(Long id); 
	public QuickFishingLessonReservation saveReservation(QuickReservationDTO dto);
	public QuickFishingLessonReservation createReservation(QuickReservationDTO dto);
}
