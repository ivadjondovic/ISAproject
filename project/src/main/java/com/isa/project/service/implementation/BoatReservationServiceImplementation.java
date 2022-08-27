package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.AdditionalBoatService;
import com.isa.project.model.AdditionalCottageService;
import com.isa.project.model.AvailableBoatPeriod;
import com.isa.project.model.AvailableCottagePeriod;
import com.isa.project.model.Boat;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageReservation;
import com.isa.project.repository.AdditionalBoatServiceRepository;
import com.isa.project.repository.AvailableBoatPeriodRepository;
import com.isa.project.repository.BoatRepository;
import com.isa.project.repository.BoatReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.BoatReservationService;

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
	private AvailableBoatPeriodRepository periodRepository;
	
	@Override
	public BoatReservation createReservation(ReservationDTO dto) {
		
		BoatReservation boatReservation = new BoatReservation();
		boatReservation.setStartDate(dto.getStartDate());
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		boatReservation.setEndDate(endDate);
		
		Boat boat = boatRepository.findById(dto.getEntityId()).get();
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		
		boatReservation.setClient(client);
		boatReservation.setBoat(boat);
		
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
		
		Set<AvailableBoatPeriod> newPeriods = new HashSet<>();
		Set<AvailableBoatPeriod> periods = boat.getAvailablePeriods();
		for(AvailableBoatPeriod period: periods) {
			if(period.getStartDate().compareTo(dto.getStartDate()) < 0 && period.getEndDate().compareTo(endDate) > 0) {
				AvailableBoatPeriod availablePeriodFirst = new AvailableBoatPeriod();
				availablePeriodFirst.setStartDate(period.getStartDate());
				availablePeriodFirst.setEndDate(dto.getStartDate());
				availablePeriodFirst.setBoat(boat);
				AvailableBoatPeriod savedFirst = periodRepository.save(availablePeriodFirst);
				newPeriods.add(savedFirst);
				
				AvailableBoatPeriod availablePeriodSecond = new AvailableBoatPeriod();
				availablePeriodSecond.setStartDate(endDate);
				availablePeriodSecond.setEndDate(period.getEndDate());
				availablePeriodSecond.setBoat(boat);
				AvailableBoatPeriod savedSecond = periodRepository.save(availablePeriodSecond);
				newPeriods.add(savedSecond);
				
				periodRepository.delete(period);
				
			}
			if(period.getStartDate().compareTo(dto.getStartDate()) == 0 && period.getEndDate().compareTo(endDate) > 0) {
				AvailableBoatPeriod availablePeriod = new AvailableBoatPeriod();
				availablePeriod.setStartDate(endDate);
				availablePeriod.setEndDate(period.getEndDate());
				availablePeriod.setBoat(boat);
				AvailableBoatPeriod savedFirst = periodRepository.save(availablePeriod);
				newPeriods.add(savedFirst);
				
				periodRepository.delete(period);
			}
			if(period.getStartDate().compareTo(dto.getStartDate()) < 0 && period.getEndDate().compareTo(endDate) == 0) {
				AvailableBoatPeriod availablePeriod = new AvailableBoatPeriod();
				availablePeriod.setStartDate(period.getStartDate());
				availablePeriod.setEndDate(dto.getStartDate());
				availablePeriod.setBoat(boat);
				AvailableBoatPeriod savedFirst = periodRepository.save(availablePeriod);
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
		
		boat.setAvailablePeriods(newPeriods);
		Boat savedBoat = boatRepository.save(boat);
		savedReservation.setBoat(savedBoat);
		
		return boatReservationRepository.save(savedReservation);
	}

}
