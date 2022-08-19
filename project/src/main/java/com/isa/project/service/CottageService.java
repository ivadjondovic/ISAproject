package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.CottageDTO;
import com.isa.project.model.Cottage;

public interface CottageService {
	
	public Cottage createCottage(CottageDTO dto);
	
	public List<Cottage> getAll();

}
