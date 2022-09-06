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

import com.isa.project.dto.IncomeBetweenDTO;
import com.isa.project.service.IncomeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/income")
public class IncomeController {

	@Autowired
	private IncomeService incomeService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/getReservationIncome/{adminId}")
    public ResponseEntity<?> getReservationIncome(@PathVariable Long adminId) {
        return new ResponseEntity<>(incomeService.getReservationIncome(adminId), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/getReservationIncomeBetween")
    public ResponseEntity<?> getReservationIncomeBetween(@RequestBody IncomeBetweenDTO dto) {
        return new ResponseEntity<>(incomeService.getReservationIncomeBetween(dto), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@GetMapping(path = "/getInstructorIncome/{instructorId}")
    public ResponseEntity<?> getInstructorIncome(@PathVariable Long instructorId) {
        return new ResponseEntity<>(incomeService.getReservationIncome(instructorId), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('INSTRUCTOR')")
	@PostMapping(path = "/getInstructorIncomeBetween")
    public ResponseEntity<?> getInstructorIncomeBetween(@RequestBody IncomeBetweenDTO dto) {
        return new ResponseEntity<>(incomeService.getInstructorIncomeBetween(dto), HttpStatus.OK);
    }
}
