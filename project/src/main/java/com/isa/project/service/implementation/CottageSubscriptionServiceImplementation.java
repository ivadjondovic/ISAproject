package com.isa.project.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.SubscriptionDTO;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageSubscription;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.CottageSubscriptionRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.CottageSubscriptionService;

@Service
public class CottageSubscriptionServiceImplementation implements CottageSubscriptionService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CottageRepository cottageRepository;
	
	@Autowired
	private CottageSubscriptionRepository cottageSubscriptionRepository;
	
	@Override
	public CottageSubscription subscribe(SubscriptionDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		Cottage cottage =  cottageRepository.findById(dto.getEntityId()).get();
		
		CottageSubscription subscription = new CottageSubscription();
		subscription.setCanceled(false);
		subscription.setClient(client);
		subscription.setCottage(cottage);
		
		CottageSubscription savedSubscription = cottageSubscriptionRepository.save(subscription);
	
		return savedSubscription;
	}

}
