package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public void deleteReservation(Long id) {
		
		quickFishingLessonReservationRepository.deleteById(id);
		
	}

	@Override
	public QuickFishingLessonReservation saveReservation(QuickReservationDTO dto) {
		
		QuickFishingLessonReservation reservation = quickFishingLessonReservationRepository.findById(dto.getId()).get();
		
		reservation.setStartDate(dto.getStartDate());
		reservation.setEndDate(dto.getEndDate());
		reservation.setPrice(dto.getPrice());
		reservation.setLocation(dto.getLocation());
		reservation.setMaxNumberOfPerson(dto.getMaxNumberOfPerson());
		reservation.setAdditionalServices(dto.getAdditionalServices());
		
		return quickFishingLessonReservationRepository.save(reservation);
	}

	@Override
	public QuickFishingLessonReservation createReservation(QuickReservationDTO dto) {
		
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
		reservation.setDiscount(dto.getDiscount());
		
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
