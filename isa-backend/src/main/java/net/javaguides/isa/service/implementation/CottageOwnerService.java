package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.ChangePasswordRequest;
import net.javaguides.isa.dto.request.CottageOwnerRequest;
import net.javaguides.isa.dto.request.GetIdRequest;
import net.javaguides.isa.dto.request.StringRequest;
import net.javaguides.isa.dto.response.CottageOwnerResponse;
import net.javaguides.isa.dto.response.CottageResponse;
import net.javaguides.isa.model.Cottage;
import net.javaguides.isa.model.CottageOwner;
import net.javaguides.isa.model.User;
import net.javaguides.isa.repository.ICottageOwnerRepository;
import net.javaguides.isa.repository.IUserRepository;
import net.javaguides.isa.service.ICottageOwnerService;
import net.javaguides.isa.service.IEmailService;
import net.javaguides.isa.utils.RequestStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CottageOwnerService implements ICottageOwnerService {

    private final ICottageOwnerRepository _cottageOwnerRepository;
    private final IEmailService _emailService;
    private final PasswordEncoder _passwordEncoder;
    private final IUserRepository _userRepository;

    public CottageOwnerService(ICottageOwnerRepository cottageOwnerRepository, IEmailService emailService, PasswordEncoder passwordEncoder,
                               IUserRepository userRepository) {
        _cottageOwnerRepository = cottageOwnerRepository;
        _emailService = emailService;
        _passwordEncoder = passwordEncoder;
        _userRepository = userRepository;
    }

    @Override
    public void approveCottageOwnerRegistrationRequest(GetIdRequest request) {
        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(request.getId());
        cottageOwner.setRequestStatus(RequestStatus.APPROVED);
        CottageOwner savedCottageOwner = _cottageOwnerRepository.save(cottageOwner);

        _emailService.approveCottageOwnerRegistrationMail(savedCottageOwner);
    }

    @Override
    public void denyCottageOwnerRegistrationRequest(StringRequest request) {
        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(request.getId());
        cottageOwner.setRequestStatus(RequestStatus.DENIED);
        CottageOwner savedCottageOwner = _cottageOwnerRepository.save(cottageOwner);
        _emailService.denyCottageOwnerRegistrationMail(savedCottageOwner, request.getText());
    }

    @Override
    public void confirmCottageOwnerRegistrationRequest(GetIdRequest request) {
        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(request.getId());
        cottageOwner.setRequestStatus(RequestStatus.CONFIRMED);
        _cottageOwnerRepository.save(cottageOwner);
    }

    @Override
    public void changePasswordCottageOwner(Long id, ChangePasswordRequest request) {
        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(id);
        User user = cottageOwner.getUser();
        if(_passwordEncoder.matches(request.getPassword(), user.getPassword())){
            if(request.getRePassword().equals(request.getNewPassword())){
                user.setPassword(_passwordEncoder.encode(request.getNewPassword()));
                CottageOwner savedCottageOwner = _cottageOwnerRepository.save(cottageOwner);
                user.setCottageOwner(savedCottageOwner);
                _userRepository.save(user);
            }
        }
    }

    @Override
    public CottageOwnerResponse updateCottageOwner(CottageOwnerRequest request, Long id) {
        CottageOwner cottageOwner = _cottageOwnerRepository.findOneById(id);

        cottageOwner.setFirstName(request.getFirstName());
        cottageOwner.setLastName(request.getLastName());
        cottageOwner.setAddress(request.getAddress());
        cottageOwner.setCity(request.getCity());
        cottageOwner.setCountry(request.getCountry());
        cottageOwner.setPhoneNumber(request.getPhoneNumber());

        CottageOwner savedCottageOwner = _cottageOwnerRepository.save(cottageOwner);

        return mapCottageOwnerToCottageOwnerResponse(savedCottageOwner);
    }

    public CottageOwnerResponse mapCottageOwnerToCottageOwnerResponse(CottageOwner cottageOwner) {

        CottageOwnerResponse cottageOwnerResponse = new CottageOwnerResponse();
        cottageOwnerResponse.setId(cottageOwner.getId());
        cottageOwnerResponse.setAddress(cottageOwner.getAddress());
        cottageOwnerResponse.setFirstName(cottageOwner.getFirstName());
        cottageOwnerResponse.setLastName(cottageOwner.getLastName());
        cottageOwnerResponse.setCity(cottageOwner.getCity());
        cottageOwnerResponse.setCountry(cottageOwner.getCountry());
        cottageOwnerResponse.setPhoneNumber(cottageOwner.getPhoneNumber());
        return cottageOwnerResponse;
    }

}