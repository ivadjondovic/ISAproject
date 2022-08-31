package com.isa.project.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("BoatOwner")
public class BoatOwner extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String userType = "ROLE_BOATOWNER";
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY)
	private Set<Boat> boats;
	private Double rating;
	
	
	
	public Set<Boat> getBoats() {
		return boats;
	}



	public void setBoats(Set<Boat> boats) {
		this.boats = boats;
	}



	public Double getRating() {
		return rating;
	}



	public void setRating(Double rating) {
		this.rating = rating;
	}



	@Override
    public String getUserType() {
        return userType;
    }

}
