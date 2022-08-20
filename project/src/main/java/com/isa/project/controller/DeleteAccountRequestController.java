package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.DeleteAccountRequestDTO;
import com.isa.project.model.DeleteAccountRequest;
import com.isa.project.service.DeleteAccountRequestService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/deactivation")
public class DeleteAccountRequestController {
	
	@Autowired
	private DeleteAccountRequestService deleteAccountRequestService;

	@PostMapping(path = "/addRequest")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> addRequest(@RequestBody DeleteAccountRequestDTO deleteAccountRequestDTO){
        DeleteAccountRequest deleteRequest = deleteAccountRequestService.addRequest(deleteAccountRequestDTO);
        if(deleteRequest == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }       
        return new ResponseEntity<>(deleteRequest, HttpStatus.OK);
    }
	
	@GetMapping(path = "/requests")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(deleteAccountRequestService.getAll(), HttpStatus.OK);
    }

}
