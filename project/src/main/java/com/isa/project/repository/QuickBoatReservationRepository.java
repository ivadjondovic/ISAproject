package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Client;
import com.isa.project.model.QuickBoatReservation;

@Repository
public interface QuickBoatReservationRepository extends JpaRepository<QuickBoatReservation, Long>  {

	public List<QuickBoatReservation> findByClientAndAcceptedAndCanceled(Client client, Boolean accepted, Boolean canceled);
	public List<QuickBoatReservation> findByAcceptedAndCanceledAndCalculated(Boolean accepted, Boolean canceled, Boolean calculated);
	public List<QuickBoatReservation> findByClient(Client client);
}
