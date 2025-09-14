package org.skypro.skyshop.service;

import org.skypro.skyshop.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchEngine;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Article> articles;
    private final Map<UUID, Product> products;

    public StorageService() throws BestResultNotFound {
        this.articles = new HashMap<>();
        this.products = new HashMap<>();
        init();
    }
    private void init() throws BestResultNotFound {
        SimpleProduct simpleProduct1=new SimpleProduct("title1",UUID.randomUUID(),12);
        SimpleProduct simpleProduct2=new SimpleProduct("title2",UUID.randomUUID(),12);
        SimpleProduct simpleProduct3=new SimpleProduct("title2",UUID.randomUUID(),12);
        SimpleProduct simpleProduct4=new SimpleProduct("title4",UUID.randomUUID(),12);
        SimpleProduct simpleProduct5=new SimpleProduct("title4",UUID.randomUUID(),12);
        ProductBasket basket=new ProductBasket();
        basket.addToProductBasket(simpleProduct1);
        basket.addToProductBasket(simpleProduct2);
        basket.addToProductBasket(simpleProduct3);
        basket.addToProductBasket(simpleProduct4);
        basket.addToProductBasket(simpleProduct5);
        basket.deleteByName("title1");
        basket.deleteByName("title2");
        basket.deleteByName("title3");
        products.put(simpleProduct1.getId(), simpleProduct1);
        products.put(simpleProduct2.getId(), simpleProduct2);
        products.put(simpleProduct3.getId(), simpleProduct3);
        products.put(simpleProduct4.getId(), simpleProduct4);
        products.put(simpleProduct5.getId(), simpleProduct5);
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
        basket.deleteByName(null);
        ProductBasket basket2=new ProductBasket();

        SearchEngine searchEngine=new SearchEngine();
        searchEngine.addSearchable(new SimpleProduct("title1",UUID.randomUUID(),12));
        searchEngine.addSearchable(new SimpleProduct("title2",UUID.randomUUID(),12));
        searchEngine.addSearchable(new SimpleProduct("title3",UUID.randomUUID(),12));
        System.out.println(searchEngine.searchable("title2"));
        SimpleProduct simpleProduct=new SimpleProduct("title4",UUID.randomUUID(),12);
        System.out.println(simpleProduct.searchTerm());
        Set<Product> deletedProducts=basket.deleteByName("title1");
        if(deletedProducts.isEmpty()){
            System.out.println("Корзина пуста!!!");
        }
        else{
            for (Product product : deletedProducts) {
                System.out.println(product);
            }
        }
    }

    public void addToProductBasket(Product product) {
        products.put(product.getId(), product);

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


    public Map<UUID, Article> getArticles() {
        return articles;
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }
    public List<Searchable> getSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }
}
