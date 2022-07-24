package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.BoatRequest;
import net.javaguides.isa.dto.response.BoatResponse;

import java.util.List;

public interface IBoatService {
    BoatResponse getBoat(Long id);
    List<BoatResponse> getOwnersBoats(Long id);
    BoatResponse createBoat(BoatRequest request) throws Exception;
    BoatResponse updateBoat(BoatRequest request, Long id) throws Exception;
    void deleteBoat(Long id) throws Exception;
}
