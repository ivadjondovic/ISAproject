package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.BoatReservation;

@Repository
public interface BoatReservationRepository  extends JpaRepository<BoatReservation, Long> {

}
