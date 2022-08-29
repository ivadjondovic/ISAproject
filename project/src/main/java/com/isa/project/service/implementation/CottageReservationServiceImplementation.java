package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ReservationDTO;
import com.isa.project.model.AdditionalCottageService;
import com.isa.project.model.AvailableCottagePeriod;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageReservation;
import com.isa.project.repository.AdditionalCottageServiceRepository;
import com.isa.project.repository.AvailableCottagePeriodRepository;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.CottageReservationRepository;
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
	
	@Override
	public CottageReservation createReservation(ReservationDTO dto) {
		
		CottageReservation cottageReservation = new CottageReservation();
		cottageReservation.setStartDate(dto.getStartDate());
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		cottageReservation.setEndDate(endDate);
		
		Cottage cottage = cottageRepository.findById(dto.getEntityId()).get();
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		
		cottageReservation.setClient(client);
		cottageReservation.setCottage(cottage);
		cottageReservation.setAccepted(false);
		
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

}
