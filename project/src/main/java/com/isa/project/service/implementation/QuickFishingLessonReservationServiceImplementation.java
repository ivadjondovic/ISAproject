package com.isa.project.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.QuickFishingLessonReservationService;

@Service
public class QuickFishingLessonReservationServiceImplementation implements QuickFishingLessonReservationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Override
	public Client clientReservation(QuickClientReservationDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		QuickFishingLessonReservation quickReservation = quickFishingLessonReservationRepository.findById(dto.getReservationId()).get();
		
		quickReservation.setClient(client);
		QuickFishingLessonReservation savedReservation =  quickFishingLessonReservationRepository.save(quickReservation);
		Set<QuickFishingLessonReservation> reservations = client.getQuickFishingLessonReservations();
		reservations.add(savedReservation);
		client.setQuickFishingLessonReservations(reservations);
		Client savedClient = userRepository.save(client);
		
		return savedClient;
	}

}
