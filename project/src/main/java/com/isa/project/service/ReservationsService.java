package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.CancelReservationDTO;
import com.isa.project.dto.ReservationResponseDTO;

public interface ReservationsService {
	
	public List<ReservationResponseDTO> getNotPassedReservations(Long clientId);
	public ReservationResponseDTO cancelReservation(CancelReservationDTO dto);
}
