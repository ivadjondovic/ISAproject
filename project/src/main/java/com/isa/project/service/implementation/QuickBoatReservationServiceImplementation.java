package com.isa.project.service.implementation;

import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.repository.QuickBoatReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.EmailService;
import com.isa.project.service.QuickBoatReservationService;

@Service
public class QuickBoatReservationServiceImplementation implements QuickBoatReservationService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuickBoatReservationRepository quickBoatReservationRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public Client clientReservation(QuickClientReservationDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		QuickBoatReservation quickReservation = quickBoatReservationRepository.findById(dto.getReservationId()).get();
		
		quickReservation.setClient(client);
		quickReservation.setReserved(true);
		QuickBoatReservation savedReservation =  quickBoatReservationRepository.save(quickReservation);
		Set<QuickBoatReservation> reservations = client.getQuickBoatReservations();
		reservations.add(savedReservation);
		client.setQuickBoatReservations(reservations);
		Client savedClient = userRepository.save(client);
		
		try {
			emailService.sendQuickBoatReservationMail(savedClient, savedReservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return savedClient;
	}

	@Override
	public QuickBoatReservation accept(Long id) {
		QuickBoatReservation reservation = quickBoatReservationRepository.findById(id).get();
		
		if(reservation == null) {
			return null;
		}
		reservation.setAccepted(true);
		return quickBoatReservationRepository.save(reservation);
	}

}
