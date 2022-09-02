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
import com.isa.project.dto.SubscriptionResponseDTO;
import com.isa.project.dto.UnsubscribeDTO;
import com.isa.project.service.SubscriptionsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/subscriptions")
public class SubscriptionsController {
	
	@Autowired
	private SubscriptionsService subscriptionsService;
	
	@PreAuthorize("hasRole('CLIENT')")
	@GetMapping(path = "/clientSubscriptions/{clientId}")
    public ResponseEntity<?> getClientSubscriptions(@PathVariable Long clientId) {
        return new ResponseEntity<>(subscriptionsService.getClientSubscriptions(clientId), HttpStatus.OK);
    }
	
	@PutMapping(path = "/unsubscribe")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> unsubscribe(@RequestBody UnsubscribeDTO dto){
        SubscriptionResponseDTO revision = subscriptionsService.unsubscribe(dto);
        if(revision == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(revision, HttpStatus.OK);
    }

}
