package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.model.Image;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.service.ImageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/images")
public class ImageController {
	
	@Autowired
	private ImageService imageService;

	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping(path = "/delete/{imageId}/{lessonId}")
	public ResponseEntity<?> deleteImage(@PathVariable Long imageId, @PathVariable Long lessonId) {
		imageService.deleteImage(imageId, lessonId);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/add")
	@PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> createImage(@RequestBody ImageDTO imageDTO){
		Image image = imageService.createImage(imageDTO);
        if(image == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
