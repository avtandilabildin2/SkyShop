package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService searchService) throws BestResultNotFound {
        this.storageService =searchService ;
    }
    public SearchResult search(String query) throws BestResultNotFound {
        return storageService.getSearchables().stream()
                .filter(s -> s.getSearchableName().contains(query))
                .findFirst()
                .map(SearchResult::fromSearchable)
                .orElseThrow(() -> new BestResultNotFound("Nothing found"));
    }
}
