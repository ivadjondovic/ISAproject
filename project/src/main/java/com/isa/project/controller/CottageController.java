package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.CottageDTO;
import com.isa.project.model.Cottage;
import com.isa.project.service.CottageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/cottage")
public class CottageController {

	@Autowired
	private CottageService cottageService;
	
	@PostMapping(path = "/createCottage")
	@PreAuthorize("hasRole('COTTAGEOWNER')")
    public ResponseEntity<?> createCottage(@RequestBody CottageDTO cottageDTO){
        Cottage cottage = cottageService.createCottage(cottageDTO);
        if(cottage == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(cottage, HttpStatus.OK);
    }
	
}
