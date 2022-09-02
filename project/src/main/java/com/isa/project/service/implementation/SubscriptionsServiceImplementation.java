package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.SubscriptionResponseDTO;
import com.isa.project.dto.UnsubscribeDTO;
import com.isa.project.model.BoatSubscription;
import com.isa.project.model.Client;
import com.isa.project.model.CottageSubscription;
import com.isa.project.model.FishingLessonSubscription;
import com.isa.project.repository.BoatSubscriptionRepository;
import com.isa.project.repository.CottageSubscriptionRepository;
import com.isa.project.repository.FishingLessonSubscriptionRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.SubscriptionsService;


@Service
public class SubscriptionsServiceImplementation implements SubscriptionsService{

	
	@Autowired
	private BoatSubscriptionRepository boatSubscriptionRepository;
	
	@Autowired
	private CottageSubscriptionRepository cottageSubscriptionRepository;
	
	@Autowired
	private FishingLessonSubscriptionRepository fishingLessonSubscriptionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<SubscriptionResponseDTO> getClientSubscriptions(Long clientId) {
		
		Client client = (Client) userRepository.findById(clientId).get();
		List<SubscriptionResponseDTO> subscriptions = new ArrayList<>();
		List<BoatSubscription> boatSubscriptions = boatSubscriptionRepository.findByClient(client);
		List<CottageSubscription> cottageSubscriptions = cottageSubscriptionRepository.findByClient(client);
		List<FishingLessonSubscription> lessonSubscriptions = fishingLessonSubscriptionRepository.findByClient(client);
		
		
		for(BoatSubscription subscription: boatSubscriptions) {
			SubscriptionResponseDTO response = new SubscriptionResponseDTO();
			response.setEntityName(subscription.getBoat().getName());
			response.setEntityOwner(subscription.getBoat().getBoatOwner().getName() + " " + subscription.getBoat().getBoatOwner().getSurname());
			response.setType("Boat");
			response.setId(subscription.getId());
			
			subscriptions.add(response);
		}
		
		for(CottageSubscription subscription: cottageSubscriptions) {
			SubscriptionResponseDTO response = new SubscriptionResponseDTO();
			response.setEntityName(subscription.getCottage().getName());
			response.setEntityOwner(subscription.getCottage().getCottageOwner().getName() + " " + subscription.getCottage().getCottageOwner().getSurname());
			response.setType("Cottage");
			response.setId(subscription.getId());
			
			subscriptions.add(response);
		}
		
		for(FishingLessonSubscription subscription: lessonSubscriptions) {
			SubscriptionResponseDTO response = new SubscriptionResponseDTO();
			response.setEntityName(subscription.getFishingLesson().getName());
			response.setEntityOwner(subscription.getFishingLesson().getInstructor().getName() + " " + subscription.getFishingLesson().getInstructor().getSurname());
			response.setType("Fishing lesson");
			response.setId(subscription.getId());
			
			subscriptions.add(response);
		}
		
		return subscriptions;
	}

	@Override
	public SubscriptionResponseDTO unsubscribe(UnsubscribeDTO dto) {
		
		SubscriptionResponseDTO response = new SubscriptionResponseDTO();
		
		if(dto.getType().equals("Boat")) {
			BoatSubscription subscription = boatSubscriptionRepository.findById(dto.getId()).get();
			response.setEntityName(subscription.getBoat().getName());
			response.setEntityOwner(subscription.getBoat().getBoatOwner().getName() + " " + subscription.getBoat().getBoatOwner().getSurname());
			response.setType("Boat");
			response.setId(subscription.getId());
			
			boatSubscriptionRepository.delete(subscription);
			
		}
		
		if(dto.getType().equals("Cottage")) {
			CottageSubscription subscription = cottageSubscriptionRepository.findById(dto.getId()).get();
			response.setEntityName(subscription.getCottage().getName());
			response.setEntityOwner(subscription.getCottage().getCottageOwner().getName() + " " + subscription.getCottage().getCottageOwner().getSurname());
			response.setType("Cottage");
			response.setId(subscription.getId());
			
			cottageSubscriptionRepository.delete(subscription);
			
		}
		
		if(dto.getType().equals("Fishing lesson")) {
			FishingLessonSubscription subscription = fishingLessonSubscriptionRepository.findById(dto.getId()).get();
			response.setEntityName(subscription.getFishingLesson().getName());
			response.setEntityOwner(subscription.getFishingLesson().getInstructor().getName() + " " + subscription.getFishingLesson().getInstructor().getSurname());
			response.setType("Fishing lesson");
			response.setId(subscription.getId());
			
			fishingLessonSubscriptionRepository.delete(subscription);
			
		}
		return response;
	}

}
