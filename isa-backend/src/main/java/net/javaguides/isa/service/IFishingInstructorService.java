package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.StringRequest;

public interface IFishingInstructorService {

    void approveFishingInstructorRegistrationRequest(GetIdRequest request);

    void denyFishingInstructorRegistrationRequest(StringRequest request);

    void confirmFishingInstructorRegistrationRequest(GetIdRequest request);
}
