package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.QuickCottageReservation;

@Repository
public interface QuickCottageReservationRepository extends JpaRepository<QuickCottageReservation, Long> {

}
