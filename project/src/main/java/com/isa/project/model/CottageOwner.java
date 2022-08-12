package com.isa.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CottageOwner")
public class CottageOwner extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String userType = "ROLE_COTTAGEOWNER";
	
	@Override
    public String getUserType() {
        return userType;
    }

}
