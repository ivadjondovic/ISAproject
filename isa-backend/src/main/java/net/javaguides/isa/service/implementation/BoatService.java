package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.BoatRequest;
import net.javaguides.isa.dto.response.BoatResponse;
import net.javaguides.isa.model.Boat;
import net.javaguides.isa.model.BoatOwner;
import net.javaguides.isa.repository.IBoatOwnerRepository;
import net.javaguides.isa.repository.IBoatRepository;
import net.javaguides.isa.repository.IUserRepository;
import net.javaguides.isa.service.IBoatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoatService implements IBoatService {

    private final IBoatRepository _boatRepository;
    private final IBoatOwnerRepository _boatOwnerRepository;

    public BoatService(IBoatRepository boatRepository, IBoatOwnerRepository boatOwnerRepository) {
        _boatRepository = boatRepository;
        _boatOwnerRepository = boatOwnerRepository;
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
        _boatRepository.save(boat);

        boatOwner.getBoats().add(boat);
        _boatOwnerRepository.save(boatOwner);

        return mapBoatToBoatResponse(boat);
    }

    @Override
    public BoatResponse updateBoat(BoatRequest request, Long id)  throws Exception {
        Boat boat = _boatRepository.findOneById(id);

        if(!boat.getReservations().isEmpty()){
            throw new Exception("The boat has reservations and cannot be updated.");
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

        if(!boat.getReservations().isEmpty()){
            throw new Exception("The boat has reservations and cannot be deleted.");
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
        return boatResponse;
    }

}
