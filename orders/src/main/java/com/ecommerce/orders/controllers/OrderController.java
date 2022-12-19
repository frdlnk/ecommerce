package com.ecommerce.orders.controllers;

import com.ecommerce.orders.models.OrderModel;
import com.ecommerce.orders.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderRepo oRepo;
    @GetMapping("/health")
    public ResponseEntity<String> home(){
        return ResponseEntity.status(HttpStatus.OK).body("Operational");
    }

    @PostMapping("/new")
    public ResponseEntity CreateOrder(@RequestBody OrderModel order) {
        oRepo.save(order);
        return ResponseEntity.status(HttpStatus.OK).body("Order successfully created");
    }
}
