package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.ClientReviewDTO;
import com.isa.project.dto.ClientReviewResponseDTO;
import com.isa.project.model.Admin;
import com.isa.project.model.Category;
import com.isa.project.model.Client;
import com.isa.project.model.ClientReview;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.Instructor;
import com.isa.project.model.InstructorIncome;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.model.User;
import com.isa.project.repository.CategoryRepository;
import com.isa.project.repository.ClientReviewRepository;
import com.isa.project.repository.FishingLessonReservationRepository;
import com.isa.project.repository.InstructorIncomeRepository;
import com.isa.project.repository.LoyaltyProgramRepository;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
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
	
	@Autowired
	private FishingLessonReservationRepository fishingLessonReservationRepository;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Autowired
	private InstructorIncomeRepository incomeRepository;

	
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
		
		InstructorIncome instructorIncome = new InstructorIncome();
		instructorIncome.setInstructor(instructor);
		instructorIncome.setReservationId(dto.getId());
		instructorIncome.setReservationType(dto.getReservationType());
		
		Double income = 0.0;
		
		if(dto.getReservationType().equals("Fishing lesson reservation")) {
			FishingLessonReservation lesson = fishingLessonReservationRepository.findById(dto.getId()).get();
			income = lesson.getPrice();
			lesson.setInstructorCalculated(true);
			fishingLessonReservationRepository.save(lesson);
		}
		if(dto.getReservationType().equals("Quick fishing lesson reservation")) {
			QuickFishingLessonReservation quick = quickFishingLessonReservationRepository.findById(dto.getId()).get();
			income = quick.getPrice();
			quick.setInstructorCalculated(true);
			quickFishingLessonReservationRepository.save(quick);
		}
		
		List<User> admins = userRepository.findAll().stream().filter(a -> a.getUserType().equals("ROLE_ADMIN")).collect(Collectors.toList());
		for(User a : admins) {
			Admin admin = (Admin) userRepository.findById(a.getId()).get();
			income -= income * admin.getIncomePercentage();
		}
	
		
		if(dto.getAutomaticPenalty() == false && dto.getPenaltySuggestion() == false && !loyaltyProgramRepository.findAll().isEmpty()) {
			int clientPoints = loyaltyProgramRepository.findAll().get(0).getClientPoints();
			int clientNewPoints = client.getPoints() + clientPoints;
			client.setPoints(clientNewPoints);
			Category silver = categoryRepository.findByCategory("silver");
			Category gold = categoryRepository.findByCategory("gold");
			if(clientNewPoints >= silver.getPointsNeeded()) {
				client.setCategory(silver);
			}
			if(clientNewPoints >= gold.getPointsNeeded()) {
				client.setCategory(gold);
			}
			userRepository.save(client);
			
			int instructorPoints = loyaltyProgramRepository.findAll().get(0).getOwnerPoints();
			int instructorNewPoints = instructor.getPoints() + instructorPoints;
			instructor.setPoints(instructorNewPoints);
			if(instructorNewPoints >= silver.getPointsNeeded()) {
				instructor.setCategory(silver);
			}
			if(instructorNewPoints >= gold.getPointsNeeded()) {
				instructor.setCategory(gold);
			}
			userRepository.save(instructor);
			
			instructorIncome.setIncome(income + instructor.getCategory().getBonus());
	
		} else
			instructorIncome.setIncome(income);
		
		incomeRepository.save(instructorIncome);
		
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
