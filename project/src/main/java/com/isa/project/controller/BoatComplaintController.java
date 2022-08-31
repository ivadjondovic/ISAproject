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

import com.isa.project.dto.ComplaintDTO;
import com.isa.project.model.BoatComplaint;
import com.isa.project.service.BoatComplaintService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boatComplaint")
public class BoatComplaintController {
	
	@Autowired
	private BoatComplaintService complaintService;
	
	@PostMapping(path = "/create")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> addComplaint(@RequestBody ComplaintDTO dto){
        BoatComplaint complaint = complaintService.addComplaint(dto);
        if(complaint == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(complaint, HttpStatus.OK);
    }

}
