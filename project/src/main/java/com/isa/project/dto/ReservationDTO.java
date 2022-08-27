package com.isa.project.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDTO {
	
	private LocalDateTime startDate;
	private int numberOfDays;
	private Set<Long> additionalServices;
	private Long entityId;
	private Long clientId;
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public Set<Long> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<Long> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long cottageId) {
		this.entityId = cottageId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	

}
