package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.BoatReservationResponseDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.BoatReservation;

public interface BoatReservationService {
	
	public BoatReservation createReservation(ReservationDTO dto) throws Exception;
	public BoatReservation accept(Long id);
	public List<BoatReservationResponseDTO> getByClientId(Long clientId);
	public List<BoatReservationResponseDTO> sort(SortDTO dto);

}
