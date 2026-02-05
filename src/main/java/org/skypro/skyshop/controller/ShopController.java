package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }
    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id")UUID id){
        return "Продукт успешно добавлен.";
    }
    @GetMapping("/basket")
    public UserBasket getUserBasket(){
        return basketService.getUserBasket();
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
       return storageService.getAvailableProducts().values();
    }
    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return  storageService.getArticles().values();
    }
    @GetMapping("/search")
    public SearchResult search(String pattern) {
       return searchService.search(pattern);
    }
}
