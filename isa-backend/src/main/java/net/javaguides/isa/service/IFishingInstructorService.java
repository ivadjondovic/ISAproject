package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.RefusalReasonRequest;

public interface IFishingInstructorService {

    void approveFishingInstructorRegistrationRequest(GetIdRequest request);

    void denyFishingInstructorRegistrationRequest(RefusalReasonRequest request);

    void confirmFishingInstructorRegistrationRequest(GetIdRequest request);
}
