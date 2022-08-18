package com.isa.project.dto;

import java.util.Set;

import com.isa.project.model.*;

public class CottageDTO {

	private String name;
	private String address;
	private String description;
	private int numberOfRooms;
	private Double price;
	private Set<RoomDTO> rooms;
	private Set<RuleDTO> rules;
	private Set<AdditionalCottageServiceDTO> additionalServices;
	private Set<AvailableCottagePeriodDTO> availablePeriods;
	private Set<QuickCottageReservationDTO> quickReservations;
	private Long cottageOwnerId;
	private Set<ImageDTO> images;
	
	
	
	public Long getCottageOwnerId() {
		return cottageOwnerId;
	}
	public void setCottageOwnerId(Long cottageOwnerId) {
		this.cottageOwnerId = cottageOwnerId;
	}
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
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Set<RoomDTO> getRooms() {
		return rooms;
	}
	public void setRooms(Set<RoomDTO> rooms) {
		this.rooms = rooms;
	}
	public Set<RuleDTO> getRules() {
		return rules;
	}
	public void setRules(Set<RuleDTO> rules) {
		this.rules = rules;
	}
	public Set<AdditionalCottageServiceDTO> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(Set<AdditionalCottageServiceDTO> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public Set<AvailableCottagePeriodDTO> getAvailablePeriods() {
		return availablePeriods;
	}
	public void setAvailablePeriods(Set<AvailableCottagePeriodDTO> availablePeriods) {
		this.availablePeriods = availablePeriods;
	}
	public Set<QuickCottageReservationDTO> getQuickReservations() {
		return quickReservations;
	}
	public void setQuickReservations(Set<QuickCottageReservationDTO> quickReservations) {
		this.quickReservations = quickReservations;
	}
	public Set<ImageDTO> getImages() {
		return images;
	}
	public void setImages(Set<ImageDTO> images) {
		this.images = images;
	}
	
	
	
	
	
	
	
	
}
