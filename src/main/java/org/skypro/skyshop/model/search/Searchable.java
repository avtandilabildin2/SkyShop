package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    UUID getId();
    String searchTerm();
    String type();
    String getSearchableName();
    default String getStringRepresentation(){
        return getSearchableName()+" - "+type();
    }


}
