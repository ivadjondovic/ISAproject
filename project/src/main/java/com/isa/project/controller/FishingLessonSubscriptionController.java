package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.SubscriptionDTO;
import com.isa.project.model.FishingLessonSubscription;
import com.isa.project.service.FishingLessonSubscriptionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/fishingLessonSubscription")
public class FishingLessonSubscriptionController {
	
	@Autowired
	private FishingLessonSubscriptionService subscriptionService;
	
	@PostMapping(path = "/subscribe")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> subscribe(@RequestBody SubscriptionDTO dto){
        FishingLessonSubscription subscription = subscriptionService.subscribe(dto);
        if(subscription == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

}
