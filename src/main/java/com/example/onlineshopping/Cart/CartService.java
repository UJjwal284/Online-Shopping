package com.example.onlineshopping.Cart;

import com.example.onlineshopping.Product.Product;
import com.example.onlineshopping.Product.ProductService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartService {
    final ProductService productService;
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public Cart getUserCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public void addToCart(String userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
        }
        Product product = productService.getProductById(productId);
        if (product != null && product.getInStock() > 0) {
            List<CartProduct> cartProducts = cart.getProducts();
            if (cartProducts == null) {
                cartProducts = Collections.singletonList(createCartProduct(product));
            } else {
                boolean productAlreadyInCart = false;
                for (CartProduct cartProduct : cartProducts) {
                    if (cartProduct.getProduct().getId().equals(productId)) {
                        if (cartProduct.getQuantity() < product.getInStock()) {
                            cartProduct.setQuantity(cartProduct.getQuantity() + 1);
                            productAlreadyInCart = true;
                            break;
                        }
                    }
                }
                if (!productAlreadyInCart) {
                    cartProducts.add(createCartProduct(product));
                }
            }
            cart.setProducts(cartProducts);
            cartRepository.save(cart);
        }
    }

    private CartProduct createCartProduct(Product product) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setQuantity(1);
        return cartProduct;
    }

    public void removeFromCart(String userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            return;
        }
        Product product = productService.getProductById(productId);
        if (product != null) {
            boolean productAlreadyInCart = false;
            if (cart.getProducts() != null) {
                for (CartProduct cartProduct : cart.getProducts()) {
                    if (cartProduct.getProduct().getId().equals(productId)) {
                        if (cartProduct.getQuantity() > 0) {
                            cartProduct.setQuantity(cartProduct.getQuantity() - 1);
                        }
                        productAlreadyInCart = true;
                        break;
                    }
                }
            }
            if (!productAlreadyInCart) {
                return;
            }
            cartRepository.save(cart);
        }
    }

}
