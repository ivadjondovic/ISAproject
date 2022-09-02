package com.isa.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.SubscriptionDTO;
import com.isa.project.model.Boat;
import com.isa.project.model.BoatSubscription;
import com.isa.project.model.Client;
import com.isa.project.repository.BoatRepository;
import com.isa.project.repository.BoatSubscriptionRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.BoatSubscriptionService;

@Service
public class BoatSubscriptionServiceImplementation implements BoatSubscriptionService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private BoatSubscriptionRepository boatSubscriptionRepository;
	
	@Override
	public BoatSubscription subscribe(SubscriptionDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		Boat boat =  boatRepository.findById(dto.getEntityId()).get();
		
		BoatSubscription subscription = new BoatSubscription();
		subscription.setClient(client);
		subscription.setBoat(boat);
		
		BoatSubscription savedSubscription = boatSubscriptionRepository.save(subscription);
	
		return savedSubscription;
	}

}
