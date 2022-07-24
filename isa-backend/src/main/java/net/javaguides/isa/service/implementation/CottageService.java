package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.CottageRequest;
import net.javaguides.isa.dto.response.CottageResponse;
import net.javaguides.isa.model.Cottage;
import net.javaguides.isa.model.CottageOwner;
import net.javaguides.isa.repository.ICottageOwnerRepository;
import net.javaguides.isa.repository.ICottageRepository;
import net.javaguides.isa.repository.IUserRepository;
import net.javaguides.isa.service.ICottageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CottageService implements ICottageService {

    private final ICottageRepository _cottageRepository;
    private final ICottageOwnerRepository _cottageOwnerRepository;

    public CottageService(ICottageRepository cottageRepository, ICottageOwnerRepository cottageOwnerRepository) {
        _cottageRepository = cottageRepository;
        _cottageOwnerRepository = cottageOwnerRepository;
    }

    @Override
    public CottageResponse getCottage(Long id) {
        Cottage cottage = _cottageRepository.findOneById(id);
        return mapCottageToCottageResponse(cottage);
    }

    @Override
    public List<CottageResponse> getOwnersCottages(Long id) {
        List<Cottage> cottages = _cottageRepository.findAll();
        List<CottageResponse> finalResponse = new ArrayList<>();
        for(Cottage c: cottages){
            if(c.getCottageOwner().getId().equals(id)){
                finalResponse.add(mapCottageToCottageResponse(c));
            }
        }
        return finalResponse;
    }

    @Override
    public CottageResponse createCottage(CottageRequest request)  throws Exception {
        Cottage cottage = new Cottage();
        List<Cottage> cottages = _cottageRepository.findAll();
        cottage.setName(request.getName());
        for(Cottage c : cottages) {
            if(c.getName().equals(cottage.getName())) {
                throw new Exception("The cottage you entered already exists.");
            }
        }
        cottage.setAddress(request.getAddress());
        cottage.setDescription(request.getDescription());
        cottage.setRulesOfConduct(request.getRulesOfConduct());
        cottage.setPriceList(request.getPriceList());
        cottage.setNumberOfRooms(request.getNumberOfRooms());
        cottage.setNumberOfBedsPerRoom(request.getNumberOfBedsPerRoom());
        cottage.setOtherInformation(request.getOtherInformation());

        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(request.getCottageOwnerId());
        cottage.setCottageOwner(cottageOwner);
        _cottageRepository.save(cottage);

        cottageOwner.getCottages().add(cottage);
        _cottageOwnerRepository.save(cottageOwner);
        return mapCottageToCottageResponse(cottage);
    }

    @Override
    public CottageResponse updateCottage(CottageRequest request, Long id)  throws Exception {
        Cottage cottage = _cottageRepository.findOneById(id);

        if(!cottage.getReservations().isEmpty()){
            throw new Exception("The cottage has reservations and cannot be updated.");
        }
        cottage.setAddress(request.getAddress());
        cottage.setDescription(request.getDescription());
        cottage.setAddress(request.getAddress());
        cottage.setPriceList(request.getPriceList());
        cottage.setName(request.getName());
        cottage.setRulesOfConduct(request.getRulesOfConduct());
        cottage.setOtherInformation(request.getOtherInformation());
        cottage.setNumberOfRooms(request.getNumberOfRooms());
        cottage.setNumberOfBedsPerRoom(request.getNumberOfBedsPerRoom());
        Cottage savedCottage = _cottageRepository.save(cottage);

        return mapCottageToCottageResponse(savedCottage);
    }

    @Override
    public void deleteCottage(Long id)  throws Exception {
        Cottage cottage = _cottageRepository.findOneById(id);

        if(!cottage.getReservations().isEmpty()){
            throw new Exception("The cottage has reservations and cannot be deleted.");
        }
        _cottageRepository.delete(cottage);
    }

    public CottageResponse mapCottageToCottageResponse(Cottage cottage) {

        CottageResponse cottageResponse = new CottageResponse();
        cottageResponse.setId(cottage.getId());
        cottageResponse.setAddress(cottage.getAddress());
        cottageResponse.setDescription(cottage.getDescription());
        cottageResponse.setPriceList(cottage.getPriceList());
        cottageResponse.setName(cottage.getName());
        cottageResponse.setRulesOfConduct(cottage.getRulesOfConduct());
        cottageResponse.setOtherInformation(cottage.getOtherInformation());
        cottageResponse.setNumberOfRooms(cottage.getNumberOfRooms());
        cottageResponse.setNumberOfBedsPerRoom(cottage.getNumberOfBedsPerRoom());
        return cottageResponse;
    }

}
