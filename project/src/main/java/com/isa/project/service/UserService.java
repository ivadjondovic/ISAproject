package com.isa.project.service;

import com.isa.project.dto.UserDTO;
import com.isa.project.model.User;

public interface UserService {

	public User registerClient(UserDTO userDTO);
	public User currentUser();
}
