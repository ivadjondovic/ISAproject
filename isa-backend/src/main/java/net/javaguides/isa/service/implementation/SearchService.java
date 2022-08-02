package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.response.BoatResponse;
import net.javaguides.isa.dto.response.CottageResponse;
import net.javaguides.isa.dto.response.SearchOwnersBoatsResponse;
import net.javaguides.isa.dto.response.SearchOwnersCottagesResponse;
import net.javaguides.isa.model.Boat;
import net.javaguides.isa.model.Cottage;
import net.javaguides.isa.repository.IBoatRepository;
import net.javaguides.isa.repository.ICottageRepository;
import net.javaguides.isa.service.ISearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService implements ISearchService {

    private final ICottageRepository _cottageRepository;
    private final IBoatRepository _boatRepository;

    private final CottageService _cottageService;
    private final BoatService _boatService;

    public SearchService(IBoatRepository boatRepository, ICottageRepository cottageRepository, CottageService cottageService, BoatService boatService) {
        _boatRepository = boatRepository;
        _cottageRepository = cottageRepository;
        _cottageService = cottageService;
        _boatService = boatService;
    }

    @Override
    public SearchOwnersCottagesResponse searchOwnersCottages(String ownerId, String name) {
        List<Cottage> cottages = filteredCottages(name, ownerId);
        List<CottageResponse> cottageResponses = new ArrayList<>();
        for(Cottage cottage: cottages){
            cottageResponses.add(_cottageService.mapCottageToCottageResponse(cottage));
        }
        return mapCottagesToSearchResponse(cottageResponses);
    }

    @Override
    public SearchOwnersBoatsResponse searchOwnersBoats(String ownerId, String name) {
        List<Boat> boats = filteredBoats(name, ownerId);
        List<BoatResponse> boatResponses = new ArrayList<>();
        for(Boat boat: boats){
            boatResponses.add(_boatService.mapBoatToBoatResponse(boat));
        }
        return mapBoatsToSearchResponse(boatResponses);
    }

    private List<Cottage> filteredCottages(String name, String ownerId) {
        List<Cottage> allCottages = _cottageRepository.findAll();
        return allCottages
                .stream()
                .filter(cottage -> {
                    if(name != "") {
                        return cottage.getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .filter(cottage -> {
                    if(ownerId != ""){
                        return cottage.getCottageOwner().getId().toString().equals(ownerId);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Boat> filteredBoats(String name, String ownerId) {
        List<Boat> allBoats = _boatRepository.findAll();
        return allBoats
                .stream()
                .filter(boat -> {
                    if(name != "") {
                        return boat.getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .filter(boat -> {
                    if(ownerId != ""){
                        return boat.getBoatOwner().getId().toString().equals(ownerId);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    private SearchOwnersCottagesResponse mapCottagesToSearchResponse(List<CottageResponse> cottageResponses) {
        SearchOwnersCottagesResponse searchResponse = new SearchOwnersCottagesResponse();
        searchResponse.setCottages(cottageResponses);
        return searchResponse;
    }

    private SearchOwnersBoatsResponse mapBoatsToSearchResponse(List<BoatResponse> boatResponses) {
        SearchOwnersBoatsResponse searchResponse = new SearchOwnersBoatsResponse();
        searchResponse.setBoats(boatResponses);
        return searchResponse;
    }
}
