package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private String title;
    private String text;
    private final UUID id;

    public Article(String title, String text, UUID id) {
        this.title = title;
        this.text = text;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article article)) return false;

        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String searchTerm() {
        return toString();
    }

    @Override
    public String type() {
        return "ARTICLE";
    }

    @Override
    public String getSearchableName() {
        return getTitle();
    }

    @Override
    public String getStringRepresentation() {
        return title+" - "+type();
    }

    @Override
    public String toString() {
        return title+" - "+text+" - "+type();
    }



}
