package com.isa.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class AdditionalFishingLessonService {

	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	private String description;
	private Double price;
	
	@JsonBackReference
	@ManyToOne
	private FishingLesson fishingLesson;
	
	@JsonBackReference
	@ManyToOne
	private FishingLessonReservation fishingLessonReservation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public FishingLessonReservation getFishingLessonReservation() {
		return fishingLessonReservation;
	}

	public void setFishingLessonReservation(FishingLessonReservation fishingLessonReservation) {
		this.fishingLessonReservation = fishingLessonReservation;
	}
	
	

	
}
