package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;

public interface IBoatOwnerService {
    void approveBoatOwnerRegistrationRequest(GetIdRequest request);

    void denyBoatOwnerRegistrationRequest(GetIdRequest request);

    void confirmBoatOwnerRegistrationRequest(GetIdRequest request);
}
