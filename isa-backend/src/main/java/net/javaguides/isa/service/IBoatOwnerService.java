package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.StringRequest;

public interface IBoatOwnerService {
    void approveBoatOwnerRegistrationRequest(GetIdRequest request);

    void denyBoatOwnerRegistrationRequest(StringRequest request);

    void confirmBoatOwnerRegistrationRequest(GetIdRequest request);
}
