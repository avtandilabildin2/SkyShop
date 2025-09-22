package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private List<BasketItem> items;
    private double total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        if (items == null) {
            this.items = List.of();
        } else {
            this.items = List.copyOf(items);
        }

        this.total = this.items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
