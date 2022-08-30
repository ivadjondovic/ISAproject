package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.FishingLessonReservationResponseDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.AdditionalFishingLessonService;
import com.isa.project.model.AvailableFishingLessonPeriod;
import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.AdditionalFishingLessonServiceRepository;
import com.isa.project.repository.AvailableFishingLessonPeriodRepository;
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
	private AvailableFishingLessonPeriodRepository periodRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Override
	public FishingLessonReservation createReservation(ReservationDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
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
		
		FishingLesson lesson = fishingLessonRepository.findById(dto.getEntityId()).get();
		
		lessonReservation.setClient(client);
		lessonReservation.setFishingLesson(lesson);
		lessonReservation.setAccepted(false);
		lessonReservation.setCanceled(false);
		
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
		
		savedReservation.setPrice(price);
		savedReservation.setAdditionalServices(additionalServices);
		
		Set<AvailableFishingLessonPeriod> newPeriods = new HashSet<>();
		Set<AvailableFishingLessonPeriod> periods = lesson.getAvailablePeriods();
		for(AvailableFishingLessonPeriod period: periods) {
			if(period.getStartDate().compareTo(dto.getStartDate()) < 0 && period.getEndDate().compareTo(endDate) > 0) {
				AvailableFishingLessonPeriod availablePeriodFirst = new AvailableFishingLessonPeriod();
				availablePeriodFirst.setStartDate(period.getStartDate());
				availablePeriodFirst.setEndDate(dto.getStartDate());
				availablePeriodFirst.setFishingLesson(lesson);
				AvailableFishingLessonPeriod savedFirst = periodRepository.save(availablePeriodFirst);
				newPeriods.add(savedFirst);
				
				AvailableFishingLessonPeriod availablePeriodSecond = new AvailableFishingLessonPeriod();
				availablePeriodSecond.setStartDate(endDate);
				availablePeriodSecond.setEndDate(period.getEndDate());
				availablePeriodSecond.setFishingLesson(lesson);
				AvailableFishingLessonPeriod savedSecond = periodRepository.save(availablePeriodSecond);
				newPeriods.add(savedSecond);
				
				periodRepository.delete(period);
				
			}
			if(period.getStartDate().compareTo(dto.getStartDate()) == 0 && period.getEndDate().compareTo(endDate) > 0) {
				AvailableFishingLessonPeriod availablePeriod = new AvailableFishingLessonPeriod();
				availablePeriod.setStartDate(endDate);
				availablePeriod.setEndDate(period.getEndDate());
				availablePeriod.setFishingLesson(lesson);
				AvailableFishingLessonPeriod savedFirst = periodRepository.save(availablePeriod);
				newPeriods.add(savedFirst);
				
				periodRepository.delete(period);
			}
			if(period.getStartDate().compareTo(dto.getStartDate()) < 0 && period.getEndDate().compareTo(endDate) == 0) {
				AvailableFishingLessonPeriod availablePeriod = new AvailableFishingLessonPeriod();
				availablePeriod.setStartDate(period.getStartDate());
				availablePeriod.setEndDate(dto.getStartDate());
				availablePeriod.setFishingLesson(lesson);
				AvailableFishingLessonPeriod savedFirst = periodRepository.save(availablePeriod);
				newPeriods.add(savedFirst);
				
				periodRepository.delete(period);
				
			}
			if(period.getStartDate().compareTo(dto.getStartDate()) == 0 && period.getEndDate().compareTo(endDate) == 0) {
				periodRepository.delete(period);
			}
			
			if((period.getStartDate().compareTo(dto.getStartDate()) > 0 && period.getEndDate().compareTo(endDate) < 0) || (period.getStartDate().compareTo(dto.getStartDate()) == 0 && period.getEndDate().compareTo(endDate) < 0) || (period.getStartDate().compareTo(dto.getStartDate()) > 0 && period.getEndDate().compareTo(endDate) == 0) || (period.getStartDate().compareTo(dto.getStartDate()) < 0 && endDate.compareTo(period.getEndDate()) > 0) || (dto.getStartDate().compareTo(period.getStartDate()) < 0 && endDate.compareTo(period.getEndDate()) < 0) || (dto.getStartDate().isAfter(period.getStartDate()) && endDate.isAfter(period.getEndDate())) || (dto.getStartDate().isBefore(period.getStartDate()) && endDate.isBefore(period.getEndDate()))) {
				
				newPeriods.add(period);
			}
		}
		
		lesson.setAvailablePeriods(newPeriods);
		FishingLesson savedLesson = fishingLessonRepository.save(lesson);
		savedReservation.setFishingLesson(savedLesson);
		
		try {
			emailService.sendFishingLessonReservationMail(client, savedReservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fishingLessonReservationRepository.save(savedReservation);
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
			result.add(lessonReservation);
			
		}
		return result;
	}

}
