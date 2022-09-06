package com.isa.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("CottageOwner")
public class CottageOwner extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String userType = "ROLE_COTTAGEOWNER";
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottageOwner", fetch = FetchType.LAZY)
	private Set<Cottage> cottages;
	
	@Column(name = "rating")
	private Double rating;
	
	@JsonBackReference
	@ManyToOne
	private Category category;
	
	@Override
    public String getUserType() {
        return userType;
    }

	public Set<Cottage> getCottages() {
		return cottages;
	}

	public void setCottages(Set<Cottage> cottages) {
		this.cottages = cottages;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	
	
	
	
	
	
	
	

}
