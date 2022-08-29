package com.isa.project.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.repository.QuickCottageReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.QuickCottageReservationService;

@Service
public class QuickCottageReservationServiceImplementation implements QuickCottageReservationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuickCottageReservationRepository quickCottageReservationRepository;
	
	
	@Override
	public Client clientReservation(QuickClientReservationDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		QuickCottageReservation quickReservation = quickCottageReservationRepository.findById(dto.getReservationId()).get();
		
		quickReservation.setClient(client);
		quickReservation.setReserved(true);
		QuickCottageReservation savedReservation =  quickCottageReservationRepository.save(quickReservation);
		Set<QuickCottageReservation> reservations = client.getQuickCottageReservations();
		reservations.add(savedReservation);
		client.setQuickCottageReservations(reservations);
		Client savedClient = userRepository.save(client);
		
		return savedClient;
	}

}
