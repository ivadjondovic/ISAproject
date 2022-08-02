package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.LoginRequest;
import net.javaguides.isa.dto.request.ServiceProviderRegistrationRequest;
import net.javaguides.isa.dto.response.UserResponse;
import net.javaguides.isa.model.*;
import net.javaguides.isa.repository.*;
import net.javaguides.isa.service.IAuthService;
import net.javaguides.isa.utils.GeneralException;
import net.javaguides.isa.utils.RequestStatus;
import net.javaguides.isa.utils.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository _userRepository;
    private final IBoatOwnerRepository _boatOwnerRepository;
    private final ICottageOwnerRepository _cottageOwnerRepository;
    private final IFishingInstructorRepository _fishingInstructorRepository;
    private final PasswordEncoder _passwordEncoder;


    public AuthService(IUserRepository userRepository, PasswordEncoder passwordEncoder, IBoatOwnerRepository boatOwnerRepository,
                       ICottageOwnerRepository cottageOwnerRepository, IFishingInstructorRepository fishingInstructorRepository) {
        _userRepository = userRepository;
        _passwordEncoder = passwordEncoder;
        _boatOwnerRepository = boatOwnerRepository;
        _cottageOwnerRepository = cottageOwnerRepository;
        _fishingInstructorRepository = fishingInstructorRepository;
    }

    @Override
    public boolean registerBoatOwner(ServiceProviderRegistrationRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        BoatOwner boatOwner = new BoatOwner();

        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserType(UserType.BOAT_OWNER);
        user.setHasSignedIn(false);
        user.setLastPasswordResetDate(new Timestamp(3));
        boatOwner.setFirstName(request.getFirstName());
        boatOwner.setLastName(request.getLastName());
        boatOwner.setPhoneNumber(request.getPhoneNumber());
        boatOwner.setAddress(request.getAddress());
        boatOwner.setCountry(request.getCountry());
        boatOwner.setCity(request.getCity());
        boatOwner.setRequestStatus(RequestStatus.PENDING);
        BoatOwner savedBoatOwner = _boatOwnerRepository.save(boatOwner);
        savedBoatOwner.setUser(user);

        user.setBoatOwner(savedBoatOwner);
        _userRepository.save(user);
        return true;
    }

    @Override
    public boolean registerCottageOwner(ServiceProviderRegistrationRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        CottageOwner cottageOwner = new CottageOwner();

        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserType(UserType.COTTAGE_OWNER);
        user.setHasSignedIn(false);

        cottageOwner.setFirstName(request.getFirstName());
        cottageOwner.setLastName(request.getLastName());
        cottageOwner.setPhoneNumber(request.getPhoneNumber());
        cottageOwner.setAddress(request.getAddress());
        cottageOwner.setCountry(request.getCountry());
        cottageOwner.setCity(request.getCity());

        CottageOwner savedCottageOwner = _cottageOwnerRepository.save(cottageOwner);
        savedCottageOwner.setUser(user);
        user.setCottageOwner(savedCottageOwner);
        _userRepository.save(user);
        return true;
    }

    @Override
    public boolean registerFishingInstructor(ServiceProviderRegistrationRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        FishingInstructor fishingInstructor = new FishingInstructor();

        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserType(UserType.FISHING_INSTRUCTOR);
        user.setHasSignedIn(false);

        fishingInstructor.setFirstName(request.getFirstName());
        fishingInstructor.setLastName(request.getLastName());
        fishingInstructor.setPhoneNumber(request.getPhoneNumber());
        fishingInstructor.setAddress(request.getAddress());
        fishingInstructor.setCountry(request.getCountry());
        fishingInstructor.setCity(request.getCity());

        FishingInstructor savedFishingInstructor = _fishingInstructorRepository.save(fishingInstructor);
        savedFishingInstructor.setUser(user);
        user.setFishingInstructor(savedFishingInstructor);
        _userRepository.save(user);
        return true;
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = _userRepository.findOneByUsername(request.getUsername());
        if(user == null || !_passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
        }
        if(user.getUserType().equals(UserType.BOAT_OWNER)){
            checkRequestStatus(user.getBoatOwner().getRequestStatus());
        }else if(user.getUserType().equals(UserType.COTTAGE_OWNER)){
            checkRequestStatus(user.getCottageOwner().getRequestStatus());
        }else if(user.getUserType().equals(UserType.FISHING_INSTRUCTOR)){
            checkRequestStatus(user.getFishingInstructor().getRequestStatus());
        }

        UserResponse userResponse = mapUserToUserResponse(user);
        return userResponse;
    }

    private void checkRequestStatus(RequestStatus status){
        if(status.equals(RequestStatus.PENDING)){
            throw new GeneralException("Your registration hasn't been approved yet.", HttpStatus.BAD_REQUEST);
        }else if(status.equals(RequestStatus.DENIED)){
            throw new GeneralException("Your registration has been denied.", HttpStatus.BAD_REQUEST);
        }else if(status.equals(RequestStatus.APPROVED)){
            throw new GeneralException("Your registration has been approved by admin. Please activate your account.", HttpStatus.BAD_REQUEST);
        }
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        if(user.getBoatOwner() != null){
            userResponse.setId(user.getBoatOwner().getId());
            userResponse.setUserRole("BOAT_OWNER");
        }else if(user.getCottageOwner() != null){
            userResponse.setId(user.getCottageOwner().getId());
            userResponse.setUserRole("COTTAGE_OWNER");
        }else if(user.getFishingInstructor() != null){
            userResponse.setId(user.getFishingInstructor().getId());
            userResponse.setUserRole("FISHING_INSTRUCTOR");
        }else if(user.getSystemAdmin() != null){
            userResponse.setId(user.getSystemAdmin().getId());
            userResponse.setUserRole("SYSTEM_ADMIN");
        }else if(user.getClient() != null){
            userResponse.setId(user.getClient().getId());
            userResponse.setUserRole("CLIENT");
        }
        userResponse.setUsername(user.getUsername());
        return userResponse;
    }
}
