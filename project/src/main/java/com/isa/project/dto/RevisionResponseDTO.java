package com.isa.project.dto;

import java.time.LocalDateTime;

public class RevisionResponseDTO {
	
	private Long id;
	private String description;
	private LocalDateTime date;
	private int entityRate;
	private int ownerRate;
	private String revisionType;
	private String entityName;
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
	public int getEntityRate() {
		return entityRate;
	}
	public void setEntityRate(int emtityRate) {
		this.entityRate = emtityRate;
	}
	public int getOwnerRate() {
		return ownerRate;
	}
	public void setOwnerRate(int ownerRate) {
		this.ownerRate = ownerRate;
	}
	public String getRevisionType() {
		return revisionType;
	}
	public void setRevisionType(String revisionType) {
		this.revisionType = revisionType;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	
	
	

}
