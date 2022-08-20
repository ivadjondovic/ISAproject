package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.CottageDTO;
import com.isa.project.model.Cottage;

public interface CottageService {
	
	public Cottage createCottage(CottageDTO dto);
	public List<Cottage> getAll();
	public Cottage getById(Long id);
	public List<Cottage> search(String searchTerm);

}
