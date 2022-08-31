package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.RevisionResponseDTO;
import com.isa.project.dto.RevisionStatusDTO;

public interface RevisionsService {
	
	public List<RevisionResponseDTO> getWaitingRevisions();
	public RevisionResponseDTO approve(RevisionStatusDTO dto);
	public RevisionResponseDTO disapprove(RevisionStatusDTO dto);

}
