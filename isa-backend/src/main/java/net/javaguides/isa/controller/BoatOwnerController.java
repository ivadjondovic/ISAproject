package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.*;
import net.javaguides.isa.dto.response.BoatOwnerResponse;
import net.javaguides.isa.dto.response.CottageOwnerResponse;
import net.javaguides.isa.service.IBoatOwnerService;
import org.springframework.web.bind.annotation.*;

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
    public void denyRegistrationRequest(@RequestBody StringRequest request){
        _boatOwnerService.denyBoatOwnerRegistrationRequest(request);
    }

    @PutMapping("/confirm")
    public void confirmRegistrationRequest(@RequestBody GetIdRequest request){
        _boatOwnerService.confirmBoatOwnerRegistrationRequest(request);
    }

    @PutMapping("/{id}/password")
    public void changePasswordBoatOwner(@PathVariable("id")Long id, @RequestBody ChangePasswordRequest request){
        _boatOwnerService.changePasswordBoatOwner(id, request);

    }

    @PutMapping("/update/{id}")
    public BoatOwnerResponse updateBoatOwner(@RequestBody BoatOwnerRequest request, @PathVariable Long id) {
        return _boatOwnerService.updateBoatOwner(request, id);
    }

    @GetMapping("/{id}")
    public BoatOwnerResponse getBoatOwner(@PathVariable Long id) {
        return _boatOwnerService.getBoatOwner(id);
    }


}
