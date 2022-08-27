package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.CottageReservation;

@Repository
public interface CottageReservationRepository extends JpaRepository<CottageReservation, Long> {

}
