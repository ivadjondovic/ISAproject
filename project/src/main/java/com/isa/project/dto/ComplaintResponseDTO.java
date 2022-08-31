package com.isa.project.dto;

import java.time.LocalDateTime;

public class ComplaintResponseDTO {
	
	private Long id;
	private String description;
	private LocalDateTime date;
	private String complaintType;
	private String entityName;
	private Long clientId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String revisionType) {
		this.complaintType = revisionType;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	
	
	

}
