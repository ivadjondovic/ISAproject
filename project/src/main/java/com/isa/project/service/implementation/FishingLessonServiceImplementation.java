package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.AdditionalServiceDTO;
import com.isa.project.dto.AvailablePeriodDTO;
import com.isa.project.dto.DateSearchDTO;
import com.isa.project.dto.FishingEquipmentDTO;
import com.isa.project.dto.FishingLessonDTO;
import com.isa.project.dto.ImageDTO;
import com.isa.project.dto.QuickReservationDTO;
import com.isa.project.dto.ReservationSearchDTO;
import com.isa.project.dto.RuleDTO;
import com.isa.project.dto.SearchParamsDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.AdditionalFishingLessonService;
import com.isa.project.model.AvailableFishingLessonPeriod;

import com.isa.project.model.Client;
import com.isa.project.model.FishingEquipment;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.FishingLessonSubscription;
import com.isa.project.model.Image;
import com.isa.project.model.Instructor;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.model.Rule;
import com.isa.project.model.User;
import com.isa.project.repository.AdditionalFishingLessonServiceRepository;
import com.isa.project.repository.AvailableFishingLessonPeriodRepository;
import com.isa.project.repository.FishingEquipmentRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.FishingLessonReservationRepository;
import com.isa.project.repository.FishingLessonSubscriptionRepository;
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
	
	@Autowired
	private FishingLessonSubscriptionRepository fishingLessonSubscriptionRepository;
	
	@Autowired
	private FishingLessonReservationRepository fishingLessonReservationRepository;
	
	@Override
	public FishingLesson createFishingLesson(FishingLessonDTO dto) {
		
		if(dto.getAddress().equals("") || dto.getDescription().equals("") || dto.getFishingInstructorBio().equals("")
				|| dto.getName().equals("") || dto.getNumberOfPeople() == 0 || dto.getPercentageForKeep() == null 
				|| dto.getPrice() == null) {
			return null;
		}

		FishingLesson fishingLesson = new FishingLesson();
		fishingLesson.setName(dto.getName());
		fishingLesson.setFishingInstructorBio(dto.getFishingInstructorBio());
		fishingLesson.setNumberOfPeople(dto.getNumberOfPeople());
		fishingLesson.setAddress(dto.getAddress());
		fishingLesson.setDescription(dto.getDescription());;
		fishingLesson.setPercentageForKeep(dto.getPercentageForKeep());
		fishingLesson.setPrice(dto.getPrice());
		fishingLesson.setRating(0.0);
		fishingLesson.setDeleted(false);
		
		Optional<User> instructor = userRepository.findById(dto.getInstructorId());
		fishingLesson.setInstructor((Instructor) instructor.get());
			
		return fishingLessonRepository.save(fishingLesson);
	}

	@Override
	public List<FishingLesson> getByInstructorId(Long instructorId) {
		User instructor = userRepository.findById(instructorId).get();
		List<FishingLesson> fishingLessons = fishingLessonRepository.findByInstructorAndDeleted(instructor, false);
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
		return fishingLessonRepository.save(fishingLesson);
	}

	@Override
	public FishingLesson getById(Long id) {
		FishingLesson fishingLesson = fishingLessonRepository.findById(id).get();
		Set<QuickFishingLessonReservation> reservations = fishingLesson.getQuickReservations();
		Set<AvailableFishingLessonPeriod> periods = fishingLesson.getAvailablePeriods();
		Set<QuickFishingLessonReservation> filteredSet = reservations.stream()
                .filter(r -> (r.getReserved() == false && r.getAccepted() == false && r.getStartDate().compareTo(LocalDateTime.now()) >= 0))
                .collect(Collectors.toSet());
		
		Set<AvailableFishingLessonPeriod> filteredPeriods = periods.stream()
                .filter(p -> p.getEndDate().compareTo(LocalDateTime.now()) >= 0)
                .collect(Collectors.toSet());
		fishingLesson.setQuickReservations(filteredSet);
		fishingLesson.setAvailablePeriods(filteredPeriods);
		return fishingLesson;
	}

	@Override
	public List<FishingLesson> getAll() {

		return fishingLessonRepository.findByDeleted(false);
	}

	@Override
	public List<FishingLesson> sort(SortDTO dto) {
		
		if(dto.getSortBy().equals("") || dto.getSortType().equals("")) {
			return null;
		}
		List<FishingLesson> lessons = fishingLessonRepository.findByDeleted(false);
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
		
		if(dto.getSortBy().equals("Rating")) {
			if(dto.getSortType().equals("Ascending")) {
				Collections.sort(lessons, (l1, l2) ->
			    Double.compare(l1.getRating(), l2.getRating()));
			}
			if(dto.getSortType().equals("Descending")) {
				Collections.sort(lessons, (l1, l2) ->
				 Double.compare(l2.getRating(), l1.getRating()));
			}
		}
		return lessons;
	}

	@Override
	public List<FishingLesson> search(String searchTerm) {
		List<FishingLesson> lessons = fishingLessonRepository.findByDeleted(false);
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

	@Override
	public List<FishingLesson> getAvailableLessons(ReservationSearchDTO dto) {
		if(dto.getStartDate().compareTo(LocalDateTime.now()) < 0) {
			return null;
		}
		if(dto.getNumberOfDays() == 0 || dto.getNumberOfGuests() == 0 || dto.getStartDate().equals("")) {
			return null;
		}
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		List<FishingLesson> lessons = fishingLessonRepository.findByDeleted(false);
		List<FishingLesson> result = new ArrayList<>();
		for(FishingLesson lesson: lessons) {
			
			List<FishingLessonReservation> reservations = fishingLessonReservationRepository.findByFishingLessonAndCanceled(lesson, false);
			Set<AvailableFishingLessonPeriod> periods = lesson.getAvailablePeriods();
			
			if(reservations.isEmpty()) {
				for(AvailableFishingLessonPeriod period: periods) {
					if(dto.getStartDate().compareTo(period.getStartDate()) >= 0 && endDate.compareTo(period.getEndDate()) <=0 && lesson.getNumberOfPeople() >= dto.getNumberOfGuests()) {
						result.add(lesson);
					}
				}
	
			}
			boolean isAvailable = false;
			for(FishingLessonReservation r: reservations) {
				if(!(dto.getStartDate().compareTo(r.getStartDate()) >= 0 && endDate.compareTo(r.getEndDate()) <= 0) 
						&& !(dto.getStartDate().compareTo(r.getStartDate()) < 0 && endDate.compareTo(r.getEndDate()) <= 0 && endDate.compareTo(r.getStartDate()) > 0)
						&& !(dto.getStartDate().compareTo(r.getStartDate()) >= 0 && endDate.compareTo(r.getEndDate()) > 0 && dto.getStartDate().compareTo(r.getEndDate()) < 0)
						&& !(dto.getStartDate().compareTo(r.getStartDate()) < 0 && endDate.compareTo(r.getEndDate()) > 0)) {
					isAvailable = true;
					continue;
				}else {
					isAvailable = false;
					break;
					
				}
			}
			if(isAvailable) {
				for(AvailableFishingLessonPeriod period: periods) {
					if(dto.getStartDate().compareTo(period.getStartDate()) >= 0 && endDate.compareTo(period.getEndDate()) <=0 && lesson.getNumberOfPeople() >= dto.getNumberOfGuests()) {
						result.add(lesson);
					}
				}
			}
			
		}
		return result;
	}

	@Override
	public List<FishingLesson> lessonsAvailableForCertainDate(DateSearchDTO dto) {
		List<FishingLesson> lessons = fishingLessonRepository.findByDeleted(false);
		List<FishingLesson> result = new ArrayList<>();
		for(FishingLesson l: lessons) {
			Set<AvailableFishingLessonPeriod> periods = l.getAvailablePeriods();
			for(AvailableFishingLessonPeriod period: periods) {
				if(period.getStartDate().compareTo(dto.getDate()) <= 0 && period.getEndDate().compareTo(dto.getDate()) > 0) {
					result.add(l);
				}
			}
			
		}
		return result;
	}

	@Override
	public FishingLesson getByIdForInstructor(Long id) {
		return fishingLessonRepository.findById(id).get();
	}

	@Override
	public List<FishingLesson> searchForInstructor(String searchTerm, Long id) {
		Instructor instructor = (Instructor) userRepository.findById(id).get();
		List<FishingLesson> lessons = fishingLessonRepository.findByInstructorAndDeleted(instructor, false);
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

	@Override
	public List<FishingLesson> getLessonsByClientSubscription(Long clientId) {
		List<FishingLesson> lessons = new ArrayList<>();
		Client client = (Client) userRepository.findById(clientId).get();
		List<FishingLessonSubscription> clientSubscriptions = fishingLessonSubscriptionRepository.findByClient(client);
		
		for(FishingLessonSubscription subscription: clientSubscriptions) {
			if(subscription.getFishingLesson().getDeleted() == false) {
				lessons.add(subscription.getFishingLesson());
			}
		}
		return lessons;
	}

	@Override
	public List<FishingLesson> sortAvailableLessons(ReservationSearchDTO dto) {
		if(dto.getNumberOfDays() == 0 || dto.getNumberOfGuests() == 0 || dto.getStartDate().equals("") || dto.getSortBy().equals("") || dto.getSortType().equals("")) {
			return null;
		}
		List<FishingLesson> lessons = getAvailableLessons(dto);
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
		
		return lessons;
	}

	@Override
	public List<FishingLesson> searchByMoreParams(SearchParamsDTO dto) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;


		String formattedDateTime = dto.getDate().format(formatter);
		if(formattedDateTime.equals("")) {
			return null;
		}
		List<FishingLesson> filteredByDate = new ArrayList<>();
		List<FishingLesson> lessons = fishingLessonRepository.findByDeleted(false);
		for(FishingLesson l: lessons) {
			
			List<FishingLessonReservation> reservations = fishingLessonReservationRepository.findByFishingLessonAndCanceled(l, false);
			Set<AvailableFishingLessonPeriod> periods = l.getAvailablePeriods();
			
			if(reservations.isEmpty()) {
				for(AvailableFishingLessonPeriod period: periods) {
					if(period.getStartDate().compareTo(dto.getDate()) <= 0 && period.getEndDate().compareTo(dto.getDate()) >= 0) {
						filteredByDate.add(l);
					}
				}
			}
			boolean isAvailable = false;
			for(FishingLessonReservation r: reservations) {
				if(!(dto.getDate().compareTo(r.getStartDate()) >= 0 && dto.getDate().compareTo(r.getEndDate()) <= 0)) {
					isAvailable = true;
					continue;
				}else {
					isAvailable = false;
					break;
					
				}
			}
			if(isAvailable) {
				for(AvailableFishingLessonPeriod period: periods) {
					if(period.getStartDate().compareTo(dto.getDate()) <= 0 && period.getEndDate().compareTo(dto.getDate()) >= 0) {
						filteredByDate.add(l);
					}
				}
			}
			
			
		}
		List<FishingLesson> filteredByLocation = new ArrayList<>();
		for(FishingLesson l: filteredByDate) {
			filteredByLocation.add(l);
		}
		
		if(!dto.getLocation().equals("")) {
			filteredByLocation = filteredByDate.stream()
					.filter(l -> l.getAddress().toLowerCase().contains(dto.getLocation().toLowerCase()))
	                .collect(Collectors.toList());
		}
		
		
		List<FishingLesson> filteredByRating = new ArrayList<>();
		for(FishingLesson l: filteredByLocation) {
			filteredByRating.add(l);
		}
		if(dto.getRating() != 0) {
			Double rating = (double) dto.getRating();
			filteredByRating = filteredByLocation.stream()
					.filter(l -> l.getRating() >= rating- 0.5 && l.getRating() <= rating + 0.5)
					.collect(Collectors.toList());
			
		}
		
		List<FishingLesson> filteredByPrice = new ArrayList<>();
		for(FishingLesson l: filteredByRating) {
			filteredByPrice.add(l);
		}
		if(dto.getPriceFrom() != null && dto.getPriceTo() != null) {
			filteredByPrice = filteredByRating.stream()
					.filter(l -> l.getPrice() >= dto.getPriceFrom() && l.getPrice() <= dto.getPriceTo())
					.collect(Collectors.toList());
		}
		
		List<FishingLesson> filteredByPeople = new ArrayList<>();
		for(FishingLesson l: filteredByPrice) {
			filteredByPeople.add(l);
		}
		if(dto.getPeopleFrom() != 0 && dto.getPeopleTo() != 0) {
			filteredByPeople = filteredByPrice.stream()
					.filter(l -> l.getNumberOfPeople() >= dto.getPeopleFrom() && l.getNumberOfPeople() <= dto.getPeopleTo())
					.collect(Collectors.toList());
					
		}
		
		return filteredByPeople;
	
	}

	@Override
	public List<FishingLesson> getAvailableLessonsForInstructor(ReservationSearchDTO dto) {
		if(dto.getStartDate().compareTo(LocalDateTime.now()) < 0) {
			return null;
		}
		if(dto.getNumberOfDays() == 0 || dto.getNumberOfGuests() == 0 || dto.getStartDate().equals("")) {
			return null;
		}
		Instructor instructor = (Instructor) userRepository.findById(dto.getInstructorId()).get();
		
		LocalDateTime endDate = dto.getStartDate().plusDays(dto.getNumberOfDays());
		List<FishingLesson> lessons = fishingLessonRepository.findByInstructorAndDeleted(instructor, false);
		List<FishingLesson> result = new ArrayList<>();
		for(FishingLesson lesson: lessons) {
			Set<AvailableFishingLessonPeriod> periods = lesson.getAvailablePeriods();
			for(AvailableFishingLessonPeriod period: periods) {
				if(dto.getStartDate().compareTo(period.getStartDate()) >=0 && endDate.compareTo(period.getEndDate()) <= 0 && lesson.getNumberOfPeople() >= dto.getNumberOfGuests()) {
					result.add(lesson);
				}
			}
		}
		return result;
	}
	
	@Override
	public void delete(Long id) {
		FishingLesson lesson = fishingLessonRepository.findById(id).get();
		lesson.setDeleted(true);
		fishingLessonRepository.save(lesson);
		
	}


}
