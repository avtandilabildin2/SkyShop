package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product
{
    public DiscountedProduct(String title, UUID id, int price, int discountedPrice) {
        super(title);
        this.id = id;
        this.price = price;
        this.discountedPrice = discountedPrice;
        if (price<1||discountedPrice>100||discountedPrice<0){
            throw new IllegalArgumentException("Неправильное число цены или процента!!!");
        }
    }
    private final UUID id;
    private int price;
    private int discountedPrice;
    @Override
    public int getPrice() {
        int newPrice=price-price*discountedPrice/100;
        return newPrice;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    @Override
    public String toString(){
        return getTitle()+": "+getPrice()+"("+getDiscountedPrice()+"%)";
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
        return getTitle()+": "+getPrice()+"("+getDiscountedPrice()+")"+type();
    }


}
