package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.RefusalReasonRequest;

public interface ICottageOwnerService {

    void approveCottageOwnerRegistrationRequest(GetIdRequest request);

    void denyCottageOwnerRegistrationRequest(RefusalReasonRequest request);

    void confirmCottageOwnerRegistrationRequest(GetIdRequest request);
}
