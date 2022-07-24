package net.javaguides.isa.controller;

import net.javaguides.isa.service.ISearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final ISearchService iSearchService;

    public SearchController(ISearchService iSearchService) {
        this.iSearchService = iSearchService;
    }

    @GetMapping("/owners-cottages")
    public ResponseEntity<?> searchOwnersCottages(@RequestParam("ownerId") String ownerId, @RequestParam("name") String name){
        return new ResponseEntity<>(iSearchService.searchOwnersCottages(ownerId, name), HttpStatus.OK);
    }

}
