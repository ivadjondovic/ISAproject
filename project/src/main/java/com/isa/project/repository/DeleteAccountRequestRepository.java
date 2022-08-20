package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.DeleteAccountRequest;

@Repository
public interface DeleteAccountRequestRepository extends JpaRepository<DeleteAccountRequest, Long> {
	
	public List<DeleteAccountRequest> findByUserId(Long userId);
	public List<DeleteAccountRequest> findByProcessed(Boolean status);

}
