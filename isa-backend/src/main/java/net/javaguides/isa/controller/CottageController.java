package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.CottageRequest;
import net.javaguides.isa.dto.response.CottageResponse;
import net.javaguides.isa.service.ICottageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cottages")
public class CottageController {

    private final ICottageService _cottageService;

    public CottageController(ICottageService cottageService) {
        _cottageService = cottageService;
    }

    @GetMapping("/{id}")
    public CottageResponse getCottage(@PathVariable Long id) {
        return _cottageService.getCottage(id);
    }

    @GetMapping("/{id}/cottage-owner")
    public ResponseEntity<?> getOwnersCottages(@PathVariable("id") Long id){
        List<CottageResponse> cottages = _cottageService.getOwnersCottages(id);
        if(cottages != null) {
            return new ResponseEntity<>(cottages, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Cottages doesn't exists.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public CottageResponse createCottage(@RequestBody CottageRequest request) throws Exception {
        return _cottageService.createCottage(request);
    }

    @PutMapping("/{id}/update")
    public CottageResponse updateCottage(@RequestBody CottageRequest request, @PathVariable Long id) throws Exception {
        return _cottageService.updateCottage(request, id);
    }


    @DeleteMapping("/{id}")
    public void deleteCottage(@PathVariable Long id) throws Exception {
        _cottageService.deleteCottage(id);
    }

}
