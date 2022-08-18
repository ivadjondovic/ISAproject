package com.isa.project.dto;

import java.time.LocalDateTime;

public class AvailableCottagePeriodDTO {
	
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	

}
