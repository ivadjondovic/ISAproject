package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.RevisionDTO;
import com.isa.project.model.Boat;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.BoatRevision;
import com.isa.project.model.Client;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.repository.BoatRepository;
import com.isa.project.repository.BoatReservationRepository;
import com.isa.project.repository.BoatRevisionRepository;
import com.isa.project.repository.QuickBoatReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.BoatRevisionService;

@Service
public class BoatRevisionServiceImplementation implements BoatRevisionService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoatReservationRepository boatReservationRepository;
	
	@Autowired
	private BoatRepository boatRepository;
	
	@Autowired
	private QuickBoatReservationRepository quickBoatReservationRepository;
	
	@Autowired
	private BoatRevisionRepository boatRevisionRepository;
	
	@Override
	public BoatRevision addRevision(RevisionDTO dto) {
		
		BoatRevision boatRevision = new BoatRevision();
		boatRevision.setStatus("Waiting");
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		boatRevision.setClient(client);
		boatRevision.setBoatRate(dto.getEntityRate());
		boatRevision.setDate(LocalDateTime.now());
		boatRevision.setDescription(dto.getDescription());
		boatRevision.setOwnerRate(dto.getOwnerRate());
		
		
		if(dto.getReservationType().equals("Boat reservation")) {
			BoatReservation boatReservation = boatReservationRepository.findById(dto.getReservationId()).get();
			Boat boat = boatRepository.findById(boatReservation.getBoat().getId()).get();
			Set<BoatRevision> revisions = boat.getRevisions();
			revisions.add(boatRevision);
			boat.setRevisions(revisions);
			Boat savedBoat = boatRepository.save(boat);
			boatRevision.setBoat(savedBoat);
			
			
			
		}
		if(dto.getReservationType().equals("Quick boat reservation")) {
			QuickBoatReservation boatReservation = quickBoatReservationRepository.findById(dto.getReservationId()).get();
			Boat boat = boatRepository.findById(boatReservation.getBoat().getId()).get();
			Set<BoatRevision> revisions = boat.getRevisions();
			revisions.add(boatRevision);
			boat.setRevisions(revisions);
			Boat savedBoat = boatRepository.save(boat);
			boatRevision.setBoat(savedBoat);
			
			
			
		}
		
		BoatRevision savedRevision = boatRevisionRepository.save(boatRevision);
		
		return savedRevision;
	}

}
