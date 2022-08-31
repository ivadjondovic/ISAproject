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

import com.isa.project.dto.RevisionDTO;
import com.isa.project.model.CottageRevision;
import com.isa.project.service.CottageRevisionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/cottageRevision")
public class CottageRevisionController {
	
	@Autowired
	private CottageRevisionService revisionService;
	
	@PostMapping(path = "/create")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> addRevision(@RequestBody RevisionDTO dto){
        CottageRevision revision = revisionService.addRevision(dto);
        if(revision == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(revision, HttpStatus.OK);
    }
	
	
	

}
