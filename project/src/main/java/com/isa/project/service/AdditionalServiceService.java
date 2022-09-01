package com.isa.project.service;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.model.AdditionalFishingLessonService;

public interface AdditionalServiceService {

	public void deleteService(Long id);
	public AdditionalFishingLessonService saveService(AdditionalServiceDTO dto);
	public AdditionalFishingLessonService createService(AdditionalServiceDTO dto);
	
}
