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

import com.isa.project.dto.AvailablePeriodDTO;
import com.isa.project.model.AvailableFishingLessonPeriod;
import com.isa.project.service.AvailablePeriodService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/availablePeriods")
public class AvailablePeriodController {

	@Autowired
	private AvailablePeriodService periodService;
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deletePeriod(@PathVariable Long id) {
		periodService.deletePeriod(id);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PutMapping(path = "/save")
	public ResponseEntity<?> savePeriod(@RequestBody AvailablePeriodDTO reservationDTO){
        AvailableFishingLessonPeriod reservation = periodService.savePeriod(reservationDTO);
        if(reservation == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
	
	@PostMapping(path = "/add")
	@PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> createService(@RequestBody AvailablePeriodDTO availablePeriodDTO){
        AvailableFishingLessonPeriod period = periodService.createPeriod(availablePeriodDTO);
        if(period == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(period, HttpStatus.OK);
    }
}
