package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.IncomeResponseDTO;

public interface IncomeService {

	public List<IncomeResponseDTO> getReservationIncome(Long adminId);
	
}
