package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Boat;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;

@Repository
public interface BoatReservationRepository  extends JpaRepository<BoatReservation, Long> {

	public List<BoatReservation> findByClientAndAcceptedAndCanceled(Client client, Boolean accepted, Boolean canceled);
	public List<BoatReservation> findByAcceptedAndCanceledAndCalculated(Boolean accepted, Boolean canceled, Boolean calculated);
	public List<BoatReservation> findByClient(Client client);
	public List<BoatReservation> findByBoatAndCanceled(Boat boat, Boolean canceled);
	
}
