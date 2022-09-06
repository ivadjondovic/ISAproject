package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.isa.project.dto.QuickClientReservationDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonSubscription;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.model.User;
import com.isa.project.repository.FishingLessonRepository;
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
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Client clientReservation(QuickClientReservationDTO dto) throws Exception{
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		QuickFishingLessonReservation quickReservation = quickFishingLessonReservationRepository.findLockById(dto.getReservationId());

		if(quickReservation.getReserved()) {
			return  null;
		}
		
		
		List<QuickFishingLessonReservation> quickReservations = quickFishingLessonReservationRepository.findByClient(client);
		
		for(QuickFishingLessonReservation r: quickReservations) {
			
			if(r.getStartDate().compareTo(quickReservation.getStartDate()) == 0 && r.getEndDate().compareTo(quickReservation.getEndDate()) == 0 && r.getFishingLesson().getId() == quickReservation.getFishingLesson().getId()) {
				return null;
			}
		}
		
		quickReservation.setClient(client);
		quickReservation.setReserved(true);
		QuickFishingLessonReservation savedReservation =  quickFishingLessonReservationRepository.save(quickReservation);
		
		try {
			emailService.sendQuickFishingLessonReservationMail(client, savedReservation);
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
	public QuickFishingLessonReservation accept(Long id) {
		QuickFishingLessonReservation reservation = quickFishingLessonReservationRepository.findById(id).get();
		
		if(reservation == null) {
			return null;
		}
		reservation.setAccepted(true);
		return quickFishingLessonReservationRepository.save(reservation);
	}

	@Override
	public void deleteReservation(Long id) {
		
		quickFishingLessonReservationRepository.deleteById(id);
		
	}

	@Override
	public QuickFishingLessonReservation saveReservation(QuickReservationDTO dto) {
		
		QuickFishingLessonReservation reservation = quickFishingLessonReservationRepository.findById(dto.getId()).get();
		
		
		if(!dto.getStartDate().equals("")) {
			reservation.setStartDate(dto.getStartDate());
		}
		if(!dto.getEndDate().equals("")) {
			reservation.setEndDate(dto.getEndDate());
		}
		
		if(dto.getPrice() != null) {
			reservation.setPrice(dto.getPrice());
		}
		
		if(!dto.getLocation().equals("")) {
			reservation.setLocation(dto.getLocation());
		}
		
		if(dto.getMaxNumberOfPerson() != 0) {
			reservation.setMaxNumberOfPerson(dto.getMaxNumberOfPerson());
		}
		
		if(!dto.getAdditionalServices().equals("")) {
			reservation.setAdditionalServices(dto.getAdditionalServices());
		}
		
		
		return quickFishingLessonReservationRepository.save(reservation);
	}

	@Override
	public QuickFishingLessonReservation createReservation(QuickReservationDTO dto) {
		
		if(dto.getAdditionalServices().equals("") || dto.getEndDate().equals("") || dto.getLocation().equals("") 
				|| dto.getMaxNumberOfPerson() == 0 || dto.getPrice() == null || dto.getStartDate().equals("")) {
			return null;
		}
		
		QuickFishingLessonReservation reservation = new QuickFishingLessonReservation();
		
		reservation.setStartDate(dto.getStartDate());
		reservation.setEndDate(dto.getEndDate());
		reservation.setPrice(dto.getPrice());
		reservation.setMaxNumberOfPerson(dto.getMaxNumberOfPerson());
		reservation.setLocation(dto.getLocation());
		reservation.setAdditionalServices(dto.getAdditionalServices());
		reservation.setAccepted(false);
		reservation.setCanceled(false);
		reservation.setReserved(false);
		reservation.setCalculated(false);
		reservation.setInstructorCalculated(false);
		reservation.setDiscount(0.15);
		
		FishingLesson lesson = fishingLessonRepository.findById(dto.getId()).get();
		
		reservation.setFishingLesson(lesson);
		
		Set<FishingLessonSubscription> subscriptions = lesson.getSubscriptions();
		
		List<User> users = new ArrayList<>();
		
		for(FishingLessonSubscription s : subscriptions) {
			users.add(s.getClient());
		}
		
		for(User u : users) {
			try {
				emailService.notifyClient(u, reservation);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return quickFishingLessonReservationRepository.save(reservation);
	}

}
