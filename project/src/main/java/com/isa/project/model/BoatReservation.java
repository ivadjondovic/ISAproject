package com.isa.project.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BoatReservation {
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	
	@Column(name = "startDate", nullable = false)
	private LocalDateTime startDate;
	
	@Column(name = "endDate", nullable = false)
	private LocalDateTime endDate;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "accepted", nullable = false)
	private Boolean accepted;
	
	@Column(name = "canceled", nullable = false)
	private Boolean canceled;
	
	@Column(name = "calculated", nullable = false)
	private Boolean calculated;
	
	
	@ManyToMany
    @JoinTable(
            name = "boat_reservation_services",
            joinColumns = @JoinColumn(name = "boat_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_service_id"))
	private Set<AdditionalBoatService> additionalServices;
	
	
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

	public Boolean getCalculated() {
		return calculated;
	}

	public void setCalculated(Boolean calculated) {
		this.calculated = calculated;
	}

	public Set<AdditionalBoatService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalBoatService> additionalServices) {
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

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}
	
	
	
	
	
	
	

}
