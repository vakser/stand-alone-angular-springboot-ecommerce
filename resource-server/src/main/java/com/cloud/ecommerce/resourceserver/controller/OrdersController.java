package com.cloud.ecommerce.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class OrdersController {

    @GetMapping("/api/orders")
    public List<String> getOrders() {
        String[] orders = {"Order 1", "Order 2", "Order 3"};
        return Arrays.asList(orders);
    }
}
