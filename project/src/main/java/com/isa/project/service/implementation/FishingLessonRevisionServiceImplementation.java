package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.RevisionDTO;
import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.FishingLessonRevision;
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
		lessonRevision.setStatus("Waiting");
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		lessonRevision.setClient(client);
		lessonRevision.setLessonRate(dto.getEntityRate());
		lessonRevision.setDate(LocalDateTime.now());
		lessonRevision.setDescription(dto.getDescription());
		lessonRevision.setInstructorRate(dto.getOwnerRate());
		
		
		if(dto.getReservationType().equals("Fishing lesson reservation")) {
			FishingLessonReservation lessonReservation = fishingLessonReservationRepository.findById(dto.getReservationId()).get();
			FishingLesson lesson = fishingLessonRepository.findById(lessonReservation.getFishingLesson().getId()).get();
			Set<FishingLessonRevision> revisions = lesson.getRevisions();
			revisions.add(lessonRevision);
			lesson.setRevisions(revisions);
			FishingLesson savedLesson = fishingLessonRepository.save(lesson);
			lessonRevision.setFishingLesson(savedLesson);
					
			
		}
		if(dto.getReservationType().equals("Quick fishing lesson reservation")) {
			QuickFishingLessonReservation cottageReservation = quickFishingLessonReservationRepository.findById(dto.getReservationId()).get();
			FishingLesson lesson = fishingLessonRepository.findById(cottageReservation.getFishingLesson().getId()).get();
			Set<FishingLessonRevision> revisions = lesson.getRevisions();
			revisions.add(lessonRevision);
			lesson.setRevisions(revisions);
			FishingLesson savedLesson = fishingLessonRepository.save(lesson);
			lessonRevision.setFishingLesson(savedLesson);
					
			
		}
		
		FishingLessonRevision savedRevision = fishingLessonRevisionRepository.save(lessonRevision);
		
		return savedRevision;
	}

}
