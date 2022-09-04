package com.isa.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ClientReview {

	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
	private Long id;
	private Boolean penaltySuggestion;
	private String penaltySuggestionReason;
	private Boolean automaticPenalty;
	private Boolean adminChecked;
	
	@JsonBackReference
	@ManyToOne
	private Client client;
	
	@JsonBackReference
	@ManyToOne
	private Instructor instructor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getPenaltySuggestion() {
		return penaltySuggestion;
	}

	public void setPenaltySuggestion(Boolean penaltySuggestion) {
		this.penaltySuggestion = penaltySuggestion;
	}

	public String getPenaltySuggestionReason() {
		return penaltySuggestionReason;
	}

	public void setPenaltySuggestionReason(String penaltySuggestionReason) {
		this.penaltySuggestionReason = penaltySuggestionReason;
	}

	public Boolean getAutomaticPenalty() {
		return automaticPenalty;
	}

	public void setAutomaticPenalty(Boolean automaticPenal) {
		this.automaticPenalty = automaticPenal;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Boolean getAdminChecked() {
		return adminChecked;
	}

	public void setAdminChecked(Boolean adminChecked) {
		this.adminChecked = adminChecked;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	
	
}
