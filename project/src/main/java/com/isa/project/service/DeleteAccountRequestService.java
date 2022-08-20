package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.DeleteAccountRequestDTO;
import com.isa.project.model.DeleteAccountRequest;

public interface DeleteAccountRequestService {
	
	public DeleteAccountRequest addRequest(DeleteAccountRequestDTO dto);
	public List<DeleteAccountRequest> getAll();

}
