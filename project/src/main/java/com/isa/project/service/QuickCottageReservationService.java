package com.isa.project.service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickCottageReservation;

public interface QuickCottageReservationService {

	public Client clientReservation(QuickClientReservationDTO dto) throws Exception;
	public QuickCottageReservation accept(Long id);
}
