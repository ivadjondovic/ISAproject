package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.QuickReservationRequest;
import net.javaguides.isa.service.IQuickReservationService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quick-reservation")
public class QuickReservationController {

    private final IQuickReservationService _quickReservationService;

    public QuickReservationController(IQuickReservationService quickReservationService) {
        _quickReservationService = quickReservationService;
    }

    @PutMapping("/create")
    public boolean createQuickReservation(@RequestBody QuickReservationRequest request){
        return  _quickReservationService.createQuickReservation(request);
    }
}
