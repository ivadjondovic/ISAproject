package com.isa.project.service.implementation;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Client clientReservation(QuickClientReservationDTO dto)  throws Exception{
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		
		QuickBoatReservation quickReservation = quickBoatReservationRepository.findLockById(dto.getReservationId());
		
		
		if(quickReservation.getReserved()) {
			return  null;
		}
		
		List<QuickBoatReservation> quickReservations = quickBoatReservationRepository.findByClient(client);
		
		for(QuickBoatReservation r: quickReservations) {
			
			if(r.getStartDate().compareTo(quickReservation.getStartDate()) == 0 && r.getEndDate().compareTo(quickReservation.getEndDate()) == 0 && r.getBoat().getId() == quickReservation.getBoat().getId()) {
				return null;
			}
			
		}
		
		quickReservation.setClient(client);
		quickReservation.setReserved(true);
		QuickBoatReservation savedReservation =  quickBoatReservationRepository.save(quickReservation);
		try {
			emailService.sendQuickBoatReservationMail(client, savedReservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return client;
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
