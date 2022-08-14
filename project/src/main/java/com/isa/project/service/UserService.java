package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.AccountActivationDTO;
import com.isa.project.dto.UserDTO;
import com.isa.project.model.User;

public interface UserService {

	public User registerClient(UserDTO userDTO);
	public User registerBoatOwner(UserDTO userDTO);
	public User registerCottageOwner(UserDTO userDTO);
	public User registerInstructor(UserDTO userDTO);
	public User currentUser();
	public List<User> getNotActivatedUsers();
	public User findUserByUsername(String username);
	public User accept(AccountActivationDTO accountActivationDTO);
	public User decline(AccountActivationDTO accountActivationDTO);
	public User editClient(UserDTO userDTO);
	
}
