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
public class FishingLesson {

	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	private String name;
	private String address;
	private String description;
	private String fishingInstructorBio;
	private int numberOfPeople;
	private Double price;
	private Double percentageForKeep;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
            name = "fishing_images",
            joinColumns = @JoinColumn(name = "fishing_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name = "rules_fishing",
			joinColumns = @JoinColumn(name = "fishing_id"),
			inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<Rule> rules;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "fishingLesson", fetch = FetchType.EAGER)
	private Set<QuickFishingLessonReservation> quickReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "fishingLesson", fetch = FetchType.LAZY)
	private Set<AdditionalFishingLessonService> additionalServices;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "fishingLesson", fetch = FetchType.EAGER)
	private Set<AvailableFishingLessonPeriod> availablePeriods;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "fishingLesson", fetch = FetchType.LAZY)
	private Set<FishingEquipment> fishingEquipment;
	
	@JsonBackReference
	@ManyToOne
	private Instructor instructor;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "fishingLesson", fetch = FetchType.EAGER)
	private Set<FishingLessonReservation> reservations;

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

	public String getFishingInstructorBio() {
		return fishingInstructorBio;
	}

	public void setFishingInstructorBio(String fishingInstructorBio) {
		this.fishingInstructorBio = fishingInstructorBio;
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

	public Double getPercentageForKeep() {
		return percentageForKeep;
	}

	public void setPercentageForKeep(Double percentageForKeep) {
		this.percentageForKeep = percentageForKeep;
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

	public Set<QuickFishingLessonReservation> getQuickReservations() {
		return quickReservations;
	}

	public void setQuickReservations(Set<QuickFishingLessonReservation> quickReservations) {
		this.quickReservations = quickReservations;
	}

	public Set<AdditionalFishingLessonService> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(Set<AdditionalFishingLessonService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public Set<AvailableFishingLessonPeriod> getAvailablePeriods() {
		return availablePeriods;
	}

	public void setAvailablePeriods(Set<AvailableFishingLessonPeriod> availablePeriods) {
		this.availablePeriods = availablePeriods;
	}

	public Set<FishingEquipment> getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(Set<FishingEquipment> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	
	
}
