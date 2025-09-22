package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService() throws BestResultNotFound {
        this.storageService =new StorageService() ;
    }
    public SearchResult search(String query) {
        return new SearchResult(UUID.randomUUID(),query,"Search result");
    }
}
