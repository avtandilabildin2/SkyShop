package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BasketService {
    private final StorageService storageService;
    private final ProductBasket productBasket;

    public BasketService(StorageService storageService, ProductBasket productBasket) {
        this.storageService = storageService;
        this.productBasket = productBasket;

    }
    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketItemsMap = productBasket.getProducts();

        List<BasketItem> basketItems = basketItemsMap.entrySet().stream()
                .map(entry -> {
                    Product product = storageService.getProductById(entry.getKey())
                            .orElseThrow(() -> new IllegalStateException("Продукт не найден!!!!"));
                    return new BasketItem(product, entry.getValue());
                })
                .collect(Collectors.toList());

        return new UserBasket(basketItems);

    }
    public void addProduct(UUID id){
        Optional<Product> product=storageService.getProductById(id);
        if(product.isPresent()){
            productBasket.addProduct(product.get().getId());
        }
        else{
            System.out.println("Пользователь с таким айди не найден!!!");
        }
    }
}
