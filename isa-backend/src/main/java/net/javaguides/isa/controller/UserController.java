package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.StringRequest;
import net.javaguides.isa.dto.response.UserRequestedForDeletionResponse;
import net.javaguides.isa.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService _userService;

    public UserController(IUserService userService) {
        _userService = userService;
    }

    @GetMapping("/deletion-requested")
    public List<UserRequestedForDeletionResponse> getAllUserWithDeletionRequested(){
        return  _userService.getAllUserWithDeletionRequested();
    }

    @PostMapping("/request-for-deletion")
    public void requestDeletion(@RequestBody StringRequest request) {
        _userService.requestDeletion(request);
    }

    @PutMapping("/approve-deletion-request")
    public void approveDeletionRequest(@RequestBody StringRequest request){
        _userService.approveDeletionRequest(request);
    }

    @PutMapping("/deny-deletion-request")
    public void denyDeletionRequest(@RequestBody StringRequest request){
        _userService.denyDeletionRequest(request);
    }
}
