package com.isa.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Instructor")
public class Instructor extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String userType = "ROLE_INSTRUCTOR";
	
	@Override
    public String getUserType() {
        return userType;
    }

}
