package com.example.demo;

import com.example.demo.testLineManWongnai.Cart;
import com.example.demo.testLineManWongnai.CartService;
import com.example.demo.testLineManWongnai.Promotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CartTest {
    private CartService cart;

    @BeforeEach
    public void setUp() {
        cart = new CartService();
    }

    @Test
    void testEmptyCart() {
        Assertions.assertEquals(0d, cart.totalPrice());
    }

    @Test
    void tetChocolateAndPocky() {
        List<Cart> cartList = List.of(new Cart("Chocolate", 10d, null)
                , new Cart("Pocky", 20d, null));
        cart.setLineItemCart(cartList);
        Assertions.assertEquals(30d, cart.totalPrice());
    }

    @Test
    void tetChocolateAndPockyWithQuantity() {
        List<Cart> cartList = List.of(new Cart("Chocolate", 16d, null, 2), new Cart("Pocky", 20d, null, 2));
        cart.setLineItemCart(cartList);
        Assertions.assertEquals(72d, cart.totalPrice());
    }


    @Test
    void testPercentagePromotion() {
        List<Cart> cartList = List.of(new Cart("Chocolate", 10d, new Promotion(0.2d, 0, false, true), 2)
                , new Cart("Pocky", 20d, null, 2));

        cart.setLineItemCart(cartList);
        Assertions.assertEquals(56d, cart.totalPrice());
    }

    @Test
    void testCashPromotion() {
        List<Cart> cartList = List.of(new Cart("Chocolate", 10d, new Promotion(0.2d, 0, false, true), 2)
                , new Cart("Pocky", 20d, new Promotion(0, 3d, true, false), 2));

        cart.setLineItemCart(cartList);
        Assertions.assertEquals(50d, cart.totalPrice());

    }

}