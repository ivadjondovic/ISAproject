package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ComplaintDTO;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageComplaint;
import com.isa.project.model.CottageReservation;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.repository.CottageComplaintRepository;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.CottageReservationRepository;
import com.isa.project.repository.QuickCottageReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.CottageComplaintService;


@Service
public class CottageComplaintServiceImplementation implements CottageComplaintService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CottageReservationRepository cottageReservationRepository;
	
	@Autowired
	private CottageRepository cottageRepository;
	
	@Autowired
	private QuickCottageReservationRepository quickCottageReservationRepository;
	
	@Autowired
	private CottageComplaintRepository cottageComplaintRepository;
	
	@Override
	public CottageComplaint addComplaint(ComplaintDTO dto) {
		CottageComplaint complaint = new CottageComplaint();
		CottageComplaint savedComplaint = new CottageComplaint();
		complaint.setDate(LocalDateTime.now());
		complaint.setDescription(dto.getDescription());
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		complaint.setClient(client);
		
		if(dto.getReservationType().equals("Cottage reservation")) {
			
			CottageReservation reservation = cottageReservationRepository.findById(dto.getReservationId()).get();
			Cottage cottage = cottageRepository.findById(reservation.getCottage().getId()).get();
			complaint.setCottage(cottage);
			savedComplaint = cottageComplaintRepository.save(complaint);
			Cottage c = cottageRepository.findById(reservation.getCottage().getId()).get();
			Set<CottageComplaint> complaints = c.getComplaints();
			complaints.add(savedComplaint);
			c.setComplaints(complaints);
			cottageRepository.save(c);
			
		}
		
		if(dto.getReservationType().equals("Quick cottage reservation")) {
			QuickCottageReservation reservation = quickCottageReservationRepository.findById(dto.getReservationId()).get();
			Cottage cottage = cottageRepository.findById(reservation.getCottage().getId()).get();
			complaint.setCottage(cottage);
			savedComplaint = cottageComplaintRepository.save(complaint);
			Cottage c = cottageRepository.findById(reservation.getCottage().getId()).get();
			Set<CottageComplaint> complaints = c.getComplaints();
			complaints.add(savedComplaint);
			c.setComplaints(complaints);
			cottageRepository.save(c);
			
		}
		
		
		return savedComplaint;
	}

}
