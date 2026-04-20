package com.bookstore.cart.service;

import com.bookstore.cart.entity.Cart;
import com.bookstore.cart.entity.CartItem;
import com.bookstore.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart currentCart() {
        Cart cart = cartRepository.findById("1").orElseGet(Cart::new);
        if (cart.getUserId() == null) {
            cart.setUserId("1");
            CartItem item = new CartItem();
            item.setProductId(1L);
            item.setProductTitle("Clean Architecture");
            item.setQuantity(1);
            item.setUnitPrice(BigDecimal.valueOf(599));
            cart.getItems().add(item);
            cart.setTotalAmount(BigDecimal.valueOf(599));
        }
        return cart;
    }
}

