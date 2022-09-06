package com.isa.project.service.implementation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.isa.project.dto.FishingLessonReservationResponseDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.AdditionalFishingLessonService;
import com.isa.project.model.AvailableFishingLessonPeriod;

import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.Instructor;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.AdditionalFishingLessonServiceRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.FishingLessonReservationRepository;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.EmailService;
import com.isa.project.service.FishingLessonReservationService;

@Service
public class FishingLessonReservationServiceImplementation implements FishingLessonReservationService{

	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FishingLessonReservationRepository fishingLessonReservationRepository;
	
	@Autowired
	private AdditionalFishingLessonServiceRepository additionalFishingLessonServiceRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public FishingLessonReservation createReservation(ReservationDTO dto)  throws Exception{
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		
		if(client.getPenalties() >= 3) {
			return null;
		}
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		List<FishingLessonReservation> clientReservations = fishingLessonReservationRepository.findByClient(client);
		
		for(FishingLessonReservation r: clientReservations) {
			if(r.getStartDate().compareTo(dto.getStartDate()) == 0  && r.getEndDate().compareTo(endDate) == 0 && r.getFishingLesson().getId() == dto.getEntityId()) {
				return null;
			}
		}
		
		FishingLessonReservation lessonReservation = new FishingLessonReservation();
		lessonReservation.setStartDate(dto.getStartDate());
		lessonReservation.setEndDate(endDate);
		
		FishingLesson lesson = fishingLessonRepository.findLockById(dto.getEntityId());
		
		
		List<FishingLessonReservation> reservations = fishingLessonReservationRepository.findByFishingLessonAndCanceled(lesson, false);
		Set<AvailableFishingLessonPeriod> periods = lesson.getAvailablePeriods();
		
		
		for(FishingLessonReservation r: reservations) {
			if((dto.getStartDate().compareTo(r.getStartDate()) >= 0 && endDate.compareTo(r.getEndDate()) <= 0) 
					|| (dto.getStartDate().compareTo(r.getStartDate()) < 0 && endDate.compareTo(r.getEndDate()) <= 0 && endDate.compareTo(r.getStartDate()) > 0)
					|| (dto.getStartDate().compareTo(r.getStartDate()) >= 0 && endDate.compareTo(r.getEndDate()) > 0 && dto.getStartDate().compareTo(r.getEndDate()) < 0)
					|| (dto.getStartDate().compareTo(r.getStartDate()) < 0 && endDate.compareTo(r.getEndDate()) > 0)) {
				System.out.println("RESERVATION NULL");
				return null;
			}else {
				continue;
				
			}
		}
		
		Boolean available = false;
		
		for(AvailableFishingLessonPeriod period: periods) {
			if(dto.getStartDate().compareTo(period.getStartDate()) >= 0 && endDate.compareTo(period.getEndDate()) <=0) {
				System.out.println("available period NULL");
					available = true;
					break;
			}	
		}
		
		if(!available) {
			return null;
		}
		
		lessonReservation.setClient(client);
		lessonReservation.setFishingLesson(lesson);
		lessonReservation.setAccepted(false);
		lessonReservation.setCanceled(false);
		lessonReservation.setCalculated(false);
		lessonReservation.setInstructorCalculated(false);
		
		FishingLessonReservation savedReservation = fishingLessonReservationRepository.save(lessonReservation);
		
		Set<AdditionalFishingLessonService> additionalServices = new HashSet<>();
		Double price = lesson.getPrice() * dto.getNumberOfDays();
		for(Long id: dto.getAdditionalServices()) {
			AdditionalFishingLessonService service = additionalFishingLessonServiceRepository.findById(id).get();
			price += service.getPrice();
			service.setFishingLessonReservation(savedReservation);
			AdditionalFishingLessonService savedService = additionalFishingLessonServiceRepository.save(service);
			additionalServices.add(savedService);
			
		}

		if(client.getCategory() != null && (client.getCategory().getCategory().equals("silver") || client.getCategory().getCategory().equals("gold"))) {
			savedReservation.setPrice(price - price * client.getCategory().getDiscount());
		} else 
			savedReservation.setPrice(price);
		
		savedReservation.setAdditionalServices(additionalServices);
		
		
		FishingLesson savedLesson = fishingLessonRepository.save(lesson);
		savedReservation.setFishingLesson(savedLesson);
		
		try {
			emailService.sendFishingLessonReservationMail(client, savedReservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			return fishingLessonReservationRepository.save(savedReservation);
		}catch(PessimisticLockingFailureException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Try again later!");
		}
		
		
	}

	@Override
	public FishingLessonReservation accept(Long id) {
		FishingLessonReservation reservation = fishingLessonReservationRepository.findById(id).get();
		
		if(reservation == null) {
			return null;
		}
		reservation.setAccepted(true);
		return fishingLessonReservationRepository.save(reservation);
	}

	@Override
	public List<FishingLessonReservationResponseDTO> getByClientId(Long clientId) {
		Client client = (Client) userRepository.findById(clientId).get();
		List<FishingLessonReservationResponseDTO> result = new ArrayList<>();
		List<FishingLessonReservation> fishingLessonReservations = fishingLessonReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		for(FishingLessonReservation flr: fishingLessonReservations) {
			FishingLesson fishingLesson = fishingLessonRepository.findById(flr.getFishingLesson().getId()).get();
			FishingLessonReservationResponseDTO lessonReservation = new FishingLessonReservationResponseDTO();
			lessonReservation.setAccepted(flr.getAccepted());
			lessonReservation.setAdditionalServices(flr.getAdditionalServices());
			lessonReservation.setClient(flr.getClient());
			lessonReservation.setFishingLesson(fishingLesson);
			lessonReservation.setEndDate(flr.getEndDate());
			lessonReservation.setId(flr.getId());
			lessonReservation.setPrice(flr.getPrice());
			lessonReservation.setStartDate(flr.getStartDate());
			lessonReservation.setReservationType("Fishing lesson reservation");
			if(flr.getEndDate().compareTo(LocalDateTime.now()) <= 0) {
				lessonReservation.setPossibleToRate(true);
			}else {
				lessonReservation.setPossibleToRate(false);
			}
			
			result.add(lessonReservation);
		}
		
		List<QuickFishingLessonReservation> quickReservations = quickFishingLessonReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		for(QuickFishingLessonReservation quickR: quickReservations) {
			
			FishingLesson lesson = fishingLessonRepository.findById(quickR.getFishingLesson().getId()).get();
			FishingLessonReservationResponseDTO lessonReservation = new FishingLessonReservationResponseDTO();
			lessonReservation.setAccepted(quickR.getAccepted());
			lessonReservation.setClient(quickR.getClient());
			lessonReservation.setFishingLesson(lesson);
			lessonReservation.setEndDate(quickR.getEndDate());
			lessonReservation.setId(quickR.getId());
			lessonReservation.setPrice(quickR.getPrice());
			lessonReservation.setStartDate(quickR.getStartDate());
			lessonReservation.setReservationType("Quick fishing lesson reservation");
			if(quickR.getEndDate().compareTo(LocalDateTime.now()) <= 0) {
				lessonReservation.setPossibleToRate(true);
			}else {
				lessonReservation.setPossibleToRate(false);
			}
			
			result.add(lessonReservation);
			
		}
		return result;
	}

	@Override
	public List<FishingLessonReservationResponseDTO> sort(SortDTO dto) {
		
		List<FishingLessonReservationResponseDTO> reservations = getByClientId(dto.getClientId());
		if(dto.getSortBy().equals("Date")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(reservations, (r1, r2) ->
			    (r1.getStartDate().compareTo(r2.getStartDate())));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(reservations, (r1, r2) ->
			    (r2.getStartDate().compareTo(r1.getStartDate())));
			}
		}
		
		if(dto.getSortBy().equals("Price")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(reservations, (r1, r2) ->
				 Double.compare(r1.getPrice(), r2.getPrice()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(reservations, (r1, r2) ->
				 Double.compare(r2.getPrice(), r1.getPrice()));
			}
		}
		
		if(dto.getSortBy().equals("Duration")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(reservations, (r1, r2) ->
				 Duration.between(r1.getStartDate(), r1.getEndDate()).compareTo(Duration.between(r2.getStartDate(), r2.getEndDate())));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(reservations, (r1, r2) ->
				 Duration.between(r2.getStartDate(), r2.getEndDate()).compareTo(Duration.between(r1.getStartDate(), r1.getEndDate())));
			}
		}
		
		return reservations;
	}

