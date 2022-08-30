package com.isa.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Cottage {

	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	private String name;
	private String address;
	private String description;
	private int numberOfRooms;
	private Double price;
	private Double rating;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY)
	private Set<Room> rooms;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY)
	private Set<CottageRevision> revisions;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "rules_cottage",
			joinColumns = @JoinColumn(name = "cottage_id"),
			inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rule> rules;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY)
	private Set<AdditionalCottageService> additionalServices;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER)
	private Set<AvailableCottagePeriod> availablePeriods;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER)
	private Set<QuickCottageReservation> quickReservations;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
            name = "cottage_images",
            joinColumns = @JoinColumn(name = "cottage_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;
	
	@JsonBackReference
	@ManyToOne
	private CottageOwner cottageOwner;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cottage", fetch = FetchType.EAGER)
	private Set<CottageReservation> reservations;
	

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

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

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

	public Set<AdditionalCottageService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalCottageService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public Set<AvailableCottagePeriod> getAvailablePeriods() {
		return availablePeriods;
	}

	public void setAvailablePeriods(Set<AvailableCottagePeriod> availablePeriods) {
		this.availablePeriods = availablePeriods;
	}

	public Set<QuickCottageReservation> getQuickReservations() {
		return quickReservations;
	}

	public void setQuickReservations(Set<QuickCottageReservation> quickReservations) {
		this.quickReservations = quickReservations;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public CottageOwner getCottageOwner() {
		return cottageOwner;
	}

	public void setCottageOwner(CottageOwner cottageOwner) {
		this.cottageOwner = cottageOwner;
	}

	public Set<CottageReservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<CottageReservation> reservations) {
		this.reservations = reservations;
	}

	public Set<CottageRevision> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<CottageRevision> revisions) {
		this.revisions = revisions;
	}
	
	
	
	
	
	
	
}
