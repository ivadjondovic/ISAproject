package net.javaguides.isa.service;

import net.javaguides.isa.dto.response.SearchOwnersBoatsResponse;
import net.javaguides.isa.dto.response.SearchOwnersCottagesResponse;

public interface ISearchService {
    SearchOwnersCottagesResponse searchOwnersCottages(String ownerId, String name);
    SearchOwnersBoatsResponse searchOwnersBoats(String ownerId, String name);
}
