package com.isa.project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.AvailablePeriodDTO;
import com.isa.project.model.AvailableFishingLessonPeriod;
import com.isa.project.model.FishingLesson;
import com.isa.project.repository.AvailableFishingLessonPeriodRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.service.AvailablePeriodService;

@Service
public class AvailablePeriodServiceImplementation implements AvailablePeriodService {

	@Autowired
	private AvailableFishingLessonPeriodRepository availableFishingLessonPeriodRepository;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Override
	public void deletePeriod(Long id) {
		
		availableFishingLessonPeriodRepository.deleteById(id);
		
	}

	@Override
	public AvailableFishingLessonPeriod savePeriod(AvailablePeriodDTO dto) {
		
		AvailableFishingLessonPeriod period = availableFishingLessonPeriodRepository.findById(dto.getId()).get();
		
		
		if(!dto.getEndDate().equals("")) {
			
			period.setEndDate(dto.getEndDate());
		}
		
		if(!dto.getStartDate().equals("")) {
			period.setStartDate(dto.getStartDate());
		}
		
		
		
		return availableFishingLessonPeriodRepository.save(period);
	}

	@Override
	public AvailableFishingLessonPeriod createPeriod(AvailablePeriodDTO dto) {
		
		if(dto.getStartDate().equals("") || dto.getEndDate().equals("")) {
			return null;
		}
		
		AvailableFishingLessonPeriod period = new AvailableFishingLessonPeriod();
		
		period.setStartDate(dto.getStartDate());
		period.setEndDate(dto.getEndDate());
		
		FishingLesson lesson = fishingLessonRepository.findById(dto.getId()).get();
		
		period.setFishingLesson(lesson);
		
		return availableFishingLessonPeriodRepository.save(period);
	}

}
