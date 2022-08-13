package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.project.dto.UserDTO;
import com.isa.project.model.Authority;
import com.isa.project.model.BoatOwner;
import com.isa.project.model.Client;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.Instructor;
import com.isa.project.model.User;
import com.isa.project.repository.UserRepository;
import com.isa.project.security.SecurityUtils;
import com.isa.project.dto.AccountActivationDTO;
import com.isa.project.service.AuthorityService;
import com.isa.project.service.EmailService;
import com.isa.project.service.UserService;

@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AuthorityService authorityService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private EmailService emailService;
	
	@Override
	public User registerClient(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());

	        if(user != null) {
	            return null;
	        }

	        Client client = new Client();
	        List<Authority> auth = authorityService.findByName("ROLE_CLIENT");
	        client.setAuthorities(auth);
	        client.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        client.setUsername(userDTO.getUsername());
	        client.setName(userDTO.getName());
	        client.setSurname(userDTO.getSurname());
	        client.setPhoneNumber(userDTO.getPhoneNumber());
	        client.setAddress(userDTO.getAddress());
	        client.setCity(userDTO.getCity());
	        client.setCountry(userDTO.getCountry());
	        client.setStatus("Not Activated");
	        try {
				emailService.sendEmail(client);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return userRepository.save(client);
	}
	
	@Override
	public User registerBoatOwner(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());

	        if(user != null) {
	            return null;
	        }

	        BoatOwner boatOwner = new BoatOwner();
	        List<Authority> auth = authorityService.findByName("ROLE_BOATOWNER");
	        boatOwner.setAuthorities(auth);
	        boatOwner.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        boatOwner.setUsername(userDTO.getUsername());
	        boatOwner.setName(userDTO.getName());
	        boatOwner.setSurname(userDTO.getSurname());
	        boatOwner.setPhoneNumber(userDTO.getPhoneNumber());
	        boatOwner.setAddress(userDTO.getAddress());
	        boatOwner.setCity(userDTO.getCity());
	        boatOwner.setCountry(userDTO.getCountry());
	        boatOwner.setStatus("Not Activated");
	        boatOwner.setExplanation(userDTO.getExplanation());
	        return userRepository.save(boatOwner);
	}
	
	@Override
	public User registerCottageOwner(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());

	        if(user != null) {
	            return null;
	        }

	        CottageOwner cottageOwner = new CottageOwner();
	        List<Authority> auth = authorityService.findByName("ROLE_COTTAGEOWNER");
	        cottageOwner.setAuthorities(auth);
	        cottageOwner.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        cottageOwner.setUsername(userDTO.getUsername());
	        cottageOwner.setName(userDTO.getName());
	        cottageOwner.setSurname(userDTO.getSurname());
	        cottageOwner.setPhoneNumber(userDTO.getPhoneNumber());
	        cottageOwner.setAddress(userDTO.getAddress());
	        cottageOwner.setCity(userDTO.getCity());
	        cottageOwner.setCountry(userDTO.getCountry());
	        cottageOwner.setStatus("Not Activated");
	        cottageOwner.setExplanation(userDTO.getExplanation());
	        return userRepository.save(cottageOwner);
	}
	
	@Override
	public User registerInstructor(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());

	        if(user != null) {
	            return null;
	        }

	        Instructor instructor = new Instructor();
	        List<Authority> auth = authorityService.findByName("ROLE_INSTRUCTOR");
	        instructor.setAuthorities(auth);
	        instructor.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        instructor.setUsername(userDTO.getUsername());
	        instructor.setName(userDTO.getName());
	        instructor.setSurname(userDTO.getSurname());
	        instructor.setPhoneNumber(userDTO.getPhoneNumber());
	        instructor.setAddress(userDTO.getAddress());
	        instructor.setCity(userDTO.getCity());
	        instructor.setCountry(userDTO.getCountry());
	        instructor.setStatus("Not Activated");
	        instructor.setExplanation(userDTO.getExplanation());
	        return userRepository.save(instructor);
	        
	}
	
	@Override
	public User currentUser() {
        String username = SecurityUtils.getCurrentUserLogin().get();
        return userRepository.findByUsername(username);
    }

	@Override
	public List<User> getNotActivatedUsers() {
		List<User> response = new ArrayList<>();
		
		List<User> notActivated = userRepository.findByStatus("Not Activated");
		
		for(User user : notActivated) {
			if(user.getUserType().equals("ROLE_BOATOWNER") || user.getUserType().equals("ROLE_COTTAGEOWNER") 
					|| user.getUserType().equals("ROLE_INSTRUCTOR")) {
				response.add(user);	
			}
		}
		
		return response;
	}


	@Override
	public User findUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return null;
		}
	    user.setStatus("Activated");
	    userRepository.save(user);
	    return user;
	}

	@Override
	public User accept(AccountActivationDTO accountActivationDTO) {
		User user = userRepository.findByUsername(accountActivationDTO.getUsername());
		user.setStatus("Activated");
		try {
			emailService.acceptEmail(user);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRepository.save(user);
	}

	@Override
	public User decline(AccountActivationDTO accountActivationDTO) {
		User user = userRepository.findByUsername(accountActivationDTO.getUsername());	
		user.setStatus("Declined");
		try {
			emailService.declineEmail(user, accountActivationDTO.getReason());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRepository.save(user);
	}

	@Override
	public User editClient(UserDTO userDTO) {
		User user = userRepository.findByUsername(userDTO.getUsername());
		if(userDTO.getPassword() != "") {
			user.setPassword(userDTO.getPassword());
		}
		if(userDTO.getName() != "") {
			user.setName(userDTO.getName());
		}
		if(userDTO.getSurname() != "") {
			user.setSurname(userDTO.getSurname());
		}
		if(userDTO.getAddress() != "") {
			user.setAddress(userDTO.getAddress());
		}
		if(userDTO.getCity() != "") {
			user.setCity(userDTO.getCity());
		}
		if(userDTO.getCountry() != "") {
			user.setCountry(userDTO.getCountry());
		}
		if(userDTO.getPhoneNumber() != "") {
			user.setPhoneNumber(userDTO.getPhoneNumber());
		}
		
		return userRepository.save(user);
	}
	
	
	
	

}