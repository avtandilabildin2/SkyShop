package org.skypro.skyshop;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.mockito.Mockito;
import org.skypro.skyshop.service.CartService;
import org.skypro.skyshop.service.StorageService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Test
    void addNonExistingProduct_ThrowsException() {

        ProductBasket basket = mock(ProductBasket.class);
        StorageService storage = mock(StorageService.class);

        UUID id = UUID.randomUUID();

        when(storage.getProductById(id))
                .thenThrow(new NoSuchProductException("not found"));

        CartService cartService = new CartService(basket, storage);

        assertThrows(NoSuchProductException.class,
                () -> cartService.addProductToBasket(id));
    }

    @Test
    void addExistingProduct_CallsBasketAddProduct() {

        ProductBasket basket = mock(ProductBasket.class);
        StorageService storage = mock(StorageService.class);

        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);

        when(storage.getProductById(id)).thenReturn(product);
        when(product.getId()).thenReturn(id);

        CartService cartService = new CartService(basket, storage);

        cartService.addProductToBasket(id);

        verify(basket, times(1)).addProduct(id);
    }

    @Test
    void getUserBasket_ReturnsEmptyList_WhenBasketEmpty() {

        ProductBasket basket = mock(ProductBasket.class);
        StorageService storage = mock(StorageService.class);

        when(basket.getProducts()).thenReturn(Map.of());

        CartService cartService = new CartService(basket, storage);

        List<Product> result = cartService.getUserBasket();

        assertTrue(result.isEmpty());
        verify(storage, never()).getProductById(any());
    }

    @Test
    void getUserBasket_ReturnsProducts_WhenBasketHasItems() {

        ProductBasket basket = mock(ProductBasket.class);
        StorageService storage = mock(StorageService.class);

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);

        when(basket.getProducts()).thenReturn(Map.of(id1, 1, id2, 2));

        when(storage.getProductById(id1)).thenReturn(p1);
        when(storage.getProductById(id2)).thenReturn(p2);

        CartService cartService = new CartService(basket, storage);

        List<Product> result = cartService.getUserBasket();

        assertEquals(2, result.size());
        assertTrue(result.contains(p1));
        assertTrue(result.contains(p2));
    }
}
