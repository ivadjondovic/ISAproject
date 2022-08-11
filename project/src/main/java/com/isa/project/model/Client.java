package com.isa.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Client")
public class Client extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String userType = "CLIENT";
	
	@Override
    public String getUserType() {
        return userType;
    }
}
