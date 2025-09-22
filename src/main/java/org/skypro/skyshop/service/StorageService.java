package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchEngine;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Article> articles;
    private final Map<UUID, Product> availableProducts;

    public StorageService() throws BestResultNotFound {
        this.articles = new HashMap<>();
        this.availableProducts = new HashMap<>();
        init();
    }
    private void init() throws BestResultNotFound {
        SimpleProduct simpleProduct1=new SimpleProduct("title1",UUID.randomUUID(),12);
        SimpleProduct simpleProduct2=new SimpleProduct("title2",UUID.randomUUID(),12);
        SimpleProduct simpleProduct3=new SimpleProduct("title2",UUID.randomUUID(),12);
        SimpleProduct simpleProduct4=new SimpleProduct("title4",UUID.randomUUID(),12);
        SimpleProduct simpleProduct5=new SimpleProduct("title4",UUID.randomUUID(),12);
        ProductBasket basket=new ProductBasket();

        availableProducts.put(simpleProduct1.getId(), simpleProduct1);
        availableProducts.put(simpleProduct2.getId(), simpleProduct2);
        availableProducts.put(simpleProduct3.getId(), simpleProduct3);
        availableProducts.put(simpleProduct4.getId(), simpleProduct4);
        availableProducts.put(simpleProduct5.getId(), simpleProduct5);
        Article article1=new Article("title1","text1",UUID.randomUUID());
        Article article2=new Article("title2","text2",UUID.randomUUID());
        Article article3=new Article("title3","text3",UUID.randomUUID());
        Article article4=new Article("title4","text4",UUID.randomUUID());
        Article article5=new Article("title5","text5",UUID.randomUUID());
        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
        articles.put(article3.getId(), article3);
        articles.put(article4.getId(), article4);
        articles.put(article5.getId(), article5);


        basket.printBasket();
        ProductBasket basket2=new ProductBasket();

        SearchEngine searchEngine=new SearchEngine();
        searchEngine.addSearchable(new SimpleProduct("title1",UUID.randomUUID(),12));
        searchEngine.addSearchable(new SimpleProduct("title2",UUID.randomUUID(),12));
        searchEngine.addSearchable(new SimpleProduct("title3",UUID.randomUUID(),12));
        System.out.println(searchEngine.searchable("title2"));
        SimpleProduct simpleProduct=new SimpleProduct("title4",UUID.randomUUID(),12);
        System.out.println(simpleProduct.searchTerm());

    }

    public void addToProductBasket(Product product) {
        availableProducts.put(product.getId(), product);

    }
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }
    public Set<Product> deleteByName(String name) {

        Set<Product> result = new HashSet<>();
        if (availableProducts.containsKey(name)) {
            result.add(availableProducts.remove(name));

        } else{
            System.out.println("Продукт с таким именем не существует!!!");
        }

        return result;

    }
    public void printBasket() {

        availableProducts.values()
                .stream()
                .forEach(i-> System.out.println(i.toString()));

    }
    public int sumOfProductBasket() {

        int sum = availableProducts.values()
                .stream()
                .mapToInt(Product::getPrice)
                .sum();
        return sum;
    }
    public void allThing() {
        if (availableProducts.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }
        System.out.println("Содержимое корзины!");
        availableProducts.values().stream()
                .forEach(i-> System.out.println(i));

        int sum = sumOfProductBasket();
        long count= availableProducts.values()
                .stream().filter(Product::isSpecial)
                .count();
        System.out.println("Итого: "+sum);
        System.out.println("Специальных товаров: "+count);
    }

    public boolean esExist(String productName) {
        return availableProducts.containsKey(productName);
    }
    public void clearProductBasket() {
        availableProducts.clear();
    }


    public Map<UUID, Article> getArticles() {
        return articles;
    }

    public Map<UUID, Product> getAvailableProducts() {
        return availableProducts;
    }
    public List<Searchable> getSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(availableProducts.values());
        result.addAll(articles.values());
        return result;
    }
}
