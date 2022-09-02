package com.isa.project.dto;


import java.time.LocalDateTime;

public class SearchParamsDTO {
	
	private LocalDateTime date;
	private String location;
	private int rating;
	private Double priceFrom;
	private Double priceTo;
	private int peopleFrom;
	private int peopleTo;
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Double getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(Double priceFrom) {
		this.priceFrom = priceFrom;
	}
	public Double getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(Double priceTo) {
		this.priceTo = priceTo;
	}
	public int getPeopleFrom() {
		return peopleFrom;
	}
	public void setPeopleFrom(int peopleFrom) {
		this.peopleFrom = peopleFrom;
	}
	public int getPeopleTo() {
		return peopleTo;
	}
	public void setPeopleTo(int peopleTo) {
		this.peopleTo = peopleTo;
	}
	
	

}
