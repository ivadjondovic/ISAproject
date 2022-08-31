package com.isa.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Boat {
	
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	private String name;
	private String boatType;
	private Double boatLength;
	private int engines;
	private Double enginePower;
	private Double maxSpeed;
	private String address;
	private String description;
	private int capacity;
	private Double percentageForKeep;
	private Double price;
	private Double rating;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
	private Set<NavigationEquipment> navigationEquipment;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
	private Set<BoatRevision> revisions;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.EAGER)
	private Set<QuickBoatReservation> quickReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
	private Set<BoatFishingEquipment> fishingEquipment;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
	private Set<AdditionalBoatService> additionalServices;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.EAGER)
	private Set<AvailableBoatPeriod> availablePeriods;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
            name = "boat_images",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "boat_rules",
			joinColumns = @JoinColumn(name = "boat_id"),
			inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rule> rules;
	

	@JsonBackReference
	@ManyToOne
	private BoatOwner boatOwner;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.EAGER)
	private Set<BoatReservation> reservations;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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
	


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public void setPercentageForKeep(Double percentageForKeep) {
		this.percentageForKeep = percentageForKeep;
	}


	public Set<NavigationEquipment> getNavigationEquipment() {
		return navigationEquipment;
	}


	public void setNavigationEquipment(Set<NavigationEquipment> navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}


	public Set<QuickBoatReservation> getQuickReservations() {
		return quickReservations;
	}


	public void setQuickReservations(Set<QuickBoatReservation> quickReservations) {
		this.quickReservations = quickReservations;
	}


	public Set<BoatFishingEquipment> getFishingEquipment() {
		return fishingEquipment;
	}


	public void setFishingEquipment(Set<BoatFishingEquipment> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}


	public Set<AdditionalBoatService> getAdditionalServices() {
		return additionalServices;
	}


	public void setAdditionalServices(Set<AdditionalBoatService> additionalServices) {
		this.additionalServices = additionalServices;
	}


	public Set<AvailableBoatPeriod> getAvailablePeriods() {
		return availablePeriods;
	}


	public void setAvailablePeriods(Set<AvailableBoatPeriod> availablePeriods) {
		this.availablePeriods = availablePeriods;
	}


	public Set<Image> getImages() {
		return images;
	}


	public void setImages(Set<Image> images) {
		this.images = images;
	}


	public Set<Rule> getRules() {
		return rules;
	}


	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}


	public BoatOwner getBoatOwner() {
		return boatOwner;
	}


	public void setBoatOwner(BoatOwner boatOwner) {
		this.boatOwner = boatOwner;
	}


	public Set<BoatReservation> getReservations() {
		return reservations;
	}


	public void setReservations(Set<BoatReservation> reservations) {
		this.reservations = reservations;
	}


	public Double getRating() {
		return rating;
	}


	public void setRating(Double rating) {
		this.rating = rating;
	}


	public Set<BoatRevision> getRevisions() {
		return revisions;
	}


	public void setRevisions(Set<BoatRevision> revisions) {
		this.revisions = revisions;
	}
	
	
	
	
	
	
	
	

}
