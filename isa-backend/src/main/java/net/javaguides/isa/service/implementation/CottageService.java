package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.AdditionalServiceRequest;
import net.javaguides.isa.dto.request.CottageRequest;
import net.javaguides.isa.dto.response.CottageResponse;
import net.javaguides.isa.model.*;
import net.javaguides.isa.repository.*;
import net.javaguides.isa.service.ICottageService;
import net.javaguides.isa.service.IReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CottageService implements ICottageService {

    private final ICottageRepository _cottageRepository;
    private final ICottageOwnerRepository _cottageOwnerRepository;
    private final IClientRepository _clientRepository;
    private final IQuickReservationRepository _quickReservationRepository;
    private final IReservationRepository _reservationRepository;

    public CottageService(ICottageRepository cottageRepository, ICottageOwnerRepository cottageOwnerRepository,
                          IClientRepository clientRepository, IQuickReservationRepository quickReservationRepository,
                          IReservationRepository reservationRepository) {
        _cottageRepository = cottageRepository;
        _cottageOwnerRepository = cottageOwnerRepository;
        _clientRepository = clientRepository;
        _quickReservationRepository = quickReservationRepository;
        _reservationRepository = reservationRepository;
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

        boolean hasReservation = false;
        List<QuickReservation> allQuickReservations = _quickReservationRepository.findAll();
        for(QuickReservation qr: allQuickReservations) {
            if(qr.getCottage().getId().equals(cottage.getId())){
                hasReservation = true;
            }
        }

        List<Reservation> allReservation = _reservationRepository.findAll();
        for(Reservation r: allReservation) {
            if(r.getCottage().getId().equals(cottage.getId())){
                hasReservation = true;
            }
        }

        if(hasReservation){
            throw new Exception("The cottage has reservations and cannot be deleted.");
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
        boolean hasReservation = false;
        List<QuickReservation> allQuickReservations = _quickReservationRepository.findAll();
        for(QuickReservation qr: allQuickReservations) {
            if(qr.getCottage().getId().equals(cottage.getId())){
                hasReservation = true;
            }
        }

        List<Reservation> allReservation = _reservationRepository.findAll();
        for(Reservation r: allReservation) {
            if(r.getCottage().getId().equals(cottage.getId())){
                hasReservation = true;
            }
        }

        if(hasReservation){
            throw new Exception("The cottage has reservations and cannot be deleted.");
        }
        List<Client> allClients = _clientRepository.findAll();
        for(Client client: allClients) {
            for(Cottage c: client.getSubscribedCottages()){
                if(c.getId().equals(cottage.getId())){
                    client.getSubscribedCottages().remove(c);
                }
            }
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
