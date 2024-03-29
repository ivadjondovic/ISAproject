package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.AccountActivationDTO;
import com.isa.project.dto.DeleteAccountRequestDTO;
import com.isa.project.dto.UserDTO;
import com.isa.project.model.User;

public interface UserService {

	public User registerClient(UserDTO userDTO);
	public User registerBoatOwner(UserDTO userDTO);
	public User registerCottageOwner(UserDTO userDTO);
	public User registerInstructor(UserDTO userDTO);
	public User registerAdmin(UserDTO userDTO);
	public User currentUser();
	public List<User> getNotActivatedUsers();
	public User findUserByUsername(String username);
	public User accept(AccountActivationDTO accountActivationDTO);
	public User decline(AccountActivationDTO accountActivationDTO);
	public User editClient(UserDTO userDTO) throws Exception;
	public User edit(UserDTO userDTO);
	public User declineDeletingAccount(DeleteAccountRequestDTO dto) throws Exception;
	public User acceptDeletingAccount(DeleteAccountRequestDTO dto) throws Exception;
	public User getClient(Long id);
	public List<User> getClients();
	public List<User> getUsers();
	public User deleteUser(Long id) throws Exception;
	
}
