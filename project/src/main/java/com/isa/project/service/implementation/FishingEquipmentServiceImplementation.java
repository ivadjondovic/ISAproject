package com.isa.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.FishingEquipmentDTO;
import com.isa.project.model.FishingEquipment;
import com.isa.project.model.FishingLesson;
import com.isa.project.repository.FishingEquipmentRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.service.FishingEquipmentService;

@Service
public class FishingEquipmentServiceImplementation implements FishingEquipmentService {

	@Autowired
	private FishingEquipmentRepository equipmentRepository;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Override
	public void deleteEquipment(Long id) {
		
		equipmentRepository.deleteById(id);
		
	}

	@Override
	public FishingEquipment saveEquipment(FishingEquipmentDTO dto) {
		
		FishingEquipment equipment = equipmentRepository.findById(dto.getId()).get();
		
		
		if(!dto.getDescription().equals("")) {
			equipment.setDescription(dto.getDescription());
		}
		
		return equipmentRepository.save(equipment);
	}

	@Override
	public FishingEquipment createEquipment(FishingEquipmentDTO dto) {
		
		if(dto.getDescription().equals("")) {
			return null;
		}
		
		FishingEquipment equipment = new FishingEquipment();
		
		equipment.setDescription(dto.getDescription());
		
		FishingLesson lesson = fishingLessonRepository.findById(dto.getId()).get();
		
		equipment.setFishingLesson(lesson);
		
		return equipmentRepository.save(equipment);
	}

}
