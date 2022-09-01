package com.isa.project.service;

import com.isa.project.dto.AvailablePeriodDTO;
import com.isa.project.model.AvailableFishingLessonPeriod;

public interface AvailablePeriodService {

	public void deletePeriod(Long id);
	public AvailableFishingLessonPeriod savePeriod(AvailablePeriodDTO dto);
	public AvailableFishingLessonPeriod createPeriod(AvailablePeriodDTO dto);
}
