package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.AdditionalServiceRequest;
import net.javaguides.isa.dto.request.QuickReservationRequest;
import net.javaguides.isa.model.*;
import net.javaguides.isa.repository.*;
import net.javaguides.isa.service.IQuickReservationService;
import net.javaguides.isa.utils.ReservationType;
import org.springframework.stereotype.Service;

@Service
public class QuickReservationService implements IQuickReservationService {

    private final IQuickReservationRepository _quickReservationRepository;
    private final IBoatRepository _boatRepository;
    private final ICottageRepository _cottageRepository;
    private final IAdditionalServiceRepository _additionalServiceRepository;

    public QuickReservationService(IQuickReservationRepository quickReservationRepository, IBoatRepository boatRepository,
                                   ICottageRepository cottageRepository, IAdditionalServiceRepository additionalServiceRepository) {
        _quickReservationRepository = quickReservationRepository;
        _boatRepository = boatRepository;
        _cottageRepository = cottageRepository;
        _additionalServiceRepository = additionalServiceRepository;
    }

    @Override
    public boolean createQuickReservation(QuickReservationRequest request) {
        QuickReservation quickReservation = new QuickReservation();
        quickReservation.setStartDateReservation(request.getStartDateReservation());
        quickReservation.setEndDateReservation(request.getEndDateReservation());
        quickReservation.setStartTimeReservation(request.getStartTimeReservation());
        quickReservation.setEndTimeReservation(request.getEndTimeReservation());
        quickReservation.setMaxNumberOfPeople(request.getMaxNumberOfPeople());
        quickReservation.setPrice(request.getPrice());
        quickReservation.setExpiresIn(request.getExpiresIn());
        quickReservation.setReservationType(request.getReservationType());
        quickReservation.setServiceId(request.getIdOfType());
        if(request.getReservationType().equals(ReservationType.BOAT)){
          Boat boat = _boatRepository.findOneById(request.getIdOfType());
          quickReservation.setBoat(boat);
          QuickReservation savedReservation = _quickReservationRepository.save(quickReservation);
          boat.getQuickReservations().add(savedReservation);
        }else{
            Cottage cottage = _cottageRepository.findOneById(request.getIdOfType());
            quickReservation.setCottage(cottage);
            QuickReservation savedReservation = _quickReservationRepository.save(quickReservation);
            cottage.getQuickReservations().add(savedReservation);
        }

        for(AdditionalServiceRequest as: request.getAdditionalServices()){
            AdditionalService additionalService = new AdditionalService();
            additionalService.setTag(as.getTag());
            additionalService.setQuickReservation(quickReservation);
            _additionalServiceRepository.save(additionalService);
            quickReservation.getAdditionalServices().add(additionalService);
            _quickReservationRepository.save(quickReservation);
        }
        return true;
    }
}
