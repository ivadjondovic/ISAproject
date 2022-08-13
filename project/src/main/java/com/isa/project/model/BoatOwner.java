package com.isa.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BoatOwner")
public class BoatOwner extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String userType = "ROLE_BOATOWNER";
	
	@Override
    public String getUserType() {
        return userType;
    }

}
