package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.RefusalReasonRequest;
import net.javaguides.isa.model.FishingInstructor;
import net.javaguides.isa.repository.IFishingInstructorRepository;
import net.javaguides.isa.service.IEmailService;
import net.javaguides.isa.service.IFishingInstructorService;
import net.javaguides.isa.utils.RequestStatus;
import org.springframework.stereotype.Service;

@Service
public class FishingInstructorService implements IFishingInstructorService {

    private final IFishingInstructorRepository _fishingInstructorRepository;
    private final IEmailService _emailService;

    public FishingInstructorService(IFishingInstructorRepository fishingInstructorRepository, IEmailService emailService) {
        _fishingInstructorRepository = fishingInstructorRepository;
        _emailService = emailService;
    }

    @Override
    public void approveFishingInstructorRegistrationRequest(GetIdRequest request) {
        FishingInstructor fishingInstructor = _fishingInstructorRepository.findOneById(request.getId());
        fishingInstructor.setRequestStatus(RequestStatus.APPROVED);
        FishingInstructor savedFishingInstructor = _fishingInstructorRepository.save(fishingInstructor);

        _emailService.approveFishingInstructorRegistrationMail(savedFishingInstructor);
    }

    @Override
    public void denyFishingInstructorRegistrationRequest(RefusalReasonRequest request) {
        FishingInstructor fishingInstructor = _fishingInstructorRepository.findOneById(request.getId());
        fishingInstructor.setRequestStatus(RequestStatus.DENIED);
        FishingInstructor savedFishingInstructor = _fishingInstructorRepository.save(fishingInstructor);
        _emailService.denyFishingInstructorRegistrationMail(savedFishingInstructor, request.getReason());
    }

    @Override
    public void confirmFishingInstructorRegistrationRequest(GetIdRequest request) {
        FishingInstructor fishingInstructor = _fishingInstructorRepository.findOneById(request.getId());
        fishingInstructor.setRequestStatus(RequestStatus.CONFIRMED);
        _fishingInstructorRepository.save(fishingInstructor);
    }
}
