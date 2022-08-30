package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.CancelReservationDTO;
import com.isa.project.dto.ReservationResponseDTO;
import com.isa.project.model.AvailableBoatPeriod;
import com.isa.project.model.AvailableCottagePeriod;
import com.isa.project.model.AvailableFishingLessonPeriod;
import com.isa.project.model.Boat;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageReservation;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.AvailableBoatPeriodRepository;
import com.isa.project.repository.AvailableCottagePeriodRepository;
import com.isa.project.repository.AvailableFishingLessonPeriodRepository;
import com.isa.project.repository.BoatRepository;
import com.isa.project.repository.BoatReservationRepository;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.CottageReservationRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.FishingLessonReservationRepository;
import com.isa.project.repository.QuickBoatReservationRepository;
import com.isa.project.repository.QuickCottageReservationRepository;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.ReservationsService;

@Service
public class ReservationsServiceImplementation implements ReservationsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CottageRepository cottageRepository;
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Autowired
	private CottageReservationRepository cottageReservationRepository;
	
	@Autowired
	private BoatReservationRepository boatReservationRepository;
	
	@Autowired
	private FishingLessonReservationRepository fishingLessonReservationRepository;
	
	@Autowired
	private QuickCottageReservationRepository quickCottageReservationRepository;
	
	@Autowired
	private QuickBoatReservationRepository quickBoatReservationRepository;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Autowired
	private AvailableCottagePeriodRepository availableCottagePeriodRepository;
	
	@Autowired
	private AvailableBoatPeriodRepository availableBoatPeriodRepository;
	
	@Autowired
	private AvailableFishingLessonPeriodRepository availableFishingLessonPeriodRepository;
	
	
	@Override
	public List<ReservationResponseDTO> getNotPassedReservations(Long clientId) {
		
		Client client = (Client) userRepository.findById(clientId).get();
		List<ReservationResponseDTO> result = new ArrayList<>();
		List<CottageReservation> cottageReservations = cottageReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		List<BoatReservation> boatReservations = boatReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		List<FishingLessonReservation> fishingLessonReservations = fishingLessonReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		List<QuickCottageReservation> quickCottageReservations = quickCottageReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		List<QuickBoatReservation> quickBoatReservations = quickBoatReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		List<QuickFishingLessonReservation> quickFishingLessonReservations = quickFishingLessonReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		
		List<CottageReservation> notPassedCottageReservations = cottageReservations.stream()
			    .filter(r -> r.getStartDate().compareTo(LocalDateTime.now()) > 0).collect(Collectors.toList());
		
		List<BoatReservation> notPassedBoatReservations = boatReservations.stream()
			    .filter(r -> r.getStartDate().compareTo(LocalDateTime.now()) > 0).collect(Collectors.toList());
		
		List<FishingLessonReservation> notPassedFishingLessonReservations = fishingLessonReservations.stream()
			    .filter(r -> r.getStartDate().compareTo(LocalDateTime.now()) > 0).collect(Collectors.toList());
		
		List<QuickFishingLessonReservation> notPassedQuickFishingLessonReservations = quickFishingLessonReservations.stream()
			    .filter(r -> r.getStartDate().compareTo(LocalDateTime.now()) > 0).collect(Collectors.toList());
		
		List<QuickBoatReservation> notPassedQuickBoatReservations = quickBoatReservations.stream()
			    .filter(r -> r.getStartDate().compareTo(LocalDateTime.now()) > 0).collect(Collectors.toList());
		
		List<QuickCottageReservation> notPassedQuickCottageReservations = quickCottageReservations.stream()
			    .filter(r -> r.getStartDate().compareTo(LocalDateTime.now()) > 0).collect(Collectors.toList());
		
		
		for(CottageReservation cr: notPassedCottageReservations) {
			ReservationResponseDTO reservation = new ReservationResponseDTO();
			reservation.setEndDate(cr.getEndDate());
			reservation.setEntityName(cr.getCottage().getName());
			reservation.setId(cr.getId());
			reservation.setPrice(cr.getPrice());
			reservation.setReservationType("Cottage reservation");
			reservation.setStartDate(cr.getStartDate());
			LocalDateTime ldt = cr.getStartDate().minusDays(3);
			if(ldt.compareTo(LocalDateTime.now()) >= 0) {
				reservation.setPosibleToCancel(true);
			}else {
				reservation.setPosibleToCancel(false);
			}
			result.add(reservation);
		}
		
		for(BoatReservation br: notPassedBoatReservations) {
			ReservationResponseDTO reservation = new ReservationResponseDTO();
			reservation.setEndDate(br.getEndDate());
			reservation.setEntityName(br.getBoat().getName());
			reservation.setId(br.getId());
			reservation.setPrice(br.getPrice());
			reservation.setReservationType("Boat reservation");
			reservation.setStartDate(br.getStartDate());
			LocalDateTime ldt = br.getStartDate().minusDays(3);
			if(ldt.compareTo(LocalDateTime.now()) >= 0) {
				reservation.setPosibleToCancel(true);
			}else {
				reservation.setPosibleToCancel(false);
			}
			result.add(reservation);
		}
		
		for(FishingLessonReservation fr: notPassedFishingLessonReservations) {
			ReservationResponseDTO reservation = new ReservationResponseDTO();
			reservation.setEndDate(fr.getEndDate());
			reservation.setEntityName(fr.getFishingLesson().getName());
			reservation.setId(fr.getId());
			reservation.setPrice(fr.getPrice());
			reservation.setReservationType("Fishing lesson reservation");
			reservation.setStartDate(fr.getStartDate());
			LocalDateTime ldt = fr.getStartDate().minusDays(3);
			if(ldt.compareTo(LocalDateTime.now()) >= 0) {
				reservation.setPosibleToCancel(true);
			}else {
				reservation.setPosibleToCancel(false);
			}
			result.add(reservation);
		}
		
		for(QuickCottageReservation qr: notPassedQuickCottageReservations) {
			ReservationResponseDTO reservation = new ReservationResponseDTO();
			reservation.setEndDate(qr.getEndDate());
			reservation.setEntityName(qr.getCottage().getName());
			reservation.setId(qr.getId());
			reservation.setPrice(qr.getPrice());
			reservation.setReservationType("Quick cottage reservation");
			reservation.setStartDate(qr.getStartDate());
			LocalDateTime ldt = qr.getStartDate().minusDays(3);
			if(ldt.compareTo(LocalDateTime.now()) >= 0) {
				reservation.setPosibleToCancel(true);
			}else {
				reservation.setPosibleToCancel(false);
			}
			result.add(reservation);
		}
		
		for(QuickBoatReservation qr: notPassedQuickBoatReservations) {
			ReservationResponseDTO reservation = new ReservationResponseDTO();
			reservation.setEndDate(qr.getEndDate());
			reservation.setEntityName(qr.getBoat().getName());
			reservation.setId(qr.getId());
			reservation.setPrice(qr.getPrice());
			reservation.setReservationType("Quick boat reservation");
			reservation.setStartDate(qr.getStartDate());
			LocalDateTime ldt = qr.getStartDate().minusDays(3);
			if(ldt.compareTo(LocalDateTime.now()) >= 0) {
				reservation.setPosibleToCancel(true);
			}else {
				reservation.setPosibleToCancel(false);
			}
			result.add(reservation);
		}
		
		for(QuickFishingLessonReservation qr: notPassedQuickFishingLessonReservations) {
			ReservationResponseDTO reservation = new ReservationResponseDTO();
			reservation.setEndDate(qr.getEndDate());
			reservation.setEntityName(qr.getFishingLesson().getName());
			reservation.setId(qr.getId());
			reservation.setPrice(qr.getPrice());
			reservation.setReservationType("Quick fishing lesson reservation");
			reservation.setStartDate(qr.getStartDate());
			LocalDateTime ldt = qr.getStartDate().minusDays(3);
			if(ldt.compareTo(LocalDateTime.now()) >= 0) {
				reservation.setPosibleToCancel(true);
			}else {
				reservation.setPosibleToCancel(false);
			}
			result.add(reservation);
		}
		
		return result;
	}


	@Override
	public ReservationResponseDTO cancelReservation(CancelReservationDTO dto) {
		
		ReservationResponseDTO response = new ReservationResponseDTO();
		if(dto.getReservationType().equals("Cottage reservation")) {
			
			
			CottageReservation reservation = cottageReservationRepository.findById(dto.getReservationId()).get();
			
			if(reservation.getStartDate().minusDays(3).compareTo(LocalDateTime.now()) < 0) {
				return null;
			}
			reservation.setCanceled(true);
			cottageReservationRepository.save(reservation);
			
			Cottage cottage = cottageRepository.findById(reservation.getCottage().getId()).get();
			Set<AvailableCottagePeriod> periods = cottage.getAvailablePeriods();
			AvailableCottagePeriod period = new AvailableCottagePeriod();
			period.setCottage(cottage);
			period.setEndDate(reservation.getEndDate());
			period.setStartDate(reservation.getStartDate());
			AvailableCottagePeriod savedPeriod = availableCottagePeriodRepository.save(period);
			periods.add(savedPeriod);
			cottage.setAvailablePeriods(periods);
			cottageRepository.save(cottage);
			
			response.setEndDate(reservation.getEndDate());
			response.setEntityName(reservation.getCottage().getName());
			response.setPrice(reservation.getPrice());
			response.setReservationType("Cottage reservation");
			response.setStartDate(reservation.getStartDate());
			
		}
		if(dto.getReservationType().equals("Boat reservation")) {
			BoatReservation reservation = boatReservationRepository.findById(dto.getReservationId()).get();
			if(reservation.getStartDate().minusDays(3).compareTo(LocalDateTime.now()) < 0) {
				return null;
			}
			reservation.setCanceled(true);
			boatReservationRepository.save(reservation);
			
			Boat boat = boatRepository.findById(reservation.getBoat().getId()).get();
			Set<AvailableBoatPeriod> periods = boat.getAvailablePeriods();
			AvailableBoatPeriod period = new AvailableBoatPeriod();
			period.setBoat(boat);
			period.setEndDate(reservation.getEndDate());
			period.setStartDate(reservation.getStartDate());
			AvailableBoatPeriod savedPeriod = availableBoatPeriodRepository.save(period);
			periods.add(savedPeriod);
			boat.setAvailablePeriods(periods);
			boatRepository.save(boat);
			
			response.setEndDate(reservation.getEndDate());
			response.setEntityName(reservation.getBoat().getName());
			response.setPrice(reservation.getPrice());
			response.setReservationType("Boat reservation");
			response.setStartDate(reservation.getStartDate());
			
		}
		
		if(dto.getReservationType().equals("Fishing lesson reservation")) {
			FishingLessonReservation reservation = fishingLessonReservationRepository.findById(dto.getReservationId()).get();
			if(reservation.getStartDate().minusDays(3).compareTo(LocalDateTime.now()) < 0) {
				return null;
			}
			reservation.setCanceled(true);
			fishingLessonReservationRepository.save(reservation);
			
			FishingLesson fishingLesson = fishingLessonRepository.findById(reservation.getFishingLesson().getId()).get();
			Set<AvailableFishingLessonPeriod> periods = fishingLesson.getAvailablePeriods();
			AvailableFishingLessonPeriod period = new AvailableFishingLessonPeriod();
			period.setFishingLesson(fishingLesson);
			period.setEndDate(reservation.getEndDate());
			period.setStartDate(reservation.getStartDate());
			AvailableFishingLessonPeriod savedPeriod = availableFishingLessonPeriodRepository.save(period);
			periods.add(savedPeriod);
			fishingLesson.setAvailablePeriods(periods);
			fishingLessonRepository.save(fishingLesson);
			
			response.setEndDate(reservation.getEndDate());
			response.setEntityName(reservation.getFishingLesson().getName());
			response.setPrice(reservation.getPrice());
			response.setReservationType("Fishing lesson reservation");
			response.setStartDate(reservation.getStartDate());
			
		}
		
		if(dto.getReservationType().equals("Quick cottage reservation")) {
			QuickCottageReservation quickReservation  = quickCottageReservationRepository.findById(dto.getReservationId()).get();
			if(quickReservation.getStartDate().minusDays(3).compareTo(LocalDateTime.now()) < 0) {
				return null;
			}
			quickReservation.setCanceled(true);
			quickCottageReservationRepository.save(quickReservation);
			
			QuickCottageReservation reservation = new QuickCottageReservation();
			reservation.setAccepted(false);
			reservation.setAdditionalServices(quickReservation.getAdditionalServices());
			reservation.setCanceled(false);
			reservation.setCottage(quickReservation.getCottage());
			reservation.setEndDate(quickReservation.getEndDate());
			reservation.setMaxNumberOfPerson(quickReservation.getMaxNumberOfPerson());
			reservation.setPrice(quickReservation.getPrice());
			reservation.setReserved(false);
			reservation.setStartDate(quickReservation.getStartDate());
			
			QuickCottageReservation savedReservation = quickCottageReservationRepository.save(reservation);
			
			Cottage cottage = cottageRepository.findById(quickReservation.getCottage().getId()).get();
			
			Set<QuickCottageReservation> quickReservations = cottage.getQuickReservations();
			quickReservations.add(savedReservation);
			
			cottage.setQuickReservations(quickReservations);
			cottageRepository.save(cottage);
			
			
			response.setEndDate(quickReservation.getEndDate());
			response.setEntityName(quickReservation.getCottage().getName());
			response.setPrice(quickReservation.getPrice());
			response.setReservationType("Quick cottage reservation");
			response.setStartDate(quickReservation.getStartDate());
		}
		
		if(dto.getReservationType().equals("Quick boat reservation")) {
			QuickBoatReservation quickReservation  = quickBoatReservationRepository.findById(dto.getReservationId()).get();
			if(quickReservation.getStartDate().minusDays(3).compareTo(LocalDateTime.now()) < 0) {
				return null;
			}
			quickReservation.setCanceled(true);
			quickBoatReservationRepository.save(quickReservation);
			
			QuickBoatReservation reservation = new QuickBoatReservation();
			reservation.setAccepted(false);
			reservation.setAdditionalServices(quickReservation.getAdditionalServices());
			reservation.setCanceled(false);
			reservation.setBoat(quickReservation.getBoat());
			reservation.setEndDate(quickReservation.getEndDate());
			reservation.setMaxNumberOfPerson(quickReservation.getMaxNumberOfPerson());
			reservation.setPrice(quickReservation.getPrice());
			reservation.setReserved(false);
			reservation.setStartDate(quickReservation.getStartDate());
			
			QuickBoatReservation savedReservation = quickBoatReservationRepository.save(reservation);
			Boat boat = boatRepository.findById(quickReservation.getBoat().getId()).get();			
			Set<QuickBoatReservation> quickReservations = boat.getQuickReservations();
			quickReservations.add(savedReservation);
			
			boat.setQuickReservations(quickReservations);
			boatRepository.save(boat);
			
			response.setEndDate(quickReservation.getEndDate());
			response.setEntityName(quickReservation.getBoat().getName());
			response.setPrice(quickReservation.getPrice());
			response.setReservationType("Quick boat reservation");
			response.setStartDate(quickReservation.getStartDate());
		}
		if(dto.getReservationType().equals("Quick fishing lesson reservation")) {
			QuickFishingLessonReservation quickReservation  = quickFishingLessonReservationRepository.findById(dto.getReservationId()).get();
			if(quickReservation.getStartDate().minusDays(3).compareTo(LocalDateTime.now()) < 0) {
				return null;
			}
			quickReservation.setCanceled(true);
			quickFishingLessonReservationRepository.save(quickReservation);
			
			QuickFishingLessonReservation reservation = new QuickFishingLessonReservation();
			reservation.setAccepted(false);
			reservation.setAdditionalServices(quickReservation.getAdditionalServices());
			reservation.setCanceled(false);
			reservation.setFishingLesson(quickReservation.getFishingLesson());
			reservation.setEndDate(quickReservation.getEndDate());
			reservation.setMaxNumberOfPerson(quickReservation.getMaxNumberOfPerson());
			reservation.setPrice(quickReservation.getPrice());
			reservation.setReserved(false);
			reservation.setStartDate(quickReservation.getStartDate());
			reservation.setLocation(quickReservation.getLocation());
			
			QuickFishingLessonReservation savedReservation  = quickFishingLessonReservationRepository.save(reservation);
			FishingLesson fishingLesson= fishingLessonRepository.findById(quickReservation.getFishingLesson().getId()).get();			
			Set<QuickFishingLessonReservation> quickReservations = fishingLesson.getQuickReservations();
			quickReservations.add(savedReservation);
			
			fishingLesson.setQuickReservations(quickReservations);
			fishingLessonRepository.save(fishingLesson);
			
			response.setEndDate(quickReservation.getEndDate());
			response.setEntityName(quickReservation.getFishingLesson().getName());
			response.setPrice(quickReservation.getPrice());
			response.setReservationType("Quick fishing lesson reservation");
			response.setStartDate(quickReservation.getStartDate());
		}
		return response;
	}

}
