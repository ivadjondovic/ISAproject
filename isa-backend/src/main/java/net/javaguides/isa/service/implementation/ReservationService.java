package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.ReservationRequest;
import net.javaguides.isa.dto.response.CottageResponse;
import net.javaguides.isa.dto.response.ReservationResponse;
import net.javaguides.isa.model.*;
import net.javaguides.isa.repository.IAdditionalServiceRepository;
import net.javaguides.isa.repository.IBoatRepository;
import net.javaguides.isa.repository.ICottageRepository;
import net.javaguides.isa.repository.IReservationRepository;
import net.javaguides.isa.service.IReservationService;
import net.javaguides.isa.utils.ReservationStatus;
import net.javaguides.isa.utils.ReservationType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final IReservationRepository _reservationRepository;
    private final IBoatRepository _boatRepository;
    private final ICottageRepository _cottageRepository;
    private final IAdditionalServiceRepository _additionalServiceRepository;

    public ReservationService(IReservationRepository reservationRepository, IBoatRepository boatRepository,
                                   ICottageRepository cottageRepository, IAdditionalServiceRepository additionalServiceRepository) {
        _reservationRepository = reservationRepository;
        _boatRepository = boatRepository;
        _cottageRepository = cottageRepository;
        _additionalServiceRepository = additionalServiceRepository;
    }


    @Override
    public boolean createReservation(ReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setStartDateReservation(request.getStartDateReservation());
        reservation.setEndDateReservation(request.getEndDateReservation());
        reservation.setStartTimeReservation(request.getStartTimeReservation());
        reservation.setEndTimeReservation(request.getEndTimeReservation());
        reservation.setReservationType(request.getReservationType());
        reservation.setReservationStatus(ReservationStatus.AVAILABLE);
        reservation.setServiceId(request.getIdOfType());
        if(request.getReservationType().equals(ReservationType.BOAT)){
            Boat boat = _boatRepository.findOneById(request.getIdOfType());
            reservation.setBoat(boat);
            Reservation savedReservation = _reservationRepository.save(reservation);
            boat.getReservations().add(savedReservation);
        }else{
            Cottage cottage = _cottageRepository.findOneById(request.getIdOfType());
            reservation.setCottage(cottage);
            Reservation savedReservation = _reservationRepository.save(reservation);
            cottage.getReservations().add(savedReservation);
        }
        return true;
    }

    @Override
    public List<ReservationResponse> getCottageOwnersReservations(Long id) {
        List<Reservation> reservations = _reservationRepository.findAll();

        List<ReservationResponse> finalResponse = new ArrayList<>();
        for(Reservation r: reservations){
            if(r.getReservationType().equals(ReservationType.COTTAGE)){
                Cottage cottage = _cottageRepository.findOneById(r.getServiceId());
                if(cottage.getCottageOwner().getId().equals(id)){
                    finalResponse.add(mapReservationToReservationResponse(r));
                }
            }
        }
        return finalResponse;
    }

    @Override
    public List<ReservationResponse> getBoatOwnersReservations(Long id) {
        List<Reservation> reservations = _reservationRepository.findAll();
        List<ReservationResponse> finalResponse = new ArrayList<>();
        for(Reservation r: reservations){
            if(r.getReservationType().equals(ReservationType.BOAT)){
                Boat boat = _boatRepository.findOneById(r.getServiceId());
                if(boat.getBoatOwner().getId().equals(id)){
                    finalResponse.add(mapReservationToReservationResponse(r));
                }
            }
        }
        return finalResponse;
    }

    public ReservationResponse mapReservationToReservationResponse(Reservation reservation) {

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setId(reservation.getId());
        reservationResponse.setStartTimeReservation(reservation.getStartTimeReservation());
        reservationResponse.setEndTimeReservation(reservation.getEndTimeReservation());
        reservationResponse.setStartDateReservation(reservation.getStartDateReservation());
        reservationResponse.setEndDateReservation(reservation.getEndDateReservation());
        reservationResponse.setReservationType(reservation.getReservationType());
        reservationResponse.setReservationStatus(reservation.getReservationStatus());
        reservationResponse.setIdOfType(reservation.getServiceId());
        return reservationResponse;
    }
}
