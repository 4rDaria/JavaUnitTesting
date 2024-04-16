package com.epam.tamentoring.bo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.tamentoring.exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {

    private ShoppingCart cart;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    public void setUp() {
        product1 = new Product(1, "Product 1", 10.0, 11);
        product2 = new Product(2, "Product 2", 20.0, 12);
        product3 = new Product(3, "Product 3", 30.0, 13);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        cart = new ShoppingCart(productList);
    }

    @Test
    public void addProductToCart() {
        int initialSize = cart.getProducts().size();
        cart.addProductToCart(product3);
        assertEquals(initialSize + 1, cart.getProducts().size());
    }

    @Test
    public void removeProductFromCart() {
        int initialSize = cart.getProducts().size();
        cart.removeProductFromCart(product1);
        assertEquals(initialSize - 1, cart.getProducts().size());
    }

    @Test
    public void cartTotalPrice() {
        List<Product> products = cart.getProducts();
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getQuantity() * product.getPrice();
        }

        assertEquals(totalPrice, cart.getCartTotalPrice());
    }

    @Test
    public void addExistingProductToCart() {
        cart.addProductToCart(new Product(1, "Product 1", 10.0, 11));
        assertEquals(22, cart.getProductById(1).getQuantity());
    }

    @Test
    public void testRemoveNonExistingProductFromCart() {
        assertThrows(
            ProductNotFoundException.class, () -> cart.removeProductFromCart(new Product(4, "Product 4", 40.0, 14)));
    }
}
