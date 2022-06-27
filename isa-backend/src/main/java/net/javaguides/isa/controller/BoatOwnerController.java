package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.RefusalReasonRequest;
import net.javaguides.isa.service.IBoatOwnerService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boat-owners")
public class BoatOwnerController {

    private final IBoatOwnerService _boatOwnerService;

    public BoatOwnerController(IBoatOwnerService boatOwnerService) {
        _boatOwnerService = boatOwnerService;
    }

    @PutMapping("/approve")
    public void approveRegistrationRequest(@RequestBody GetIdRequest request){
        _boatOwnerService.approveBoatOwnerRegistrationRequest(request);
    }

    @PutMapping("/deny")
    public void denyRegistrationRequest(@RequestBody RefusalReasonRequest request){
        _boatOwnerService.denyBoatOwnerRegistrationRequest(request);
    }

    @PutMapping("/confirm")
    public void confirmRegistrationRequest(@RequestBody GetIdRequest request){
        _boatOwnerService.confirmBoatOwnerRegistrationRequest(request);
    }
}
