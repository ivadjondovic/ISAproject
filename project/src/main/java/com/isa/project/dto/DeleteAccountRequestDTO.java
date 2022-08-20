package com.isa.project.dto;

public class DeleteAccountRequestDTO {

	private String reason;
	private Long userId;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
