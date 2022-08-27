package com.isa.project.service;

import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.BoatReservation;

public interface BoatReservationService {
	
	public BoatReservation createReservation(ReservationDTO dto);

}
