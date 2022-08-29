package com.isa.project.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.project.model.AdditionalBoatService;
import com.isa.project.model.Boat;
import com.isa.project.model.Client;


public class BoatReservationResponseDTO {
	
	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double price;
	private Boat Boat;
	private Boolean accepted;
	private Set<AdditionalBoatService> additionalServices;
	private Client client;
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
	public Boat getBoat() {
		return Boat;
	}
	public void setBoat(Boat boat) {
		Boat = boat;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public Set<AdditionalBoatService> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<AdditionalBoatService> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

}
