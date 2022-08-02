package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.ReservationRequest;
import net.javaguides.isa.dto.response.ReservationResponse;

import java.util.List;

public interface IReservationService {
    boolean createReservation(ReservationRequest request);
    List<ReservationResponse> getCottageOwnersReservations(Long id);
    List<ReservationResponse> getBoatOwnersReservations(Long id);

}
