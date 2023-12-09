package com.example.onlineshopping;

import com.example.onlineshopping.Cart.CartService;
import com.example.onlineshopping.Product.Product;
import com.example.onlineshopping.Product.ProductService;
import com.example.onlineshopping.UserOrder.OrderService;
import com.example.onlineshopping.UserOrder.UserOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UIController {

    final ProductService productService;
    final CartService cartService;

    final OrderService orderService;

    public UIController(ProductService productService, CartService cartService, OrderService orderService) {
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> product = productService.getAllProducts();
        model.addAttribute("products", product);
        return "home";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        return "cart";
    }

    @GetMapping("/account")
    public String account(Model model) {
        List<UserOrder> userOrders = orderService.getUserOrders(String.valueOf(5));
        model.addAttribute("userOrders", userOrders);
        return "account";
    }
}
