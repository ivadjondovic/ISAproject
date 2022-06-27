package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.RefusalReasonRequest;

public interface IBoatOwnerService {
    void approveBoatOwnerRegistrationRequest(GetIdRequest request);

    void denyBoatOwnerRegistrationRequest(RefusalReasonRequest request);

    void confirmBoatOwnerRegistrationRequest(GetIdRequest request);
}
