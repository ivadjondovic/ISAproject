package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ReservationResponseDTO;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;
import com.isa.project.model.CottageReservation;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.BoatReservationRepository;
import com.isa.project.repository.CottageReservationRepository;
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
	
	
	@Override
	public List<ReservationResponseDTO> getNotPassedReservations(Long clientId) {
		
		Client client = (Client) userRepository.findById(clientId).get();
		List<ReservationResponseDTO> result = new ArrayList<>();
		List<CottageReservation> cottageReservations = cottageReservationRepository.findByClientAndAccepted(client, true);
		List<BoatReservation> boatReservations = boatReservationRepository.findByClientAndAccepted(client, true);
		List<FishingLessonReservation> fishingLessonReservations = fishingLessonReservationRepository.findByClientAndAccepted(client, true);
		List<QuickCottageReservation> quickCottageReservations = quickCottageReservationRepository.findByClientAndAccepted(client, true);
		List<QuickBoatReservation> quickBoatReservations = quickBoatReservationRepository.findByClientAndAccepted(client, true);
		List<QuickFishingLessonReservation> quickFishingLessonReservations = quickFishingLessonReservationRepository.findByClientAndAccepted(client, true);
		
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
			result.add(reservation);
		}
		
		return result;
	}

}
