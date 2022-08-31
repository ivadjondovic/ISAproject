package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.RevisionDTO;
import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.FishingLessonRevision;
import com.isa.project.model.Instructor;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.FishingLessonReservationRepository;
import com.isa.project.repository.FishingLessonRevisionRepository;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.FishingLessonRevisionService;

@Service
public class FishingLessonRevisionServiceImplementation implements FishingLessonRevisionService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FishingLessonReservationRepository fishingLessonReservationRepository;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Autowired
	private FishingLessonRevisionRepository fishingLessonRevisionRepository;
	
	@Override
	public FishingLessonRevision addRevision(RevisionDTO dto) {
		
		FishingLessonRevision lessonRevision = new FishingLessonRevision();
		lessonRevision.setAccepted(false);
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		lessonRevision.setClient(client);
		lessonRevision.setLessonRate(dto.getEntityRate());
		lessonRevision.setDate(LocalDateTime.now());
		lessonRevision.setDescription(dto.getDescription());
		lessonRevision.setInstructorRate(dto.getOwnerRate());
		
		FishingLesson l = new FishingLesson();
		
		if(dto.getReservationType().equals("Fishing lesson reservation")) {
			FishingLessonReservation lessonReservation = fishingLessonReservationRepository.findById(dto.getReservationId()).get();
			FishingLesson lesson = fishingLessonRepository.findById(lessonReservation.getFishingLesson().getId()).get();
			Set<FishingLessonRevision> revisions = lesson.getRevisions();
			revisions.add(lessonRevision);
			lesson.setRevisions(revisions);
			FishingLesson savedLesson = fishingLessonRepository.save(lesson);
			lessonRevision.setFishingLesson(savedLesson);
			
			l = savedLesson;
			
		}
		if(dto.getReservationType().equals("Quick fishing lesson reservation")) {
			QuickFishingLessonReservation cottageReservation = quickFishingLessonReservationRepository.findById(dto.getReservationId()).get();
			FishingLesson lesson = fishingLessonRepository.findById(cottageReservation.getFishingLesson().getId()).get();
			Set<FishingLessonRevision> revisions = lesson.getRevisions();
			revisions.add(lessonRevision);
			lesson.setRevisions(revisions);
			FishingLesson savedLesson = fishingLessonRepository.save(lesson);
			lessonRevision.setFishingLesson(savedLesson);
			
			l = savedLesson;
			
		}
		
		FishingLessonRevision savedRevision = fishingLessonRevisionRepository.save(lessonRevision);
		List<FishingLessonRevision> lessonRevisions = fishingLessonRevisionRepository.findByFishingLesson(l);
		
		Double ratingLesson = 0.0;
		Double sumLesson = 0.0;
		int numLesson = 0;
		
		for(FishingLessonRevision lr: lessonRevisions) {
			sumLesson += lr.getLessonRate();
			numLesson++;
		}
		ratingLesson = sumLesson/numLesson;
		l.setRating(ratingLesson);
		FishingLesson saved = fishingLessonRepository.save(l);
		
		List<FishingLessonRevision> allRevisions = fishingLessonRevisionRepository.findAll();
		List<FishingLessonRevision> lessonRevisionsByOwner = allRevisions.stream()
			    .filter(r -> r.getFishingLesson().getInstructor().getId() == saved.getInstructor().getId()).collect(Collectors.toList());
		
		Double ratingInstructor = 0.0;
		Double sumInstructor = 0.0;
		int numInstructor = 0;
		
		for(FishingLessonRevision lr: lessonRevisionsByOwner) {
			sumInstructor += lr.getInstructorRate();
			numInstructor++;
		}
		
		ratingInstructor = sumInstructor/numInstructor;
		Instructor instructor = (Instructor) userRepository.findById(saved.getInstructor().getId()).get();
		instructor.setRating(ratingInstructor);
		userRepository.save(instructor);
		
		return savedRevision;
	}

}
