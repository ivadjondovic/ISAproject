package com.isa.project.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.dto.AvailablePeriodDTO;
import com.isa.project.dto.FishingEquipmentDTO;
import com.isa.project.dto.FishingLessonDTO;
import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.dto.RuleDTO;
import com.isa.project.model.AdditionalFishingLessonService;
import com.isa.project.model.AvailableFishingLessonPeriod;
import com.isa.project.model.FishingEquipment;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.Image;
import com.isa.project.model.Instructor;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.model.Rule;
import com.isa.project.model.User;
import com.isa.project.repository.AdditionalFishingLessonServiceRepository;
import com.isa.project.repository.AvailableFishingLessonPeriodRepository;
import com.isa.project.repository.FishingEquipmentRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.FishingLessonService;

@Service
public class FishingLessonServiceImplementation implements FishingLessonService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Autowired
	private AdditionalFishingLessonServiceRepository additionalServiceRepository;
	
	@Autowired
	private AvailableFishingLessonPeriodRepository availablePeriodRepository;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickReservationRepository;
	
	@Autowired
	private FishingEquipmentRepository fishingEquipmentRepository;
	
	@Override
	public FishingLesson createFishingLesson(FishingLessonDTO dto) {

		FishingLesson fishingLesson = new FishingLesson();
		fishingLesson.setName(dto.getName());
		fishingLesson.setFishingInstructorBio(dto.getFishingInstructorBio());
		fishingLesson.setNumberOfPeople(dto.getNumberOfPeople());
		fishingLesson.setAddress(dto.getAddress());
		fishingLesson.setDescription(dto.getDescription());;
		fishingLesson.setPercentageForKeep(dto.getPercentageForKeep());
		fishingLesson.setPrice(dto.getPrice());
		FishingLesson savedFishingLesson = fishingLessonRepository.save(fishingLesson);
		
		Set<FishingEquipment> fishingEquipments = new HashSet<>();
		Set<Rule> rules = new HashSet<>();
		Set<AvailableFishingLessonPeriod> availablePeriods = new HashSet<>();
		Set<QuickFishingLessonReservation> quickReservations = new HashSet<>();
		Set<AdditionalFishingLessonService> additionalServices = new HashSet<>();
		Set<Image> images = new HashSet<>();
		
		for(RuleDTO ruleDto: dto.getRules()) {
			Rule rule = new Rule();
			rule.setDescription(ruleDto.getDescription());
			rules.add(rule);
		}
		
		for(AdditionalServiceDTO serviceDto: dto.getAdditionalServices()) {		
			AdditionalFishingLessonService service = new AdditionalFishingLessonService();
			service.setDescription(serviceDto.getDescription());
			service.setPrice(serviceDto.getPrice());
			service.setFishingLesson(savedFishingLesson);
			AdditionalFishingLessonService savedService = additionalServiceRepository.save(service);
			additionalServices.add(savedService);
		}
		
		for(AvailablePeriodDTO periodDto: dto.getAvailablePeriods()) {
			AvailableFishingLessonPeriod period = new AvailableFishingLessonPeriod();
			period.setStartDate(periodDto.getStartDate());
			period.setEndDate(periodDto.getEndDate());
			period.setFishingLesson(savedFishingLesson);
			AvailableFishingLessonPeriod savedPeriod = availablePeriodRepository.save(period);
			availablePeriods.add(savedPeriod);
		}
		
		for(QuickReservationDTO quickReservationDto: dto.getQuickReservations()) {
			QuickFishingLessonReservation quickReservation = new QuickFishingLessonReservation();
			quickReservation.setStartDate(quickReservationDto.getStartDate());
			quickReservation.setEndDate(quickReservationDto.getEndDate());
			quickReservation.setAdditionalServices(quickReservationDto.getAdditionalServices());
			quickReservation.setMaxNumberOfPerson(quickReservationDto.getMaxNumberOfPerson());
			quickReservation.setPrice(quickReservationDto.getPrice());
			quickReservation.setFishingLesson(savedFishingLesson);
			QuickFishingLessonReservation savedReservation = quickReservationRepository.save(quickReservation);
			quickReservations.add(savedReservation);
			
		}
		
		for(FishingEquipmentDTO fishingEquipmentDTO: dto.getEquipment()) {
			FishingEquipment fishingEquipment = new FishingEquipment();
			fishingEquipment.setDescription(fishingEquipmentDTO.getDescription());
			fishingEquipment.setFishingLesson(savedFishingLesson);
			FishingEquipment savedFishingEquipment = fishingEquipmentRepository.save(fishingEquipment);
			fishingEquipments.add(savedFishingEquipment);
		}
		
		for(ImageDTO imageDto: dto.getImages()) {
			Image image = new Image();
			image.setPath(imageDto.getPath());
			images.add(image);
		}
				
		savedFishingLesson.setRules(rules);
		savedFishingLesson.setFishingEquipment(fishingEquipments);
		savedFishingLesson.setAdditionalServices(additionalServices);
		savedFishingLesson.setAvailablePeriods(availablePeriods);
		savedFishingLesson.setQuickReservations(quickReservations);
		savedFishingLesson.setImages(images);
		
		Optional<User> instructor = userRepository.findById(dto.getInstructorId());
		savedFishingLesson.setInstructor((Instructor) instructor.get());
			
		return fishingLessonRepository.save(savedFishingLesson);
	}

	@Override
	public List<FishingLesson> getByInstructorId(Long instructorId) {
		User instructor = userRepository.findById(instructorId).get();
		List<FishingLesson> fishingLessons = fishingLessonRepository.findByInstructor(instructor);
		return fishingLessons;
	}

	@Override
	public FishingLesson editFishingLesson(FishingLessonDTO dto) {
		FishingLesson fishingLesson = fishingLessonRepository.findById(dto.getId()).get();
		
		if(dto.getName() != "") {
			fishingLesson.setName(dto.getName());
		}
		if(dto.getDescription() != "") {
			fishingLesson.setDescription(dto.getDescription());
		}
		if(dto.getAddress() != "") {
			fishingLesson.setAddress(dto.getAddress());
		}
		if(dto.getFishingInstructorBio() != "") {
			fishingLesson.setFishingInstructorBio(dto.getFishingInstructorBio());
		}
		if(dto.getNumberOfPeople() != 0) {
			fishingLesson.setNumberOfPeople(dto.getNumberOfPeople());
		}
		if(dto.getPrice() != null) {
			fishingLesson.setPrice(dto.getPrice());
		}
		if(dto.getPercentageForKeep() != null) {
			fishingLesson.setPercentageForKeep(dto.getPercentageForKeep());
		}
		
		
		
		Set<FishingEquipment> fishingEquipments = new HashSet<>();
		Set<Rule> rules = new HashSet<>();
		Set<AvailableFishingLessonPeriod> availablePeriods = new HashSet<>();
		Set<QuickFishingLessonReservation> quickReservations = new HashSet<>();
		Set<AdditionalFishingLessonService> additionalServices = new HashSet<>();
		Set<Image> images = new HashSet<>();
		
		FishingLesson savedFishingLesson = fishingLessonRepository.save(fishingLesson);
		
		/*
		for(RuleDTO ruleDto: dto.getRules()) {
			Rule rule = new Rule();
			rule.setDescription(ruleDto.getDescription());
			rules.add(rule);
		}
		 */
		if(!dto.getRules().isEmpty()) {
			for(RuleDTO ruleDto: dto.getRules()) {
				Rule rule = new Rule();
				rule.setDescription(ruleDto.getDescription());
				rules.add(rule);
			}
			savedFishingLesson.setRules(rules);
		}
		
		if(!dto.getImages().isEmpty()) {
			for(ImageDTO imageDto: dto.getImages()) {
				Image image = new Image();
				image.setPath(imageDto.getPath());
				images.add(image);
			}
			savedFishingLesson.setImages(images);
		}
		
		if(!dto.getQuickReservations().isEmpty()) {
			for(QuickReservationDTO quickReservationDto: dto.getQuickReservations()) {
				QuickFishingLessonReservation quickReservation = new QuickFishingLessonReservation();
				quickReservation.setStartDate(quickReservationDto.getStartDate());
				quickReservation.setEndDate(quickReservationDto.getEndDate());
				quickReservation.setAdditionalServices(quickReservationDto.getAdditionalServices());
				quickReservation.setMaxNumberOfPerson(quickReservationDto.getMaxNumberOfPerson());
				quickReservation.setPrice(quickReservationDto.getPrice());
				quickReservation.setFishingLesson(savedFishingLesson);
				QuickFishingLessonReservation savedReservation = quickReservationRepository.save(quickReservation);
				quickReservations.add(savedReservation);
			}
			savedFishingLesson.setQuickReservations(quickReservations);
		}
		
		if(!dto.getAdditionalServices().isEmpty()) {
			
			for(AdditionalServiceDTO serviceDto: dto.getAdditionalServices()) {		
				AdditionalFishingLessonService service = new AdditionalFishingLessonService();
				service.setDescription(serviceDto.getDescription());
				service.setPrice(serviceDto.getPrice());
				service.setFishingLesson(savedFishingLesson);
				AdditionalFishingLessonService savedService = additionalServiceRepository.save(service);
				additionalServices.add(savedService);
			}
			
			savedFishingLesson.setAdditionalServices(additionalServices);
		}
		
		if(!dto.getAvailablePeriods().isEmpty()) {
			for(AvailablePeriodDTO periodDto: dto.getAvailablePeriods()) {
				AvailableFishingLessonPeriod period = new AvailableFishingLessonPeriod();
				period.setStartDate(periodDto.getStartDate());
				period.setEndDate(periodDto.getEndDate());
				period.setFishingLesson(savedFishingLesson);
				AvailableFishingLessonPeriod savedPeriod = availablePeriodRepository.save(period);
				availablePeriods.add(savedPeriod);
			}
			savedFishingLesson.setAvailablePeriods(availablePeriods);
			
		}
		
		if(!dto.getEquipment().isEmpty()) {
			for(FishingEquipmentDTO fishingEquipmentDTO: dto.getEquipment()) {
				FishingEquipment fishingEquipment = new FishingEquipment();
				fishingEquipment.setDescription(fishingEquipmentDTO.getDescription());
				fishingEquipment.setFishingLesson(savedFishingLesson);
				FishingEquipment savedFishingEquipment = fishingEquipmentRepository.save(fishingEquipment);
				fishingEquipments.add(savedFishingEquipment);
			}
			savedFishingLesson.setFishingEquipment(fishingEquipments);
			
		}
		
		return fishingLessonRepository.save(savedFishingLesson);
	}

}