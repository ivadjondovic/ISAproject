package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.*;
import net.javaguides.isa.dto.response.BoatOwnerResponse;
import net.javaguides.isa.dto.response.CottageOwnerResponse;
import net.javaguides.isa.model.Boat;
import net.javaguides.isa.model.BoatOwner;
import net.javaguides.isa.model.CottageOwner;
import net.javaguides.isa.model.User;
import net.javaguides.isa.repository.IBoatOwnerRepository;
import net.javaguides.isa.repository.IUserRepository;
import net.javaguides.isa.service.IBoatOwnerService;
import net.javaguides.isa.service.IEmailService;
import net.javaguides.isa.utils.RequestStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BoatOwnerService implements IBoatOwnerService {

    private final IBoatOwnerRepository _boatOwnerRepository;
    private final IEmailService _emailService;
    private final PasswordEncoder _passwordEncoder;
    private final IUserRepository _userRepository;

    public BoatOwnerService(IBoatOwnerRepository boatOwnerRepository, IEmailService emailService, PasswordEncoder passwordEncoder,
                            IUserRepository userRepository) {
        _boatOwnerRepository = boatOwnerRepository;
        _emailService = emailService;
        _passwordEncoder = passwordEncoder;
        _userRepository = userRepository;
    }

    @Override
    public void approveBoatOwnerRegistrationRequest(GetIdRequest request) {
        BoatOwner boatOwner = _boatOwnerRepository.findOneById(request.getId());
        boatOwner.setRequestStatus(RequestStatus.APPROVED);
        BoatOwner savedBoatOwner = _boatOwnerRepository.save(boatOwner);

        _emailService.approveBoatOwnerRegistrationMail(savedBoatOwner);
    }

    @Override
    public void denyBoatOwnerRegistrationRequest(StringRequest request) {
        BoatOwner boatOwner = _boatOwnerRepository.findOneById(request.getId());
        boatOwner.setRequestStatus(RequestStatus.DENIED);
        BoatOwner savedBoatOwner = _boatOwnerRepository.save(boatOwner);
        _emailService.denyBoatOwnerRegistrationMail(savedBoatOwner, request.getText());
    }

    @Override
    public void confirmBoatOwnerRegistrationRequest(GetIdRequest request) {
        BoatOwner boatOwner = _boatOwnerRepository.findOneById(request.getId());
        boatOwner.setRequestStatus(RequestStatus.CONFIRMED);
        _boatOwnerRepository.save(boatOwner);
    }

    @Override
    public void changePasswordBoatOwner(Long id, ChangePasswordRequest request) {
        BoatOwner boatOwner = _boatOwnerRepository.findOneById(id);
        User user = boatOwner.getUser();
        if(_passwordEncoder.matches(request.getPassword(), user.getPassword())){
            if(request.getRePassword().equals(request.getNewPassword())){
                user.setPassword(_passwordEncoder.encode(request.getNewPassword()));
                BoatOwner savedBoatOwner = _boatOwnerRepository.save(boatOwner);
                user.setBoatOwner(savedBoatOwner);
                _userRepository.save(user);
            }
        }
    }

    @Override
    public BoatOwnerResponse updateBoatOwner(BoatOwnerRequest request, Long id) {
        BoatOwner boatOwner = _boatOwnerRepository.findOneById(id);

        boatOwner.setFirstName(request.getFirstName());
        boatOwner.setLastName(request.getLastName());
        boatOwner.setAddress(request.getAddress());
        boatOwner.setCity(request.getCity());
        boatOwner.setCountry(request.getCountry());
        boatOwner.setPhoneNumber(request.getPhoneNumber());

        BoatOwner savedBoatOwner = _boatOwnerRepository.save(boatOwner);

        return mapBoatOwnerToBoatOwnerResponse(savedBoatOwner);
    }

    public BoatOwnerResponse mapBoatOwnerToBoatOwnerResponse(BoatOwner boatOwner) {

        BoatOwnerResponse boatOwnerResponse = new BoatOwnerResponse();
        boatOwnerResponse.setId(boatOwner.getId());
        boatOwnerResponse.setAddress(boatOwner.getAddress());
        boatOwnerResponse.setFirstName(boatOwner.getFirstName());
        boatOwnerResponse.setLastName(boatOwner.getLastName());
        boatOwnerResponse.setCity(boatOwner.getCity());
        boatOwnerResponse.setCountry(boatOwner.getCountry());
        boatOwnerResponse.setPhoneNumber(boatOwner.getPhoneNumber());
        return boatOwnerResponse;
    }
}
