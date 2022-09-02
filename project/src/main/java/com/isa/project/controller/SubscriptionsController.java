package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
