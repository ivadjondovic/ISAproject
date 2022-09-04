package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.ClientReviewDTO;
import com.isa.project.dto.ClientReviewResponseDTO;
import com.isa.project.model.ClientReview;

public interface ClientReviewService {

	public ClientReview createReview(ClientReviewDTO dto);
	public ClientReview accept(ClientReviewDTO dto);
	public ClientReview decline(ClientReviewDTO dto);
	public List<ClientReviewResponseDTO> getNotCheckedPenalties();
	
}
