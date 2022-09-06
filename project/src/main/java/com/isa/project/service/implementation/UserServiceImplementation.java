package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.isa.project.dto.UserDTO;
import com.isa.project.model.Admin;
import com.isa.project.model.Authority;
import com.isa.project.model.BoatOwner;
import com.isa.project.model.Category;
import com.isa.project.model.Client;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.DeleteAccountRequest;
import com.isa.project.model.Instructor;
import com.isa.project.model.LoyaltyProgram;
import com.isa.project.model.User;
import com.isa.project.repository.CategoryRepository;
import com.isa.project.repository.DeleteAccountRequestRepository;
import com.isa.project.repository.LoyaltyProgramRepository;
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
	
	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
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
	        client.setDeleted("false");
	        client.setPoints(0);
	        client.setPenalties(0);
	        
	        List<LoyaltyProgram> programs = loyaltyProgramRepository.findAll();
	        
	        if(!programs.isEmpty()) {
	        	Category category = categoryRepository.findByCategory("regular");
	        	client.setCategory(category);
	        }
	        
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

		 
		 if(userDTO.getAddress().equals("") || userDTO.getCity().equals("") || userDTO.getCountry().equals("")
					|| userDTO.getName().equals("") || userDTO.getPassword().equals("") || userDTO.getPhoneNumber().equals("") 
					|| userDTO.getSurname().equals("") || userDTO.getUsername().equals("") || userDTO.getExplanation().equals("")) {
				return null;
			}
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
	        boatOwner.setDeleted("false");
	        boatOwner.setPoints(0);
	        boatOwner.setRating(0.0);
	        return userRepository.save(boatOwner);
	}
	
	@Override
	public User registerCottageOwner(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());
		 
		 if(userDTO.getAddress().equals("") || userDTO.getCity().equals("") || userDTO.getCountry().equals("")
					|| userDTO.getName().equals("") || userDTO.getPassword().equals("") || userDTO.getPhoneNumber().equals("") 
					|| userDTO.getSurname().equals("") || userDTO.getUsername().equals("") || userDTO.getExplanation().equals("")) {
				return null;
			}
		 
		 
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
	        cottageOwner.setDeleted("false");
	        cottageOwner.setPoints(0);
	        cottageOwner.setRating(0.0);
	        return userRepository.save(cottageOwner);
	}
	
	@Override
	public User registerInstructor(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());

		 
		 if(userDTO.getAddress().equals("") || userDTO.getCity().equals("") || userDTO.getCountry().equals("")
					|| userDTO.getName().equals("") || userDTO.getPassword().equals("") || userDTO.getPhoneNumber().equals("") 
					|| userDTO.getSurname().equals("") || userDTO.getUsername().equals("") || userDTO.getExplanation().equals("")) {
				return null;
			}
		 
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
	        instructor.setDeleted("false");
	        instructor.setPoints(0);
	        instructor.setRating(0.0);
	        return userRepository.save(instructor);
	        
	}
	
	@Override
	public User registerAdmin(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());

		 if(userDTO.getAddress().equals("") || userDTO.getCity().equals("") || userDTO.getCountry().equals("")
					|| userDTO.getName().equals("") || userDTO.getPhoneNumber().equals("") 
					|| userDTO.getSurname().equals("") || userDTO.getUsername().equals("")) {
				return null;
			}
		 
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
	        admin.setDeleted("false");
	        admin.setIncome(0.0);
	        admin.setIncomePercentage(0.0);
	        admin.setPoints(0);
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
		
		if(accountActivationDTO.getReason().equals("")) {
			return  null;
		}
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public User declineDeletingAccount(DeleteAccountRequestDTO dto) throws Exception {
		
		if(dto.getReason().equals("")) {
			return null;
		}
		User user = userRepository.findLockById(dto.getUserId());
		if(!user.getDeleted().equals("waiting")) {
			System.out.println("Ne");
			return null;
		}
		System.out.println("DObro");
		user.setDeleted("false");	
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
		
		try{
			return userRepository.save(user);
		}catch(PessimisticLockingFailureException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Try again later!");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public User acceptDeletingAccount(DeleteAccountRequestDTO dto) throws Exception {
		
		if(dto.getReason().equals("")) {
			return null;
		}
	
		User user = userRepository.findLockById(dto.getUserId());
		if(!user.getDeleted().equals("waiting")) {
			return null;
		}
		user.setDeleted("true");
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
		
		try{
			return userRepository.save(user);
		}catch(PessimisticLockingFailureException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Try again later!");
		}
	}

	@Override
	public User getClient(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> getClients() {
		List<User> users = userRepository.findByStatusAndDeleted("Activated", "false");
		users.addAll(userRepository.findByStatusAndDeleted("Activated", "waiting"));

		List<User> clients = users.stream().filter(u -> u.getUserType().equals("ROLE_CLIENT")).collect(Collectors.toList());
		
		return clients;
	} 

	@Override
	public List<User> getUsers() {
		
		List<User> users = userRepository.findByStatusAndDeleted("Activated", "false");
		users.addAll(userRepository.findByStatusAndDeleted("Activated", "waiting"));
		
		List<User> result = users.stream().filter(u -> u.getUserType().equals("ROLE_CLIENT") ||
				u.getUserType().equals("ROLE_COTTAGEOWNER") || u.getUserType().equals("ROLE_BOATOWNER") 
				|| u.getUserType().equals("ROLE_INSTRUCTOR")).collect(Collectors.toList());
		
		return result;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public User deleteUser(Long id) throws Exception {
		User user = userRepository.findLockById(id);
		if(user.getDeleted().equals("true")) {
			return null;
		}
		user.setDeleted("true");
		
		try{
			return userRepository.save(user);
		}catch(PessimisticLockingFailureException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Try again later!");
		}
		
	}

	@Scheduled(cron = " 0 0 0 1 * ?")
	//@Scheduled(cron = "* 0/5 * * * *")
	public void deletePenatlties() {
		System.out.println("Radim");
		List<User> users = userRepository.findByStatusAndDeleted("Activated", "false");
		users.addAll(userRepository.findByStatusAndDeleted("Activated", "waiting"));
		
		List<User> clients = users.stream().filter(u -> u.getUserType().equals("ROLE_CLIENT")).collect(Collectors.toList());
		
		for(User u: clients) {
			Client client = (Client) userRepository.findById(u.getId()).get();
			client.setPenalties(0);
			userRepository.save(client);
			
		}
		
	}
	
	

}