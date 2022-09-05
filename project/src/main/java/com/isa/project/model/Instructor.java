package com.isa.project.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("Instructor")
public class Instructor extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String userType = "ROLE_INSTRUCTOR";
	
	@JsonManagedReference
	@OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
	private Set<FishingLesson> lessons;
	
	private Double rating;
	
	@JsonBackReference
	@ManyToOne
	private Category category;
	
	
	




	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Set<FishingLesson> getLessons() {
		return lessons;
	}



	public void setLessons(Set<FishingLesson> lessons) {
		this.lessons = lessons;
	}



	public Double getRating() {
		return rating;
	}



	public void setRating(Double rating) {
		this.rating = rating;
	}



	@Override
    public String getUserType() {
        return userType;
    }

}
