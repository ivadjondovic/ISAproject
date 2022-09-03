package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.project.dto.UserDTO;
import com.isa.project.model.Admin;
import com.isa.project.model.Authority;
import com.isa.project.model.BoatOwner;
import com.isa.project.model.Client;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.DeleteAccountRequest;
import com.isa.project.model.Instructor;
import com.isa.project.model.User;
import com.isa.project.repository.DeleteAccountRequestRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.security.SecurityUtils;
import com.isa.project.dto.AccountActivationDTO;
import com.isa.project.dto.DeleteAccountRequestDTO;
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
	
	@Autowired
    private DeleteAccountRequestRepository deleteAccountRequestRepository;
	
	@Override
	public User registerClient(UserDTO userDTO) {
		
		if(userDTO.getAddress().equals("") || userDTO.getCity().equals("") || userDTO.getCountry().equals("")
				|| userDTO.getName().equals("") || userDTO.getPassword().equals("") || userDTO.getPhoneNumber().equals("") 
				|| userDTO.getSurname().equals("") || userDTO.getUsername().equals("")) {
			return null;
		}
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
	        client.setDeleted(false);
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
	        boatOwner.setDeleted(false);
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
	        cottageOwner.setDeleted(false);
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
	        instructor.setDeleted(false);
	        return userRepository.save(instructor);
	        
	}
	
	@Override
	public User registerAdmin(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());

	        if(user != null) {
	            return null;
	        }

	        Admin admin = new Admin();
	        List<Authority> auth = authorityService.findByName("ROLE_ADMIN");
	        admin.setAuthorities(auth);
	        admin.setPassword(passwordEncoder.encode("admin"));
	        admin.setUsername(userDTO.getUsername());
	        admin.setName(userDTO.getName());
	        admin.setSurname(userDTO.getSurname());
	        admin.setPhoneNumber(userDTO.getPhoneNumber());
	        admin.setAddress(userDTO.getAddress());
	        admin.setCity(userDTO.getCity());
	        admin.setCountry(userDTO.getCountry());
	        admin.setStatus("Activated");
	        admin.setFirstPasswordChanged(false);
	        admin.setDeleted(false);
	        return userRepository.save(admin);
	        
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
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
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
	
	
	@Override
	public User edit(UserDTO userDTO) {
		User user = userRepository.findByUsername(userDTO.getUsername());
		if(userDTO.getPassword() != "") {
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
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

	@Override
	public User declineDeletingAccount(DeleteAccountRequestDTO dto) {
		
		if(dto.getReason().equals("")) {
			return null;
		}
		User user = userRepository.findById(dto.getUserId()).get();
		user.setDeleted(false);	
		try {
			emailService.declineDeletingAccountEmail(user, dto.getReason());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DeleteAccountRequest> requests = deleteAccountRequestRepository.findByUserId(dto.getUserId());
		for(DeleteAccountRequest deleteRequest: requests) {
			deleteRequest.setProcessed(true);
			deleteAccountRequestRepository.save(deleteRequest);
		}
		return userRepository.save(user);
	}

	@Override
	public User acceptDeletingAccount(DeleteAccountRequestDTO dto) {
		
		if(dto.getReason().equals("")) {
			return null;
		}
		User user = userRepository.findById(dto.getUserId()).get();
		user.setDeleted(true);
		try {
			emailService.acceptDeletingAccountEmail(user, dto.getReason());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DeleteAccountRequest> requests = deleteAccountRequestRepository.findByUserId(dto.getUserId());
		for(DeleteAccountRequest deleteRequest: requests) {
			deleteRequest.setProcessed(true);
			deleteAccountRequestRepository.save(deleteRequest);
		}
		return userRepository.save(user);
	}

	@Override
	public User getClient(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> getClients() {
		List<User> users = userRepository.findByStatusAndDeleted("Activated", false);
		for(User u : users) {
			System.out.println("user: " + u.getName());
		}
		List<User> clients = users.stream().filter(u -> u.getUserType().equals("ROLE_CLIENT")).collect(Collectors.toList());
		for(User u : clients) {
			System.out.println("client: " + u.getName());
		}
		return clients;
	}
	
	
	
	

}