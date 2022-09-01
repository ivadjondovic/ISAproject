package com.isa.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.repository.FishingEquipmentRepository;
import com.isa.project.service.FishingEquipmentService;

@Service
public class FishingEquipmentServiceImplementation implements FishingEquipmentService {

	@Autowired
	private FishingEquipmentRepository equipmentRepository;
	
	@Override
	public void deleteEquipment(Long id) {
		
		equipmentRepository.deleteById(id);
		
	}

}
