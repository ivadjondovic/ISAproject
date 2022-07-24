package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.*;
import net.javaguides.isa.dto.response.BoatOwnerResponse;
import net.javaguides.isa.dto.response.CottageOwnerResponse;

public interface IBoatOwnerService {
    void approveBoatOwnerRegistrationRequest(GetIdRequest request);

    void denyBoatOwnerRegistrationRequest(StringRequest request);

    void confirmBoatOwnerRegistrationRequest(GetIdRequest request);

    void changePasswordBoatOwner(Long id, ChangePasswordRequest request);

    BoatOwnerResponse updateBoatOwner(BoatOwnerRequest request, Long id);
}
