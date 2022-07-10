package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.StringRequest;
import net.javaguides.isa.service.IFishingInstructorService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fishing-instructors")
public class FishingInstructorController {

    private final IFishingInstructorService _fishingInstructorService;

    public FishingInstructorController(IFishingInstructorService fishingInstructorService) {
        _fishingInstructorService = fishingInstructorService;
    }

    @PutMapping("/approve")
    public void approveFishingInstructorRegistrationRequest(@RequestBody GetIdRequest request){
        _fishingInstructorService.approveFishingInstructorRegistrationRequest(request);
    }

    @PutMapping("/deny")
    public void denyFishingInstructorRegistrationRequest(@RequestBody StringRequest request){
        _fishingInstructorService.denyFishingInstructorRegistrationRequest(request);
    }

    @PutMapping("/confirm")
    public void confirmFishingInstructorRegistrationRequest(@RequestBody GetIdRequest request){
        _fishingInstructorService.confirmFishingInstructorRegistrationRequest(request);
    }
}

