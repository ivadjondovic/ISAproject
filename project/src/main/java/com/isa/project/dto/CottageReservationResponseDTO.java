package com.isa.project.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.project.model.AdditionalCottageService;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;

public class CottageReservationResponseDTO {
	
	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double price;
	private Cottage cottage;
	private Boolean accepted;
	private Set<AdditionalCottageService> additionalServices;
	private Client client;
	private Boolean possibleToRate;
	private String reservationType;
	
	
	public Boolean getPossibleToRate() {
		return possibleToRate;
	}
	public void setPossibleToRate(Boolean possibleToRate) {
		this.possibleToRate = possibleToRate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Cottage getCottage() {
		return cottage;
	}
	public void setCottage(Cottage cottage) {
		this.cottage = cottage;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public Set<AdditionalCottageService> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<AdditionalCottageService> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	
	
	
	

}
