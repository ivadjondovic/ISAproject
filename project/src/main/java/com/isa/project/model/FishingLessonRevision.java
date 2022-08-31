package com.isa.project.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class FishingLessonRevision {
	
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
	private String description;
	private LocalDateTime date;
	private int lessonRate;
	private int instructorRate;
	private Boolean accepted;
	
	@JsonBackReference
	@ManyToOne
	private FishingLesson fishingLesson;
	
	@JsonBackReference
	@ManyToOne
	private Client client;

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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getLessonRate() {
		return lessonRate;
	}

	public void setLessonRate(int lessonRate) {
		this.lessonRate = lessonRate;
	}

	public int getInstructorRate() {
		return instructorRate;
	}

	public void setInstructorRate(int instructorRate) {
		this.instructorRate = instructorRate;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public FishingLesson getFishingLesson() {
		return fishingLesson;
	}

	public void setFishingLesson(FishingLesson fishingLesson) {
		this.fishingLesson = fishingLesson;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	

}
