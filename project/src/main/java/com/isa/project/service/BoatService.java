package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.BoatDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.Boat;

public interface BoatService {

	public Boat createBoat(BoatDTO dto);
	public List<Boat> getAll();
	public Boat getById(Long id);
	public List<Boat> search(String searchTerm);
	public List<Boat> sort(SortDTO dto);
}
