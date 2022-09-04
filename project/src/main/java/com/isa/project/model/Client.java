package com.isa.project.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("Client")
public class Client extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String userType = "ROLE_CLIENT";
	private int penalties;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<CottageReservation> cottageReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<BoatReservation> boatReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<FishingLessonReservation> fishingLessonReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<QuickCottageReservation> quickCottageReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<QuickBoatReservation> quickBoatReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private Set<QuickFishingLessonReservation> quickFishingLessonReservations;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Set<CottageSubscription> cottageSubscriptions;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Set<BoatSubscription> boatSubscriptions;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Set<FishingLessonSubscription> fishingLessonSubscriptions;
	
	@Override
    public String getUserType() {
        return userType;
    }

	public Set<CottageReservation> getCottageReservations() {
		return cottageReservations;
	}

	public void setCottageReservations(Set<CottageReservation> reservations) {
		this.cottageReservations = reservations;
	}

	public Set<BoatReservation> getBoatReservations() {
		return boatReservations;
	}

	public void setBoatReservations(Set<BoatReservation> boatReservations) {
		this.boatReservations = boatReservations;
	}

	public Set<FishingLessonReservation> getFishingLessonReservations() {
		return fishingLessonReservations;
	}

	public void setFishingLessonReservations(Set<FishingLessonReservation> fishingLessonReservations) {
		this.fishingLessonReservations = fishingLessonReservations;
	}

	public Set<QuickCottageReservation> getQuickCottageReservations() {
		return quickCottageReservations;
	}

	public void setQuickCottageReservations(Set<QuickCottageReservation> quickCottageReservations) {
		this.quickCottageReservations = quickCottageReservations;
	}

	public Set<QuickBoatReservation> getQuickBoatReservations() {
		return quickBoatReservations;
	}

	public void setQuickBoatReservations(Set<QuickBoatReservation> quickBoatReservations) {
		this.quickBoatReservations = quickBoatReservations;
	}

	public Set<QuickFishingLessonReservation> getQuickFishingLessonReservations() {
		return quickFishingLessonReservations;
	}

	public void setQuickFishingLessonReservations(Set<QuickFishingLessonReservation> quickFishingLessonReservations) {
		this.quickFishingLessonReservations = quickFishingLessonReservations;
	}

	public Set<CottageSubscription> getCottageSubscriptions() {
		return cottageSubscriptions;
	}

	public void setCottageSubscriptions(Set<CottageSubscription> subscriptions) {
		this.cottageSubscriptions = subscriptions;
	}

	public Set<BoatSubscription> getBoatSubscriptions() {
		return boatSubscriptions;
	}

	public void setBoatSubscriptions(Set<BoatSubscription> boatSubscriptions) {
		this.boatSubscriptions = boatSubscriptions;
	}

	public Set<FishingLessonSubscription> getFishingLessonSubscriptions() {
		return fishingLessonSubscriptions;
	}

	public void setFishingLessonSubscriptions(Set<FishingLessonSubscription> fishingLessonSubscriptions) {
		this.fishingLessonSubscriptions = fishingLessonSubscriptions;
	}

	public int getPenalties() {
		return penalties;
	}

	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}
	
	
}
