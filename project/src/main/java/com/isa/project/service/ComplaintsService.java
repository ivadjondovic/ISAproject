package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.ComplaintAnswerDTO;
import com.isa.project.dto.ComplaintResponseDTO;

public interface ComplaintsService {
	
	public List<ComplaintResponseDTO> notAnsweredComplaints();
	public ComplaintResponseDTO answer(ComplaintAnswerDTO dto);

}
