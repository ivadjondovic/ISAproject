package com.isa.project.service.implementation;

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
import com.isa.project.model.Client;
import com.isa.project.model.User;
import com.isa.project.repository.UserRepository;
import com.isa.project.security.SecurityUtils;
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
	public User currentUser() {
        String username = SecurityUtils.getCurrentUserLogin().get();
        return userRepository.findByUsername(username);
    }

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
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
	
	

}