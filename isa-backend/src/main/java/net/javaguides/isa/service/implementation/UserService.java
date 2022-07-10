package net.javaguides.isa.service.implementation;

import net.javaguides.isa.dto.request.StringRequest;
import net.javaguides.isa.dto.response.UserRequestedForDeletionResponse;
import net.javaguides.isa.model.User;
import net.javaguides.isa.repository.*;
import net.javaguides.isa.service.IEmailService;
import net.javaguides.isa.service.IUserService;
import net.javaguides.isa.utils.UserType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements IUserService {

    private final IUserRepository _userRepository;
    private final IEmailService _emailService;

    public UserService(IUserRepository userRepository, IEmailService emailService) {

        _userRepository = userRepository;
        _emailService = emailService;
    }


    @Override
    public List<UserRequestedForDeletionResponse> getAllUserWithDeletionRequested() {
        List<User> users = _userRepository.findAll();
        List<UserRequestedForDeletionResponse> userResponses = new ArrayList<>();
        for(User u: users){
            if(u.isRequestedForDeletion()){
                userResponses.add(mapToUserRequestedForDeletionResponse(u));
            }
        }
        return userResponses;
    }

    @Override
    public void requestDeletion(StringRequest request) {
        User user = _userRepository.findOneById(request.getId());
        user.setReasonForDeletion(request.getText());
        user.setRequestedForDeletion(true);
        _userRepository.save(user);
    }

    @Override
    public void approveDeletionRequest(StringRequest request) {
        User user = _userRepository.findOneById(request.getId());
        _emailService.approveDeletionRequest(user, request.getText());
        _userRepository.deleteById(request.getId());
    }

    @Override
    public void denyDeletionRequest(StringRequest request) {
        User user = _userRepository.findOneById(request.getId());
        _emailService.denyDeletionRequest(user, request.getText());
        user.setRequestedForDeletion(false);
        user.setReasonForDeletion("");
        _userRepository.save(user);
    }

    private UserRequestedForDeletionResponse mapToUserRequestedForDeletionResponse(User user) {
        UserRequestedForDeletionResponse userResponse = new UserRequestedForDeletionResponse();
        userResponse.setId(user.getId());
        userResponse.setReasonForDeletion(user.getReasonForDeletion());
        if(user.getUserType().equals(UserType.BOAT_OWNER)){
            userResponse.setFirstName(user.getBoatOwner().getFirstName());
            userResponse.setLastName(user.getBoatOwner().getLastName());
        }else if(user.getUserType().equals(UserType.COTTAGE_OWNER)){
            userResponse.setFirstName(user.getCottageOwner().getFirstName());
            userResponse.setLastName(user.getCottageOwner().getLastName());
        }else if(user.getUserType().equals(UserType.FISHING_INSTRUCTOR)){
            userResponse.setFirstName(user.getFishingInstructor().getFirstName());
            userResponse.setLastName(user.getFishingInstructor().getLastName());
        }else{
            userResponse.setFirstName(user.getClient().getFirstName());
            userResponse.setLastName(user.getClient().getLastName());
        }
        return userResponse;
    }

}