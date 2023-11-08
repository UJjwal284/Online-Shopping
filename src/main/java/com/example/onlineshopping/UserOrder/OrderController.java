package com.example.onlineshopping.UserOrder;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    public UserOrder placeOrder(@PathVariable String userId) {
        return orderService.placeOrder(userId);
    }

    @GetMapping("/{userId}")
    List<UserOrder> getUserOrders(@PathVariable String userId) {
        return orderService.getUserOrders(userId);
    }
}