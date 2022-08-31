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

import com.isa.project.dto.ComplaintAnswerDTO;
import com.isa.project.dto.ComplaintResponseDTO;
import com.isa.project.service.ComplaintsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/complaints")
public class ComplaintsController {
	
	@Autowired
	private ComplaintsService complaintsService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/notAnswered")
    public ResponseEntity<?> getNotAnsweredComplaints() {
        return new ResponseEntity<>(complaintsService.notAnsweredComplaints(), HttpStatus.OK);
    }
	
	@PutMapping(path = "/answer")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> answer(@RequestBody ComplaintAnswerDTO dto){
        ComplaintResponseDTO complaint = complaintsService.answer(dto);
        if(complaint == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(complaint, HttpStatus.OK);
    }

}
