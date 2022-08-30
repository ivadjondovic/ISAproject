package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Client;
import com.isa.project.model.QuickCottageReservation;

@Repository
public interface QuickCottageReservationRepository extends JpaRepository<QuickCottageReservation, Long> {

	public List<QuickCottageReservation> findByClientAndAcceptedAndCanceled(Client client, Boolean accepted, Boolean canceled);
}
