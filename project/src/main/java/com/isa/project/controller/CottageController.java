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

import com.isa.project.dto.CottageDTO;
import com.isa.project.dto.DateSearchDTO;
import com.isa.project.dto.ReservationSearchDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.Cottage;
import com.isa.project.service.CottageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/cottage")
public class CottageController {

	@Autowired
	private CottageService cottageService;
	
	@PostMapping(path = "/createCottage")
	@PreAuthorize("hasRole('COTTAGEOWNER')")
    public ResponseEntity<?> createCottage(@RequestBody CottageDTO cottageDTO){
        Cottage cottage = cottageService.createCottage(cottageDTO);
        if(cottage == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(cottage, HttpStatus.OK);
    }
	
	@GetMapping(path = "/cottages")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(cottageService.getAll(), HttpStatus.OK);
    }
	
	@GetMapping(path = "/cottage/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(cottageService.getById(id), HttpStatus.OK);
    }
	
	@GetMapping(path = "/search/{searchTerm}")
    public ResponseEntity<?> search(@PathVariable String searchTerm) {
        return new ResponseEntity<>(cottageService.search(searchTerm), HttpStatus.OK);
    }
	
	@PostMapping(path = "/sort")
    public ResponseEntity<?> sort(@RequestBody SortDTO dto) {
		List<Cottage> response = cottageService.sort(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('CLIENT')")
	@PostMapping(path = "/sortAvailable")
    public ResponseEntity<?> sortAvailable(@RequestBody ReservationSearchDTO dto) {
		
		List<Cottage> response = cottageService.sortAvailableCottages(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('CLIENT')")
	@PostMapping(path = "/availableCottages")
    public ResponseEntity<?> availableCottages(@RequestBody ReservationSearchDTO dto) {
		List<Cottage> response = cottageService.getAvailableCottages(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('CLIENT')")
	@PostMapping(path = "/availableCottagesForCertainDate")
    public ResponseEntity<?> availableCottagesForCertainDate(@RequestBody DateSearchDTO dto) {
		List<Cottage> response = cottageService.cottagesAvailableForCertainDate(dto);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping(path = "/subscribedCottages/{clientId}")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> subscribedCottages(@PathVariable Long clientId) {
        return new ResponseEntity<>(cottageService.getCottagesByClientSubscription(clientId), HttpStatus.OK);
    }
	
	
}
