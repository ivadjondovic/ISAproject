package com.isa.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.project.model.Client;
import com.isa.project.model.CottageSubscription;

@Repository
public interface CottageSubscriptionRepository extends JpaRepository<CottageSubscription, Long>  {
	
	public List<CottageSubscription> findByClient(Client client); 

}
