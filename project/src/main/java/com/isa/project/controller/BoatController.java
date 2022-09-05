package com.isa.project.controller;

import java.util.List;


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

import com.isa.project.dto.BoatDTO;
import com.isa.project.dto.DateSearchDTO;
import com.isa.project.dto.ReservationSearchDTO;
import com.isa.project.dto.SearchParamsDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.Boat;
import com.isa.project.service.BoatService;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/boat")
public class BoatController {
	
	@Autowired
	private BoatService boatService;
	
	@PostMapping(path = "/createBoat")
	@PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<?> createBoat(@RequestBody BoatDTO boatDTO){
        Boat boat = boatService.createBoat(boatDTO);
        if(boat == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(boat, HttpStatus.OK);
    }
	
	@GetMapping(path = "/boats")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(boatService.getAll(), HttpStatus.OK);
    }
	
	@GetMapping(path = "/boat/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(boatService.getById(id), HttpStatus.OK);
    }
	
	@GetMapping(path = "/search/{searchTerm}")
    public ResponseEntity<?> search(@PathVariable String searchTerm) {
        return new ResponseEntity<>(boatService.search(searchTerm), HttpStatus.OK);
    }
	
	@PostMapping(path = "/sort")
    public ResponseEntity<?> sort(@RequestBody SortDTO dto) {
		List<Boat> response = boatService.sort(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
       
    }
	
	@PreAuthorize("hasRole('CLIENT')")
	@PostMapping(path = "/sortAvailable")
    public ResponseEntity<?> sortAvailable(@RequestBody ReservationSearchDTO dto) {
		List<Boat> response = boatService.sortAvailableBoats(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }
	
	@PostMapping(path = "/searchByManyParams")
    public ResponseEntity<?> searchByManyParams(@RequestBody SearchParamsDTO dto) {
		List<Boat> response = boatService.searchByMoreParams(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }
	
	@PreAuthorize("hasRole('CLIENT')")
	@PostMapping(path = "/availableBoats")
    public ResponseEntity<?> availableBoats(@RequestBody ReservationSearchDTO dto) {
		List<Boat> response = boatService.getAvailableBoats(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('CLIENT')")
	@PostMapping(path = "/availableBoatsForCertainDate")
    public ResponseEntity<?> availableBoatsForCertainDate(@RequestBody DateSearchDTO dto) {
		List<Boat> response = boatService.boatsAvailableForCertainDate(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping(path = "/subscribedBoats/{clientId}")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> subscribedBoats(@PathVariable Long clientId) {
        return new ResponseEntity<>(boatService.getBoatsByClientSubscription(clientId), HttpStatus.OK);
    }
	
	@GetMapping(path = "/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		boatService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK); 
    }

}
