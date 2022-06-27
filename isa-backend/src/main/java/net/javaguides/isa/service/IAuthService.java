package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.LoginRequest;
import net.javaguides.isa.dto.request.ServiceProviderRegistrationRequest;
import net.javaguides.isa.dto.response.UserResponse;

public interface IAuthService {

    boolean registerBoatOwner(ServiceProviderRegistrationRequest request);
    boolean registerCottageOwner(ServiceProviderRegistrationRequest request);
    boolean registerFishingInstructor(ServiceProviderRegistrationRequest request);
    UserResponse login(LoginRequest request);
}
