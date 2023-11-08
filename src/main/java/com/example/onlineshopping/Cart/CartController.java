package com.example.onlineshopping.Cart;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/{userId}")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public Cart getUserCart(@PathVariable String userId) {
        return cartService.getUserCart(userId);
    }

    @PostMapping("/{productId}")
    public Cart addProductToCart(@PathVariable String userId, @PathVariable Long productId) {
        cartService.addToCart(userId, productId);
        return cartService.getUserCart(userId);
    }

    @DeleteMapping("/{productId}")
    public Cart removeProductFromCart(@PathVariable String userId, @PathVariable Long productId) {
        cartService.removeFromCart(userId, productId);
        return cartService.getUserCart(userId);
    }
}