package com.isa.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.model.AdditionalFishingLessonService;
import com.isa.project.model.FishingLesson;
import com.isa.project.repository.AdditionalFishingLessonServiceRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.service.AdditionalServiceService;

@Service
public class AdditionalServiceServiceImplementation implements AdditionalServiceService {

	@Autowired
	AdditionalFishingLessonServiceRepository additionalFishingLessonServiceRepository;
	
	@Autowired
	FishingLessonRepository fishingLessonRepository;
	
	@Override
	public void deleteService(Long id) {
		
		additionalFishingLessonServiceRepository.deleteById(id);
		
	}

	@Override
	public AdditionalFishingLessonService saveService(AdditionalServiceDTO dto) {
		
		AdditionalFishingLessonService service = additionalFishingLessonServiceRepository.findById(dto.getId()).get();
		
		if(!dto.getDescription().equals("")) {
			service.setDescription(dto.getDescription());
		}
		
		if(dto.getPrice() != null) {
			service.setPrice(dto.getPrice());
		}
	
		
		return additionalFishingLessonServiceRepository.save(service);
		
	}

	@Override
	public AdditionalFishingLessonService createService(AdditionalServiceDTO dto) {
		
		if(dto.getDescription().equals("") || dto.getPrice() == null) {
			return null;
		}
		
		AdditionalFishingLessonService service = new AdditionalFishingLessonService();
		
		service.setDescription(dto.getDescription());
		service.setPrice(dto.getPrice());
		
		FishingLesson lesson = fishingLessonRepository.findById(dto.getId()).get();
		
		service.setFishingLesson(lesson);
		
		return additionalFishingLessonServiceRepository.save(service);
	}

}
