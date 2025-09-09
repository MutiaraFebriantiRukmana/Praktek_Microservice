package com.tiara.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiara.order_service.model.Order;
import com.tiara.order_service.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllorders() {
        return orderService.getAllorders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getorderById(@PathVariable Long id) {
        Order order = orderService.getorderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Order createorder(@RequestBody Order order) {
        return orderService.createorder(order);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteorder(@PathVariable Long id) {
        orderService.deleteorder(id);
        return ResponseEntity.ok().build();
    }
}
