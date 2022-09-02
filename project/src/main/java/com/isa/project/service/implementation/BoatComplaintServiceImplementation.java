package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ComplaintDTO;
import com.isa.project.model.Boat;
import com.isa.project.model.BoatComplaint;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.Client;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.repository.BoatComplaintRepository;
import com.isa.project.repository.BoatRepository;
import com.isa.project.repository.BoatReservationRepository;
import com.isa.project.repository.QuickBoatReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.BoatComplaintService;

@Service
public class BoatComplaintServiceImplementation implements BoatComplaintService{

	
	@Autowired
	private BoatReservationRepository boatReservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoatComplaintRepository boatComplaintRepository;
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private QuickBoatReservationRepository quickBoatReservationRepository;
	
	@Override
	public BoatComplaint addComplaint(ComplaintDTO dto) {
		
		if(dto.getDescription().equals("") || dto.getReservationType().equals("")) {
			return null;
		}
		
		BoatComplaint complaint = new BoatComplaint();
		BoatComplaint savedComplaint = new BoatComplaint();
		complaint.setDate(LocalDateTime.now());
		complaint.setDescription(dto.getDescription());
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		complaint.setClient(client);
		complaint.setAnswered(false);
		
		if(dto.getReservationType().equals("Boat reservation")) {
			
			BoatReservation reservation = boatReservationRepository.findById(dto.getReservationId()).get();
			Boat boat = boatRepository.findById(reservation.getBoat().getId()).get();
			complaint.setBoat(boat);
			savedComplaint = boatComplaintRepository.save(complaint);
			Boat b = boatRepository.findById(reservation.getBoat().getId()).get();
			Set<BoatComplaint> complaints = b.getComplaints();
			complaints.add(savedComplaint);
			b.setComplaints(complaints);
			boatRepository.save(b);
			
		}
		
		if(dto.getReservationType().equals("Quick boat reservation")) {
			QuickBoatReservation reservation = quickBoatReservationRepository.findById(dto.getReservationId()).get();
			Boat boat = boatRepository.findById(reservation.getBoat().getId()).get();
			complaint.setBoat(boat);
			savedComplaint = boatComplaintRepository.save(complaint);
			Boat b = boatRepository.findById(reservation.getBoat().getId()).get();
			Set<BoatComplaint> complaints = b.getComplaints();
			complaints.add(savedComplaint);
			b.setComplaints(complaints);
			boatRepository.save(b);
			
		}
		
		
		return savedComplaint;
	}

}
