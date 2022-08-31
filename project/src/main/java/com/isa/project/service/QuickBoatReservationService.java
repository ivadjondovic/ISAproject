package com.isa.project.service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickBoatReservation;

public interface QuickBoatReservationService {

	public Client clientReservation(QuickClientReservationDTO dto);
	public QuickBoatReservation accept(Long id);
}
