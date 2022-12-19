package com.ecommerce.cart.controllers;

import com.ecommerce.cart.models.ProductModel;
import com.ecommerce.cart.models.UserModel;
import com.ecommerce.cart.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    UserRepository uRepo;

    @GetMapping("/health")
    public ResponseEntity<String> home(){
        return ResponseEntity.status(HttpStatus.OK).body("Operational");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> UpdateCart(@PathVariable String id, @RequestBody ProductModel product){
        if(id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No ID provided in the request, Check again the request");
        Optional<UserModel> user = uRepo.findById(id);
        UserModel userFound = user.get();
        List cart = userFound.getCart();
        cart.add(product);
        userFound.setCart(cart);
        uRepo.save(userFound);
        return ResponseEntity.status(HttpStatus.OK).body("Cart updated");
    }

    @PutMapping("/clean/{id}")
    public ResponseEntity<String> cleanCart(@PathVariable String id) {
        if(id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No ID provided in the request, Check again the request");
        Optional<UserModel> user = uRepo.findById(id);
        UserModel userFound = user.get();
        List cart = new ArrayList();
        userFound.setCart(cart);
        uRepo.save(userFound);
        return ResponseEntity.status(HttpStatus.OK).body("Cart cleaned");
    }
}
