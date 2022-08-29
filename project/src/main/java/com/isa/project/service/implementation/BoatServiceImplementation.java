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
import com.isa.project.dto.BoatDTO;
import com.isa.project.dto.FishingEquipmentDTO;
import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.NavigationEquipmentDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.dto.ReservationSearchDTO;
import com.isa.project.dto.RuleDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.AdditionalBoatService;
import com.isa.project.model.AvailableBoatPeriod;
import com.isa.project.model.Boat;
import com.isa.project.model.BoatFishingEquipment;
import com.isa.project.model.BoatOwner;
import com.isa.project.model.Image;
import com.isa.project.model.NavigationEquipment;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.model.Rule;
import com.isa.project.model.User;
import com.isa.project.repository.AdditionalBoatServiceRepository;
import com.isa.project.repository.AvailableBoatPeriodRepository;
import com.isa.project.repository.BoatFishingEquipmentRepository;
import com.isa.project.repository.BoatRepository;
import com.isa.project.repository.NavigationEquipmentRepository;
import com.isa.project.repository.QuickBoatReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.BoatService;

@Service
public class BoatServiceImplementation implements BoatService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private NavigationEquipmentRepository navigationEquipmentRepository;
	
	@Autowired
	private AvailableBoatPeriodRepository availablePeriodRepository;
	
	@Autowired
	private QuickBoatReservationRepository quickReservationRepository;
	
	@Autowired
	private AdditionalBoatServiceRepository additionalServiceRepository;
	
	@Autowired
	private BoatFishingEquipmentRepository fishingEquipmentRepository;
	
	@Override
	public Boat createBoat(BoatDTO dto) {
		
		Boat boat = new Boat();
		boat.setName(dto.getName());
		boat.setBoatType(dto.getBoatType());
		boat.setBoatLength(dto.getBoatLength());
		boat.setEngines(dto.getEngines());
		boat.setEnginePower(dto.getEnginePower());
		boat.setMaxSpeed(dto.getMaxSpeed());
		boat.setAddress(dto.getAddress());
		boat.setDescription(dto.getDescription());
		boat.setCapacity(dto.getCapacity());
		boat.setPercentageForKeep(dto.getPercentageForKeep());
		boat.setPrice(dto.getPrice());
		Boat savedBoat = boatRepository.save(boat);
		
		Set<NavigationEquipment> navigationEquipments = new HashSet<>();
		Set<Rule> rules = new HashSet<>();
		Set<BoatFishingEquipment> fishingEquipments = new HashSet<>();
		Set<AvailableBoatPeriod> availablePeriods = new HashSet<>();
		Set<QuickBoatReservation> quickReservations = new HashSet<>();
		Set<AdditionalBoatService> additionalServices = new HashSet<>();
		Set<Image> images = new HashSet<>();
		
		for(NavigationEquipmentDTO navEquipmentDto: dto.getNavigationEquipment()) {		
			NavigationEquipment navigationEquipment = new NavigationEquipment();
			navigationEquipment.setDescription(navEquipmentDto.getDescription());
			navigationEquipment.setBoat(savedBoat);
			NavigationEquipment savedNavigationEquipment = navigationEquipmentRepository.save(navigationEquipment);
			navigationEquipments.add(savedNavigationEquipment);
		}
		
		for(RuleDTO ruleDto: dto.getRules()) {
			Rule rule = new Rule();
			rule.setDescription(ruleDto.getDescription());
			rules.add(rule);
		}
		
		for(AdditionalServiceDTO serviceDto: dto.getAdditionalServices()) {		
			AdditionalBoatService service = new AdditionalBoatService();
			service.setDescription(serviceDto.getDescription());
			service.setPrice(serviceDto.getPrice());
			service.setBoat(savedBoat);
			AdditionalBoatService savedService = additionalServiceRepository.save(service);
			additionalServices.add(savedService);
		}
		
		for(AvailablePeriodDTO periodDto: dto.getAvailablePeriods()) {
			AvailableBoatPeriod period = new AvailableBoatPeriod();
			period.setStartDate(periodDto.getStartDate());
			period.setEndDate(periodDto.getEndDate());
			period.setBoat(savedBoat);
			AvailableBoatPeriod savedPeriod = availablePeriodRepository.save(period);
			availablePeriods.add(savedPeriod);
		}
		
		for(QuickReservationDTO quickReservationDto: dto.getQuickReservations()) {
			QuickBoatReservation quickReservation = new QuickBoatReservation();
			quickReservation.setStartDate(quickReservationDto.getStartDate());
			quickReservation.setEndDate(quickReservationDto.getEndDate());
			quickReservation.setAdditionalServices(quickReservationDto.getAdditionalServices());
			quickReservation.setMaxNumberOfPerson(quickReservationDto.getMaxNumberOfPerson());
			quickReservation.setPrice(quickReservationDto.getPrice());
			quickReservation.setBoat(savedBoat);
			quickReservation.setReserved(false);
			QuickBoatReservation savedReservation = quickReservationRepository.save(quickReservation);
			quickReservations.add(savedReservation);
			
		}
		
		for(FishingEquipmentDTO fishingEquipmentDTO: dto.getFishingEquipment()) {
			BoatFishingEquipment fishingEquipment = new BoatFishingEquipment();
			fishingEquipment.setDescription(fishingEquipmentDTO.getDescription());
			fishingEquipment.setBoat(savedBoat);
			BoatFishingEquipment savedFishingEquipment = fishingEquipmentRepository.save(fishingEquipment);
			fishingEquipments.add(savedFishingEquipment);
		}
		
		for(ImageDTO imageDto: dto.getImages()) {
			Image image = new Image();
			image.setPath(imageDto.getPath());
			images.add(image);
		}
				
		savedBoat.setRules(rules);
		savedBoat.setNavigationEquipment(navigationEquipments);
		savedBoat.setAdditionalServices(additionalServices);
		savedBoat.setAvailablePeriods(availablePeriods);
		savedBoat.setQuickReservations(quickReservations);
		savedBoat.setImages(images);
		savedBoat.setFishingEquipment(fishingEquipments);
		
		Optional<User> owner = userRepository.findById(dto.getBoatOwnerId());
		savedBoat.setBoatOwner((BoatOwner) owner.get());
			
		return boatRepository.save(savedBoat);
	}

	@Override
	public List<Boat> getAll() {
		return boatRepository.findAll();
	}

	@Override
	public Boat getById(Long id) {
		return boatRepository.findById(id).get();
	}

	@Override
	public List<Boat> search(String searchTerm) {
		List<Boat> boats = boatRepository.findAll();
		List<Boat> result = new ArrayList<>();
		List<Boat> filtered = new ArrayList<>();
		for(Boat boat: boats) {
			if(boat.getAddress().toLowerCase().contains(searchTerm.toLowerCase())) {
				result.add(boat);
			}
			if(boat.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
				result.add(boat);
			}
			
			if(boat.getBoatType().toLowerCase().contains(searchTerm.toLowerCase())) {
				result.add(boat);
			}
			
		}
		
		filtered = result.stream().distinct().collect( Collectors.toList() );
		return filtered;
	}

	@Override
	public List<Boat> sort(SortDTO dto) {
		List<Boat> boats = boatRepository.findAll();
		if(dto.getSortBy().equals("Name")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			    (b1.getName().compareTo(b2.getName())));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
			    (b2.getName().compareTo(b1.getName())));
			}
		}
		
		if(dto.getSortBy().equals("Type")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			    (b1.getBoatType().compareTo(b2.getBoatType())));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				 (b2.getBoatType().compareTo(b1.getBoatType())));
			}
		}
		if(dto.getSortBy().equals("Price")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			    Double.compare(b1.getPrice(), b2.getPrice()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				 Double.compare(b2.getPrice(), b1.getPrice()));
			}
		}
		
		if(dto.getSortBy().equals("Length")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			    Double.compare(b1.getBoatLength(), b2.getBoatLength()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				 Double.compare(b2.getBoatLength(), b1.getBoatLength()));
			}
		}
		
		if(dto.getSortBy().equals("Number of engines")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			   Integer.compare(b1.getEngines(), b2.getEngines()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				Integer.compare(b2.getEngines(), b1.getEngines()));
			}
		}
		
		if(dto.getSortBy().equals("Engine power")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			    Double.compare(b1.getEnginePower(), b2.getEnginePower()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				 Double.compare(b2.getEnginePower(), b1.getEnginePower()));
			}
		}
		
		if(dto.getSortBy().equals("Max speed")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			    Double.compare(b1.getMaxSpeed(), b2.getMaxSpeed()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				 Double.compare(b2.getMaxSpeed(), b1.getMaxSpeed()));
			}
		}
		
		if(dto.getSortBy().equals("Capacity")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			   Integer.compare(b1.getCapacity(), b2.getCapacity()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				Integer.compare(b2.getCapacity(), b1.getCapacity()));
			}
		}
		
		if(dto.getSortBy().equals("Percentage for keep")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			    Double.compare(b1.getPercentageForKeep(), b2.getPercentageForKeep()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				 Double.compare(b2.getPercentageForKeep(), b1.getPercentageForKeep()));
			}
		}
		return boats;
	}

	@Override
	public List<Boat> getAvailableBoats(ReservationSearchDTO dto) {
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		List<Boat> boats = boatRepository.findAll();
		List<Boat> result = new ArrayList<>();
		for(Boat boat: boats) {
			Set<AvailableBoatPeriod> periods = boat.getAvailablePeriods();
			for(AvailableBoatPeriod period: periods) {
				if(dto.getStartDate().compareTo(period.getStartDate()) >=0 && endDate.compareTo(period.getEndDate()) <= 0 && boat.getCapacity() >= dto.getNumberOfGuests()) {
					result.add(boat);
				}
			}
		}
		return result;
	}

}
