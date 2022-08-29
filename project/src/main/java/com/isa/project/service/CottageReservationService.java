package com.isa.project.service;

import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.CottageReservation;

public interface CottageReservationService {
	
	public CottageReservation createReservation(ReservationDTO dto);
	public CottageReservation accept(Long id);

}
