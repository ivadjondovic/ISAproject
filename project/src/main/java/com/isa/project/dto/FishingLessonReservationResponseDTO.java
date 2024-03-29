package com.isa.project.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.isa.project.model.AdditionalFishingLessonService;
import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;

public class FishingLessonReservationResponseDTO {

	
	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double price;
	private FishingLesson fishingLesson;
	private Boolean accepted;
	private Set<AdditionalFishingLessonService> additionalServices;
	private Client client;
	private Boolean possibleToRate;
	private String reservationType;
	
	
	public Boolean getPossibleToRate() {
		return possibleToRate;
	}
	public void setPossibleToRate(Boolean possibleToRate) {
		this.possibleToRate = possibleToRate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public FishingLesson getFishingLesson() {
		return fishingLesson;
	}
	public void setFishingLesson(FishingLesson fishingLesson) {
		this.fishingLesson = fishingLesson;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public Set<AdditionalFishingLessonService> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<AdditionalFishingLessonService> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	
	
	
	
	
}
