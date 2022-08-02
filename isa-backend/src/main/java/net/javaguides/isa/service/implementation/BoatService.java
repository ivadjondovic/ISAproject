package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.BoatRequest;
import net.javaguides.isa.dto.request.NavigationEquipmentRequest;
import net.javaguides.isa.dto.response.BoatResponse;
import net.javaguides.isa.dto.response.NavigationEquipmentResponse;
import net.javaguides.isa.model.*;
import net.javaguides.isa.repository.*;
import net.javaguides.isa.service.IBoatService;
import net.javaguides.isa.utils.ReservationType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoatService implements IBoatService {

    private final IBoatRepository _boatRepository;
    private final IBoatOwnerRepository _boatOwnerRepository;
    private final IQuickReservationRepository _quickReservationRepository;
    private final IReservationRepository _reservationRepository;
    private final IClientRepository _clientRepository;
    private final INavigationEquipmentRepository _navigationEquipmentRepository;

    public BoatService(IBoatRepository boatRepository, IBoatOwnerRepository boatOwnerRepository, IQuickReservationRepository quickReservationRepository,
                       IReservationRepository reservationRepository, IClientRepository clientRepository, INavigationEquipmentRepository navigationEquipmentRepository) {
        _boatRepository = boatRepository;
        _boatOwnerRepository = boatOwnerRepository;
        _quickReservationRepository = quickReservationRepository;
        _reservationRepository = reservationRepository;
        _clientRepository = clientRepository;
        _navigationEquipmentRepository = navigationEquipmentRepository;

    }

    @Override
    public BoatResponse getBoat(Long id) {
        Boat boat = _boatRepository.findOneById(id);
        return mapBoatToBoatResponse(boat);
    }

    @Override
    public List<BoatResponse> getOwnersBoats(Long id) {
        List<Boat> boats = _boatRepository.findAll();
        List<BoatResponse> finalResponse = new ArrayList<>();
        for(Boat b: boats){
            if(b.getBoatOwner().getId().equals(id)){
                finalResponse.add(mapBoatToBoatResponse(b));
            }
        }
        return finalResponse;
    }

    @Override
    public BoatResponse createBoat(BoatRequest request)  throws Exception {
        Boat boat = new Boat();
        List<Boat> boats = _boatRepository.findAll();
        boat.setName(request.getName());
        for(Boat b : boats) {
            if(b.getName().equals(boat.getName())) {
                throw new Exception("The boat you entered already exists.");
            }
        }
        boat.setAddress(request.getAddress());
        boat.setDescription(request.getDescription());
        boat.setRulesOfConduct(request.getRulesOfConduct());
        boat.setPriceList(request.getPriceList());
        boat.setCapacity(request.getCapacity());
        boat.setCancellationReservationFee(request.getCancellationReservationFee());
        boat.setEngineNumber(request.getEngineNumber());
        boat.setEnginePower(request.getEnginePower());
        boat.setLength(request.getLength());
        boat.setFishingEquipment(request.getFishingEquipment());
        boat.setMaxSpeed(request.getMaxSpeed());
        boat.setType(request.getType());

        BoatOwner boatOwner = _boatOwnerRepository.findOneById(request.getBoatOwnerId());
        boat.setBoatOwner(boatOwner);
        Boat savedBoat = _boatRepository.save(boat);

        boatOwner.getBoats().add(boat);
        _boatOwnerRepository.save(boatOwner);

        for(NavigationEquipmentRequest n : request.getNavigationEquipmentRequests()) {
            NavigationEquipment navigationEquipment = new NavigationEquipment();
            if(n.getName().equals(net.javaguides.isa.utils.NavigationEquipment.FISH_FINDER)){
                navigationEquipment.setName(net.javaguides.isa.utils.NavigationEquipment.FISH_FINDER);
                navigationEquipment.setBoat(savedBoat);
            }else if(n.getName().equals(net.javaguides.isa.utils.NavigationEquipment.RADAR)){
                navigationEquipment.setName(net.javaguides.isa.utils.NavigationEquipment.RADAR);
                navigationEquipment.setBoat(savedBoat);
            }else if(n.getName().equals(net.javaguides.isa.utils.NavigationEquipment.GPS)){
                navigationEquipment.setName(net.javaguides.isa.utils.NavigationEquipment.GPS);
                navigationEquipment.setBoat(savedBoat);
            }else if(n.getName().equals(net.javaguides.isa.utils.NavigationEquipment.VHF_RADIO)){
                navigationEquipment.setName(net.javaguides.isa.utils.NavigationEquipment.VHF_RADIO);
                navigationEquipment.setBoat(savedBoat);
            }
        }

        return mapBoatToBoatResponse(boat);
    }

    @Override
    public BoatResponse updateBoat(BoatRequest request, Long id)  throws Exception {
        Boat boat = _boatRepository.findOneById(id);

        boolean hasReservation = false;

        List<QuickReservation> allQuickReservations = _quickReservationRepository.findAll();
        for(QuickReservation qr: allQuickReservations) {
            if(qr.getReservationType().equals(ReservationType.BOAT)){
                if(qr.getServiceId().equals(boat.getId())){
                    hasReservation = true;
                }
            }
        }

        List<Reservation> allReservation = _reservationRepository.findAll();
        for(Reservation r: allReservation) {
            if(r.getReservationType().equals(ReservationType.BOAT)){

                if(r.getBoat().getId().equals(boat.getId())){
                    hasReservation = true;
                }
            }
        }

        if(hasReservation){
            throw new Exception("The cottage has reservations and cannot be deleted.");
        }
        boat.setAddress(request.getAddress());
        boat.setDescription(request.getDescription());
        boat.setAddress(request.getAddress());
        boat.setPriceList(request.getPriceList());
        boat.setName(request.getName());
        boat.setRulesOfConduct(request.getRulesOfConduct());
        boat.setCapacity(request.getCapacity());
        boat.setCancellationReservationFee(request.getCancellationReservationFee());
        boat.setEngineNumber(request.getEngineNumber());
        boat.setEnginePower(request.getEnginePower());
        boat.setLength(request.getLength());
        boat.setFishingEquipment(request.getFishingEquipment());
        boat.setMaxSpeed(request.getMaxSpeed());
        boat.setType(request.getType());

        Boat savedBoat = _boatRepository.save(boat);

        return mapBoatToBoatResponse(savedBoat);
    }

    @Override
    public void deleteBoat(Long id)  throws Exception {

        Boat boat = _boatRepository.findOneById(id);
        boolean hasReservation = false;
        List<QuickReservation> allQuickReservations = _quickReservationRepository.findAll();
        for(QuickReservation qr: allQuickReservations) {
            if(qr.getCottage().getId().equals(boat.getId())){
                hasReservation = true;
            }
        }

        List<Reservation> allReservation = _reservationRepository.findAll();
        for(Reservation r: allReservation) {
            if(r.getCottage().getId().equals(boat.getId())){
                hasReservation = true;
            }
        }

        if(hasReservation){
            throw new Exception("The boat has reservations and cannot be deleted.");
        }
        List<Client> allClients = _clientRepository.findAll();
        for(Client client: allClients) {
            for(Cottage c: client.getSubscribedCottages()){
                if(c.getId().equals(boat.getId())){
                    client.getSubscribedCottages().remove(c);
                }
            }
        }
        _boatRepository.delete(boat);
    }

    public BoatResponse mapBoatToBoatResponse(Boat boat) {

        BoatResponse boatResponse = new BoatResponse();
        boatResponse.setId(boat.getId());
        boatResponse.setAddress(boat.getAddress());
        boatResponse.setDescription(boat.getDescription());
        boatResponse.setPriceList(boat.getPriceList());
        boatResponse.setName(boat.getName());
        boatResponse.setRulesOfConduct(boat.getRulesOfConduct());
        boatResponse.setCapacity(boat.getCapacity());
        boatResponse.setCancellationReservationFee(boat.getCancellationReservationFee());
        boatResponse.setEngineNumber(boat.getEngineNumber());
        boatResponse.setEnginePower(boat.getEnginePower());
        boatResponse.setLength(boat.getLength());
        boatResponse.setFishingEquipment(boat.getFishingEquipment());
        boatResponse.setMaxSpeed(boat.getMaxSpeed());
        boatResponse.setType(boat.getType());
        List<NavigationEquipmentResponse> navigationEquipmentResponses = new ArrayList<>();
        for(NavigationEquipment ne: boat.getNavigationEquipments()){
            NavigationEquipmentResponse navigationEquipmentResponse = new NavigationEquipmentResponse();
            navigationEquipmentResponse.setId(ne.getId());
            navigationEquipmentResponse.setName(ne.getName());
            navigationEquipmentResponses.add(navigationEquipmentResponse);

        }
        boatResponse.setNavigationEquipmentResponseList(navigationEquipmentResponses);
        return boatResponse;
    }

}
