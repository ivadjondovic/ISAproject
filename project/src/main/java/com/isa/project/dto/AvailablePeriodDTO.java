package com.isa.project.dto;

import java.time.LocalDateTime;

public class AvailablePeriodDTO {
	
	private Long id;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
