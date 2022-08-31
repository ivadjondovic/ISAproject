package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.RevisionResponseDTO;
import com.isa.project.dto.RevisionStatusDTO;
import com.isa.project.service.RevisionsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/revisions")
public class RevisionsController {
	
	@Autowired
	private RevisionsService revisionsService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/waiting")
    public ResponseEntity<?> getWaitingRevisions() {
        return new ResponseEntity<>(revisionsService.getWaitingRevisions(), HttpStatus.OK);
    }
	
	@PutMapping(path = "/approve")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveRevision(@RequestBody RevisionStatusDTO dto){
        RevisionResponseDTO revision = revisionsService.approve(dto);
        if(revision == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(revision, HttpStatus.OK);
    }
	
	@PutMapping(path = "/disapprove")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> disapproveRevision(@RequestBody RevisionStatusDTO dto){
		RevisionResponseDTO revision = revisionsService.disapprove(dto);
        if(revision == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(revision, HttpStatus.OK);
    }

}
