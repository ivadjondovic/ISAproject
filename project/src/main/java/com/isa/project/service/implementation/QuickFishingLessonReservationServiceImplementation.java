package com.isa.project.service.implementation;

import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.EmailService;
import com.isa.project.service.QuickFishingLessonReservationService;

@Service
public class QuickFishingLessonReservationServiceImplementation implements QuickFishingLessonReservationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public Client clientReservation(QuickClientReservationDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		QuickFishingLessonReservation quickReservation = quickFishingLessonReservationRepository.findById(dto.getReservationId()).get();
		
		quickReservation.setClient(client);
		quickReservation.setReserved(true);
		QuickFishingLessonReservation savedReservation =  quickFishingLessonReservationRepository.save(quickReservation);
		Set<QuickFishingLessonReservation> reservations = client.getQuickFishingLessonReservations();
		reservations.add(savedReservation);
		client.setQuickFishingLessonReservations(reservations);
		Client savedClient = userRepository.save(client);
		
		try {
			emailService.sendQuickFishingLessonReservationMail(savedClient, savedReservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return savedClient;
	}

	@Override
	public QuickFishingLessonReservation accept(Long id) {
		QuickFishingLessonReservation reservation = quickFishingLessonReservationRepository.findById(id).get();
		
		if(reservation == null) {
			return null;
		}
		reservation.setAccepted(true);
		return quickFishingLessonReservationRepository.save(reservation);
	}

}
