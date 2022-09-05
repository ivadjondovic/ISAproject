package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.FishingLessonReservationResponseDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.FishingLessonReservation;

public interface FishingLessonReservationService {

	public FishingLessonReservation createReservation(ReservationDTO dto)throws Exception;
	public FishingLessonReservation accept(Long id);
	public List<FishingLessonReservationResponseDTO> getByClientId(Long clientId);
	public List<FishingLessonReservationResponseDTO> getByInstructorId(Long instructorId);
	public List<FishingLessonReservationResponseDTO> sort(SortDTO dto);
}
