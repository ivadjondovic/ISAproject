package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.RefusalReasonRequest;
import net.javaguides.isa.service.ICottageOwnerService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cottage-owners")
public class CottageOwnerController {

    private final ICottageOwnerService _cottageOwnerService;

    public CottageOwnerController(ICottageOwnerService cottageOwnerService) {
        _cottageOwnerService = cottageOwnerService;
    }

    @PutMapping("/approve")
    public void approveCottageOwnerRegistrationRequest(@RequestBody GetIdRequest request){
        _cottageOwnerService.approveCottageOwnerRegistrationRequest(request);
    }

    @PutMapping("/deny")
    public void denyCottageOwnerRegistrationRequest(@RequestBody RefusalReasonRequest request){
        _cottageOwnerService.denyCottageOwnerRegistrationRequest(request);
    }

    @PutMapping("/confirm")
    public void confirmCottageOwnerRegistrationRequest(@RequestBody GetIdRequest request){
        _cottageOwnerService.confirmCottageOwnerRegistrationRequest(request);
    }
}