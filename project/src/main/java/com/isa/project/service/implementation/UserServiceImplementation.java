package com.isa.project.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.project.dto.UserDTO;
import com.isa.project.model.Authority;
import com.isa.project.model.Client;
import com.isa.project.model.User;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.AuthorityService;
import com.isa.project.service.UserService;

@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AuthorityService authorityService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public User registerClient(UserDTO userDTO) {
		 User user = userRepository.findByUsername(userDTO.getUsername());

	        if(user != null) {
	            return null;
	        }

	        Client client = new Client();
	        List<Authority> auth = authorityService.findByName(userDTO.getType());
	        client.setAuthorities(auth);
	        client.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        client.setUsername(userDTO.getUsername());
	        client.setName(userDTO.getName());
	        client.setSurname(userDTO.getSurname());
	        client.setPhoneNumber(userDTO.getPhoneNumber());
	        client.setAddress(userDTO.getAddress());
	        client.setCity(userDTO.getCity());
	        client.setCountry(userDTO.getCountry());

	        return userRepository.save(client);
	}

}