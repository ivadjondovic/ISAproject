package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.FishingLessonDTO;
import com.isa.project.dto.UserDTO;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.User;
import com.isa.project.service.FishingLessonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/fishing")
public class FishingLessonController {

	@Autowired
	private FishingLessonService fishingLessonService;
	
	@PostMapping(path = "/createFishingLesson")
	@PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> createFishingLesson(@RequestBody FishingLessonDTO fishingLessonDTO){
        FishingLesson fishingLesson = fishingLessonService.createFishingLesson(fishingLessonDTO);
        if(fishingLesson == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(fishingLesson, HttpStatus.OK);
    }
	
	@GetMapping(path = "/fishingLessons/{instructorId}")
	@PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> getByInstructorId(@PathVariable Long instructorId) {
        return new ResponseEntity<>(fishingLessonService.getByInstructorId(instructorId), HttpStatus.OK);
    }
	
	@PutMapping(path = "/editFishingLesson")
	@PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<?> editFishingLesson(@RequestBody FishingLessonDTO fishingLessonDTO){
        FishingLesson fishingLesson = fishingLessonService.editFishingLesson(fishingLessonDTO);
        if(fishingLesson == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(fishingLesson, HttpStatus.OK);
    }
	
}