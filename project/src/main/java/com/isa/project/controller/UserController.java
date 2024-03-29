package com.isa.project.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.isa.project.dto.AccountActivationDTO;
import com.isa.project.dto.ChangePasswordDTO;
import com.isa.project.dto.DeleteAccountRequestDTO;
import com.isa.project.dto.UserDTO;
import com.isa.project.dto.UserTokenState;
import com.isa.project.model.User;
import com.isa.project.repository.UserRepository;
import com.isa.project.security.JwtAuthenticationRequest;
import com.isa.project.security.TokenUtils;
import com.isa.project.service.UserService;
import com.isa.project.service.implementation.CustomUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@PostMapping(consumes = "application/json", path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {

		
		if(userDTO.getType().equals("Client")) {
	        User user = userService.registerClient(userDTO);
	
	        if(user == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	
	        return new ResponseEntity<>(HttpStatus.CREATED);
        
		} else if(userDTO.getType().equals("Boat owner")) {
			User user = userService.registerBoatOwner(userDTO);
			
	        if(user == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	
	        return new ResponseEntity<>(HttpStatus.CREATED);
	        
		} else if(userDTO.getType().equals("Cottage owner")) {
			User user = userService.registerCottageOwner(userDTO);
			
	        if(user == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	
	        return new ResponseEntity<>(HttpStatus.CREATED);
	        
		} else if(userDTO.getType().equals("Instructor")) {
			User user = userService.registerInstructor(userDTO);
			
	        if(user == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	
	        return new ResponseEntity<>(HttpStatus.CREATED);
		} else if(userDTO.getType().equals("Admin")) {
			User user = userService.registerAdmin(userDTO);
			
	        if(user == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	
	        return new ResponseEntity<>(HttpStatus.CREATED);
	      
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		// 
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		if(!user.getStatus().equals("Activated") || user.getStatus().equals("Declined") || user.getDeleted().equals("true")) {
			System.out.println("Ne");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String jwt = tokenUtils.generateToken(user.getUsername(), userRepository.findTypeById(user.getId()));
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}
	
	
	@GetMapping(path = "/current")
	@PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'BOATOWNER', 'COTTAGEOWNER', 'INSTRUCTOR')")
    public ResponseEntity<?> currentUser() {
		User user = userService.currentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@GetMapping(path = "/notActivatedUsers") 
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> notActivatedUsers() {
		List<User> users = userService.getNotActivatedUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
	
	@GetMapping(path = "/activate/{username}")
    public RedirectView activateClient(@PathVariable String username){
        userService.findUserByUsername(username);
        return new RedirectView("http://localhost:4200/activatedAccount");
    }
	
	@PutMapping(path = "/acceptActivation")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> acceptActivation(@RequestBody AccountActivationDTO accountActivationDTO){
        User user = userService.accept(accountActivationDTO);
        if(user == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@PutMapping(path = "/declineActivation")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> declineActivation(@RequestBody AccountActivationDTO accountActivationDTO){
        User user = userService.decline(accountActivationDTO);
        if(user == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@PutMapping(path = "/editClient")
	@PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> editClient(@RequestBody UserDTO userDTO) throws Exception{
        User user = userService.editClient(userDTO);
        if(user == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@PutMapping(path = "/changePassword")
	@PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'BOATOWNER', 'COTTAGEOWNER', 'INSTRUCTOR')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO passwordDTO){  
        userDetailsService.changePassword(passwordDTO.getOldPassword(), passwordDTO.getNewPassword()); 
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PutMapping(path = "/edit")
	@PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<?> edit(@RequestBody UserDTO userDTO){
        User user = userService.edit(userDTO);
        if(user == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@PutMapping(path = "/declineDeactivation")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> declineDeactivation(@RequestBody DeleteAccountRequestDTO deleteAccountRequestDTO) throws Exception{
        User user = userService.declineDeletingAccount(deleteAccountRequestDTO);
        if(user == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@PutMapping(path = "/acceptDeactivation")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> acceptDeactivation(@RequestBody DeleteAccountRequestDTO deleteAccountRequestDTO) throws Exception{
        User user = userService.acceptDeletingAccount(deleteAccountRequestDTO);
        if(user == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@GetMapping(path = "/getClient/{id}")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public ResponseEntity<?> getClient(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getClient(id), HttpStatus.OK);
    } 
	
	@GetMapping(path = "/getClients")
	@PreAuthorize("hasRole('INSTRUCTOR')")
	public ResponseEntity<?> getClients() {
        return new ResponseEntity<>(userService.getClients(), HttpStatus.OK);
    } 
	
	@GetMapping(path = "/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    } 
	
	@GetMapping(path = "/deleteUser/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
		User user = userService.deleteUser(id);
		
		if(user == null) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
}
