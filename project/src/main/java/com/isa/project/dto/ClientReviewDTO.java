package com.isa.project.dto;

public class ClientReviewDTO {

	private Long id;
	private Long clientId;
	private Long instructorId;
	private Boolean penaltySuggestion;
	private String penaltySuggestionReason;
	private Boolean automaticPenalty;
	private String reservationType;
	
	
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
	
	
}
