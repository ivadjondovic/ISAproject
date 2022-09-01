package com.isa.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.repository.AdditionalFishingLessonServiceRepository;
import com.isa.project.service.AdditionalServiceService;

@Service
public class AdditionalServiceServiceImplementation implements AdditionalServiceService {

	@Autowired
	AdditionalFishingLessonServiceRepository additionalFishingLessonServiceRepository;
	
	@Override
	public void deleteService(Long id) {
		
		additionalFishingLessonServiceRepository.deleteById(id);
		
	}

}
