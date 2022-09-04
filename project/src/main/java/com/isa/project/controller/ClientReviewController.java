package com.isa.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.ClientReviewDTO;
import com.isa.project.dto.ClientReviewResponseDTO;
import com.isa.project.model.ClientReview;
import com.isa.project.service.ClientReviewService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/clientReview")
public class ClientReviewController {

	@Autowired
	private ClientReviewService reviewService;
	
	@PostMapping(path = "/createReview")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public ResponseEntity<?> createReview(@RequestBody ClientReviewDTO dto){
        ClientReview review = reviewService.createReview(dto);
        if(review == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
	
	@PutMapping(path = "/acceptPenalty")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> acceptPenalty(@RequestBody ClientReviewDTO clientReviewDTO){
        ClientReview review = reviewService.accept(clientReviewDTO);
        if(review == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
	
	@PutMapping(path = "/declinePenalty")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> declinePenalty(@RequestBody ClientReviewDTO clientReviewDTO){
		ClientReview review = reviewService.decline(clientReviewDTO);
        if(review == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
	
	@GetMapping(path = "/notCheckedPenalties") 
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> notCheckedPentalties() {
		List<ClientReviewResponseDTO> reviews = reviewService.getNotCheckedPenalties();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
	
}
