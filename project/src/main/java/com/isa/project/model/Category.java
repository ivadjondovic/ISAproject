package com.isa.project.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Category {
	
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
	private Long id;
	private String category;
	private int pointsNeeded;
	private Double discount;
	private Double bonus;

	@JsonBackReference
	@ManyToOne
	private LoyaltyProgram loyaltyProgram;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Client> clients;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<BoatOwner> boatOwners;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<CottageOwner> cottageOwners;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Instructor> instructors;
	
	public Set<Client> getClients() {
		return clients;
	}
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPointsNeeded() {
		return pointsNeeded;
	}
	public void setPointsNeeded(int pointsNeeded) {
		this.pointsNeeded = pointsNeeded;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Set<BoatOwner> getBoatOwners() {
		return boatOwners;
	}
	public void setBoatOwners(Set<BoatOwner> boatOwners) {
		this.boatOwners = boatOwners;
	}
	public Set<CottageOwner> getCottageOwners() {
		return cottageOwners;
	}
	public void setCottageOwners(Set<CottageOwner> cottageOwners) {
		this.cottageOwners = cottageOwners;
	}
	public Set<Instructor> getInstructors() {
		return instructors;
	}
	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}
	public LoyaltyProgram getLoyaltyProgram() {
		return loyaltyProgram;
	}
	public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
	}
	
	

	
	
}
