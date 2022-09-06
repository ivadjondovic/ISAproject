package com.isa.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class LoyaltyProgram {

	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
	
	private Long id;
	

	@Column(name = "clientPoints", nullable = false)
	private int clientPoints;
	

	@Column(name = "ownerPoints", nullable = false)
	private int ownerPoints;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "loyaltyProgram", fetch = FetchType.LAZY)
	private Set<Category> categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getClientPoints() {
		return clientPoints;
	}

	public void setClientPoints(int clientPoints) {
		this.clientPoints = clientPoints;
	}

	public int getOwnerPoints() {
		return ownerPoints;
	}

	public void setOwnerPoints(int ownerPoints) {
		this.ownerPoints = ownerPoints;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	
}
