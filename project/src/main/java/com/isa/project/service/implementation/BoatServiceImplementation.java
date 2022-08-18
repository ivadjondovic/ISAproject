package com.isa.project.service.implementation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.dto.AvailablePeriodDTO;
import com.isa.project.dto.BoatDTO;
import com.isa.project.dto.BoatFishingEquipmentDTO;
import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.NavigationEquipmentDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.dto.RuleDTO;
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
			QuickBoatReservation savedReservation = quickReservationRepository.save(quickReservation);
			quickReservations.add(savedReservation);
			
		}
		
		for(BoatFishingEquipmentDTO fishingEquipmentDTO: dto.getFishingEquipment()) {
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

}
