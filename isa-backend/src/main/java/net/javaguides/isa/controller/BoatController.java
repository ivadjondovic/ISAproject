package net.javaguides.isa.controller;

import net.javaguides.isa.dto.request.BoatRequest;
import net.javaguides.isa.dto.response.BoatResponse;
import net.javaguides.isa.service.IBoatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boats")
public class BoatController {
    private final IBoatService _boatService;

    public BoatController(IBoatService boatService) {
        _boatService = boatService;
    }

    @GetMapping("/{id}")
    public BoatResponse getBoat(@PathVariable Long id) {
        return _boatService.getBoat(id);
    }

    @GetMapping("/{id}/boat-owner")
    public ResponseEntity<?> getOwnersBoats(@PathVariable("id") Long id){
        List<BoatResponse> boats = _boatService.getOwnersBoats(id);
        if(boats != null) {
            return new ResponseEntity<>(boats, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("oats doesn't exists.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public BoatResponse createBoat(@RequestBody BoatRequest request) throws Exception {
        return _boatService.createBoat(request);
    }

    @PutMapping("/{id}/update")
    public BoatResponse updateBoat(@RequestBody BoatRequest request, @PathVariable Long id) throws Exception {
        return _boatService.updateBoat(request, id);
    }


    @DeleteMapping("/{id}")
    public void deleteBoat(@PathVariable Long id) throws Exception {
        _boatService.deleteBoat(id);
    }
}
