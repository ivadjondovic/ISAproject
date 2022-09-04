package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.project.model.ClientReview;

public interface ClientReviewRepository extends JpaRepository<ClientReview, Long> {

	public List<ClientReview> findByAdminChecked(Boolean adminChecked);
	
}
