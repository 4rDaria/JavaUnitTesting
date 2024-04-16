package com.epam.tamentoring.bo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {

    @Mock
    private DiscountUtility discountUtility;

    @InjectMocks
    private OrderService orderService;

    private ShoppingCart cart;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cart = shoppingCartSetUp();
    }

    @Test
    public void testGetOrderPriceWithDiscount() {
        UserAccount user = new UserAccount();
        user.setName("John");
        user.setSurname("Smith");
        user.setDateOfBirth("1990/10/10");
        user.setShoppingCart(cart);

        when(discountUtility.calculateDiscount(user)).thenReturn(3.0);

        double actualDiscount = cart.getCartTotalPrice() - orderService.getOrderPrice(user);

        assertEquals(3.0, actualDiscount);

        verify(discountUtility, times(1)).calculateDiscount(user);
        verifyNoMoreInteractions(discountUtility);
    }

    private ShoppingCart shoppingCartSetUp() {
        Product product1 = new Product(1, "Product 1", 1.0, 10);
        Product product2 = new Product(2, "Product 2", 2.0, 10);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        return new ShoppingCart(productList);
    }

}
