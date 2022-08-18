package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.QuickBoatReservation;

@Repository
public interface QuickBoatReservationRepository extends JpaRepository<QuickBoatReservation, Long>  {

}
