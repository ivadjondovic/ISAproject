package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.BoatSubscription;
import com.isa.project.model.Client;

@Repository
public interface BoatSubscriptionRepository extends JpaRepository<BoatSubscription, Long> {

	public List<BoatSubscription> findByClient(Client client); 
}
