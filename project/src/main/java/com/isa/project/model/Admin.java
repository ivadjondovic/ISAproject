package com.isa.project.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String userType = "ROLE_ADMIN";
	private boolean firstPasswordChanged;
	private Double income;
	private Double incomePercentage;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
	private Set<AdminIncome> adminIncome;
	
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

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getIncomePercentage() {
		return incomePercentage;
	}

	public void setIncomePercentage(Double incomePercentage) {
		this.incomePercentage = incomePercentage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setUserType(String userType) {
		Admin.userType = userType;
	}

}
