package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ComplaintAnswerDTO;
import com.isa.project.dto.ComplaintResponseDTO;
import com.isa.project.model.BoatComplaint;
import com.isa.project.model.BoatOwner;
import com.isa.project.model.Client;
import com.isa.project.model.CottageComplaint;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.FishingLessonComplaint;
import com.isa.project.model.Instructor;
import com.isa.project.repository.BoatComplaintRepository;
import com.isa.project.repository.CottageComplaintRepository;
import com.isa.project.repository.FishingLessonComplaintRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.ComplaintsService;
import com.isa.project.service.EmailService;

@Service
public class ComplaintsServiceImplementation implements ComplaintsService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoatComplaintRepository boatComplaintRepository;
	
	@Autowired
	private CottageComplaintRepository cottageComplaintRepository;
	
	@Autowired
	private FishingLessonComplaintRepository fishingLessonComplaintRepository;
	
	@Autowired
	private EmailService emailService;

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
			response.setClientId(complaint.getClient().getId());
			
			complaints.add(response);
		}
		for(CottageComplaint complaint: cottageComplaints) {
			ComplaintResponseDTO response = new ComplaintResponseDTO();
			response.setDate(complaint.getDate());
			response.setDescription(complaint.getDescription());
			response.setEntityName(complaint.getCottage().getName());
			response.setId(complaint.getId());
			response.setComplaintType("Cottage");
			response.setClientId(complaint.getClient().getId());
			
			complaints.add(response);
		}
		
		for(FishingLessonComplaint complaint: lessonComplaints) {
			ComplaintResponseDTO response = new ComplaintResponseDTO();
			response.setDate(complaint.getDate());
			response.setDescription(complaint.getDescription());
			response.setEntityName(complaint.getFishingLesson().getName());
			response.setId(complaint.getId());
			response.setComplaintType("Fishing lesson");
			response.setClientId(complaint.getClient().getId());
			
			complaints.add(response);
		}
		return complaints;
	}

	@Override
	public ComplaintResponseDTO answer(ComplaintAnswerDTO dto) {
		
		ComplaintResponseDTO response = new ComplaintResponseDTO();
		
		if(dto.getComplaintType().equals("Cottage")) {
			CottageComplaint complaint = cottageComplaintRepository.findById(dto.getComplaintId()).get();
			complaint.setAnswered(true);
			CottageComplaint savedComplaint = cottageComplaintRepository.save(complaint);
			
			CottageOwner owner = savedComplaint.getCottage().getCottageOwner();
			Client client = (Client) userRepository.findById(dto.getClientId()).get();
			
			response = new ComplaintResponseDTO();
			response.setDescription(savedComplaint.getDescription());
			response.setEntityName(savedComplaint.getCottage().getName());
			response.setComplaintType("Cottage");
			response.setDate(savedComplaint.getDate());
			response.setId(savedComplaint.getId());
			
			try {
				emailService.complaintOwnerEmail(owner, response, dto.getAnswer());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				emailService.complaintClientEmail(client, response, dto.getAnswer());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		if(dto.getComplaintType().equals("Boat")) {
			BoatComplaint complaint = boatComplaintRepository.findById(dto.getComplaintId()).get();
			complaint.setAnswered(true);
			BoatComplaint savedComplaint = boatComplaintRepository.save(complaint);
			
			BoatOwner owner = savedComplaint.getBoat().getBoatOwner();
			Client client = (Client) userRepository.findById(dto.getClientId()).get();
			
			response = new ComplaintResponseDTO();
			response.setDescription(savedComplaint.getDescription());
			response.setEntityName(savedComplaint.getBoat().getName());
			response.setComplaintType("Boat");
			response.setDate(savedComplaint.getDate());
			response.setId(savedComplaint.getId());
			
			try {
				emailService.complaintOwnerEmail(owner, response, dto.getAnswer());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				emailService.complaintClientEmail(client, response, dto.getAnswer());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		if(dto.getComplaintType().equals("Fishing lesson")) {
			FishingLessonComplaint complaint = fishingLessonComplaintRepository.findById(dto.getComplaintId()).get();
			complaint.setAnswered(true);
			FishingLessonComplaint savedComplaint = fishingLessonComplaintRepository.save(complaint);
			
			Instructor instructor = savedComplaint.getFishingLesson().getInstructor();
			Client client = (Client) userRepository.findById(dto.getClientId()).get();
			
			response = new ComplaintResponseDTO();
			response.setDescription(savedComplaint.getDescription());
			response.setEntityName(savedComplaint.getFishingLesson().getName());
			response.setComplaintType("Fishing lesson");
			response.setDate(savedComplaint.getDate());
			response.setId(savedComplaint.getId());
			
			try {
				emailService.complaintOwnerEmail(instructor, response, dto.getAnswer());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				emailService.complaintClientEmail(client, response, dto.getAnswer());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		return response;
		
	}

}
