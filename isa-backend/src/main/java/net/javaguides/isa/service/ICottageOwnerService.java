package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.ChangePasswordRequest;
import net.javaguides.isa.dto.request.CottageOwnerRequest;
import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.StringRequest;
import net.javaguides.isa.dto.response.CottageOwnerResponse;

public interface ICottageOwnerService {

    void approveCottageOwnerRegistrationRequest(GetIdRequest request);

    void denyCottageOwnerRegistrationRequest(StringRequest request);

    void confirmCottageOwnerRegistrationRequest(GetIdRequest request);

    void changePasswordCottageOwner(Long id, ChangePasswordRequest request);

    CottageOwnerResponse updateCottageOwner(CottageOwnerRequest request, Long id);

    CottageOwnerResponse getCottageOwner(Long id);
}
