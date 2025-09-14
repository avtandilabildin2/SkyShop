package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    public SimpleProduct(String title, UUID id, int price) {
        super(title);
        this.id = id;
        this.price = price;
        if(price<1){
            throw new IllegalArgumentException("Недостаточно денег!!!");
        }
    }
    private final UUID id;
    private int price;
    @Override
    public int getPrice() {
        return this.price;
    }
    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getTitle() + ": " + getPrice();
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
        return "PRODUCT";
    }

    @Override
    public String getSearchableName() {
        return getTitle();
    }

    @Override
    public String getStringRepresentation() {
        return getTitle()+" - "+getPrice()+" - "+type();
    }


}
