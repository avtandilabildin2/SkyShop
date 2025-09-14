package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private final UUID id;
    @Override
    public boolean isSpecial() {
        return true;
    }

    private static final int FIX_PRICE_PROUCT=100;

    public int getPrice() {
        return FIX_PRICE_PROUCT;
    }

    public FixPriceProduct(String productName, UUID id) {
        super(productName);

        this.id = id;
    }

    @Override
    public String toString() {
        return getTitle()+": "+getPrice();
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
