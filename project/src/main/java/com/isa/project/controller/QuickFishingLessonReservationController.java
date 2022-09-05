package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.service.QuickFishingLessonReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/quickFishingLessonReservations")
public class QuickFishingLessonReservationController {
	
	@Autowired
	private QuickFishingLessonReservationService reservationService;
	
	@PostMapping(path = "/reserve")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> reserve(@RequestBody QuickClientReservationDTO dto) throws Exception{
        Client client = reservationService.clientReservation(dto);
        if(client == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
	
	@GetMapping(path = "/accept/{id}")
    public RedirectView acceptReservation(@PathVariable Long id){
        reservationService.accept(id);
        return new RedirectView("http://localhost:4200");
    }
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
		reservationService.deleteReservation(id);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PutMapping(path = "/save")
	public ResponseEntity<?> saveReservation(@RequestBody QuickReservationDTO reservationDTO){
        QuickFishingLessonReservation reservation = reservationService.saveReservation(reservationDTO);
        if(reservation == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
	
	@PostMapping(path = "/add")
	@PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> createResercation(@RequestBody QuickReservationDTO reservationDTO){
        QuickFishingLessonReservation reservation = reservationService.createReservation(reservationDTO);
        if(reservation == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

}
