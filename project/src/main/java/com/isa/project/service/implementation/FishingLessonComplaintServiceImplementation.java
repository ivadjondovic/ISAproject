package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ComplaintDTO;
import com.isa.project.model.Client;
import com.isa.project.model.FishingLesson;
import com.isa.project.model.FishingLessonComplaint;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.FishingLessonComplaintRepository;
import com.isa.project.repository.FishingLessonRepository;
import com.isa.project.repository.FishingLessonReservationRepository;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.FishingLessonComplaintService;

@Service
public class FishingLessonComplaintServiceImplementation implements FishingLessonComplaintService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FishingLessonReservationRepository fishingLessonReservationRepository ;
	
	@Autowired
	private FishingLessonRepository fishingLessonRepository ;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Autowired
	private FishingLessonComplaintRepository fishingLessonComplaintRepository;

	@Override
	public FishingLessonComplaint addComplaint(ComplaintDTO dto) {
		
		FishingLessonComplaint complaint = new FishingLessonComplaint();
		FishingLessonComplaint savedComplaint = new FishingLessonComplaint();
		complaint.setDate(LocalDateTime.now());
		complaint.setDescription(dto.getDescription());
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		complaint.setClient(client);
		complaint.setAnswered(false);
		
		if(dto.getReservationType().equals("Fishing lesson reservation")) {
			
			FishingLessonReservation reservation = fishingLessonReservationRepository.findById(dto.getReservationId()).get();
			FishingLesson fishingLesson = fishingLessonRepository.findById(reservation.getFishingLesson().getId()).get();
			complaint.setFishingLesson(fishingLesson);
			savedComplaint = fishingLessonComplaintRepository.save(complaint);
			FishingLesson f = fishingLessonRepository.findById(reservation.getFishingLesson().getId()).get();
			Set<FishingLessonComplaint> complaints = f.getComplaints();
			complaints.add(savedComplaint);
			f.setComplaints(complaints);
			fishingLessonRepository.save(f);
			
		}
		
		if(dto.getReservationType().equals("Quick fishing lesson reservation")) {
			QuickFishingLessonReservation reservation = quickFishingLessonReservationRepository.findById(dto.getReservationId()).get();
			FishingLesson fishingLesson = fishingLessonRepository.findById(reservation.getFishingLesson().getId()).get();
			complaint.setFishingLesson(fishingLesson);
			savedComplaint = fishingLessonComplaintRepository.save(complaint);
			FishingLesson f = fishingLessonRepository.findById(reservation.getFishingLesson().getId()).get();
			Set<FishingLessonComplaint> complaints = f.getComplaints();
			complaints.add(savedComplaint);
			f.setComplaints(complaints);
			fishingLessonRepository.save(f);
			
		}
		
		
		return savedComplaint;
	}

}
