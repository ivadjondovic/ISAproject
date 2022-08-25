package com.isa.project.dto;

import java.time.LocalDateTime;

public class QuickReservationDTO {

	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double price;
	private int maxNumberOfPerson;
	private String additionalServices;
	private String location; 
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getMaxNumberOfPerson() {
		return maxNumberOfPerson;
	}
	public void setMaxNumberOfPerson(int maxNumberOfPerson) {
		this.maxNumberOfPerson = maxNumberOfPerson;
	}
	public String getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(String additionalServices) {
		this.additionalServices = additionalServices;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
