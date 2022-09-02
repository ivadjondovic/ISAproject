package com.isa.project.dto;

public class SubscriptionResponseDTO {
	
	private Long id;
	private String type;
	private String entityName;
	private String entityOwner;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityOwner() {
		return entityOwner;
	}
	public void setEntityOwner(String entityOwner) {
		this.entityOwner = entityOwner;
	}
	
	

}
