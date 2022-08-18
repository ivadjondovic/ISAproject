package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.AdditionalBoatService;

@Repository
public interface AdditionalBoatServiceRepository extends JpaRepository<AdditionalBoatService, Long>  {

}
