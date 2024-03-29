package com.isa.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "boatType", nullable = false)
	private String boatType;
	
	@Column(name = "boatLength", nullable = false)
	private Double boatLength;
	
	@Column(name = "engines", nullable = false)
	private int engines;
	
	@Column(name = "enginePower", nullable = false)
	private Double enginePower;
	
	@Column(name = "maxSpeed", nullable = false)
	private Double maxSpeed;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "capacity", nullable = false)
	private int capacity;
	
	@Column(name = "percentageForKeep", nullable = false)
	private Double percentageForKeep;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "rating", nullable = false)
	private Double rating;
	
	@Column(name = "deleted", nullable = false)
	private Boolean deleted;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
	private Set<BoatSubscription> subscriptions;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
	private Set<NavigationEquipment> navigationEquipment;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
	private Set<BoatRevision> revisions;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "boat", fetch = FetchType.LAZY)
	private Set<BoatComplaint> complaints;
	
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


	public Set<BoatComplaint> getComplaints() {
		return complaints;
	}


	public void setComplaints(Set<BoatComplaint> complaints) {
		this.complaints = complaints;
	}


	public Set<BoatSubscription> getSubscriptions() {
		return subscriptions;
	}


	public void setSubscriptions(Set<BoatSubscription> subscriptions) {
		this.subscriptions = subscriptions;
	}


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
