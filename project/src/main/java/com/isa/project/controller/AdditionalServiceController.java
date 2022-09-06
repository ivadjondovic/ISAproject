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

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.model.AdditionalFishingLessonService;
import com.isa.project.service.AdditionalServiceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/additionalServices")
public class AdditionalServiceController {

	@Autowired
	private AdditionalServiceService additionalService;
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteService(@PathVariable Long id) {
		additionalService.deleteService(id);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PutMapping(path = "/save")
	public ResponseEntity<?> saveService(@RequestBody AdditionalServiceDTO serviceDTO){
        AdditionalFishingLessonService lessonService = additionalService.saveService(serviceDTO);
        if(lessonService == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(lessonService, HttpStatus.OK);
    }
	
	
	@PostMapping(path = "/add")
	@PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> createService(@RequestBody AdditionalServiceDTO additionalServiceDTO){
        AdditionalFishingLessonService service = additionalService.createService(additionalServiceDTO);
        if(service == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(service, HttpStatus.OK);
    }
}
