package com.isa.project.service;

import com.isa.project.dto.LoyaltyProgramDTO;
import com.isa.project.model.LoyaltyProgram;

public interface LoyaltyProgramService {

	public LoyaltyProgram createProgram(LoyaltyProgramDTO dto) throws Exception;
	
}
