package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.StringRequest;
import net.javaguides.isa.dto.response.UserRequestedForDeletionResponse;

import java.util.List;

public interface IUserService {
    List<UserRequestedForDeletionResponse> getAllUserWithDeletionRequested();
    void requestDeletion(StringRequest request);
    void approveDeletionRequest(StringRequest request);
    void denyDeletionRequest(StringRequest request);

}
