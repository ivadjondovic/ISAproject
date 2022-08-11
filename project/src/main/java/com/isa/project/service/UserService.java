package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.UserDTO;
import com.isa.project.model.User;

public interface UserService {

	public User registerClient(UserDTO userDTO);
	public User currentUser();
	List<User> getAll();
}
