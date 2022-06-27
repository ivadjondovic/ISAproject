package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.LoginRequest;
import net.javaguides.isa.dto.request.ServiceProviderRegistrationRequest;
import net.javaguides.isa.dto.response.TempResponse;
import net.javaguides.isa.dto.response.UserResponse;
import net.javaguides.isa.security.TokenUtils;
import net.javaguides.isa.service.IAuthService;
import net.javaguides.isa.utils.RegistrationType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenUtils _tokenUtils;

    private final IAuthService _authService;

    public AuthController(TokenUtils tokenUtils, IAuthService authService) {
        _tokenUtils = tokenUtils;
        _authService = authService;
    }

    @PutMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request){
        return _authService.login(request);
    }

    @PostMapping("/register-service-provider")
    public ResponseEntity<?> registerServiceProvider(@RequestBody ServiceProviderRegistrationRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered service provider");
        if(request.getRegistrationType().equals(RegistrationType.BOAT_OWNER)){
            if(_authService.registerBoatOwner(request)){
                return new ResponseEntity<>(temp, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
            }
        }else if(request.getRegistrationType().equals(RegistrationType.COTTAGE_OWNER)){
            if(_authService.registerCottageOwner(request)){
                return new ResponseEntity<>(temp, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
            }
        }else{
            if(_authService.registerFishingInstructor(request)){
                return new ResponseEntity<>(temp, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
            }
        }
    }
}
