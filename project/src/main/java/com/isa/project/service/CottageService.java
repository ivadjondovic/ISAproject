package com.isa.project.service;

import java.util.List;

import com.isa.project.dto.CottageDTO;
import com.isa.project.dto.DateSearchDTO;
import com.isa.project.dto.ReservationSearchDTO;
import com.isa.project.dto.SearchParamsDTO;
import com.isa.project.dto.SortDTO;
import com.isa.project.model.Cottage;

public interface CottageService {
	
	public Cottage createCottage(CottageDTO dto);
	public List<Cottage> getAll();
	public Cottage getById(Long id);
	public List<Cottage> search(String searchTerm);
	public List<Cottage> sort(SortDTO dto);
	public List<Cottage> getAvailableCottages(ReservationSearchDTO dto);
	public List<Cottage> cottagesAvailableForCertainDate(DateSearchDTO dto);
	public List<Cottage> getCottagesByClientSubscription(Long clientId);
	public List<Cottage> sortAvailableCottages(ReservationSearchDTO dto);
	public List<Cottage> searchByMoreParams(SearchParamsDTO dto);
	public void delete(Long id);

}
