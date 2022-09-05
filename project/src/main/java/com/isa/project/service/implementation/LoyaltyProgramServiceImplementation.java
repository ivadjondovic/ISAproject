package com.isa.project.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.dto.LoyaltyProgramDTO;
import com.isa.project.model.BoatOwner;
import com.isa.project.model.Category;
import com.isa.project.model.Client;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.Instructor;
import com.isa.project.model.LoyaltyProgram;
import com.isa.project.model.User;
import com.isa.project.repository.CategoryRepository;
import com.isa.project.repository.LoyaltyProgramRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.LoyaltyProgramService;

@Service
public class LoyaltyProgramServiceImplementation implements LoyaltyProgramService {

	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public LoyaltyProgram createProgram(LoyaltyProgramDTO dto) {
		LoyaltyProgram program = new LoyaltyProgram();

		List<User> users = userRepository.findByStatusAndDeleted("Activated", "false");
		
		if(!loyaltyProgramRepository.findAll().isEmpty()) {
			for(User u : users) {
				if(u.getUserType().equals("ROLE_CLIENT")) {
					Client c = (Client) userRepository.findById(u.getId()).get();
					c.setCategory(null);
					userRepository.save(c);
				}
				if(u.getUserType().equals("ROLE_BOATOWNER")) {
					BoatOwner b = (BoatOwner) userRepository.findById(u.getId()).get();
					b.setCategory(null);
					userRepository.save(b);
				}
				if(u.getUserType().equals("ROLE_COTTAGEOWNER")) {
					CottageOwner c = (CottageOwner) userRepository.findById(u.getId()).get();
					c.setCategory(null);
					userRepository.save(c);
				}
				if(u.getUserType().equals("ROLE_INSTRUCTOR")) {
					Instructor i = (Instructor) userRepository.findById(u.getId()).get();
					i.setCategory(null);
					userRepository.save(i);
				}
				
			}
			categoryRepository.deleteAll();
			loyaltyProgramRepository.deleteAll();	
		}
		
		program.setClientPoints(dto.getClientPointsPerReservation());
		program.setOwnerPoints(dto.getOwnerPointsPerReservation());
		
		LoyaltyProgram savedProgram = loyaltyProgramRepository.save(program);
		
		Category regular = new Category();
		regular.setBonus(0.0);
		regular.setCategory("regular");
		regular.setDiscount(0.0);
		regular.setPointsNeeded(0);
		regular.setLoyaltyProgram(savedProgram);
		
		Category silver = new Category();
		silver.setBonus(dto.getOwnerBonusForSilver());
		silver.setCategory("silver");
		silver.setDiscount(dto.getClientDiscountForSilver());
		silver.setPointsNeeded(dto.getPointsNeededForSilver());
		silver.setLoyaltyProgram(savedProgram);
		
		Category gold = new Category();
		gold.setBonus(dto.getOwnerBonusForGold());
		gold.setCategory("gold");
		gold.setDiscount(dto.getClientDiscountForGold());
		gold.setPointsNeeded(dto.getPointsNeededForGold());
		gold.setLoyaltyProgram(savedProgram);
		
		Category savedRegular = categoryRepository.save(regular);
		Category savedSilver = categoryRepository.save(silver);
		Category savedGold = categoryRepository.save(gold);
		
		Set<Category> categories = new HashSet<>();
		categories.add(savedRegular);
		categories.add(savedGold);
		categories.add(savedSilver);
		
		savedProgram.setCategories(categories);
		
		for(User u : users) {
			if(u.getUserType().equals("ROLE_CLIENT")) {
				Client c = (Client) userRepository.findById(u.getId()).get();
				c.setPoints(0);
				c.setCategory(savedRegular);
				userRepository.save(c);
			}
			if(u.getUserType().equals("ROLE_BOATOWNER")) {
				BoatOwner b = (BoatOwner) userRepository.findById(u.getId()).get();
				b.setPoints(0);
				b.setCategory(savedRegular);
				userRepository.save(b);
			}
			if(u.getUserType().equals("ROLE_COTTAGEOWNER")) {
				CottageOwner c = (CottageOwner) userRepository.findById(u.getId()).get();
				c.setPoints(0);
				c.setCategory(savedRegular);
				userRepository.save(c);
			}
			if(u.getUserType().equals("ROLE_INSTRUCTOR")) {
				Instructor i = (Instructor) userRepository.findById(u.getId()).get();
				i.setPoints(0);
				i.setCategory(savedRegular);
				userRepository.save(i);
			}
			
		}
		
	
		
		return loyaltyProgramRepository.save(savedProgram);
	}

}
