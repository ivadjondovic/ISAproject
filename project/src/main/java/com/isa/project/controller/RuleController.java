package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.service.RuleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/rules")
public class RuleController {

	@Autowired
	private RuleService ruleService;
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@DeleteMapping(path = "/delete/{ruleId}/{lessonId}")
	public ResponseEntity<?> deleteRule(@PathVariable Long ruleId, @PathVariable Long lessonId) {
		ruleService.deleteRule(ruleId, lessonId);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
