package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.ChangePasswordRequest;
import net.javaguides.isa.dto.request.CottageOwnerRequest;
import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.StringRequest;
import net.javaguides.isa.dto.response.CottageOwnerResponse;
import net.javaguides.isa.service.ICottageOwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void denyCottageOwnerRegistrationRequest(@RequestBody StringRequest request){
        _cottageOwnerService.denyCottageOwnerRegistrationRequest(request);
    }

    @PutMapping("/confirm")
    public void confirmCottageOwnerRegistrationRequest(@RequestBody GetIdRequest request){
        _cottageOwnerService.confirmCottageOwnerRegistrationRequest(request);
    }

    @PutMapping("/{id}/password")
    public void changePasswordCottageOwner(@PathVariable("id")Long id, @RequestBody ChangePasswordRequest request){
        _cottageOwnerService.changePasswordCottageOwner(id, request);

    }

    @PutMapping("/update/{id}")
    public CottageOwnerResponse updateCottageOwner(@RequestBody CottageOwnerRequest request, @PathVariable Long id) {
        return _cottageOwnerService.updateCottageOwner(request, id);
    }

}