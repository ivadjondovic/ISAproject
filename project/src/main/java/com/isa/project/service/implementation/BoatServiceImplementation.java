package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.isa.project.dto.DateSearchDTO;
import com.isa.project.dto.FishingEquipmentDTO;
import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.NavigationEquipmentDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.dto.ReservationSearchDTO;
import com.isa.project.dto.RuleDTO;
import com.isa.project.dto.SearchParamsDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.AdditionalBoatService;
import com.isa.project.model.AvailableBoatPeriod;
import com.isa.project.model.Boat;
import com.isa.project.model.BoatFishingEquipment;
import com.isa.project.model.BoatOwner;
import com.isa.project.model.BoatSubscription;
import com.isa.project.model.Client;
import com.isa.project.model.Image;
import com.isa.project.model.NavigationEquipment;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.model.Rule;
import com.isa.project.model.User;
import com.isa.project.repository.AdditionalBoatServiceRepository;
import com.isa.project.repository.AvailableBoatPeriodRepository;
import com.isa.project.repository.BoatFishingEquipmentRepository;
import com.isa.project.repository.BoatRepository;
import com.isa.project.repository.BoatSubscriptionRepository;
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
	
	@Autowired
	private BoatSubscriptionRepository boatSubscriptionRepository;
	
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
		boat.setRating(0.0);
		boat.setDeleted(false);
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
			quickReservation.setAccepted(false);
			quickReservation.setCanceled(false);
			quickReservation.setDiscount(quickReservationDto.getDiscount());
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
		return boatRepository.findByDeleted(false);
	}

	@Override
	public Boat getById(Long id) {
		Boat boat = boatRepository.findById(id).get();
		Set<QuickBoatReservation> reservations = boat.getQuickReservations();
		Set<AvailableBoatPeriod> periods = boat.getAvailablePeriods();
		Set<QuickBoatReservation> filteredSet = reservations.stream()
				.filter(r -> (r.getReserved() == false && r.getAccepted() == false && r.getStartDate().compareTo(LocalDateTime.now()) >= 0))
                .collect(Collectors.toSet());
		
		Set<AvailableBoatPeriod> filteredPeriods = periods.stream()
				.filter(p -> p.getStartDate().compareTo(LocalDateTime.now()) >= 0)
                .collect(Collectors.toSet());
		boat.setQuickReservations(filteredSet);
		boat.setAvailablePeriods(filteredPeriods);
		return boat;
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
		
		if(dto.getSortBy().equals("") || dto.getSortType().equals("")) {
			return null;
		}
		List<Boat> boats = boatRepository.findByDeleted(false);
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
		
		if(dto.getSortBy().equals("Rating")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(boats, (b1, b2) ->
			    Double.compare(b1.getRating(), b2.getRating()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(boats, (b1, b2) ->
				 Double.compare(b2.getRating(), b1.getRating()));
			}
		}
		return boats;
	}

	@Override
	public List<Boat> getAvailableBoats(ReservationSearchDTO dto) {
		if(dto.getStartDate().compareTo(LocalDateTime.now()) < 0) {
			return null;
		}
		
		if(dto.getNumberOfDays() == 0 || dto.getNumberOfGuests() == 0 || dto.getStartDate().equals("")) {
			return null;
		}
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		List<Boat> boats = boatRepository.findByDeleted(false);
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

	@Override
	public List<Boat> boatsAvailableForCertainDate(DateSearchDTO dto) {
		List<Boat> boats = boatRepository.findByDeleted(false);
		List<Boat> result = new ArrayList<>();
		for(Boat b: boats) {
			Set<AvailableBoatPeriod> periods = b.getAvailablePeriods();
			for(AvailableBoatPeriod period: periods) {
				if(period.getStartDate().compareTo(dto.getDate()) <= 0 && period.getEndDate().compareTo(dto.getDate()) > 0) {
					result.add(b);
				}
			}
			
		}
		return result;
	}

	@Override
	public List<Boat> getBoatsByClientSubscription(Long clientId) {
		List<Boat> boats = new ArrayList<>();
		Client client = (Client) userRepository.findById(clientId).get();
		List<BoatSubscription> clientSubscriptions = boatSubscriptionRepository.findByClient(client);
		
		for(BoatSubscription subscription: clientSubscriptions) {
			if(subscription.getBoat().getDeleted() == false) {
				boats.add(subscription.getBoat());
			}
		}
		return boats;
	}

	@Override
	public List<Boat> sortAvailableBoats(ReservationSearchDTO dto) {
		if(dto.getNumberOfDays() == 0 || dto.getNumberOfGuests() == 0 || dto.getStartDate().equals("") || dto.getSortBy().equals("") || dto.getSortType().equals("")) {
			return null;
		}
		List<Boat> boats = getAvailableBoats(dto);
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
		
		return boats;
	}

	@Override
	public List<Boat> searchByMoreParams(SearchParamsDTO dto) {
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;


		String formattedDateTime = dto.getDate().format(formatter);
		if(formattedDateTime.equals("")) {
			return null;
		}
		List<Boat> filteredByDate = new ArrayList<>();
		List<Boat> boats = boatRepository.findByDeleted(false);
		for(Boat b: boats) {
			Set<AvailableBoatPeriod> periods = b.getAvailablePeriods();
			for(AvailableBoatPeriod period: periods) {
				if(period.getStartDate().compareTo(dto.getDate()) <= 0 && period.getEndDate().compareTo(dto.getDate()) > 0) {
					filteredByDate.add(b);
				}
			}
			
		}
		List<Boat> filteredByLocation = new ArrayList<>();
		for(Boat b: filteredByDate) {
			filteredByLocation.add(b);
		}
		
		if(!dto.getLocation().equals("")) {
			filteredByLocation = filteredByDate.stream()
					.filter(b -> b.getAddress().contains(dto.getLocation()))
	                .collect(Collectors.toList());
		}
		
		
		List<Boat> filteredByRating = new ArrayList<>();
		for(Boat b: filteredByLocation) {
			filteredByRating.add(b);
		}
		if(dto.getRating() != 0) {
			Double rating = (double) dto.getRating();
			filteredByRating = filteredByLocation.stream()
					.filter(b -> b.getRating() <= rating && b.getRating() > rating-1)
					.collect(Collectors.toList());
			
		}
		
		List<Boat> filteredByPrice = new ArrayList<>();
		for(Boat b: filteredByRating) {
			filteredByPrice.add(b);
		}
		if(dto.getPriceFrom() != null && dto.getPriceTo() != null) {
			filteredByPrice = filteredByRating.stream()
					.filter(b -> b.getPrice() >= dto.getPriceFrom() && b.getPrice() <= dto.getPriceTo())
					.collect(Collectors.toList());
		}
		
		List<Boat> filteredByPeople = new ArrayList<>();
		for(Boat b: filteredByPrice) {
			filteredByPeople.add(b);
		}
		if(dto.getPeopleFrom() != 0 && dto.getPeopleTo() != 0) {
			filteredByPeople = filteredByPrice.stream()
					.filter(b -> b.getCapacity() >= dto.getPeopleFrom() && b.getCapacity() <= dto.getPeopleTo())
					.collect(Collectors.toList());
					
		}
		
		return filteredByPeople;
	
	}

	@Override
	public void delete(Long id) {
		Boat boat = boatRepository.findById(id).get();
		boat.setDeleted(true);
		boatRepository.save(boat);
		
	}

}
