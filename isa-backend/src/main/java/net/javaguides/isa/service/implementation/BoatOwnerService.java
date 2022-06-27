package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.RefusalReasonRequest;
import net.javaguides.isa.model.BoatOwner;
import net.javaguides.isa.repository.IBoatOwnerRepository;
import net.javaguides.isa.service.IBoatOwnerService;
import net.javaguides.isa.service.IEmailService;
import net.javaguides.isa.utils.RequestStatus;
import org.springframework.stereotype.Service;

@Service
public class BoatOwnerService implements IBoatOwnerService {

    private final IBoatOwnerRepository _boatOwnerRepository;
    private final IEmailService _emailService;

    public BoatOwnerService(IBoatOwnerRepository boatOwnerRepository, IEmailService emailService) {
        _boatOwnerRepository = boatOwnerRepository;
        _emailService = emailService;
    }

    @Override
    public void approveBoatOwnerRegistrationRequest(GetIdRequest request) {
        BoatOwner boatOwner = _boatOwnerRepository.findOneById(request.getId());
        boatOwner.setRequestStatus(RequestStatus.APPROVED);
        BoatOwner savedBoatOwner = _boatOwnerRepository.save(boatOwner);

        _emailService.approveBoatOwnerRegistrationMail(savedBoatOwner);
    }

    @Override
    public void denyBoatOwnerRegistrationRequest(RefusalReasonRequest request) {
        BoatOwner boatOwner = _boatOwnerRepository.findOneById(request.getId());
        boatOwner.setRequestStatus(RequestStatus.DENIED);
        BoatOwner savedBoatOwner = _boatOwnerRepository.save(boatOwner);
        _emailService.denyBoatOwnerRegistrationMail(savedBoatOwner, request.getReason());
    }

    @Override
    public void confirmBoatOwnerRegistrationRequest(GetIdRequest request) {
        BoatOwner boatOwner = _boatOwnerRepository.findOneById(request.getId());
        boatOwner.setRequestStatus(RequestStatus.CONFIRMED);
        _boatOwnerRepository.save(boatOwner);
    }
}
