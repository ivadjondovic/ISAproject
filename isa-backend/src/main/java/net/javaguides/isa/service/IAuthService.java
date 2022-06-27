package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.ServiceProviderRegistrationRequest;

public interface IAuthService {

    boolean registerBoatOwner(ServiceProviderRegistrationRequest request);
    boolean registerCottageOwner(ServiceProviderRegistrationRequest request);
    boolean registerFishingInstructor(ServiceProviderRegistrationRequest request);

}
