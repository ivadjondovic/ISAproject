package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.StringRequest;

public interface ICottageOwnerService {

    void approveCottageOwnerRegistrationRequest(GetIdRequest request);

    void denyCottageOwnerRegistrationRequest(StringRequest request);

    void confirmCottageOwnerRegistrationRequest(GetIdRequest request);
}
