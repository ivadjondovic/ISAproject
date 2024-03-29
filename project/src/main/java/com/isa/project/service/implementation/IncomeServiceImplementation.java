package com.isa.project.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.IncomeBetweenDTO;
import com.isa.project.dto.IncomeResponseDTO;
import com.isa.project.model.Admin;
import com.isa.project.model.AdminIncome;
import com.isa.project.model.BoatReservation;
import com.isa.project.model.CottageReservation;
import com.isa.project.model.FishingLessonReservation;
import com.isa.project.model.Instructor;
import com.isa.project.model.InstructorIncome;
import com.isa.project.model.QuickBoatReservation;
import com.isa.project.model.QuickCottageReservation;
import com.isa.project.model.QuickFishingLessonReservation;
import com.isa.project.repository.AdminIncomeRepository;
import com.isa.project.repository.BoatReservationRepository;
import com.isa.project.repository.CottageReservationRepository;
import com.isa.project.repository.FishingLessonReservationRepository;
import com.isa.project.repository.InstructorIncomeRepository;
import com.isa.project.repository.QuickBoatReservationRepository;
import com.isa.project.repository.QuickCottageReservationRepository;
import com.isa.project.repository.QuickFishingLessonReservationRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.IncomeService;

@Service
public class IncomeServiceImplementation implements IncomeService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminIncomeRepository adminIncomeRepository;
	
	@Autowired
	private CottageReservationRepository cottageReservationRepository;
	
	@Autowired
	private BoatReservationRepository boatReservationRepository;
	
	@Autowired
	private FishingLessonReservationRepository fishingLessonReservationRepository;
	
	@Autowired
	private QuickCottageReservationRepository quickCottageReservationRepository;
	
	@Autowired
	private QuickBoatReservationRepository quickBoatReservationRepository;
	
	@Autowired
	private QuickFishingLessonReservationRepository quickFishingLessonReservationRepository;
	
	@Autowired
	private InstructorIncomeRepository incomeRepository;
	

	@Override
	public List<IncomeResponseDTO> getReservationIncome(Long adminId) {
		
		List<IncomeResponseDTO> income = new ArrayList<>();
		
		Admin admin = (Admin) userRepository.findById(adminId).get();
		
		List<AdminIncome> adminIncome = adminIncomeRepository.findByAdmin(admin);
		
		for(AdminIncome ai : adminIncome) {
			IncomeResponseDTO dto = new IncomeResponseDTO();
			dto.setId(ai.getId());
			dto.setType(ai.getReservationType());
			dto.setIncome(ai.getIncome());
			if(ai.getReservationType().equals("Cottage")) {
				CottageReservation cr = cottageReservationRepository.findById(ai.getReservationId()).get();
				dto.setStartDate(cr.getStartDate());
				dto.setEndDate(cr.getEndDate());
				dto.setPrice(cr.getPrice());
				dto.setEntityName(cr.getCottage().getName());
			} 
			if(ai.getReservationType().equals("Boat")) {
				BoatReservation br = boatReservationRepository.findById(ai.getReservationId()).get();
				dto.setStartDate(br.getStartDate());
				dto.setEndDate(br.getEndDate());
				dto.setPrice(br.getPrice());
				dto.setEntityName(br.getBoat().getName());
			} 
			if(ai.getReservationType().equals("Fishing")) {
				FishingLessonReservation fr = fishingLessonReservationRepository.findById(ai.getReservationId()).get();
				dto.setStartDate(fr.getStartDate());
				dto.setEndDate(fr.getEndDate());
				dto.setPrice(fr.getPrice());
				dto.setEntityName(fr.getFishingLesson().getName());
			} 
			if(ai.getReservationType().equals("QuickCottage")) {
				QuickCottageReservation cr = quickCottageReservationRepository.findById(ai.getReservationId()).get();
				dto.setStartDate(cr.getStartDate());
				dto.setEndDate(cr.getEndDate());
				dto.setPrice(cr.getPrice());
				dto.setEntityName(cr.getCottage().getName());
			} 
			if(ai.getReservationType().equals("QuickBoat")) {
				QuickBoatReservation br = quickBoatReservationRepository.findById(ai.getReservationId()).get();
				dto.setStartDate(br.getStartDate());
				dto.setEndDate(br.getEndDate());
				dto.setPrice(br.getPrice());
				dto.setEntityName(br.getBoat().getName());
			} 
			if(ai.getReservationType().equals("QuickFishing")) {
				QuickFishingLessonReservation fr = quickFishingLessonReservationRepository.findById(ai.getReservationId()).get();
				dto.setStartDate(fr.getStartDate());
				dto.setEndDate(fr.getEndDate());
				dto.setPrice(fr.getPrice());
				dto.setEntityName(fr.getFishingLesson().getName());
			} 
			income.add(dto);
			
		}
		
		return income;
	}


	@Override
	public List<IncomeResponseDTO> getReservationIncomeBetween(IncomeBetweenDTO dto) {
		List<IncomeResponseDTO> income = getReservationIncome(dto.getAdminId());
		
		List<IncomeResponseDTO> filtered = income.stream().filter(i -> i.getStartDate().compareTo(dto.getDateFrom()) >= 0 &&
																	i.getEndDate().compareTo(dto.getDateTo()) <= 0).collect(Collectors.toList());
		
		return filtered;
	}


	@Override
	public List<IncomeResponseDTO> getInstructorIncome(Long instructorId) {
		List<IncomeResponseDTO> income = new ArrayList<>();
		
		Instructor instructor = (Instructor) userRepository.findById(instructorId).get();
		
		List<InstructorIncome> instructorIncome = incomeRepository.findByInstructor(instructor);
		
		for(InstructorIncome ai : instructorIncome) {
			IncomeResponseDTO dto = new IncomeResponseDTO();
			dto.setId(ai.getId());
			dto.setType(ai.getReservationType());
			dto.setIncome(ai.getIncome());
			if(ai.getReservationType().equals("Fishing lesson reservation")) {
				FishingLessonReservation fr = fishingLessonReservationRepository.findById(ai.getReservationId()).get();
				dto.setStartDate(fr.getStartDate());
				dto.setEndDate(fr.getEndDate());
				dto.setPrice(fr.getPrice());
				dto.setEntityName(fr.getFishingLesson().getName());
			} 
			if(ai.getReservationType().equals("Quick fishing lesson reservation")) {
				QuickFishingLessonReservation fr = quickFishingLessonReservationRepository.findById(ai.getReservationId()).get();
				dto.setStartDate(fr.getStartDate());
				dto.setEndDate(fr.getEndDate());
				dto.setPrice(fr.getPrice());
				dto.setEntityName(fr.getFishingLesson().getName());
			} 
			income.add(dto);
			
		}
		
		return income;
	}


	@Override
	public List<IncomeResponseDTO> getInstructorIncomeBetween(IncomeBetweenDTO dto) {
		List<IncomeResponseDTO> income = getInstructorIncome(dto.getAdminId());
		
		List<IncomeResponseDTO> filtered = income.stream().filter(i -> i.getStartDate().compareTo(dto.getDateFrom()) >= 0 &&
																	i.getEndDate().compareTo(dto.getDateTo()) <= 0).collect(Collectors.toList());
		
		return filtered;
	}

	
	
}
