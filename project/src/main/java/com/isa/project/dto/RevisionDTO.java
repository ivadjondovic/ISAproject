package com.isa.project.dto;


public class RevisionDTO {

	private Long reservationId;
	private String description;
	private int entityRate;
	private int ownerRate;
	private String reservationType;
	private Long clientId;
	
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getEntityRate() {
		return entityRate;
	}
	public void setEntityRate(int entityRate) {
		this.entityRate = entityRate;
	}
	public int getOwnerRate() {
		return ownerRate;
	}
	public void setOwnerRate(int ownerRate) {
		this.ownerRate = ownerRate;
	}
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	
	
}
