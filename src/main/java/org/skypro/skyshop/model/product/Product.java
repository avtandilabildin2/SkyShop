package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {
    private String title;


    public Product(String title) {
        this.title = title;
        if(title == null ||title.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым!!!");
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;

        return Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract int getPrice();
    public abstract boolean isSpecial();

}
