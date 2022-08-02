package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.QuickReservationRequest;

public interface IQuickReservationService {
    boolean createQuickReservation(QuickReservationRequest request);
}
