package com.isa.project.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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
	
	
	
	
	
	

}
