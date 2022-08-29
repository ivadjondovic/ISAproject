package com.isa.project.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class QuickBoatReservation {

	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double price;
	private int maxNumberOfPerson;
	private String additionalServices;
	
	@JsonBackReference
	@ManyToOne
	private Boat boat;
	
	@JsonBackReference
	@ManyToOne
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

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
}
