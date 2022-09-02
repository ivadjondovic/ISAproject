package com.isa.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.SubscriptionDTO;
import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonSubscription;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.FishingLessonSubscriptionRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.FishingLessonSubscriptionService;


@Service
public class FishingLessonSubscriptionServiceImplementation implements FishingLessonSubscriptionService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Autowired
	private FishingLessonSubscriptionRepository fishingLessonSubscriptionRepository;
	
	@Override
	public FishingLessonSubscription subscribe(SubscriptionDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		FishingLesson lesson =  fishingLessonRepository.findById(dto.getEntityId()).get();
		
		FishingLessonSubscription subscription = new FishingLessonSubscription();
		subscription.setCanceled(false);
		subscription.setClient(client);
		subscription.setFishingLesson(lesson);
		
		FishingLessonSubscription savedSubscription = fishingLessonSubscriptionRepository.save(subscription);
	
		return savedSubscription;
	}

}
