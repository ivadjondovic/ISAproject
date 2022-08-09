package com.isa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.project.dto.UserDTO;
import com.isa.project.model.User;
import com.isa.project.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(consumes = "application/json", path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {

		if(userDTO.getType().equals("Klijent")) {
	        User user = userService.registerClient(userDTO);
	
	        if(user == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	
	        return new ResponseEntity<>(HttpStatus.CREATED);
        
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
