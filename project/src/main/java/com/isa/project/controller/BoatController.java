package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.BoatDTO;
import com.isa.project.model.Boat;
import com.isa.project.service.BoatService;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boat")
public class BoatController {
	
	@Autowired
	private BoatService boatService;
	
	@PostMapping(path = "/createBoat")
	@PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<?> createBoat(@RequestBody BoatDTO boatDTO){
        Boat boat = boatService.createBoat(boatDTO);
        if(boat == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(boat, HttpStatus.OK);
    }

}
