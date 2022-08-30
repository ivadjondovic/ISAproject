package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.dto.AvailablePeriodDTO;
import com.isa.project.dto.CottageDTO;
import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.dto.ReservationSearchDTO;
import com.isa.project.dto.RoomDTO;
import com.isa.project.dto.RuleDTO;
import com.isa.project.dto.SortDTO;
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
import com.isa.project.repository.UserRepository;
import com.isa.project.service.CottageService;


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
		
		for(AdditionalServiceDTO serviceDto: dto.getAdditionalServices()) {		
			AdditionalCottageService service = new AdditionalCottageService();
			service.setDescription(serviceDto.getDescription());
			service.setPrice(serviceDto.getPrice());
			service.setCottage(savedCottage);
			AdditionalCottageService savedService = serviceRepository.save(service);
			additionalServices.add(savedService);
		}
		
		for(AvailablePeriodDTO periodDto: dto.getAvailablePeriods()) {
			AvailableCottagePeriod period = new AvailableCottagePeriod();
			period.setStartDate(periodDto.getStartDate());
			period.setEndDate(periodDto.getEndDate());
			period.setCottage(savedCottage);
			AvailableCottagePeriod savedPeriod = periodRepository.save(period);
			availablePeriods.add(savedPeriod);
		}
		
		for(QuickReservationDTO quickReservationDto: dto.getQuickReservations()) {
			QuickCottageReservation quickReservation = new QuickCottageReservation();
			quickReservation.setStartDate(quickReservationDto.getStartDate());
			quickReservation.setEndDate(quickReservationDto.getEndDate());
			quickReservation.setAdditionalServices(quickReservationDto.getAdditionalServices());
			quickReservation.setMaxNumberOfPerson(quickReservationDto.getMaxNumberOfPerson());
			quickReservation.setPrice(quickReservationDto.getPrice());
			quickReservation.setCottage(savedCottage);
			quickReservation.setReserved(false);
			quickReservation.setAccepted(false);
			quickReservation.setCanceled(false);
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


	@Override
	public List<Cottage> getAll() {	
		return cottageRepository.findAll();
	}


	@Override
	public Cottage getById(Long id) {
		Cottage cottage = cottageRepository.findById(id).get();
		Set<QuickCottageReservation> reservations = cottage.getQuickReservations();
		Set<QuickCottageReservation> filteredSet = reservations.stream()
				.filter(r -> (r.getReserved() == false && r.getAccepted() == false))
                .collect(Collectors.toSet());
		cottage.setQuickReservations(filteredSet);
		return cottage;
	}


	@Override
	public List<Cottage> search(String searchTerm) {
		List<Cottage> cottages = cottageRepository.findAll();
		List<Cottage> result = new ArrayList<>();
		List<Cottage> filtered = new ArrayList<>();
		for(Cottage cottage: cottages) {
			if(cottage.getAddress().toLowerCase().contains(searchTerm.toLowerCase())) {
				result.add(cottage);
			}
			if(cottage.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
				result.add(cottage);
			}
			
		}
		
		filtered = result.stream().distinct().collect( Collectors.toList() );
		return filtered;
	}


	@Override
	public List<Cottage> sort(SortDTO dto) {
		
		List<Cottage> cottages = cottageRepository.findAll();
		if(dto.getSortBy().equals("Name")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(cottages, (c1, c2) ->
			    (c1.getName().compareTo(c2.getName())));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(cottages, (c1, c2) ->
			    (c2.getName().compareTo(c1.getName())));
			}
		}
		
		if(dto.getSortBy().equals("Address")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(cottages, (c1, c2) ->
			    (c1.getAddress().compareTo(c2.getAddress())));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(cottages, (c1, c2) ->
			    (c2.getAddress().compareTo(c1.getAddress())));
			}
		}
		if(dto.getSortBy().equals("Price")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(cottages, (c1, c2) ->
			    Double.compare(c1.getPrice(), c2.getPrice()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(cottages, (c1, c2) ->
				 Double.compare(c2.getPrice(), c1.getPrice()));
			}
		}
		
		if(dto.getSortBy().equals("Number of rooms")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(cottages, (c1, c2) ->
			   Integer.compare(c1.getNumberOfRooms(), c2.getNumberOfRooms()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(cottages, (c1, c2) ->
				Integer.compare(c2.getNumberOfRooms(), c1.getNumberOfRooms()));
			}
		}
		return cottages;
	}


	@Override
	public List<Cottage> getAvailableCottages(ReservationSearchDTO dto) {
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		List<Cottage> cottages = cottageRepository.findAll();
		List<Cottage> result = new ArrayList<>();
		for(Cottage cottage: cottages) {
			int numberOfGuests = 0;
			Set<Room> rooms = cottage.getRooms();
			for(Room room: rooms) {
				numberOfGuests += room.getNumberOfBeds();
			}
			Set<AvailableCottagePeriod> periods = cottage.getAvailablePeriods();
			for(AvailableCottagePeriod period: periods) {
				if(dto.getStartDate().compareTo(period.getStartDate()) >=0 && endDate.compareTo(period.getEndDate()) <= 0 && numberOfGuests >= dto.getNumberOfGuests()) {
					result.add(cottage);
				}
			}
		}
		return result;
	}

	
}
