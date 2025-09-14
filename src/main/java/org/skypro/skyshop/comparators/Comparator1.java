package org.skypro.skyshop.comparators;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Comparator;

public class Comparator1 implements Comparator<Searchable> {

    @Override
    public int compare(Searchable o1, Searchable o2) {
        int result=Integer.compare(o2.getSearchableName().length(), o1.getSearchableName().length());
        if(result==0) {
            return o1.getSearchableName().compareTo(o2.getSearchableName());
        }
        return result;
    }
}
