package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.FishingEquipment;

@Repository
public interface FishingEquipmentRepository extends JpaRepository<FishingEquipment, Long> {

}
