package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}