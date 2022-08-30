package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.ReservationResponseDTO;

public interface ReservationsService {
	
	List<ReservationResponseDTO> getNotPassedReservations(Long clientId);
	
}
