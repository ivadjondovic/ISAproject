package com.isa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.NavigationEquipment;

@Repository
public interface NavigationEquipmentRepository extends JpaRepository<NavigationEquipment, Long>  {

}
