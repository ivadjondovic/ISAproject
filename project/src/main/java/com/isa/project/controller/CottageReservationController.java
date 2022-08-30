package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.isa.project.dto.ReservationDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.CottageReservation;
import com.isa.project.service.CottageReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/cottageReservations")
public class CottageReservationController {

	@Autowired
	private CottageReservationService reservationService;
	
	@PostMapping(path = "/createReservation")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO){
        CottageReservation reservation = reservationService.createReservation(reservationDTO);
        if(reservation == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
	
	@GetMapping(path = "/accept/{id}")
    public RedirectView acceptReservation(@PathVariable Long id){
        reservationService.accept(id);
        return new RedirectView("http://localhost:4200");
    }
	
	@GetMapping(path = "/clientReservations/{clientId}")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> clientReservations(@PathVariable Long clientId){
        return new ResponseEntity<>(reservationService.getByClientId(clientId), HttpStatus.OK);
    }
	
	@PostMapping(path = "/sort")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> sort(@RequestBody SortDTO dto) {
        return new ResponseEntity<>(reservationService.sort(dto), HttpStatus.OK);
    }
}
