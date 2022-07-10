package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.ReservationRequest;
import net.javaguides.isa.service.IReservationService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final IReservationService _reservationService;

    public ReservationController(IReservationService reservationService) {
        _reservationService = reservationService;
    }

    @PutMapping("/create")
    public boolean createReservation(@RequestBody ReservationRequest request){
        return  _reservationService.createReservation(request);
    }
}
