package org.skypro.skyshop.model.search;

import org.skypro.skyshop.exceptions.NoSuchProductException;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    private final String contentType ;

    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }
    public SearchResult fromSearchable(Searchable searchable) {
        SearchResult result = new SearchResult(searchable.getId(),searchable.getSearchableName(), searchable.type());
        if(result.getId() == null) {
            throw new NoSuchProductException("Нету такого продукта!!!");
        }
        return result;
    }
}
