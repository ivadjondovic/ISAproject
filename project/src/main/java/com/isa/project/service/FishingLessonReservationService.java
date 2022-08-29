package com.isa.project.service;

import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.FishingLessonReservation;

public interface FishingLessonReservationService {

	public FishingLessonReservation createReservation(ReservationDTO dto);
	public FishingLessonReservation accept(Long id);
}
