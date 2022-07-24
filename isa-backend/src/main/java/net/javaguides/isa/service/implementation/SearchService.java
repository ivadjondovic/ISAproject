package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.response.CottageResponse;
import net.javaguides.isa.dto.response.SearchOwnersCottagesResponse;
import net.javaguides.isa.model.Cottage;
import net.javaguides.isa.repository.ICottageRepository;
import net.javaguides.isa.service.ISearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService implements ISearchService {

    private final ICottageRepository _cottageRepository;
    private final CottageService _cottageService;

    public SearchService(ICottageRepository cottageRepository, CottageService cottageService) {
        _cottageRepository = cottageRepository;
        _cottageService = cottageService;
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

    private SearchOwnersCottagesResponse mapCottagesToSearchResponse(List<CottageResponse> cottageResponses) {
        SearchOwnersCottagesResponse searchResponse = new SearchOwnersCottagesResponse();
        searchResponse.setCottages(cottageResponses);
        return searchResponse;
    }
}
