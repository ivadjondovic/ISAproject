package com.isa.project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.repository.AvailableFishingLessonPeriodRepository;
import com.isa.project.service.AvailablePeriodService;

@Service
public class AvailablePeriodServiceImplementation implements AvailablePeriodService {

	@Autowired
	private AvailableFishingLessonPeriodRepository availableFishingLessonPeriodRepository;
	
	@Override
	public void deletePeriod(Long id) {
		
		availableFishingLessonPeriodRepository.deleteById(id);
		
	}

}
