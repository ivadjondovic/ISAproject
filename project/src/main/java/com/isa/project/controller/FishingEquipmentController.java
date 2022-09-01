package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.FishingEquipmentDTO;
import com.isa.project.model.FishingEquipment;
import com.isa.project.service.FishingEquipmentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/fishingEquipment")
public class FishingEquipmentController {

	
	@Autowired
	private FishingEquipmentService fishingEquipmentService;
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteEquipment(@PathVariable Long id) {
		fishingEquipmentService.deleteEquipment(id);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PutMapping(path = "/save")
	public ResponseEntity<?> saveEquipment(@RequestBody FishingEquipmentDTO equipmentDTO){
        FishingEquipment equipment = fishingEquipmentService.saveEquipment(equipmentDTO);
        if(equipment == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }
	
	@PostMapping(path = "/add")
	@PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> createEquipment(@RequestBody FishingEquipmentDTO equipmentDTO){
        FishingEquipment equipment = fishingEquipmentService.createEquipment(equipmentDTO);
        if(equipment == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }
}
