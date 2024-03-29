package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.CottageReservationResponseDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.CottageReservation;

public interface CottageReservationService {
	
	public CottageReservation createReservation(ReservationDTO dto) throws Exception;
	public CottageReservation accept(Long id);
	public List<CottageReservationResponseDTO> getByClientId(Long clientId);
	public List<CottageReservationResponseDTO> sort(SortDTO dto);

}
