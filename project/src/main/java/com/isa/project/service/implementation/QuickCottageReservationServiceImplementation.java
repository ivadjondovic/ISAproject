package com.isa.project.service.implementation;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.repository.QuickCottageReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.EmailService;
import com.isa.project.service.QuickCottageReservationService;

@Service
public class QuickCottageReservationServiceImplementation implements QuickCottageReservationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuickCottageReservationRepository quickCottageReservationRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Client clientReservation(QuickClientReservationDTO dto) throws Exception {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		QuickCottageReservation quickReservation = quickCottageReservationRepository.findLockById(dto.getReservationId());
		
		if(quickReservation.getReserved()) {
			return  null;
		}
		
		
		List<QuickCottageReservation> quickReservations = quickCottageReservationRepository.findByClient(client);
		
		for(QuickCottageReservation r: quickReservations) {
			
			if(r.getStartDate().compareTo(quickReservation.getStartDate()) == 0 && r.getEndDate().compareTo(quickReservation.getEndDate()) == 0 && r.getCottage().getId() == quickReservation.getCottage().getId()) {
				return null;
			}
		}
		
		quickReservation.setClient(client);
		quickReservation.setReserved(true);
		QuickCottageReservation savedReservation =  quickCottageReservationRepository.save(quickReservation);
		
		try {
			emailService.sendQuickCottageReservationMail(client, savedReservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			return client;
		}catch(PessimisticLockingFailureException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Try again later!");
		}
	}

	@Override
	public QuickCottageReservation accept(Long id) {
		QuickCottageReservation reservation = quickCottageReservationRepository.findById(id).get();
		
		if(reservation == null) {
			return null;
		}
		reservation.setAccepted(true);
		return quickCottageReservationRepository.save(reservation);
	}

}
