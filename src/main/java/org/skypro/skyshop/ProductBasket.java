package org.skypro.skyshop;


import org.skypro.skyshop.model.product.Product;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProductBasket {

    private Map<String, Product> products;
    public ProductBasket() {
        products = new HashMap<>();

    }
    public void addToProductBasket(Product product) {
        products.put(product.getTitle(), product);

    }

    public Set<Product> deleteByName(String name) {

        Set<Product> result = new HashSet<>();
        if (products.containsKey(name)) {
            result.add(products.remove(name));

        } else{
            System.out.println("Продукт с таким именем не существует!!!");
        }

        return result;

    }

    public void printBasket() {

        products.values()
                .stream()
                .forEach(i-> System.out.println(i.toString()));

    }
    public int sumOfProductBasket() {

        int sum = products.values()
                .stream()
                .mapToInt(Product::getPrice)
                .sum();
        return sum;
    }
    public void allThing() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }
        System.out.println("Содержимое корзины!");
        products.values().stream()
                .forEach(i-> System.out.println(i));

        int sum = sumOfProductBasket();
        long count= products.values()
                .stream().filter(Product::isSpecial)
                .count();
        System.out.println("Итого: "+sum);
        System.out.println("Специальных товаров: "+count);
    }

    public boolean esExist(String productName) {
        return products.containsKey(productName);
    }
    public void clearProductBasket() {
        products.clear();
    }


}
