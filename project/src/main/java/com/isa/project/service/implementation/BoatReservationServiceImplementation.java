package com.isa.project.service.implementation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

import com.isa.project.dto.BoatReservationResponseDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.AdditionalBoatService;
import com.isa.project.model.AvailableBoatPeriod;
import com.isa.project.model.Boat;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.repository.AdditionalBoatServiceRepository;
import com.isa.project.repository.BoatRepository;
import com.isa.project.repository.BoatReservationRepository;
import com.isa.project.repository.QuickBoatReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.BoatReservationService;
import com.isa.project.service.EmailService;

@Service
public class BoatReservationServiceImplementation implements BoatReservationService{

	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoatReservationRepository boatReservationRepository;
	
	@Autowired
	private AdditionalBoatServiceRepository additionalBoatServiceRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private QuickBoatReservationRepository quickBoatReservationRepository;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public BoatReservation createReservation(ReservationDTO dto) throws Exception{
		
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		List<BoatReservation> clientReservations = boatReservationRepository.findByClient(client);
		
		for(BoatReservation r: clientReservations) {
			if(r.getStartDate().compareTo(dto.getStartDate()) == 0  && r.getEndDate().compareTo(endDate) == 0 && r.getBoat().getId() == dto.getEntityId()) {
				return null;
			}
		}
		
		
		
		BoatReservation boatReservation = new BoatReservation();
		boatReservation.setStartDate(dto.getStartDate());
		boatReservation.setEndDate(endDate);
		
		Boat boat = boatRepository.findLockById(dto.getEntityId());

		List<BoatReservation> reservations = boatReservationRepository.findByBoatAndCanceled(boat, false);
		Set<AvailableBoatPeriod> periods = boat.getAvailablePeriods();
		
		
		for(BoatReservation r: reservations) {
			if((dto.getStartDate().compareTo(r.getStartDate()) >= 0 && endDate.compareTo(r.getEndDate()) <= 0) 
					|| (dto.getStartDate().compareTo(r.getStartDate()) < 0 && endDate.compareTo(r.getEndDate()) <= 0 && endDate.compareTo(r.getStartDate()) > 0)
					|| (dto.getStartDate().compareTo(r.getStartDate()) >= 0 && endDate.compareTo(r.getEndDate()) > 0 && dto.getStartDate().compareTo(r.getEndDate()) < 0)
					|| (dto.getStartDate().compareTo(r.getStartDate()) < 0 && endDate.compareTo(r.getEndDate()) > 0)) {
				return null;
			}else {
				continue;
				
			}
		}
		
		for(AvailableBoatPeriod period: periods) {
			if(!(dto.getStartDate().compareTo(period.getStartDate()) >= 0 && endDate.compareTo(period.getEndDate()) <=0)) {
					return null;
			}
		}
			
		boatReservation.setClient(client);
		boatReservation.setBoat(boat);
		boatReservation.setAccepted(false);
		boatReservation.setCanceled(false);
		boatReservation.setCalculated(false);
		
		BoatReservation savedReservation = boatReservationRepository.save(boatReservation);
		
		Set<AdditionalBoatService> additionalServices = new HashSet<>();
		Double price = boat.getPrice() * dto.getNumberOfDays();
		for(Long id: dto.getAdditionalServices()) {
			AdditionalBoatService service = additionalBoatServiceRepository.findById(id).get();
			price += service.getPrice();
			service.setBoatReservation(savedReservation);
			AdditionalBoatService savedService = additionalBoatServiceRepository.save(service);
			additionalServices.add(savedService);
			
		}
		
		savedReservation.setPrice(price);
		savedReservation.setAdditionalServices(additionalServices);
		
		Boat savedBoat = boatRepository.save(boat);
		savedReservation.setBoat(savedBoat);
		
		try {
			emailService.sendBoatReservationMail(client, savedReservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			return boatReservationRepository.save(savedReservation);
		}catch(PessimisticLockingFailureException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Try again later!");
		}
		
		
		
	}

	@Override
	public BoatReservation accept(Long id) {
		
		BoatReservation reservation = boatReservationRepository.findById(id).get();
		
		if(reservation == null) {
			return null;
		}
		reservation.setAccepted(true);
		return boatReservationRepository.save(reservation);
	}

	@Override
	public List<BoatReservationResponseDTO> getByClientId(Long clientId) {
		
		Client client = (Client) userRepository.findById(clientId).get();
		List<BoatReservationResponseDTO> result = new ArrayList<>();
		List<BoatReservation> boatReservations = boatReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		for(BoatReservation br: boatReservations) {
			Boat boat = boatRepository.findById(br.getBoat().getId()).get();
			BoatReservationResponseDTO boatReservation = new BoatReservationResponseDTO();
			boatReservation.setAccepted(br.getAccepted());
			boatReservation.setAdditionalServices(br.getAdditionalServices());
			boatReservation.setClient(br.getClient());
			boatReservation.setBoat(boat);
			boatReservation.setEndDate(br.getEndDate());
			boatReservation.setId(br.getId());
			boatReservation.setPrice(br.getPrice());
			boatReservation.setStartDate(br.getStartDate());
			boatReservation.setReservationType("Boat reservation");
			if(br.getEndDate().compareTo(LocalDateTime.now()) <= 0) {
				boatReservation.setPossibleToRate(true);
			}else {
				boatReservation.setPossibleToRate(false);
			}
			
			
			result.add(boatReservation);
		}
		
		List<QuickBoatReservation> quickReservations = quickBoatReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		for(QuickBoatReservation quickR: quickReservations) {
			
			Boat boat = boatRepository.findById(quickR.getBoat().getId()).get();
			BoatReservationResponseDTO boatReservation = new BoatReservationResponseDTO();
			boatReservation.setAccepted(quickR.getAccepted());
			boatReservation.setClient(quickR.getClient());
			boatReservation.setBoat(boat);
			boatReservation.setEndDate(quickR.getEndDate());
			boatReservation.setId(quickR.getId());
			boatReservation.setPrice(quickR.getPrice());
			boatReservation.setStartDate(quickR.getStartDate());
			boatReservation.setReservationType("Quick boat reservation");
			if(quickR.getEndDate().compareTo(LocalDateTime.now()) <= 0) {
				boatReservation.setPossibleToRate(true);
			}else {
				boatReservation.setPossibleToRate(false);
			}
			result.add(boatReservation);
			
		}
		return result;
	}

	@Override
	public List<BoatReservationResponseDTO> sort(SortDTO dto) {
		List<BoatReservationResponseDTO> reservations = getByClientId(dto.getClientId());
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

}
