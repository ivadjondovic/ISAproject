package com.isa.project.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Client;
import com.isa.project.model.QuickCottageReservation;

@Repository
public interface QuickCottageReservationRepository extends JpaRepository<QuickCottageReservation, Long> {

	public List<QuickCottageReservation> findByClientAndAcceptedAndCanceled(Client client, Boolean accepted, Boolean canceled);
	public List<QuickCottageReservation> findByAcceptedAndCanceledAndCalculated(Boolean accepted, Boolean canceled, Boolean calculated);
	public List<QuickCottageReservation> findByClient(Client client);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select r from QuickCottageReservation r where r.id = :id")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="10000")})
	public QuickCottageReservation findLockById(@Param("id")Long id);
}
