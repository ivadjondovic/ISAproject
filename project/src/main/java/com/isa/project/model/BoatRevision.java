package com.isa.project.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BoatRevision {
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "date", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "boatRate", nullable = false)
	private int boatRate;
	
	@Column(name = "ownerRate", nullable = false)
	private int ownerRate;
	
	@Column(name = "status", nullable = false)
	private String status;
	
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

	public int getBoatRate() {
		return boatRate;
	}

	public void setBoatRate(int cottageRate) {
		this.boatRate = cottageRate;
	}

	public int getOwnerRate() {
		return ownerRate;
	}

	public void setOwnerRate(int ownerRate) {
		this.ownerRate = ownerRate;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
