package org.skypro.skyshop.model.basket;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@Component
@SessionScope
public class ProductBasket {

    private Map<UUID, Integer> products;
    public ProductBasket() {
        products = new HashMap<>();
    }
    public Map<UUID, Integer> getProducts() {
        return products;
    }
    public void addProduct(UUID id){
        products.put(id, products.getOrDefault(id, 0) + 1);

    }





    public void printBasket() {

        products.values()
                .stream()
                .forEach(i-> System.out.println(i));

    }




    public void clearProductBasket() {
        products.clear();
    }


}
