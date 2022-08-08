package com.isa.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.UserDTO;
import com.isa.project.model.Client;
import com.isa.project.model.User;
import com.isa.project.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public User registerClient(UserDTO userDTO) {
		 Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());

	        if(!optionalUser.isEmpty()) {
	            return null;
	        }

	        Client client = new Client();
	        client.setEmail(userDTO.getEmail());
	        client.setPassword(userDTO.getPassword());
	        client.setName(userDTO.getName());
	        client.setSurname(userDTO.getSurname());
	        client.setPhoneNumber(userDTO.getPhoneNumber());
	        client.setAddress(userDTO.getAddress());
	        client.setCity(userDTO.getCity());
	        client.setCountry(userDTO.getCountry());

	        return userRepository.save(client);
	}

}
