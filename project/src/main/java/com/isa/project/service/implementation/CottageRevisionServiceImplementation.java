package com.isa.project.service.implementation;

import java.time.LocalDateTime;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.RevisionDTO;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;

import com.isa.project.model.CottageReservation;
import com.isa.project.model.CottageRevision;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.CottageReservationRepository;
import com.isa.project.repository.CottageRevisionRepository;
import com.isa.project.repository.QuickCottageReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.CottageRevisionService;


@Service
public class CottageRevisionServiceImplementation implements CottageRevisionService{

	@Autowired
	private CottageRevisionRepository cottageRevisionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CottageReservationRepository cottageReservationRepository;
	
	@Autowired
	private QuickCottageReservationRepository quickCottageReservationRepository;
	
	@Autowired
	private CottageRepository cottageRepository;
	
	
	@Override
	public CottageRevision addRevision(RevisionDTO dto) {
		
		if(dto.getDescription().equals("") || dto.getEntityRate() ==0 || dto.getOwnerRate() == 0 || dto.getReservationType().equals("")) {
			return null;
		}
		
		CottageRevision cottageRevision = new CottageRevision();
		cottageRevision.setStatus("Waiting");
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		cottageRevision.setClient(client);
		cottageRevision.setCottageRate(dto.getEntityRate());
		cottageRevision.setDate(LocalDateTime.now());
		cottageRevision.setDescription(dto.getDescription());
		cottageRevision.setOwnerRate(dto.getOwnerRate());
		
		
		if(dto.getReservationType().equals("Cottage reservation")) {
			CottageReservation cottageReservation = cottageReservationRepository.findById(dto.getReservationId()).get();
			Cottage cottage = cottageRepository.findById(cottageReservation.getCottage().getId()).get();
			Set<CottageRevision> revisions = cottage.getRevisions();
			revisions.add(cottageRevision);
			cottage.setRevisions(revisions);
			Cottage savedCottage = cottageRepository.save(cottage);
			cottageRevision.setCottage(savedCottage);
					
			
		}
		if(dto.getReservationType().equals("Quick cottage reservation")) {
			QuickCottageReservation cottageReservation = quickCottageReservationRepository.findById(dto.getReservationId()).get();
			Cottage cottage = cottageRepository.findById(cottageReservation.getCottage().getId()).get();
			Set<CottageRevision> revisions = cottage.getRevisions();
			revisions.add(cottageRevision);
			cottage.setRevisions(revisions);
			Cottage savedCottage = cottageRepository.save(cottage);
			cottageRevision.setCottage(savedCottage);
		
		}
		
		CottageRevision savedRevision = cottageRevisionRepository.save(cottageRevision);
		
		return savedRevision;
	}


	
}
