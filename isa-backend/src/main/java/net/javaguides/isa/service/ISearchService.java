package net.javaguides.isa.service;


import net.javaguides.isa.dto.response.SearchOwnersCottagesResponse;

public interface ISearchService {
    SearchOwnersCottagesResponse searchOwnersCottages(String ownerId, String name);
}
