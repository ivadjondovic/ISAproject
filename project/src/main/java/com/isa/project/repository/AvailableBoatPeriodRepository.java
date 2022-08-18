package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.AvailableBoatPeriod;

@Repository
public interface AvailableBoatPeriodRepository  extends JpaRepository<AvailableBoatPeriod, Long>  {

}
