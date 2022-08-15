package com.isa.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String userType = "ROLE_ADMIN";
	private boolean firstPasswordChanged;
	
	@Override
    public String getUserType() {
        return userType;
    }

	public boolean isFirstPasswordChanged() {
		return firstPasswordChanged;
	}

	public void setFirstPasswordChanged(boolean firstPasswordChanged) {
		this.firstPasswordChanged = firstPasswordChanged;
	}

}
