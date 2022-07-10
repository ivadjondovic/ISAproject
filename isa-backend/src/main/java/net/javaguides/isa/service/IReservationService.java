package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.ReservationRequest;

public interface IReservationService {
    boolean createReservation(ReservationRequest request);
}
