package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.AdditionalFishingLessonService;
import com.isa.project.model.AvailableFishingLessonPeriod;
import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.repository.AdditionalFishingLessonServiceRepository;
import com.isa.project.repository.AvailableFishingLessonPeriodRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.FishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
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
	
	@Override
	public FishingLessonReservation createReservation(ReservationDTO dto) {
		
		FishingLessonReservation lessonReservation = new FishingLessonReservation();
		lessonReservation.setStartDate(dto.getStartDate());
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		lessonReservation.setEndDate(endDate);
		
		FishingLesson lesson = fishingLessonRepository.findById(dto.getEntityId()).get();
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		
		lessonReservation.setClient(client);
		lessonReservation.setFishingLesson(lesson);
		
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
		
		return fishingLessonReservationRepository.save(savedReservation);
	}

}
