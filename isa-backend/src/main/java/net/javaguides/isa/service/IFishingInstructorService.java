package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;

public interface IFishingInstructorService {

    void approveFishingInstructorRegistrationRequest(GetIdRequest request);

    void denyFishingInstructorRegistrationRequest(GetIdRequest request);

    void confirmFishingInstructorRegistrationRequest(GetIdRequest request);
}
