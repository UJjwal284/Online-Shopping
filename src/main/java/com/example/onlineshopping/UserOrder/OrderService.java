package com.example.onlineshopping.UserOrder;

import com.example.onlineshopping.Cart.Cart;
import com.example.onlineshopping.Cart.CartProduct;
import com.example.onlineshopping.Cart.CartRepository;
import com.example.onlineshopping.Product.Product;
import com.example.onlineshopping.Product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    final ProductRepository productRepository;


    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<UserOrder> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public UserOrder placeOrder(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null || cart.getProducts().isEmpty()) {
            return new UserOrder();
        }

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (CartProduct cartProduct : cart.getProducts()) {
            Product product = productRepository.findById(cartProduct.getProduct().getId()).orElse(null);
            if (product != null) {
                if (product.getInStock() < cartProduct.getQuantity()) {
                    continue;
                }
                product.setInStock(product.getInStock() - cartProduct.getQuantity());
                productRepository.save(product);
                orderProducts.add(new OrderProduct(cartProduct.getProduct().getId(), cartProduct.getProduct().getName(), cartProduct.getQuantity(), cartProduct.getProduct().getPrice()));
            }
        }
        UserOrder userOrder = new UserOrder();
        if (!orderProducts.isEmpty()) {
            userOrder.setUserId(userId);
            userOrder.setStatus("Placed");
            userOrder.setProducts(orderProducts);
            orderRepository.save(userOrder);
            cart.getProducts().clear();
            cartRepository.save(cart);
        }
        return userOrder;
    }
}