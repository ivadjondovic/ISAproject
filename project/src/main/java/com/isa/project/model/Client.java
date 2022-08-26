package com.isa.project.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("Client")
public class Client extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String userType = "ROLE_CLIENT";
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<CottageReservation> reservations;
	
	@Override
    public String getUserType() {
        return userType;
    }

	public Set<CottageReservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<CottageReservation> reservations) {
		this.reservations = reservations;
	}
	
	
}
