package com.ecommerce.auth.controller;

import com.ecommerce.auth.modelos.LoginData;
import com.ecommerce.auth.modelos.RegisterData;
import com.ecommerce.auth.modelos.UserModel;
import com.ecommerce.auth.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserRepo uRepo;

    @GetMapping("/health")
    public ResponseEntity<String> home() {
        return ResponseEntity.status(HttpStatus.OK).body("Operational");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginData data){
        UserModel user = uRepo.findByEmail(data.email);
        if(data.email == null || data.password == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No email and no password provided in the request");
        if(data.email.isEmpty() || data.password.isEmpty()) return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Fill the blanks before send");
        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user was not found. Login or create another account");
        if(!Objects.equals(user.getPassword(), data.password))  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The password you've typed is incorrect");
        else return ResponseEntity.status(HttpStatus.OK).body(user.get_id());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterData data) {
        UserModel user = uRepo.findByEmail(data.email);
        if(data.email == null || data.password == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No email and no password provided in the request");
        if(user != null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This user already exist. Login or create another account");
        UserModel newUser = new UserModel();
        newUser.setName(data.name);
        newUser.setEmail(data.email);
        newUser.setPassword(data.password);
        newUser.setNotifications(new ArrayList<>());
        newUser.setCart(new ArrayList<>());
        uRepo.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser.get_id());
    }
}
