package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ComplaintResponseDTO;
import com.isa.project.model.BoatComplaint;
import com.isa.project.model.CottageComplaint;
import com.isa.project.model.FishingLessonComplaint;
import com.isa.project.repository.BoatComplaintRepository;
import com.isa.project.repository.CottageComplaintRepository;
import com.isa.project.repository.FishingLessonComplaintRepository;
import com.isa.project.service.ComplaintsService;

@Service
public class ComplaintsServiceImplementation implements ComplaintsService{
	
	@Autowired
	private BoatComplaintRepository boatComplaintRepository;
	
	@Autowired
	private CottageComplaintRepository cottageComplaintRepository;
	
	@Autowired
	private FishingLessonComplaintRepository fishingLessonComplaintRepository;

	@Override
	public List<ComplaintResponseDTO> notAnsweredComplaints() {
		
		List<ComplaintResponseDTO> complaints = new ArrayList<>();
		List<BoatComplaint> boatComplaints = boatComplaintRepository.findByAnswered(false);
		List<CottageComplaint> cottageComplaints = cottageComplaintRepository.findByAnswered(false);
		List<FishingLessonComplaint> lessonComplaints = fishingLessonComplaintRepository.findByAnswered(false);
		
		for(BoatComplaint complaint: boatComplaints) {
			ComplaintResponseDTO response = new ComplaintResponseDTO();
			response.setDate(complaint.getDate());
			response.setDescription(complaint.getDescription());
			response.setEntityName(complaint.getBoat().getName());
			response.setId(complaint.getId());
			response.setComplaintType("Boat");
			
			complaints.add(response);
		}
		for(CottageComplaint complaint: cottageComplaints) {
			ComplaintResponseDTO response = new ComplaintResponseDTO();
			response.setDate(complaint.getDate());
			response.setDescription(complaint.getDescription());
			response.setEntityName(complaint.getCottage().getName());
			response.setId(complaint.getId());
			response.setComplaintType("Cottage");
			
			complaints.add(response);
		}
		
		for(FishingLessonComplaint complaint: lessonComplaints) {
			ComplaintResponseDTO response = new ComplaintResponseDTO();
			response.setDate(complaint.getDate());
			response.setDescription(complaint.getDescription());
			response.setEntityName(complaint.getFishingLesson().getName());
			response.setId(complaint.getId());
			response.setComplaintType("Fishing lesson");
			
			complaints.add(response);
		}
		return complaints;
	}

}
