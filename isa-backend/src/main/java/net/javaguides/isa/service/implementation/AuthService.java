package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.ServiceProviderRegistrationRequest;
import net.javaguides.isa.model.BoatOwner;
import net.javaguides.isa.model.CottageOwner;
import net.javaguides.isa.model.FishingInstructor;
import net.javaguides.isa.model.User;
import net.javaguides.isa.repository.IBoatOwnerRepository;
import net.javaguides.isa.repository.ICottageOwnerRepository;
import net.javaguides.isa.repository.IFishingInstructorRepository;
import net.javaguides.isa.repository.IUserRepository;
import net.javaguides.isa.service.IAuthService;
import net.javaguides.isa.utils.GeneralException;
import net.javaguides.isa.utils.RequestStatus;
import net.javaguides.isa.utils.UserType;
import org.springframework.format.annotation.DateTimeFormat;
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
}
