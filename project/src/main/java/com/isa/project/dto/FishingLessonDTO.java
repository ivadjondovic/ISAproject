package com.isa.project.dto;

import java.util.Set;

public class FishingLessonDTO {

	private Long id;
	private String name;
	private String address;
	private String description;
	private int numberOfPeople;
	private String fishingInstructorBio;
	private Double price;
	private Double percentageForKeep;
	private Set<FishingEquipmentDTO> equipment;
	private Set<RuleDTO> rules;
	private Set<AdditionalServiceDTO> additionalServices;
	private Set<AvailablePeriodDTO> availablePeriods;
	private Set<QuickReservationDTO> quickReservations;
	private Long instructorId;
	private Set<ImageDTO> images;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Set<FishingEquipmentDTO> getEquipment() {
		return equipment;
	}
	public void setEquipment(Set<FishingEquipmentDTO> equipment) {
		this.equipment = equipment;
	}
	public Set<RuleDTO> getRules() {
		return rules;
	}
	public void setRules(Set<RuleDTO> rules) {
		this.rules = rules;
	}
	public Set<AdditionalServiceDTO> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<AdditionalServiceDTO> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public Set<AvailablePeriodDTO> getAvailablePeriods() {
		return availablePeriods;
	}
	public void setAvailablePeriods(Set<AvailablePeriodDTO> availablePeriods) {
		this.availablePeriods = availablePeriods;
	}
	public Set<QuickReservationDTO> getQuickReservations() {
		return quickReservations;
	}
	public void setQuickReservations(Set<QuickReservationDTO> quickReservations) {
		this.quickReservations = quickReservations;
	}
	public Long getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}
	public Set<ImageDTO> getImages() {
		return images;
	}
	public void setImages(Set<ImageDTO> images) {
		this.images = images;
	}
	public String getFishingInstructorBio() {
		return fishingInstructorBio;
	}
	public void setFishingInstructorBio(String fishingInstructorBio) {
		this.fishingInstructorBio = fishingInstructorBio;
	}
	public Double getPercentageForKeep() {
		return percentageForKeep;
	}
	public void setPercentageForKeep(Double percentageForKeep) {
		this.percentageForKeep = percentageForKeep;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
