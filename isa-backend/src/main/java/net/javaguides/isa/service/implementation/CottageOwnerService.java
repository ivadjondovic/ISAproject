package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.RefusalReasonRequest;
import net.javaguides.isa.model.CottageOwner;
import net.javaguides.isa.repository.ICottageOwnerRepository;
import net.javaguides.isa.service.ICottageOwnerService;
import net.javaguides.isa.service.IEmailService;
import net.javaguides.isa.utils.RequestStatus;
import org.springframework.stereotype.Service;

@Service
public class CottageOwnerService implements ICottageOwnerService {

    private final ICottageOwnerRepository _cottageOwnerRepository;
    private final IEmailService _emailService;

    public CottageOwnerService(ICottageOwnerRepository cottageOwnerRepository, IEmailService emailService) {
        _cottageOwnerRepository = cottageOwnerRepository;
        _emailService = emailService;
    }

    @Override
    public void approveCottageOwnerRegistrationRequest(GetIdRequest request) {
        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(request.getId());
        cottageOwner.setRequestStatus(RequestStatus.APPROVED);
        CottageOwner savedCottageOwner = _cottageOwnerRepository.save(cottageOwner);

        _emailService.approveCottageOwnerRegistrationMail(savedCottageOwner);
    }

    @Override
    public void denyCottageOwnerRegistrationRequest(RefusalReasonRequest request) {
        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(request.getId());
        cottageOwner.setRequestStatus(RequestStatus.DENIED);
        CottageOwner savedCottageOwner = _cottageOwnerRepository.save(cottageOwner);
        _emailService.denyCottageOwnerRegistrationMail(savedCottageOwner, request.getReason());
    }

    @Override
    public void confirmCottageOwnerRegistrationRequest(GetIdRequest request) {
        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(request.getId());
        cottageOwner.setRequestStatus(RequestStatus.CONFIRMED);
        _cottageOwnerRepository.save(cottageOwner);
    }
}