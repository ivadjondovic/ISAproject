package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;

public interface ICottageOwnerService {

    void approveCottageOwnerRegistrationRequest(GetIdRequest request);

    void denyCottageOwnerRegistrationRequest(GetIdRequest request);

    void confirmCottageOwnerRegistrationRequest(GetIdRequest request);
}
