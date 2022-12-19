package com.ecommerce.search.controller;

import com.ecommerce.search.models.ProductModel;
import com.ecommerce.search.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    ProductRepository pRepo;

    @GetMapping("/health")
    public ResponseEntity<String> home(){
        return ResponseEntity.status(HttpStatus.OK).body("Operational");
    }

    @GetMapping("/")
    public ResponseEntity getSearchedProducts(@RequestParam String title){
        List<ProductModel> productsFinded = pRepo.search(title);
        if(productsFinded.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing found to your search");
        return ResponseEntity.status(HttpStatus.OK).body(productsFinded);
    }
}