	@Override
	public List<FishingLessonReservationResponseDTO> getByInstructorId(Long instructorId) {
		Instructor instructor = (Instructor) userRepository.findById(instructorId).get();
		List<FishingLesson> instructorLessons = fishingLessonRepository.findByInstructor(instructor);
		List<FishingLessonReservationResponseDTO> result = new ArrayList<>();
		Set<FishingLessonReservation> reservationSet = new HashSet<>();
		Set<QuickFishingLessonReservation> quickReservationSet = new HashSet<>();
		for(FishingLesson f : instructorLessons) {
			reservationSet.addAll(f.getReservations());
			quickReservationSet.addAll(f.getQuickReservations());
		}
		
		Set<FishingLessonReservation> filteredReservationSet = reservationSet.stream()
				.filter(r -> (r.getAccepted() == true && r.getCanceled() == false && r.getInstructorCalculated() == false))
                .collect(Collectors.toSet());

		for(FishingLessonReservation flr: filteredReservationSet) {
			FishingLesson fishingLesson = fishingLessonRepository.findById(flr.getFishingLesson().getId()).get();
			FishingLessonReservationResponseDTO lessonReservation = new FishingLessonReservationResponseDTO();
			lessonReservation.setAccepted(flr.getAccepted());
			lessonReservation.setAdditionalServices(flr.getAdditionalServices());
			lessonReservation.setClient(flr.getClient());
			lessonReservation.setFishingLesson(fishingLesson);
			lessonReservation.setEndDate(flr.getEndDate());
			lessonReservation.setId(flr.getId());
			lessonReservation.setPrice(flr.getPrice());
			lessonReservation.setStartDate(flr.getStartDate());
			lessonReservation.setReservationType("Fishing lesson reservation");
			if(flr.getEndDate().compareTo(LocalDateTime.now()) <= 0) {
				lessonReservation.setPossibleToRate(true);
			}else {
				lessonReservation.setPossibleToRate(false);
			}
			
			result.add(lessonReservation);
		}
		
		Set<QuickFishingLessonReservation> filteredQuickReservationSet = quickReservationSet.stream()
				.filter(r -> (r.getAccepted() == true && r.getCanceled() == false && r.getInstructorCalculated() == false))
                .collect(Collectors.toSet());
		
		for(QuickFishingLessonReservation quickR: filteredQuickReservationSet) {
			
			FishingLesson lesson = fishingLessonRepository.findById(quickR.getFishingLesson().getId()).get();
			FishingLessonReservationResponseDTO lessonReservation = new FishingLessonReservationResponseDTO();
			lessonReservation.setAccepted(quickR.getAccepted());
			lessonReservation.setClient(quickR.getClient());
			lessonReservation.setFishingLesson(lesson);
			lessonReservation.setEndDate(quickR.getEndDate());
			lessonReservation.setId(quickR.getId());
			lessonReservation.setPrice(quickR.getPrice());
			lessonReservation.setStartDate(quickR.getStartDate());
			lessonReservation.setReservationType("Quick fishing lesson reservation");
			if(quickR.getEndDate().compareTo(LocalDateTime.now()) <= 0) {
				lessonReservation.setPossibleToRate(true);
			}else {
				lessonReservation.setPossibleToRate(false);
			}
			
			result.add(lessonReservation);
			
		}
		return result;
	}

}
