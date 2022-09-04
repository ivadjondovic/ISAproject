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
import org.springframework.stereotype.Service;

import com.isa.project.dto.CottageReservationResponseDTO;
import com.isa.project.dto.ReservationDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.AdditionalCottageService;
import com.isa.project.model.AvailableCottagePeriod;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageReservation;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.repository.AdditionalCottageServiceRepository;
import com.isa.project.repository.AvailableCottagePeriodRepository;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.CottageReservationRepository;
import com.isa.project.repository.QuickCottageReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.CottageReservationService;
import com.isa.project.service.EmailService;

@Service
public class CottageReservationServiceImplementation implements CottageReservationService{

	@Autowired
	private CottageReservationRepository cottageReservationRepository;
	
	@Autowired
	private CottageRepository cottageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdditionalCottageServiceRepository additionalCottageServiceRepository;
	
	@Autowired
	private AvailableCottagePeriodRepository periodRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private QuickCottageReservationRepository quickCottageReservationRepository;
	
	@Override
	public CottageReservation createReservation(ReservationDTO dto) {
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		List<CottageReservation> clientReservations = cottageReservationRepository.findByClient(client);
		
		for(CottageReservation r: clientReservations) {
			if(r.getStartDate().compareTo(dto.getStartDate()) == 0  && r.getEndDate().compareTo(endDate) == 0 && r.getCottage().getId() == dto.getEntityId()) {
				return null;
			}
		}
		
		CottageReservation cottageReservation = new CottageReservation();
		cottageReservation.setStartDate(dto.getStartDate());
		
		cottageReservation.setEndDate(endDate);
		
		Cottage cottage = cottageRepository.findById(dto.getEntityId()).get();
		
		
		cottageReservation.setClient(client);
		cottageReservation.setCottage(cottage);
		cottageReservation.setAccepted(false);
		cottageReservation.setCanceled(false);
		cottageReservation.setCalculated(false);
		
		CottageReservation savedReservation = cottageReservationRepository.save(cottageReservation);
		
		Set<AdditionalCottageService> additionalServices = new HashSet<>();
		Double price = cottage.getPrice() * dto.getNumberOfDays();
		for(Long id: dto.getAdditionalServices()) {
			AdditionalCottageService service = additionalCottageServiceRepository.findById(id).get();
			price += service.getPrice();
			service.setCottageReservation(savedReservation);
			AdditionalCottageService savedService = additionalCottageServiceRepository.save(service);
			additionalServices.add(savedService);
			
		}
		
		savedReservation.setPrice(price);
		savedReservation.setAdditionalServices(additionalServices);
		
		Set<AvailableCottagePeriod> newPeriods = new HashSet<>();
		Set<AvailableCottagePeriod> periods = cottage.getAvailablePeriods();
		for(AvailableCottagePeriod period: periods) {
			if(period.getStartDate().compareTo(dto.getStartDate()) < 0 && period.getEndDate().compareTo(endDate) > 0) {
				AvailableCottagePeriod availablePeriodFirst = new AvailableCottagePeriod();
				availablePeriodFirst.setStartDate(period.getStartDate());
				availablePeriodFirst.setEndDate(dto.getStartDate());
				availablePeriodFirst.setCottage(cottage);
				AvailableCottagePeriod savedFirst = periodRepository.save(availablePeriodFirst);
				newPeriods.add(savedFirst);
				
				AvailableCottagePeriod availablePeriodSecond = new AvailableCottagePeriod();
				availablePeriodSecond.setStartDate(endDate);
				availablePeriodSecond.setEndDate(period.getEndDate());
				availablePeriodSecond.setCottage(cottage);
				AvailableCottagePeriod savedSecond = periodRepository.save(availablePeriodSecond);
				newPeriods.add(savedSecond);
				
				periodRepository.delete(period);
				
			}
			if(period.getStartDate().compareTo(dto.getStartDate()) == 0 && period.getEndDate().compareTo(endDate) > 0) {
				AvailableCottagePeriod availablePeriod = new AvailableCottagePeriod();
				availablePeriod.setStartDate(endDate);
				availablePeriod.setEndDate(period.getEndDate());
				availablePeriod.setCottage(cottage);
				AvailableCottagePeriod savedFirst = periodRepository.save(availablePeriod);
				newPeriods.add(savedFirst);
				
				periodRepository.delete(period);
			}
			if(period.getStartDate().compareTo(dto.getStartDate()) < 0 && period.getEndDate().compareTo(endDate) == 0) {
				AvailableCottagePeriod availablePeriod = new AvailableCottagePeriod();
				availablePeriod.setStartDate(period.getStartDate());
				availablePeriod.setEndDate(dto.getStartDate());
				availablePeriod.setCottage(cottage);
				AvailableCottagePeriod savedFirst = periodRepository.save(availablePeriod);
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
		
		cottage.setAvailablePeriods(newPeriods);
		Cottage savedCottage = cottageRepository.save(cottage);
		savedReservation.setCottage(savedCottage);
		try {
			emailService.sendCottageReservationMail(client, savedReservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cottageReservationRepository.save(savedReservation);
	}

	@Override
	public CottageReservation accept(Long id) {
		CottageReservation reservation = cottageReservationRepository.findById(id).get();
		
		if(reservation == null) {
			return null;
		}
		reservation.setAccepted(true);
		return cottageReservationRepository.save(reservation);
		
	}

	@Override
	public List<CottageReservationResponseDTO> getByClientId(Long clientId) {
		
		Client client = (Client) userRepository.findById(clientId).get();
		List<CottageReservationResponseDTO> result = new ArrayList<>();
		List<CottageReservation> cottageReservations = cottageReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		for(CottageReservation cr: cottageReservations) {
			Cottage cottage = cottageRepository.findById(cr.getCottage().getId()).get();
			CottageReservationResponseDTO cottageReservation = new CottageReservationResponseDTO();
			cottageReservation.setAccepted(cr.getAccepted());
			cottageReservation.setAdditionalServices(cr.getAdditionalServices());
			cottageReservation.setClient(cr.getClient());
			cottageReservation.setCottage(cottage);
			cottageReservation.setEndDate(cr.getEndDate());
			cottageReservation.setId(cr.getId());
			cottageReservation.setPrice(cr.getPrice());
			cottageReservation.setStartDate(cr.getStartDate());
			cottageReservation.setReservationType("Cottage reservation");
			if(cr.getEndDate().compareTo(LocalDateTime.now()) <= 0) {
				cottageReservation.setPossibleToRate(true);
			}else {
				cottageReservation.setPossibleToRate(false);
			}
			
			result.add(cottageReservation);
		}
		
		List<QuickCottageReservation> quickReservations = quickCottageReservationRepository.findByClientAndAcceptedAndCanceled(client, true, false);
		for(QuickCottageReservation quickR: quickReservations) {
			
			Cottage cottage = cottageRepository.findById(quickR.getCottage().getId()).get();
			CottageReservationResponseDTO cottageReservation = new CottageReservationResponseDTO();
			cottageReservation.setAccepted(quickR.getAccepted());
			cottageReservation.setClient(quickR.getClient());
			cottageReservation.setCottage(cottage);
			cottageReservation.setEndDate(quickR.getEndDate());
			cottageReservation.setId(quickR.getId());
			cottageReservation.setPrice(quickR.getPrice());
			cottageReservation.setStartDate(quickR.getStartDate());
			cottageReservation.setReservationType("Quick cottage reservation");
			if(quickR.getEndDate().compareTo(LocalDateTime.now()) <= 0) {
				cottageReservation.setPossibleToRate(true);
			}else {
				cottageReservation.setPossibleToRate(false);
			}
			result.add(cottageReservation);
			
		}
		
		return result;
	}

	@Override
	public List<CottageReservationResponseDTO> sort(SortDTO dto) {
		List<CottageReservationResponseDTO> reservations = getByClientId(dto.getClientId());
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
