package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.CancelReservationDTO;
import com.isa.project.dto.ReservationResponseDTO;
import com.isa.project.service.ReservationsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/reservations")
public class ReservationsController {
	
	@Autowired
	private ReservationsService reservationsService;
	
	@GetMapping(path = "/notPassed/{clientId}") 
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> notPassedReservations(@PathVariable Long clientId) {	
        return new ResponseEntity<>(reservationsService.getNotPassedReservations(clientId), HttpStatus.OK);
    }
	
	@PutMapping(path = "/cancel") 
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> cancel(@RequestBody CancelReservationDTO dto) {
		ReservationResponseDTO response = reservationsService.cancelReservation(dto);
		
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
