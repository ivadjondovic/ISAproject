package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.ReservationRequest;
import net.javaguides.isa.dto.response.CottageResponse;
import net.javaguides.isa.dto.response.ReservationResponse;
import net.javaguides.isa.service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}/cottage-owner")
    public ResponseEntity<?> getCottageOwnersReservations(@PathVariable("id") Long id){
        List<ReservationResponse> reservations = _reservationService.getCottageOwnersReservations(id);
        if(reservations != null) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Reservations doesn't exists.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/boat-owner")
    public ResponseEntity<?> getBoatOwnersReservations(@PathVariable("id") Long id){
        List<ReservationResponse> reservations = _reservationService.getBoatOwnersReservations(id);
        if(reservations != null) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Reservations doesn't exists.", HttpStatus.NOT_FOUND);
        }
    }

}
