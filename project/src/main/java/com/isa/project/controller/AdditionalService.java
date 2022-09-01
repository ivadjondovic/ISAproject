package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.service.AdditionalServiceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/additionalServices")
public class AdditionalService {

	@Autowired
	private AdditionalServiceService additionalService;
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteService(@PathVariable Long id) {
		additionalService.deleteService(id);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
