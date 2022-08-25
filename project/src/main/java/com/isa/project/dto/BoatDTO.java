package com.isa.project.dto;

import java.util.Set;


public class BoatDTO {
	
	private String name;
	private String boatType;
	private Double boatLength;
	private int engines;
	private Double enginePower;
	private Double maxSpeed;
	private String address;
	private String description;
	private int capacity;
	private Double price;
	private Double percentageForKeep;
	private Long boatOwnerId;
	private Set<NavigationEquipmentDTO> navigationEquipment;
	private Set<QuickReservationDTO> quickReservations;
	private Set<FishingEquipmentDTO> fishingEquipment;
	private Set<AdditionalServiceDTO> additionalServices;
	private Set<AvailablePeriodDTO> availablePeriods;
	private Set<RuleDTO> rules;
	private Set<ImageDTO> images;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBoatType() {
		return boatType;
	}
	public void setBoatType(String boatType) {
		this.boatType = boatType;
	}
	public Double getBoatLength() {
		return boatLength;
	}
	public void setBoatLength(Double boatLength) {
		this.boatLength = boatLength;
	}
	public int getEngines() {
		return engines;
	}
	public void setEngines(int engines) {
		this.engines = engines;
	}
	public Double getEnginePower() {
		return enginePower;
	}
	public void setEnginePower(Double enginePower) {
		this.enginePower = enginePower;
	}
	public Double getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(Double maxSpeed) {
		this.maxSpeed = maxSpeed;
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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Double getPercentageForKeep() {
		return percentageForKeep;
	}
	public void setPercentageForKeep(Double percentageForKeep) {
		this.percentageForKeep = percentageForKeep;
	}
	public Long getBoatOwnerId() {
		return boatOwnerId;
	}
	public void setBoatOwnerId(Long boatOwnerId) {
		this.boatOwnerId = boatOwnerId;
	}
	public Set<NavigationEquipmentDTO> getNavigationEquipment() {
		return navigationEquipment;
	}
	public void setNavigationEquipment(Set<NavigationEquipmentDTO> navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}
	public Set<QuickReservationDTO> getQuickReservations() {
		return quickReservations;
	}
	public void setQuickReservations(Set<QuickReservationDTO> quickReservations) {
		this.quickReservations = quickReservations;
	}
	public Set<FishingEquipmentDTO> getFishingEquipment() {
		return fishingEquipment;
	}
	public void setFishingEquipment(Set<FishingEquipmentDTO> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
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
	public Set<RuleDTO> getRules() {
		return rules;
	}
	public void setRules(Set<RuleDTO> rules) {
		this.rules = rules;
	}
	public Set<ImageDTO> getImages() {
		return images;
	}
	public void setImages(Set<ImageDTO> images) {
		this.images = images;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	

}
