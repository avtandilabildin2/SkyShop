package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public CartService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Product product = storageService.getProductById(id); // выбросит NoSuchProductException
        productBasket.addProduct(product.getId());
    }

    public List<Product> getUserBasket() {
        List<Product> result = new ArrayList<>();
        for (UUID id : productBasket.getProducts().keySet()) {
            Product product = storageService.getProductById(id);
            result.add(product);
        }
        return result;
    }
}

