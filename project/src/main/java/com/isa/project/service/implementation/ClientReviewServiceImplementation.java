package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ClientReviewDTO;
import com.isa.project.dto.ClientReviewResponseDTO;
import com.isa.project.model.Category;
import com.isa.project.model.Client;
import com.isa.project.model.ClientReview;
import com.isa.project.model.Instructor;
import com.isa.project.repository.CategoryRepository;
import com.isa.project.repository.ClientReviewRepository;
import com.isa.project.repository.LoyaltyProgramRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.ClientReviewService;
import com.isa.project.service.EmailService;


@Service
public class ClientReviewServiceImplementation implements ClientReviewService {

	@Autowired
	private ClientReviewRepository clientReviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;
			
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public ClientReview createReview(ClientReviewDTO dto) {
		ClientReview review = new ClientReview();
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		Instructor instructor = (Instructor) userRepository.findById(dto.getInstructorId()).get();
		
		review.setClient(client);
		review.setInstructor(instructor);
		review.setPenaltySuggestion(dto.getPenaltySuggestion());
		review.setPenaltySuggestionReason(dto.getPenaltySuggestionReason());
		review.setAutomaticPenalty(dto.getAutomaticPenalty());
		review.setAdminChecked(false);
		
		if(dto.getAutomaticPenalty() == true) {
			client.setPenalties(client.getPenalties() + 1);
			review.setAdminChecked(true);
			userRepository.save(client);
		}
		
		if(dto.getAutomaticPenalty() == false && dto.getPenaltySuggestion() == false) {
			int clientPoints = loyaltyProgramRepository.findAll().get(0).getClientPoints();
			int newPoints = client.getPoints() + clientPoints;
			client.setPoints(newPoints);
			Category silver = categoryRepository.findByCategory("silver");
			Category gold = categoryRepository.findByCategory("gold");
			if(newPoints >= silver.getPointsNeeded()) {
				client.setCategory(silver);
			}
			if(newPoints >= gold.getPointsNeeded()) {
				client.setCategory(gold);
			}
			userRepository.save(client);
		}
		
		return clientReviewRepository.save(review);
	}

	@Override
	public ClientReview accept(ClientReviewDTO dto) {
		ClientReview review = clientReviewRepository.findById(dto.getId()).get();
		review.setAdminChecked(true);
		
		Instructor instructor = (Instructor) userRepository.findById(dto.getInstructorId()).get();
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		client.setPenalties(client.getPenalties() + 1);
		userRepository.save(client);
		
		try {
			emailService.acceptPenaltyClientEmail(client, review);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			emailService.acceptPenaltyInstructorEmail(instructor, review);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clientReviewRepository.save(review);
	}

	@Override
	public ClientReview decline(ClientReviewDTO dto) {
		ClientReview review = clientReviewRepository.findById(dto.getId()).get();
		review.setAdminChecked(true);
		
		Instructor instructor = (Instructor) userRepository.findById(dto.getInstructorId()).get();
		
		Client client = (Client) userRepository.findById(dto.getClientId()).get();
		
		try {
			emailService.declinePenaltyClientEmail(client, review);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			emailService.declinePenaltyInstructorEmail(instructor, review);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clientReviewRepository.save(review);
	}

	@Override
	public List<ClientReviewResponseDTO> getNotCheckedPenalties() {
		List<ClientReview> reviews = clientReviewRepository.findByAdminChecked(false);
		
		List<ClientReviewResponseDTO> notCheckedReviews = new ArrayList<>();
		
		for(ClientReview r : reviews) {
			ClientReviewResponseDTO response = new ClientReviewResponseDTO();
			response.setClient(r.getClient());
			response.setId(r.getId());
			response.setInstructor(r.getInstructor());
			response.setReason(r.getPenaltySuggestionReason());
			notCheckedReviews.add(response);
			
		}
		
		return notCheckedReviews;
	}

}
