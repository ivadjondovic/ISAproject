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

import com.isa.project.dto.LoyaltyProgramDTO;
import com.isa.project.model.LoyaltyProgram;
import com.isa.project.service.LoyaltyProgramService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/loyaltyProgram")
public class LoyaltyProgramController {
	
	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@PostMapping(path = "/create")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProgram(@RequestBody LoyaltyProgramDTO dto){
        LoyaltyProgram program = loyaltyProgramService.createProgram(dto);
        if(program == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(program, HttpStatus.OK);
    }

}
