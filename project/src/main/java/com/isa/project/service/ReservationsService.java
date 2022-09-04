package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.AdminIncomeDTO;
import com.isa.project.dto.CancelReservationDTO;
import com.isa.project.dto.ReservationResponseDTO;
import com.isa.project.model.User;

public interface ReservationsService {
	
	public List<ReservationResponseDTO> getNotPassedReservations(Long clientId);
	public ReservationResponseDTO cancelReservation(CancelReservationDTO dto);
	public AdminIncomeDTO adminIncome(Long adminId);
	public User adminIncomePercentage(AdminIncomeDTO adminIncomeDTO);
}
