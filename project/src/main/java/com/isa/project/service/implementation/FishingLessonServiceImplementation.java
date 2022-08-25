package com.isa.project.service.implementation;

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
import com.isa.project.dto.FishingEquipmentDTO;
import com.isa.project.dto.FishingLessonDTO;
import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.dto.RuleDTO;
import com.isa.project.dto.SortDTO;
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
import com.isa.project.repository.RuleRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.FishingLessonService;

@Service
public class FishingLessonServiceImplementation implements FishingLessonService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RuleRepository ruleRepository;
	
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
		
		FishingLesson savedFishingLesson = fishingLessonRepository.save(fishingLesson);
		
		Set<FishingEquipment> fishingEquipments = savedFishingLesson.getFishingEquipment();
		Set<Rule> rules = savedFishingLesson.getRules();
		Set<AvailableFishingLessonPeriod> availablePeriods = savedFishingLesson.getAvailablePeriods();
		Set<QuickFishingLessonReservation> quickReservations = savedFishingLesson.getQuickReservations();
		Set<AdditionalFishingLessonService> additionalServices = savedFishingLesson.getAdditionalServices();
		Set<Image> images = savedFishingLesson.getImages();
		
		/*
		for(RuleDTO ruleDto: dto.getRules()) {
			Rule rule = new Rule();
			rule.setDescription(ruleDto.getDescription());
			rules.add(rule);
		}
		 */
		if(!dto.getRules().isEmpty()) {
			for(RuleDTO ruleDto: dto.getRules()) {
				if(ruleDto.getId() != null) {
					Rule existingRule = ruleRepository.findById(ruleDto.getId()).get();
					existingRule.setDescription(ruleDto.getDescription());
					ruleRepository.save(existingRule);
				}
				else {
					Rule rule = new Rule();
					rule.setDescription(ruleDto.getDescription());
					rules.add(rule);
				}
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
				if(quickReservationDto.getId() != null) {
					QuickFishingLessonReservation existingReservation = quickReservationRepository.findById(quickReservationDto.getId()).get();
					existingReservation.setStartDate(quickReservationDto.getStartDate());
					existingReservation.setEndDate(quickReservationDto.getEndDate());
					existingReservation.setAdditionalServices(quickReservationDto.getAdditionalServices());
					existingReservation.setMaxNumberOfPerson(quickReservationDto.getMaxNumberOfPerson());
					existingReservation.setPrice(quickReservationDto.getPrice());
					existingReservation.setFishingLesson(savedFishingLesson);
					quickReservationRepository.save(existingReservation);
				} else {
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
			}
			savedFishingLesson.setQuickReservations(quickReservations);
		}
		
		if(!dto.getAdditionalServices().isEmpty()) {
			
			for(AdditionalServiceDTO serviceDto: dto.getAdditionalServices()) {		
				if(serviceDto.getId() != null) {
					AdditionalFishingLessonService existingService = additionalServiceRepository.findById(serviceDto.getId()).get();
					existingService.setDescription(serviceDto.getDescription());
					existingService.setPrice(serviceDto.getPrice());
					existingService.setFishingLesson(savedFishingLesson);
					additionalServiceRepository.save(existingService);
				} else {
					AdditionalFishingLessonService service = new AdditionalFishingLessonService();
					service.setDescription(serviceDto.getDescription());
					service.setPrice(serviceDto.getPrice());
					service.setFishingLesson(savedFishingLesson);
					AdditionalFishingLessonService savedService = additionalServiceRepository.save(service);
					additionalServices.add(savedService);
				}
			}
			
			savedFishingLesson.setAdditionalServices(additionalServices);
		}
		
		if(!dto.getAvailablePeriods().isEmpty()) {
			for(AvailablePeriodDTO periodDto: dto.getAvailablePeriods()) {
				if(periodDto.getId() != null) {
					AvailableFishingLessonPeriod existingPeriod = availablePeriodRepository.findById(periodDto.getId()).get();
					existingPeriod.setStartDate(periodDto.getStartDate());
					existingPeriod.setEndDate(periodDto.getEndDate());
					existingPeriod.setFishingLesson(savedFishingLesson);
					availablePeriodRepository.save(existingPeriod);
				} else {
					AvailableFishingLessonPeriod period = new AvailableFishingLessonPeriod();
					period.setStartDate(periodDto.getStartDate());
					period.setEndDate(periodDto.getEndDate());
					period.setFishingLesson(savedFishingLesson);
					AvailableFishingLessonPeriod savedPeriod = availablePeriodRepository.save(period);
					availablePeriods.add(savedPeriod);
				}
			}
			savedFishingLesson.setAvailablePeriods(availablePeriods);
			
		}
		
		if(!dto.getEquipment().isEmpty()) {
			for(FishingEquipmentDTO fishingEquipmentDTO: dto.getEquipment()) {
				if(fishingEquipmentDTO.getId() != null) {
					FishingEquipment existingEquipment = fishingEquipmentRepository.findById(fishingEquipmentDTO.getId()).get();
					existingEquipment.setDescription(fishingEquipmentDTO.getDescription());
					existingEquipment.setFishingLesson(savedFishingLesson);
					fishingEquipmentRepository.save(existingEquipment);
				} else {
					FishingEquipment fishingEquipment = new FishingEquipment();
					fishingEquipment.setDescription(fishingEquipmentDTO.getDescription());
					fishingEquipment.setFishingLesson(savedFishingLesson);
					FishingEquipment savedFishingEquipment = fishingEquipmentRepository.save(fishingEquipment);
					fishingEquipments.add(savedFishingEquipment);
				}
			}
			savedFishingLesson.setFishingEquipment(fishingEquipments);
			
		}
		
		return fishingLessonRepository.save(savedFishingLesson);
	}

	@Override
	public FishingLesson getById(Long id) {
		FishingLesson fishingLesson = fishingLessonRepository.findById(id).get();
		return fishingLesson;
	}

	@Override
	public List<FishingLesson> getAll() {

		return fishingLessonRepository.findAll();
	}

	@Override
	public List<FishingLesson> sort(SortDTO dto) {
		List<FishingLesson> lessons = fishingLessonRepository.findAll();
		if(dto.getSortBy().equals("Name")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(lessons, (l1, l2) ->
			    (l1.getName().compareTo(l2.getName())));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(lessons, (l1, l2) ->
			    (l2.getName().compareTo(l1.getName())));
			}
		}
		
		if(dto.getSortBy().equals("Address")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(lessons, (l1, l2) ->
			    (l1.getAddress().compareTo(l2.getAddress())));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(lessons, (l1, l2) ->
			    (l2.getAddress().compareTo(l1.getAddress())));
			}
		}
		if(dto.getSortBy().equals("Price")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(lessons, (l1, l2) ->
			    Double.compare(l1.getPrice(), l2.getPrice()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(lessons, (l1, l2) ->
				 Double.compare(l2.getPrice(), l1.getPrice()));
			}
		}
		
		if(dto.getSortBy().equals("Number of people")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(lessons, (l1, l2) ->
			   Integer.compare(l1.getNumberOfPeople(), l2.getNumberOfPeople()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(lessons, (l1, l2) ->
				 Integer.compare(l2.getNumberOfPeople(), l1.getNumberOfPeople()));
			}
		}
		
		if(dto.getSortBy().equals("Percentage for keep")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(lessons, (l1, l2) ->
			    Double.compare(l1.getPercentageForKeep(), l2.getPercentageForKeep()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(lessons, (l1, l2) ->
				 Double.compare(l2.getPercentageForKeep(), l1.getPercentageForKeep()));
			}
		}
		return lessons;
	}

	@Override
	public List<FishingLesson> search(String searchTerm) {
		List<FishingLesson> lessons = fishingLessonRepository.findAll();
		List<FishingLesson> result = new ArrayList<>();
		List<FishingLesson> filtered = new ArrayList<>();
		for(FishingLesson lesson: lessons) {
			if(lesson.getAddress().toLowerCase().contains(searchTerm.toLowerCase())) {
				result.add(lesson);
			}
			if(lesson.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
				result.add(lesson);
			}
			
		}
		
		filtered = result.stream().distinct().collect( Collectors.toList() );
		return filtered;
	}

}
