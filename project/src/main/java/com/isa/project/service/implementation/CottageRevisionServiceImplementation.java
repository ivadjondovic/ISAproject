package com.isa.project.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.RevisionDTO;
import com.isa.project.model.Client;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
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
		
		CottageRevision cottageRevision = new CottageRevision();
		cottageRevision.setAccepted(false);
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		cottageRevision.setClient(client);
		cottageRevision.setCottageRate(dto.getEntityRate());
		cottageRevision.setDate(LocalDateTime.now());
		cottageRevision.setDescription(dto.getDescription());
		cottageRevision.setOwnerRate(dto.getOwnerRate());
		
		Cottage c = new Cottage();
		
		if(dto.getReservationType().equals("Cottage reservation")) {
			CottageReservation cottageReservation = cottageReservationRepository.findById(dto.getReservationId()).get();
			Cottage cottage = cottageRepository.findById(cottageReservation.getCottage().getId()).get();
			Set<CottageRevision> revisions = cottage.getRevisions();
			revisions.add(cottageRevision);
			cottage.setRevisions(revisions);
			Cottage savedCottage = cottageRepository.save(cottage);
			cottageRevision.setCottage(savedCottage);
			
			c = savedCottage;
			
		}
		if(dto.getReservationType().equals("Quick cottage reservation")) {
			QuickCottageReservation cottageReservation = quickCottageReservationRepository.findById(dto.getReservationId()).get();
			Cottage cottage = cottageRepository.findById(cottageReservation.getCottage().getId()).get();
			Set<CottageRevision> revisions = cottage.getRevisions();
			revisions.add(cottageRevision);
			cottage.setRevisions(revisions);
			Cottage savedCottage = cottageRepository.save(cottage);
			cottageRevision.setCottage(savedCottage);
			
			c = savedCottage;
			
		}
		
		CottageRevision savedRevision = cottageRevisionRepository.save(cottageRevision);
		List<CottageRevision> cottageRevisions = cottageRevisionRepository.findByCottage(c);
		
		Double ratingCottage = 0.0;
		Double sumCottage = 0.0;
		int numCottage = 0;
		
		for(CottageRevision cr: cottageRevisions) {
			sumCottage += cr.getCottageRate();
			numCottage++;
		}
		ratingCottage = sumCottage/numCottage;
		c.setRating(ratingCottage);
		Cottage saved = cottageRepository.save(c);
		
		List<CottageRevision> allRevisions = cottageRevisionRepository.findAll();
		List<CottageRevision> cottageRevisionsByOwner = allRevisions.stream()
			    .filter(r -> r.getCottage().getCottageOwner().getId() == saved.getCottageOwner().getId()).collect(Collectors.toList());
		
		Double ratingCottageOwner = 0.0;
		Double sumCottageOwner = 0.0;
		int numCottageOwner = 0;
		
		for(CottageRevision cr: cottageRevisionsByOwner) {
			sumCottageOwner += cr.getOwnerRate();
			numCottageOwner++;
		}
		
		ratingCottageOwner = sumCottageOwner/numCottageOwner;
		CottageOwner cottageOwner = (CottageOwner) userRepository.findById(saved.getCottageOwner().getId()).get();
		cottageOwner.setRating(ratingCottageOwner);
		userRepository.save(cottageOwner);
		
		return savedRevision;
	}

}
