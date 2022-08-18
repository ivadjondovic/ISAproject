package com.isa.project.service.implementation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.AdditionalCottageServiceDTO;
import com.isa.project.dto.AvailableCottagePeriodDTO;
import com.isa.project.dto.CottageDTO;
import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.QuickCottageReservationDTO;
import com.isa.project.dto.RoomDTO;
import com.isa.project.dto.RuleDTO;
import com.isa.project.model.AdditionalCottageService;
import com.isa.project.model.AvailableCottagePeriod;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.Image;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.model.Room;
import com.isa.project.model.Rule;
import com.isa.project.model.User;
import com.isa.project.repository.AdditionalCottageServiceRepository;
import com.isa.project.repository.AvailableCottagePeriodRepository;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.QuickCottageReservationRepository;
import com.isa.project.repository.RoomRepository;
import com.isa.project.repository.RuleRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.CottageService;

import ch.qos.logback.classic.util.LevelToSyslogSeverity;

@Service
public class CottageServiceImplementation implements CottageService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CottageRepository cottageRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private AdditionalCottageServiceRepository serviceRepository;
	
	@Autowired
	private AvailableCottagePeriodRepository periodRepository;
	
	@Autowired
	private QuickCottageReservationRepository quickReservationRepository;
	
	
	@Override
	public Cottage createCottage(CottageDTO dto) {
		
		Cottage cottage = new Cottage();
		cottage.setName(dto.getName());
		cottage.setAddress(dto.getAddress());
		cottage.setDescription(dto.getDescription());
		cottage.setNumberOfRooms(dto.getNumberOfRooms());
		cottage.setPrice(dto.getPrice());
		Cottage savedCottage = cottageRepository.save(cottage);
		Set<Room> rooms = new HashSet<>();
		Set<Rule> rules = new HashSet<>();
		Set<AdditionalCottageService> additionalServices = new HashSet<>();
		Set<AvailableCottagePeriod> availablePeriods = new HashSet<>();
		Set<QuickCottageReservation> quickReservations = new HashSet<>();
		Set<Image> images = new HashSet<>();
		for(RoomDTO roomDto: dto.getRooms()) {		
			Room room = new Room();
			room.setCottage(savedCottage);
			room.setNumberOfBeds(roomDto.getNumberOfBeds());
			Room savedRoom = roomRepository.save(room);
			rooms.add(savedRoom);
		}
		
		for(RuleDTO ruleDto: dto.getRules()) {
			Rule rule = new Rule();
			rule.setDescription(ruleDto.getDescription());
			rules.add(rule);
		}
		
		for(AdditionalCottageServiceDTO serviceDto: dto.getAdditionalServices()) {		
			AdditionalCottageService service = new AdditionalCottageService();
			service.setDescription(serviceDto.getDescription());
			service.setPrice(serviceDto.getPrice());
			service.setCottage(savedCottage);
			AdditionalCottageService savedService = serviceRepository.save(service);
			additionalServices.add(savedService);
		}
		
		for(AvailableCottagePeriodDTO periodDto: dto.getAvailablePeriods()) {
			AvailableCottagePeriod period = new AvailableCottagePeriod();
			period.setStartDate(periodDto.getStartDate());
			period.setEndDate(periodDto.getEndDate());
			period.setCottage(savedCottage);
			AvailableCottagePeriod savedPeriod = periodRepository.save(period);
			availablePeriods.add(savedPeriod);
		}
		
		for(QuickCottageReservationDTO quickReservationDto: dto.getQuickReservations()) {
			QuickCottageReservation quickReservation = new QuickCottageReservation();
			quickReservation.setStartDate(quickReservationDto.getStartDate());
			quickReservation.setEndDate(quickReservationDto.getEndDate());
			quickReservation.setAdditionalServices(quickReservationDto.getAdditionalServices());
			quickReservation.setMaxNumberOfPerson(quickReservationDto.getMaxNumberOfPerson());
			quickReservation.setPrice(quickReservationDto.getPrice());
			quickReservation.setCottage(savedCottage);
			QuickCottageReservation savedReservation = quickReservationRepository.save(quickReservation);
			quickReservations.add(savedReservation);
			
		}
		
		for(ImageDTO imageDto: dto.getImages()) {
			Image image = new Image();
			image.setPath(imageDto.getPath());
			images.add(image);
		}
				
		savedCottage.setRules(rules);
		savedCottage.setRooms(rooms);
		savedCottage.setAdditionalServices(additionalServices);
		savedCottage.setAvailablePeriods(availablePeriods);
		savedCottage.setQuickReservations(quickReservations);
		savedCottage.setImages(images);
		
		Optional<User> owner = userRepository.findById(dto.getCottageOwnerId());
		savedCottage.setCottageOwner((CottageOwner) owner.get());
		
		
		return cottageRepository.save(savedCottage);
	}

	
}
