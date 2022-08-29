package com.isa.project.service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;

public interface QuickBoatReservationService {

	public Client clientReservation(QuickClientReservationDTO dto);
}
