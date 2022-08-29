package com.isa.project.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.repository.QuickBoatReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.QuickBoatReservationService;

@Service
public class QuickBoatReservationServiceImplementation implements QuickBoatReservationService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuickBoatReservationRepository quickBoatReservationRepository;
	
	
	@Override
	public Client clientReservation(QuickClientReservationDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		QuickBoatReservation quickReservation = quickBoatReservationRepository.findById(dto.getReservationId()).get();
		
		quickReservation.setClient(client);
		QuickBoatReservation savedReservation =  quickBoatReservationRepository.save(quickReservation);
		Set<QuickBoatReservation> reservations = client.getQuickBoatReservations();
		reservations.add(savedReservation);
		client.setQuickBoatReservations(reservations);
		Client savedClient = userRepository.save(client);
		
		return savedClient;
	}

}
