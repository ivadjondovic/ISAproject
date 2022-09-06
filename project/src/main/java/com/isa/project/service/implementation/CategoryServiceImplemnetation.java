package com.isa.project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.project.model.Category;
import com.isa.project.model.Client;
import com.isa.project.repository.LoyaltyProgramRepository;
import com.isa.project.repository.UserRepository;
import com.isa.project.service.CategoryService;


@Service
public class CategoryServiceImplemnetation implements CategoryService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;
	
	
	@Override
	public Category findCategoryByUser(Long id) {
		
		Client client = (Client) userRepository.findById(id).get();
		
		if(loyaltyProgramRepository.findAll().isEmpty()) {
			return null;
		}
		Category category = new Category();
		category.setCategory(client.getCategory().getCategory());
		category.setDiscount(client.getCategory().getDiscount());
		
		return category;
	}

}
