package com.isa.project.dto;

import java.time.LocalDateTime;

public class IncomeBetweenDTO {

	private Long adminId;
	private LocalDateTime dateFrom;
	private LocalDateTime dateTo;
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public LocalDateTime getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}
	public LocalDateTime getDateTo() {
		return dateTo;
	}
	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}
	
	
	
}
