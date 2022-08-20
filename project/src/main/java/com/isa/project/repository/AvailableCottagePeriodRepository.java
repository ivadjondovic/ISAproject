package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.AvailableCottagePeriod;

@Repository
public interface AvailableCottagePeriodRepository extends JpaRepository<AvailableCottagePeriod, Long> {

}
