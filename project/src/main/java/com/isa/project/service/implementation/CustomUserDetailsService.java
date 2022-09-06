package com.isa.project.service.implementation;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.project.model.Admin;
import com.isa.project.model.Authority;
import com.isa.project.model.User;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.AuthorityService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private AuthorityService authorityService;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user;
		}
	}

	// Funkcija pomocu koje korisnik menja svoju lozinku
	public void changePassword(String oldPassword, String newPassword) {

		// Ocitavamo trenutno ulogovanog korisnika
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();

		if (authenticationManager != null) {
			LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			LOGGER.debug("No authentication manager set. can't change Password!");

			return;
		}

		LOGGER.debug("Changing password for user '" + username + "'");

		User user = (User) loadUserByUsername(username);
		
		if(user.getUserType().equals("ROLE_ADMIN")) {
			Admin admin = new Admin();
			Admin notChanged = (Admin) loadUserByUsername(username);
	        List<Authority> auth = authorityService.findByName("ROLE_ADMIN");
	        admin.setId(user.getId());
	        admin.setAuthorities(auth);
	        admin.setPassword(passwordEncoder.encode(newPassword));
	        admin.setUsername(user.getUsername());
	        admin.setName(user.getName());
	        admin.setSurname(user.getSurname());
	        admin.setPhoneNumber(user.getPhoneNumber());
	        admin.setAddress(user.getAddress());
	        admin.setCity(user.getCity());
	        admin.setCountry(user.getCountry());
	        admin.setStatus("Activated");
	        admin.setDeleted("false");
	        admin.setIncome(notChanged.getIncome());
	        admin.setIncomePercentage(notChanged.getIncomePercentage());
	        admin.setPoints(0);
	        admin.setFirstPasswordChanged(true);
	 
	        userRepository.save(admin);
	        
	        
	        
		}

		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// ne zelimo da u bazi cuvamo lozinke u plain text formatu
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

	}
}
