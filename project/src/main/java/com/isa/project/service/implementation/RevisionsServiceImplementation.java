package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.RevisionResponseDTO;
import com.isa.project.dto.RevisionStatusDTO;
import com.isa.project.model.BoatRevision;
import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.CottageRevision;
import com.isa.project.model.FishingLessonRevision;
import com.isa.project.repository.BoatRevisionRepository;
import com.isa.project.repository.CottageRepository;
import com.isa.project.repository.CottageRevisionRepository;
import com.isa.project.repository.FishingLessonRevisionRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.EmailService;
import com.isa.project.service.RevisionsService;

@Service
public class RevisionsServiceImplementation implements RevisionsService{

	@Autowired
	private CottageRevisionRepository cottageRevisionRepository;
	
	@Autowired
	private BoatRevisionRepository boatRevisionRepository;
	
	@Autowired
	private FishingLessonRevisionRepository fishingLessonRevisionRepository;
	
	@Autowired
	private CottageRepository cottageRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<RevisionResponseDTO> getWaitingRevisions() {
	
		List<RevisionResponseDTO> revisions = new ArrayList<>();
		
		List<CottageRevision> cottageRevisions = cottageRevisionRepository.findByStatus("Waiting");
		
		for(CottageRevision revision: cottageRevisions) {
			RevisionResponseDTO response = new RevisionResponseDTO();
			response.setDate(revision.getDate());
			response.setDescription(revision.getDescription());
			response.setEntityName(revision.getCottage().getName());
			response.setEntityRate(revision.getCottageRate());
			response.setId(revision.getId());
			response.setOwnerRate(revision.getOwnerRate());
			response.setRevisionType("Cottage");
			revisions.add(response);
		}
		
		List<BoatRevision> boatRevisions = boatRevisionRepository.findByStatus("Waiting");
		
		for(BoatRevision revision: boatRevisions) {
			RevisionResponseDTO response = new RevisionResponseDTO();
			response.setDate(revision.getDate());
			response.setDescription(revision.getDescription());
			response.setEntityName(revision.getBoat().getName());
			response.setEntityRate(revision.getBoatRate());
			response.setId(revision.getId());
			response.setOwnerRate(revision.getOwnerRate());
			response.setRevisionType("Boat");
			revisions.add(response);
		}
		
		List<FishingLessonRevision> lessonRevisions = fishingLessonRevisionRepository.findByStatus("Waiting");
		
		for(FishingLessonRevision revision:lessonRevisions) {
			RevisionResponseDTO response = new RevisionResponseDTO();
			response.setDate(revision.getDate());
			response.setDescription(revision.getDescription());
			response.setEntityName(revision.getFishingLesson().getName());
			response.setEntityRate(revision.getLessonRate());
			response.setId(revision.getId());
			response.setOwnerRate(revision.getInstructorRate());
			response.setRevisionType("Fishing lesson");
			revisions.add(response);
		}
		return revisions;
	}

	@Override
	public RevisionResponseDTO approve(RevisionStatusDTO dto) {
		
		RevisionResponseDTO response = new RevisionResponseDTO();
		
		if(dto.getType().equals("Cottage")) {
		
		CottageRevision revision = cottageRevisionRepository.findById(dto.getId()).get();
		revision.setStatus("Approved");
		CottageRevision savedRevision = cottageRevisionRepository.save(revision);
		
		Cottage c = cottageRepository.findById(savedRevision.getCottage().getId()).get();
		
		List<CottageRevision> cottageRevisions = cottageRevisionRepository.findByCottageAndStatus(c, "Approved");
		
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
			    .filter(r -> r.getCottage().getCottageOwner().getId() == saved.getCottageOwner().getId() && r.getStatus().equals("Approved")).collect(Collectors.toList());
		
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
				
		response.setDate(savedRevision.getDate());
		response.setDescription(savedRevision.getDescription());
		response.setEntityName(savedRevision.getCottage().getName());
		response.setEntityRate(savedRevision.getCottageRate());
		response.setId(savedRevision.getId());
		response.setOwnerRate(savedRevision.getOwnerRate());
		response.setRevisionType("Cottage");
		
		try {
			emailService.approveRevisionEmail(cottageOwner, response);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		
		
		return response;
	}

	@Override
	public RevisionResponseDTO disapprove(RevisionStatusDTO dto) {
		RevisionResponseDTO response = new RevisionResponseDTO();
		
		if(dto.getType().equals("Cottage")) {
			CottageRevision revision = cottageRevisionRepository.findById(dto.getId()).get();
			revision.setStatus("Disapproved");
			CottageRevision savedRevision = cottageRevisionRepository.save(revision);
			
			response.setDate(savedRevision.getDate());
			response.setDescription(savedRevision.getDescription());
			response.setEntityName(savedRevision.getCottage().getName());
			response.setEntityRate(savedRevision.getCottageRate());
			response.setId(savedRevision.getId());
			response.setOwnerRate(savedRevision.getOwnerRate());
			response.setRevisionType("Cottage");
		
		}
		
		return response;
	}

}
