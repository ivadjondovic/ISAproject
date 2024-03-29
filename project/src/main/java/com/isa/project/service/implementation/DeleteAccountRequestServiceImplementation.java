package com.isa.project.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.DeleteAccountRequestDTO;
import com.isa.project.model.DeleteAccountRequest;
import com.isa.project.model.User;
import com.isa.project.repository.DeleteAccountRequestRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.DeleteAccountRequestService;

@Service
public class DeleteAccountRequestServiceImplementation implements DeleteAccountRequestService{

	@Autowired
	private DeleteAccountRequestRepository deleteAccountRequestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public DeleteAccountRequest addRequest(DeleteAccountRequestDTO dto) {
		
		if(dto.getReason().equals("")) {
			return null;
		}
		
		DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest();
		deleteAccountRequest.setReason(dto.getReason());
		User user = userRepository.findById(dto.getUserId()).get();
		user.setDeleted("waiting");
		userRepository.save(user);
		deleteAccountRequest.setUser(user);	
		deleteAccountRequest.setProcessed(false);
		return deleteAccountRequestRepository.save(deleteAccountRequest);
	}

	@Override
	public List<DeleteAccountRequest> getAll() {
		
		return deleteAccountRequestRepository.findByProcessed(false);
	}

}
