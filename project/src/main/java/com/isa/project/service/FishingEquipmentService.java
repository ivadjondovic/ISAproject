package com.isa.project.service;

import com.isa.project.dto.FishingEquipmentDTO;
import com.isa.project.model.FishingEquipment;

public interface FishingEquipmentService {

	public void deleteEquipment(Long id);
	public FishingEquipment saveEquipment(FishingEquipmentDTO dto);
	public FishingEquipment createEquipment(FishingEquipmentDTO dto);
	
}
