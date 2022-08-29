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

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.service.QuickFishingLessonReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/quickFishingLessonReservations")
public class QuickFishingLessonReservationController {
	
	@Autowired
	private QuickFishingLessonReservationService reservationService;
	
	@PostMapping(path = "/reserve")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> reserve(@RequestBody QuickClientReservationDTO dto){
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

}
