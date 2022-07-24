package net.javaguides.isa.service;

import net.javaguides.isa.dto.request.CottageRequest;
import net.javaguides.isa.dto.response.CottageResponse;
import java.util.List;

public interface ICottageService {

    CottageResponse getCottage(Long id);
    List<CottageResponse> getOwnersCottages(Long id);
    CottageResponse createCottage(CottageRequest request) throws Exception;
    CottageResponse updateCottage(CottageRequest request, Long id) throws Exception;
    void deleteCottage(Long id) throws Exception;
}
